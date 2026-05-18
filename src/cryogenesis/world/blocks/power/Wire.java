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

import static mindustry.Vars.*;

public class Wire extends PowerDistributor implements Autotiler{
	public TextureRegion[] topRegions;
	public TextureRegion bottomRegion;

	public Block bridgeReplacement;

	public Wire(String name){
		super(name);
		destructible = true;
		update = false;
		conveyorPlacement = true;
        rotate = true;
	}

	@Override
    public void setBars(){
        super.setBars();
        addBar("power", makePowerBalance());
        addBar("batteries", makeBatteryBalance());
    }

    public static Func<Building, Bar> makePowerBalance(){
        return entity -> new Bar(() ->
        Core.bundle.format("bar.powerbalance",
            ((entity.power.graph.getPowerBalance() >= 0 ? "+" : "") + UI.formatAmount((long)(entity.power.graph.getPowerBalance() * 60)))),
            () -> Pal.powerBar,
            () -> Mathf.clamp(entity.power.graph.getLastPowerProduced() / entity.power.graph.getLastPowerNeeded())
        );
    }

    public static Func<Building, Bar> makeBatteryBalance(){
        return entity -> new Bar(() ->
        Core.bundle.format("bar.powerstored",
            (UI.formatAmount((long)entity.power.graph.getLastPowerStored())), UI.formatAmount((long)entity.power.graph.getLastCapacity())),
            () -> Pal.powerBar,
            () -> Mathf.clamp(entity.power.graph.getLastPowerStored() / entity.power.graph.getLastCapacity())
        );
    }

	public void load(){
		super.load();
		topRegions = new TextureRegion[5];
		for(int i = 0; i < 5; i++){
			topRegions[i] = Core.atlas.find(name + "-top-" + i);
		}
		bottomRegion = Core.atlas.find(name + "-bottom");
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
        Draw.rect(bottomRegion, plan.drawx(), plan.drawy());
        Draw.color();
        Draw.rect(topRegions[bits[0]], plan.drawx(), plan.drawy(), plan.rotation * 90);
        Draw.scl();
    }

	@Override
	public boolean blends(Tile tile, int rotation, int otherx, int othery, int otherrot, Block otherblock){
		return otherblock.hasPower;
	}

	@Override
	public TextureRegion[] icons(){
		return new TextureRegion[]{Core.atlas.find(name + "-bottom"), topRegions[0]};
	}

	public class WireBuild extends Building{
		public int blendbits, xscl, yscl, blending;
        public Building next;
        public WireBuild nextc;

		@Override
		public void draw(){
            float rotation = rotdeg();
            int r = this.rotation;

            Draw.scl(xscl, yscl);
            drawAt(x, y, blendbits, rotation, SliceMode.none);
            Draw.reset();
		}

		protected void drawAt(float x, float y, int bits, float rotation, SliceMode slice){
            Draw.z(Layer.block);
            Draw.rect(bottomRegion, x, y, 0);
            Draw.color();
            Draw.rect(sliced(topRegions[bits], slice), x, y, rotation);
		}

		@Override
        public void onProximityUpdate(){
            super.onProximityUpdate();

            int[] bits = buildBlending(tile, rotation, null, true);
            blendbits = bits[0];
            xscl = bits[1];
            yscl = bits[2];
            blending = bits[4];
            next = front();
            nextc = next instanceof WireBuild d ? d : null;
        }
	}
}