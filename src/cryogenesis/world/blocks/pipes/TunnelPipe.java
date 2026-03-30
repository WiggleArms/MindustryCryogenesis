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

    public TunnelPipe(String name){
        super(name);
        //buildType = TunnelPipeBuild::new;
    }

    public class TunnelPipeBuild extends BufferedItemBridgeBuild {

        @Override
        public void draw(){
            Draw.z(Layer.blockUnder);
            super.draw();
        }
    }
}