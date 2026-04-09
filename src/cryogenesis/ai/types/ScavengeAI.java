package cryogenesis.ai.types;

//import cryogenesis.world.meta.*;
import cryogenesis.type.unit.*;

//import arc.util.Log;
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

import static mindustry.Vars.*;

public class ScavengeAI extends AIController{
	static Seq<PayloadDeconstructorBuild> scrappers = new Seq<>();
	
	public PayloadDeconstructorBuild unloadTarget;
	public Unit unitTarget;
    protected float payloadPickupCooldown;

	@Override
	public void updateMovement(){
		
		//Log.info("Running scavengeAI from @", unit.id);
		if(!net.client() && unit instanceof Payloadc pay){
			// look for nearby enemies
			// if enemy, flee
			// otherwise check payload
			if(!pay.hasPayload()){

				// if no target, or target is not valid, or target is already picked up (commented out as units in payloads may not be considered units)
				if(unitTarget == null || !unitTarget.isValid()/* || !unitTarget.isPayload()*/ ){
					// find nearest non-payload scrap unit
					unitTarget = Units.closest(null, unit.x, unit.y, u -> u.type instanceof ScrapUnitType/* && !u.isPayload()*/);
					//Log.info("Found target: @", unitTarget);
				}

				// if good target
				if(unitTarget != null){
					// move to and pickup target
					moveTo(unitTarget, 5f);
					//Log.info("Moving");

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
				}
				// otherwise, find scrapper
				// if scrapper, go to it
				// otherwise, idle I guess?
			} else {
				// unit has payload, look for scrapper
				// if scrapper, go to it
				// otherwise, idle I guess?
			}
		}
	}

	/*
	public void findBuilding(Building build){
		unloadTarget = null

        targets = Seq<PayloadDeconstructorBuild>)(Seq)Vars.indexer.getFlagged(unit.team, CryogenesisBlockFlag.unitScrapper);

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
