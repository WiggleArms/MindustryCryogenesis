package cryogenesis.world.blocks.payloads;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.types.*;
import mindustry.ui.*;
import mindustry.world.blocks.payloads.*;

import static mindustry.Vars.*;

public class PayloadScrapper extends PayloadDeconstructor{
	public Item defaultItem = Items.scrap;
	public Seq<Item> blockedItems;
    public boolean onlyOutputDefault;

	 public PayloadScrapper(String name){
		super(name);
	}

	public class PayloadScrapperBuild extends PayloadDeconstructorBuild{
        public Item filteredItem(Item item){
            if(blockedItems.contains(item) || onlyOutputDefault) return defaultItem;
            return item;
        }

        @Override
        public void updateTile(){
            if(payload != null){
                payload.update(null, this);
            }

            if(items.total() > 0){
                for(int i = 0; i < dumpRate; i++){
                    dumpAccumulate();
                }
            }

            if(deconstructing == null){
                progress = 0f;
            }

            payRotation = Angles.moveToward(payRotation, 90f, payloadRotateSpeed * edelta());

            if(deconstructing != null){
                var reqs = deconstructing.requirements();
                if(accum == null || reqs.length != accum.length){
                    accum = new float[reqs.length];
                }

                //check if there is enough space to get the items for deconstruction
                boolean canProgress = items.total() <= itemCapacity;
                if(canProgress){
                    for(var ac : accum){
                        if(ac >= 1f){
                            canProgress = false;
                            break;
                        }
                    }
                }

                //move progress forward if possible
                if(canProgress){
                    float shift = edelta() * deconstructSpeed / deconstructing.buildTime();
                    float realShift = Math.min(shift, 1f - progress);

                    //if began deconstruction...
                    if(progress == 0f && shift > 0f && deconstructing instanceof BuildPayload pay){
                        var build = pay.build;
                        //dump liquid on floor (does not respect block configuration with respect to dumping liquids on floor)
                        if(build.liquids != null && build.liquids.currentAmount() > 0){
                            float perCell = build.liquids.currentAmount() / (block.size * block.size) * 2f;
                            tile.getLinkedTiles(other -> Puddles.deposit(other, build.liquids.current(), perCell));
                        }
                    }

                    progress += shift;
                    time += edelta();

                    for(int i = 0; i < reqs.length; i++){
                        accum[i] += reqs[i].amount * (deconstructing instanceof BuildPayload ? state.rules.buildCostMultiplier : state.rules.unitCost(team)) * realShift;
                    }
                }

                speedScl = Mathf.lerpDelta(speedScl, canProgress ? 1f : 0f, 0.1f);

                //transfer items from accumulation buffer into block inventory when they reach integers
                for(int i = 0; i < reqs.length; i++){
                    int taken = Math.min((int)accum[i], itemCapacity - items.total());
                    if(taken > 0){
                        items.add(filteredItem(reqs[i].item), taken);
                        accum[i] -= taken;
                    }
                }

                //finish deconstruction, prepare for next payload.
                if(progress >= 1f){
                    canProgress = true;
                    //check for rounding errors
                    for(int i = 0; i < reqs.length; i++){
                        if(Mathf.equal(accum[i], 1f, 0.0001f)){
                            if(items.total() < itemCapacity){
                                items.add(filteredItem(reqs[i].item), 1);
                                accum[i] = 0f;
                            }else{
                                canProgress = false;
                                break;
                            }
                        }
                    }

                    if(canProgress){
                        Fx.breakBlock.at(x, y, deconstructing.size() / tilesize);

                        deconstructing = null;
                        accum = null;
                    }
                }
            }else if(moveInPayload(false) && payload != null){
                accum = new float[payload.requirements().length];
                deconstructing = payload;
                payload = null;
                progress = 0f;
            }
        }
	}
}