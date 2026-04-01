package cryogenesis.world.blocks.pipes;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
//import mindustry.annotations.Annotations.*;
import mindustry.core.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.input.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.distribution.*;

import static mindustry.Vars.*;

public class TunnelPipe extends BufferedItemBridge{

    public TextureRegion[] liquidRegion;

    public TunnelPipe(String name){
        super(name);
        hasLiquids = true;
        outputsLiquid = true;
    }

    public void load(){
        super.load();
        liquidRegion = Core.atlas.find(name + "-liquid", "conduit-liquid");
    }

    public class TunnelPipeBuild extends BufferedItemBridgeBuild {

        @Override
        public void draw(){
            Draw.z(Layer.blockUnder + 0.2f);
            super.draw();
            
            if(liquids.currentAmount() > 0.001f){
                Draw.z(Layer.blockUnder + 0.3f);
                Drawf.liquid(liquidRegion, x, y, liquids.currentAmount() / liquidCapacity, liquids.current().color);
            }
        }

        @Override
        public void updateTransport(Building other){
            super.updateTransport(other);
            if(warmup >= 0.25f){
                moved |= moveLiquid(other, liquids.current()) > 0.05f;
            }
        }

        @Override
        public void doDump(){
            super.doDump();
            dumpLiquid(liquids.current(), 1f);
        }
    }
}