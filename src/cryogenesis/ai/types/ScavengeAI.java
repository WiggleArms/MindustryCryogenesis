package cryogenesis.ai.types;

//import cryogenesis.world.meta.*;
import cryogenesis.type.unit.*;

import arc.*;
import arc.func.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
//import arc.util.Log;
import mindustry.ai.*;
import mindustry.content.*;
import mindustry.core.*;
import mindustry.entities.*;
import mindustry.entities.comp.*;
import mindustry.entities.units.*;
import mindustry.game.*;
import mindustry.game.Teams.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.payloads.PayloadDeconstructor.*;
import mindustry.world.meta.*;
//import mindustry.content.Blocks;

import static mindustry.Vars.*;

public class ScavengeAI extends AIController{
	static Seq<PayloadDeconstructorBuild> scrappers = new Seq<>();
	
	public PayloadDeconstructorBuild unloadTarget;
	public Unit unitTarget;
    protected float payloadPickupCooldown;
	protected boolean full = false;
	
    public static float retreatDst = 160f, fleeRange = 310f, retreatDelay = Time.toSeconds * 3f;
	private static Unit result;
	private static float cdist;
    Teamc avoid;

	@Override
	public void updateMovement(){
		
		//Log.info("Running scavengeAI from @", unit.id);
		if(!net.client() && unit instanceof Payloadc pay){

			// occasional enemy check
			if(timer.get(timerTarget4, 40)){
				avoid = target(unit.x, unit.y, fleeRange, true, true);
				Log.info("Enemy check: @", avoid);
			}

			//fly away if enemy
			if(avoid != null){
				unitTarget = null; // invalidate current target		
				Vec2 flee = Tmp.v1.set(unit).sub(avoid).nor();
				unit.moveAt(flee.scl(unit.speed()));

				// don't let anything else run
				return;
			}

			// recalculate target after a second to catch any initial miscalculations
			//Log.info("Cooldown: @", payloadPickupCooldown);
			if(payloadPickupCooldown < 0f || payloadPickupCooldown == 60f || (unitTarget == null || !unitTarget.isValid()) && !full){
				findScrap(pay);
				if(payloadPickupCooldown < 0f) payloadPickupCooldown = 0f;
			}

			if(payloadPickupCooldown != 0f) payloadPickupCooldown -= Time.delta;

			// look for nearby enemies
			// if enemy, flee
			// otherwise check payload

			// if unloaded, reset hasPayload
			if(!pay.hasPayload()) full = false;

			// if not full, look for units to pickup and grab them
			// otherwise, deliver payload(s) to base
			if(!full){

				// if no unit, or unit is not valid
				//if(unitTarget == null || !unitTarget.isValid()){
				//	findScrap(pay);
				//}

				// if good unit
				if(unitTarget != null){
					// move to and pickup unit
					moveTo(unitTarget, 5f);
					//Log.info("Moving to unit");

					if(unit.within(unitTarget, 8f)){
					
						int prev = -1;
						while(prev != pay.payloads().size){
							prev = pay.payloads().size;
							tryPickupUnit(pay);
						}

						//wait to load things before running code below
						if(!pay.hasPayload()){
							return;
						}
						payloadPickupCooldown = 60f;
					}
				} else {
					// no units, so go back to base to wait

					// if current base does not exist
					if(unloadTarget == null || !unloadTarget.isValid()){
						// find new base
						Building build = Units.closestBuilding(unit.team, unit.x, unit.y, 160f, b -> b instanceof PayloadDeconstructorBuild);
						unloadTarget = (PayloadDeconstructorBuild)build;
					}

					// if good base
					if(unloadTarget != null){
						moveTo(unloadTarget, 5f);
						//Log.info("No units, moving to base");
					} else {
						//Log.info("No units or bases found, idling");
					}
				}
			} else {
				// if current base does not exist
				if(unloadTarget == null || !unloadTarget.isValid()){
					// find new base
					Building build = Units.closestBuilding(unit.team, unit.x, unit.y, 160f, b -> b instanceof PayloadDeconstructorBuild);
					unloadTarget = (PayloadDeconstructorBuild)build;
				}

				// if good base
				if(unloadTarget != null){
					// move to base and unload unit
					moveTo(unloadTarget, 5f);
					//Log.info("Transporting unit to base");
					
					if(unit.within(unloadTarget, 8f)){

						int prev = -1;
						while(pay.hasPayload() && prev != pay.payloads().size){
							prev = pay.payloads().size;
							Call.payloadDropped(unit, unit.x, unit.y);
						}

						//wait for everything to unload before running code below
						if(pay.hasPayload()){
							return;
						}
						payloadPickupCooldown = 60f;
					}
				} else {
					//Log.info("No bases found, idling");
				}
			}
		}
	}

	/*
	public void findBuilding(Building build){
		unloadTarget = null

        scrappers = indexer.getBuildings(unit.team, b -> b instanceof PayloadDeconstructorBuild);

		if(baseTargets.isEmpty()) return;
	}
	*/

	public static Unit closestUnit(Team team, float x, float y, Boolf<Unit> predicate){
        result = null;
        cdist = 0f;

        for(Unit e : Groups.unit){
            if(!predicate.get(e) || (e.team() != team && team != null)) continue;

            float dist = e.dst2(x, y);
            if(result == null || dist < cdist){
                result = e;
                cdist = dist;
            }
        }

        return result;
    }

	public void findScrap(Payloadc pay){
		// find nearest scrap unit that fits in remaining payload capacity
		unitTarget = closestUnit(null, unit.x, unit.y, u -> u.type instanceof ScrapUnitType && pay.payloadUsed() + u.hitSize * u.hitSize <= unit.type.payloadCapacity + 0.001f &&  target(u.x, u.y, fleeRange, true, true) == null);
		//Log.info("Recalculated payload capacity: @", unit.type.payloadCapacity + 0.001f - pay.payloadUsed());
		if(unitTarget == null) full = true;
		//Log.info("Found unit: @", unitTarget);
	}
	
    void tryPickupUnit(Payloadc pay){
        Unit target = Units.closest(unit.team, unit.x, unit.y, unit.type.hitSize * 2f, u -> u.isAI() && u != unit && u.isGrounded() && pay.canPickup(u) && u.within(unit, u.hitSize + unit.hitSize));
        if(target != null){
            Call.pickedUnitPayload(unit, target);
        }
    }
}
