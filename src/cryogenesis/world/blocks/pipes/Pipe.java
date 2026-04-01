package cryogenesis.world.blocks.pipes;

import cryogenesis.content.CryogenesisBlocks;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
//import mindustry.annotations.Annotations.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.input.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

public class Pipe extends Duct{

    public final int timerFlow = timers++;
    public boolean leaks = true;

    public float waterBoost =  5.0f;
    public float cryoBoost = 3.0f;

    public TextureRegion liquidRegion;

    public Pipe(String name){
        super(name);
        hasLiquids = true;
        outputsLiquid = true;
    }

    public void load(){
        super.load();
        liquidRegion = Core.atlas.find(name + "-liquid", "conduit-liquid");
    }

    @Override
    public void init(){
        super.init();

        bridgeReplacement = CryogenesisBlocks.tunnelPipe;
        //if(junctionReplacement == null) junctionReplacement = Blocks.ductJunction;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.booster, StatValues.speedBoosters("{0}" + StatUnit.timesSpeed.localized(), 0f, waterBoost, false, consBase::consumes));
    }

    public class PipeBuild extends DuctBuild{

        public float smoothLiquid;
        
        @Override
        public void draw(){
            super.draw();
            
            if(liquids.currentAmount() > 0.001f){
                Draw.z(Layer.blockUnder + 0.05f);
                Drawf.liquid(liquidRegion, x, y, liquids.currentAmount() / liquidCapacity, liquids.current().color);
            }
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid){
            noSleep();
            return (liquids.current() == liquid || liquids.currentAmount() < 0.2f) && (tile == null || source == this || (source.relativeTo(tile.x, tile.y) + 2) % 4 != rotation);
        }

        @Override
        public void updateTile(){
            
            float actualSpeed = speed;
            
            if (liquids.current() == Liquids.water && liquids.currentAmount() > 0.0001f) {
                actualSpeed = speed / waterBoost;
            } else if (liquids.current() == Liquids.cryofluid && liquids.currentAmount() > 0.0001f) {
                actualSpeed = speed / cryoBoost;
            }
            
            progress += edelta() / actualSpeed * 2f;

            if(current != null && next != null){
                if(progress >= (1f - 1f/speed) && moveForward(current)){
                    items.remove(current, 1);
                    current = null;
                    progress %= (1f - 1f/actualSpeed);
                }
            }else{
                progress = 0;
            }

            if(current == null && items.total() > 0){
                current = items.first();
            }

            smoothLiquid = Mathf.lerpDelta(smoothLiquid, liquids.currentAmount() / liquidCapacity, 0.05f);

            if(liquids.currentAmount() > 0.0001f && timer(timerFlow, 1)){
                moveLiquidForward(leaks, liquids.current());
                noSleep();
            }
        }

        @Override
        public void onProximityUpdate(){
            super.onProximityUpdate();

            nextc = next instanceof PipeBuild d ? d : null;
        }
    }
}