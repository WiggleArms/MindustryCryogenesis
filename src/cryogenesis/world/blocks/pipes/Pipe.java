package cryogenesis.world.blocks.pipes;

import cryogenesis.content.CryogenesisBlocks;
import cryogenesis.content.CryogenesisLiquids;

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
    //not sure why it doesn't find this, hopefully that's not a bad sign
    public static final int animationFrames = 50;

    static final float rotatePad = 6, hpad = rotatePad / 2f / 4f;
    static final float[][] rotateOffsets = {{hpad, hpad}, {-hpad, hpad}, {-hpad, -hpad}, {hpad, -hpad}};

    public final int timerFlow = timers++;
    
    //public Color botColor = Color.valueOf("2c2d38");

    public TextureRegion liquidRegion;
    public TextureRegion capRegion;
    
    public TextureRegion[][][] rotateRegions;

    public boolean padCorners = true;
    public boolean leaks = true;

    public ObjectMap<Liquid, Float> boostValues = new ObjectMap<>(){{
        put(Liquids.water, 5.0f);
        put(Liquids.cryofluid, 3.0f);
        put(CryogenesisLiquids.sluice, 1.2f);
    }};

    public Color transparentColor = new Color(0.44f, 0.45f, 0.56f, 0.1f);
    
    //public float liquidPadding = 1f;

    public PipeBuild nextPipe, prevPipe;

    public Pipe(String name){
        super(name);
        hasLiquids = true;
        outputsLiquid = true;
    }

    public void load(){
        super.load();
        liquidRegion = Core.atlas.find(name + "-liquid", "conduit-liquid");
        capRegion = Core.atlas.find(name + "-cap", "conduit-cap");

        rotateRegions = new TextureRegion[4][2][animationFrames];

        if(renderer != null){
            float pad = rotatePad;
            var frames = renderer.getFluidFrames();

            for(int rot = 0; rot < 4; rot++){
                for(int fluid = 0; fluid < 2; fluid++){
                    for(int frame = 0; frame < animationFrames; frame++){
                        TextureRegion base = frames[fluid][frame];
                        TextureRegion result = new TextureRegion();
                        result.set(base);

                        if(rot == 0){
                            result.setX(result.getX() + pad);
                            result.setHeight(result.height - pad);
                        }else if(rot == 1){
                            result.setWidth(result.width - pad);
                            result.setHeight(result.height - pad);
                        }else if(rot == 2){
                            result.setWidth(result.width - pad);
                            result.setY(result.getY() + pad);
                        }else{
                            result.setX(result.getX() + pad);
                            result.setY(result.getY() + pad);
                        }

                        rotateRegions[rot][fluid][frame] = result;
                    }
                }
            }
        }
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

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{Core.atlas.find("-bottom", "duct-bottom"), topRegions[0]};
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
        public float smoothLiquid;
        //public int blendbits, xscl, yscl, blending;
        public boolean capped, backCapped = false;
        
        @Override
        public void draw(){
            float rotation = rotdeg();
            int r = this.rotation;

            //draw extra pipes facing this one for tiling purposes
            Draw.z(Layer.blockUnder);
            for(int i = 0; i < 4; i++){
                if((blending & (1 << i)) != 0){
                    int dir = r - i;
                    drawAt(x + Geometry.d4x(dir) * tilesize*0.75f, y + Geometry.d4y(dir) * tilesize*0.75f, 0, i == 0 ? r : dir, i != 0 ? SliceMode.bottom : SliceMode.top);
                }
            }

            //draw item
            if(current != null){
                Draw.z(Layer.blockUnder + 0.1f);
                Tmp.v1.set(Geometry.d4x(recDir) * tilesize / 2f, Geometry.d4y(recDir) * tilesize / 2f)
                .lerp(Geometry.d4x(r) * tilesize / 2f, Geometry.d4y(r) * tilesize / 2f,
                Mathf.clamp((progress + 1f) / (2f - 1f/speed)));

                Draw.rect(current.fullIcon, x + Tmp.v1.x, y + Tmp.v1.y, itemSize, itemSize);
            }

            Draw.z(Layer.blockUnder);

            Draw.scl(xscl, yscl);
            drawAt(x, y, blendbits, r, SliceMode.none);
            Draw.reset();

            Draw.z(Layer.blockUnder + 0.25f);

            if(capped && capRegion.found()) Draw.rect(capRegion, x, y, rotdeg());
            if(backCapped && capRegion.found()) Draw.rect(capRegion, x, y, rotdeg() + 180);
        }

        protected void drawAt(float x, float y, int bits, int rotation, SliceMode slice){
            float angle = rotation * 90f;
            //Draw.color(botColor);
            Draw.rect(sliced(botRegions[bits], slice), x, y, angle);

            int offset = yscl == -1 ? 3 : 0;

            int frame = liquids.current().getAnimationFrame();
            int gas = liquids.current().gas ? 1 : 0;
            float ox = 0f, oy = 0f;
            int wrapRot = (rotation + offset) % 4;
            TextureRegion liquidr = bits == 1 && padCorners ? rotateRegions[wrapRot][gas][frame] : renderer.fluidFrames[gas][frame];

            if(bits == 1 && padCorners){
                ox = rotateOffsets[wrapRot][0];
                oy = rotateOffsets[wrapRot][1];
            }

            //the drawing state machine sure was a great design choice with no downsides or hidden behavior!!!
            float xscl = Draw.xscl, yscl = Draw.yscl;
            Draw.scl(1f, 1f);
            Drawf.liquid(sliced(liquidr, slice), x + ox, y + oy, smoothLiquid, liquids.current().color.write(Tmp.c1).a(1f));
            Draw.scl(xscl, yscl);
            
            Draw.z(Layer.blockUnder + 0.2f);
            Draw.color(transparentColor);
            Draw.rect(sliced(botRegions[bits], slice), x, y, angle);
            Draw.color();
            Draw.rect(sliced(topRegions[bits], slice), x, y, angle);
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid){
            noSleep();
            return (liquids.current() == liquid || liquids.currentAmount() < 0.2f) && (tile == null || source == this || (source.relativeTo(tile.x, tile.y) + 2) % 4 != rotation);
        }

        @Override
        public void updateTile(){
            
            float actualSpeed = speed;

            if (liquids.currentAmount() > 0.0001f && boostValues.containsKey(liquids.current())){
                actualSpeed = speed / boostValues.get(liquids.current());
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

        boolean blockRequiresCap(Building b){
            boolean curvedPipe = b instanceof PipeBuild d && (d.rotation != this.rotation || d.blendbits != 0);
            boolean nonPipe = b != null && !(b instanceof PipeBuild) && b.block.squareSprite;
            return curvedPipe || nonPipe;
        }

        @Override
        public void onProximityUpdate(){
            super.onProximityUpdate();

            Building next = front(), prev = back();

            nextPipe = next instanceof PipeBuild d ? d : null;
            prevPipe = prev instanceof PipeBuild d ? d : null;

            capped = blendbits == 0 && blockRequiresCap(next);
            backCapped = blendbits == 0 && blockRequiresCap(prev);

            /* 
            cap if straight pipe and:
            exiting non-pipe team Block
            entering non-pipe team Block
            entering curved pipe of same team
            exiting curved pipe of same team
            */

            //rework logic to only apply dynamic caps to straight pipes, non-straight ALWAYS have caps, and are built into the sprite
        }
    }
}