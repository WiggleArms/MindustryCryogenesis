package cryogenesis.world.blocks.pipes;

import cryogenesis.content.CryogenesisBlocks;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.annotations.Annotations.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.distribution.*;

public class Pipe extends Duct{

    //public TextureRegion[] liquidRegion;

    public Pipe(String name){
        super(name);
        hasLiquids = true;
        outputsLiquid = true;
    }

    @Override
    public void init(){
        super.init();

        bridgeReplacement = CryogenesisBlocks.tunnelPipe;
        //if(junctionReplacement == null) junctionReplacement = Blocks.ductJunction;
    }
}