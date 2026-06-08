package cryogenesis.world.blocks.power;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.core.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.input.*;
import mindustry.logic.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.power.*;
import mindustry.world.meta.*;

import java.util.*;

import static mindustry.Vars.*;

public class Wire extends PowerBlock implements Autotiler{
	public TextureRegion[] regions;
	public TextureRegion anchorRegion;

	public Block bridgeReplacement;

    public WireBuild nextWire, prevWire;
    
    public Color laserColor1 = Color.white;
    public Color laserColor2 = Pal.powerLight;

	public Wire(String name){
		super(name);
		conveyorPlacement = true;
        rotate = true;
        hasShadow = false;
        solid = false;
        destructible = true;
	}

    
	@Override
    public void setBars(){
        super.setBars();
        addBar("power", PowerNode.makePowerBalance());
        addBar("batteries", PowerNode.makeBatteryBalance());
    }
    

	public void load(){
		super.load();
		regions = new TextureRegion[5];
		for(int i = 0; i < 5; i++){
			regions[i] = Core.atlas.find(name + "-" + i);
		}
        anchorRegion = Core.atlas.find(name + "-anchor");
	}

	@Override
	public void init(){
		super.init();

		//if(bridgeReplacement == null || !(bridgeReplacement instanceof WireBridge)) bridgeReplacement = CryogenesisBlocks.wireBridge;
	}

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        int[] bits = getTiling(plan, list);

        if(bits == null) return;

        Draw.scl(bits[1], bits[2]);
        Draw.alpha(0.5f);
        Draw.color();
        Draw.rect(regions[bits[0]], plan.drawx(), plan.drawy(), plan.rotation * 90);
        Draw.scl();
    }


    //TODO: don't link to parallel wires, make sure this is functional as well as visual
    //TODO: Remove directionality entirely? or somehow prevent nubs 
	@Override
	public boolean blends(Tile tile, int rotation, int otherx, int othery, int otherrot, Block otherblock){
        return otherblock.hasPower
        && !(otherblock instanceof Wire && !(lookingAtEither(tile, rotation, otherx, othery, otherrot, otherblock)));
	}

    protected void setupColor(float satisfaction){
        Draw.color(Tmp.c1.set(laserColor1).lerp(laserColor2, (1f - satisfaction) * 0.86f + Mathf.absin(3f, 0.1f)).a(Renderer.laserOpacity));
    }

    //custom pseudoblending for anchors

    public boolean anchors(Tile tile, int rotation, int otherx, int othery, int otherrot, Block otherblock){
        return otherblock.hasPower
        && !(otherblock instanceof Wire);
    }

    public boolean anchors(Tile tile, int rotation, int direction){
        Building other = tile.nearbyBuild(Mathf.mod(rotation - direction, 4));
        return other != null && other.team == tile.team() && anchors(tile, rotation, other.tileX(), other.tileY(), other.rotation, other.block);
    }

    public boolean anchors(Tile tile, int rotation, BuildPlan[] directional, int direction, boolean checkWorld){
        int realDir = Mathf.mod(rotation - direction, 4);
        if(directional != null && directional[realDir] != null){
            BuildPlan req = directional[realDir];
            if(anchors(tile, rotation, req.x, req.y, req.rotation, req.block)){
                return true;
            }
        }
        return checkWorld && anchors(tile, rotation, direction);
    }

    public int buildAnchoring(Tile tile, int rotation, BuildPlan[] directional, boolean world){
        int anchorresult = 0;
        for(int i = 0; i < 4; i++){
            if(anchors(tile, rotation, directional, i, world)){
                anchorresult |= (1 << i);
            }
        }
        return anchorresult;
    }

	public class WireBuild extends Building{
		public int blendbits, xscl, yscl, anchoring;

        public Building[] links = new Building[4];
        public Tile[] dests = new Tile[4];
        public int lastChange = -2;

        
        @Override
        public BlockStatus status(){
            float balance = power.graph.getPowerBalance();
            if(balance > 0f) return BlockStatus.active;
            if(balance < 0f && power.graph.getLastPowerStored() > 0) return BlockStatus.noOutput;
            return BlockStatus.noInput;
        }
        

		@Override
		public void draw(){
            float rotation = rotdeg();
            int r = this.rotation;
            
            setupColor(power.graph.getSatisfaction());
            Draw.scl(xscl, yscl);
            Draw.z(Layer.blockUnder);

            drawAt(x, y, blendbits, rotation, SliceMode.none);

            Draw.color();
            
            //draw connection anchors to other blocks
            Draw.z(Layer.blockUnder);
            for(int i = 0; i < 4; i++){
                if((anchoring & (1 << i)) != 0){
                    int dir = r - i;
                    Draw.rect(anchorRegion, x, y, i == 0 ? r * 90f : dir * 90f);
                }
            }

            Draw.reset();
		}

		protected void drawAt(float x, float y, int bits, float rotation, SliceMode slice){
            Draw.rect(sliced(regions[bits], slice), x, y, rotation);
		}

        
        @Override
        public void pickedUp(){
            Arrays.fill(links, null);
            Arrays.fill(dests, null);
        }
        

		@Override
        public void onProximityUpdate(){
            super.onProximityUpdate();

            int[] bits = buildBlending(tile, rotation, null, true);
            blendbits = bits[0];
            xscl = bits[1];
            yscl = bits[2];
            anchoring = buildAnchoring(tile, rotation, null, true);
            updateDirections();
        }

        /*
        @Override
        public void updateTile(){
            //TODO this block technically does not need to update every frame, perhaps put it in a special list.
            if(lastChange != world.tileChanges){
                lastChange = world.tileChanges;
                updateDirections();
            }
        }
        */

        public void updateDirections(){
            for(int i = 0; i < 4; i ++){
                var prev = links[i];
                var dir = Geometry.d4[i];
                links[i] = null;
                dests[i] = null;
                int offset = size/2;

                //find first block with power in range
                var other = world.build(tile.x + (1 + offset) * dir.x, tile.y + (1 + offset) * dir.y);
                if(other != null) Log.info("tile.build got from @, tilex() returns @", tile.x + (1 + offset) * dir.x, other.tileX());
                //power nodes do NOT play nice with other links, do not touch them as that forcefully modifies their links
                if(other != null && other.block.hasPower && !(other.block instanceof Wire && !(lookingAtEither(tile, rotation, other.tileX(), other.tileY(), other.rotation, other.block))) && !(other.block instanceof PowerNode)){
                    links[i] = other;
                    dests[i] = world.tile(tile.x + 1 * dir.x, tile.y + 1 * dir.y);
                }

                var next = links[i];

                if(next != prev){
                    //unlinked, disconnect and reflow
                    if(prev != null && prev.isAdded()){
                        prev.power.links.removeValue(pos());
                        power.links.removeValue(prev.pos());

                        PowerGraph newgraph = new PowerGraph();
                        //reflow from this point, covering all tiles on this side
                        newgraph.reflow(this);

                        if(prev.power.graph != newgraph){
                            //reflow power for other end
                            PowerGraph og = new PowerGraph();
                            og.reflow(prev);
                        }
                    }

                    //linked to a new one, connect graphs
                    if(next != null){
                        power.links.addUnique(next.pos());
                        next.power.links.addUnique(pos());

                        power.graph.addGraph(next.power.graph);
                    }
                }
            }
        }
    }
}