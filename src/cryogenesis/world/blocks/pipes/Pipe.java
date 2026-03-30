package cryogenesis.world.blocks.pipes;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.world.blocks.liquid.*;

public class Pipe extends Conduit{

    public Pipe(String name){
        super(name);


        TextureRegion result = new TextureRegion();
        result.set(base);

        float crop = base.width / 8f;

        result.set(
            (int)(base.getX() + crop),
            (int)(base.getY() + crop),
            (int)(base.width - crop * 2),
            (int)(base.height - crop * 2)
        );
    }
}