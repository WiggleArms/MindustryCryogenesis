package cryogenesis.ai.types;

//import cryogenesis.world.meta.*;
import cryogenesis.type.unit.*;

import arc.util.Log;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.ai.*;
import mindustry.core.*;
import mindustry.entities.*;
import mindustry.entities.comp.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.world.blocks.payloads.PayloadDeconstructor.*;
import mindustry.world.meta.*;
//import mindustry.content.Blocks;

import static mindustry.Vars.*;

public class ScavengeAI extends AIController{
	static Seq<PayloadDeconstructorBuild> scrappers = new Seq<>();
	
	public PayloadDeconstructorBuild unloadTarget;
	public Unit unitTarget;
    protected float payloadPickupCooldown;

	@Override
	public void updateMovement(){
		
		Log.info("Running scavengeAI from @", unit.id);
		if(!net.client() && unit instanceof Payloadc pay){
			// look for nearby enemies
			// if enemy, flee
			// otherwise check payload
			if(!pay.hasPayload()){

				// if no unit, or unit is not valid, or unit is already picked up (commented out as units in payloads may not be considered units)
				if(unitTarget == null || !unitTarget.isValid()/* || !unitTarget.isPayload()*/ ){
					// find nearest non-payload scrap unit
					unitTarget = Units.closest(null, unit.x, unit.y, 160f, u -> u.type instanceof ScrapUnitType/* && !u.isPayload()*/); // remove range if possible
					Log.info("Found unit: @", unitTarget);
				}

				// if good unit
				if(unitTarget != null){
					// move to and pickup unit
					moveTo(unitTarget, 5f);
					Log.info("Moving to unit");

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
						unloadTarget = Units.closestBuilding(unit.team, unit.x, unit.y, 160f, b -> b instanceof PayloadDeconstructor);
					}

					// if good base
					if(unloadTarget != null){
						moveTo(unloadTarget, 5f);
						Log.info("No units, moving to base");
					} else {
						Log.info("No units or bases found, idling");
					}
				}
			} else {
				// if current base does not exist
				if(unloadTarget == null || !unloadTarget.isValid()){
					// find new base
					unloadTarget = Units.closestBuilding(unit.team, unit.x, unit.y, 160f, b -> b instanceof PayloadDeconstructor);
				}

				// if good base
				if(unloadTarget != null){
					// move to base and unload unit
					moveTo(unloadTarget, 5f);
					Log.info("Transporting unit to base");
					
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
					Log.info("No bases found, idling");
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
	
	
    void tryPickupUnit(Payloadc pay){
        Unit target = Units.closest(unit.team, unit.x, unit.y, unit.type.hitSize * 2f, u -> u.isAI() && u != unit && u.isGrounded() && pay.canPickup(u) && u.within(unit, u.hitSize + unit.hitSize));
        if(target != null){
            Call.pickedUnitPayload(unit, target);
        }
    }
}
