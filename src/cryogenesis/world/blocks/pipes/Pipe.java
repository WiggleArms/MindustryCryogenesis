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

import static mindustry.Vars.*;

public class Pipe extends Duct{

    public final int timerFlow = timers++;
    public boolean leaks = true;

    public float waterBoost =  5.0f;
    public float cryoBoost = 3.0f;
    
    public float liquidPadding = 0f;

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

    /*
    @Override
    public void setStats(){
        super.setStats();

        if(findConsumer(f -> f instanceof ConsumeLiquidBase && f.booster) instanceof ConsumeLiquidBase consBase){
            stats.add(Stat.booster, StatValues.speedBoosters("{0}" + StatUnit.timesSpeed.localized(), 0f, waterBoost, false, consBase::consumes));
        }
    }
    */

    @Override
    public boolean blends(Tile tile, int rotation, int otherx, int othery, int otherrot, Block otherblock){
        if(!armored){
            return ((otherblock.outputsItems() || otherblock.outputsLiquid) || (lookingAt(tile, rotation, otherx, othery, otherblock) && (otherblock.hasItems || otherblock.hasLiquids)))
            && lookingAtEither(tile, rotation, otherx, othery, otherrot, otherblock);
        }else{
            return ((otherblock.outputsItems() || otherblock.outputsLiquid) && blendsArmored(tile, rotation, otherx, othery, otherrot, otherblock)) || (lookingAt(tile, rotation, otherx, othery, otherblock) && (otherblock.hasItems || otherblock.hasLiquids));
        }
    }

    public static void drawTiledFrames(int size, float x, float y, float padding, Liquid liquid, float alpha){
        drawTiledFrames(size, x, y, padding, padding, padding, padding, liquid, alpha);
    }

    public static void drawTiledFrames(int size, float x, float y, float padLeft, float padRight, float padTop, float padBottom, Liquid liquid, float alpha){
        TextureRegion region = renderer.fluidFrames[liquid.gas ? 1 : 0][liquid.getAnimationFrame()];
        TextureRegion toDraw = Tmp.tr1;

        float leftBounds = size/2f * tilesize - padRight;
        float bottomBounds = size/2f * tilesize - padTop;
        Color color = Tmp.c1.set(liquid.color).a(1f);

        for(int sx = 0; sx < size; sx++){
            for(int sy = 0; sy < size; sy++){
                float relx = sx - (size-1)/2f, rely = sy - (size-1)/2f;

                toDraw.set(region);

                //truncate region if at border
                float rightBorder = relx*tilesize + padLeft, topBorder = rely*tilesize + padBottom;
                float squishX = rightBorder + tilesize/2f - leftBounds, squishY = topBorder + tilesize/2f - bottomBounds;
                float ox = 0f, oy = 0f;

                if(squishX >= 8 || squishY >= 8) continue;

                //cut out the parts that don't fit inside the padding
                if(squishX > 0){
                    toDraw.setWidth(toDraw.width - squishX * 4f);
                    ox = -squishX/2f;
                }

                if(squishY > 0){
                    toDraw.setY(toDraw.getY() + squishY * 4f);
                    oy = -squishY/2f;
                }

                Drawf.liquid(toDraw, x + rightBorder + ox, y + topBorder + oy, alpha, color);
            }
        }
    }

    public class PipeBuild extends DuctBuild{

        //public float smoothLiquid;
        
        @Override
        public void draw(){
            super.draw();
            
            if(liquids.currentAmount() > 0.001f){
                Draw.z(Layer.blockUnder + 0.05f);
                drawTiledFrames(size, x, y, liquidPadding, liquids.current(), liquids.currentAmount() / liquidCapacity);
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

            //smoothLiquid = Mathf.lerpDelta(smoothLiquid, liquids.currentAmount() / liquidCapacity, 0.05f);

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