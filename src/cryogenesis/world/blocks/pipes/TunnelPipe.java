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
    }

    @Override
    public void draw(){

        super.draw();

        Draw.z(Layer.blockUnder);

        /*
        Tile other = world.tile(link);
        if(!linkValid(tile, other)) return;

        if(Mathf.zero(Renderer.bridgeOpacity)) return;

        int i = relativeTo(other.x, other.y);

        if(pulse){
            Draw.color(Color.white, Color.black, Mathf.absin(Time.time, 6f, 0.07f));
        }

        float warmup = hasPower ? this.warmup : 1f;

        Draw.alpha((fadeIn ? Math.max(warmup, 0.25f) : 1f) * Renderer.bridgeOpacity);

        Draw.rect(endRegion, x, y, i * 90 + 90);
        Draw.rect(endRegion, other.drawx(), other.drawy(), i * 90 + 270);

        Lines.stroke(bridgeWidth);

        Tmp.v1.set(x, y).sub(other.worldx(), other.worldy()).setLength(tilesize/2f).scl(-1f);

        Lines.line(bridgeRegion,
        x + Tmp.v1.x,
        y + Tmp.v1.y,
        other.worldx() - Tmp.v1.x,
        other.worldy() - Tmp.v1.y, false);

        int dist = Math.max(Math.abs(other.x - tile.x), Math.abs(other.y - tile.y)) - 1;

        Draw.color();

        int arrows = (int)(dist * tilesize / arrowSpacing), dx = Geometry.d4x(i), dy = Geometry.d4y(i);

        for(int a = 0; a < arrows; a++){
            Draw.alpha(Mathf.absin(a - time / arrowTimeScl, arrowPeriod, 1f) * warmup * Renderer.bridgeOpacity);
            Draw.rect(arrowRegion,
            x + dx * (tilesize / 2f + a * arrowSpacing + arrowOffset),
            y + dy * (tilesize / 2f + a * arrowSpacing + arrowOffset),
            i * 90f);
        }

        Draw.reset();
        */
    }
}