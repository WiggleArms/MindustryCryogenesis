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

public class Pipe extends Duct{

    public final int timerFlow = timers++;
    public boolean leaks = true;

    public float baseSpeed = 30f;
    public float waterBoost =  5.0f;
    public float cryoBoost = 3.0f;

    public TextureRegion liquidRegion;

    public Pipe(String name){
        super(name);
        hasLiquids = true;
        outputsLiquid = true;
        speed = baseSpeed;
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
            super.updateTile();

            if liquids.current() == Liquids.water {
                speed = baseSpeed / (Mathf.lerp(1f, waterBoost, optionalEfficiency) * efficiency);
            } else if liquids.current() == Liquids.cryofluid {
                speed = baseSpeed / (Mathf.lerp(1f, cryoBoost, optionalEfficiency) * efficiency);
            }

            smoothLiquid = Mathf.lerpDelta(smoothLiquid, liquids.currentAmount() / liquidCapacity, 0.05f);

            if(liquids.currentAmount() > 0.0001f && timer(timerFlow, 1)){
                moveLiquidForward(leaks, liquids.current());
                noSleep();
            }
        }
    }
}