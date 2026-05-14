package cryogenesis.type.unit;

import cryogenesis.graphics.*;
import cryogenesis.ai.types.*;

import mindustry.ai.types.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.meta.*;

import static mindustry.type.ItemStack.*;
import static mindustry.Vars.*;

public class ScrapUnitType extends UnitType{

	public ItemStack[] scrapValue;
	public float scrapTime;

	public ScrapUnitType(String name){
		super(name);

        constructor = ElevationMoveUnit::create;
        controller = u -> new ScrapAI();

        isEnemy = false;
        speed = 0f;
        rotateSpeed = 0f;
        fogRadius = 0f;
        lightRadius = 0f;
        hittable = false;
        targetable = false;
        //physics = false;
        canDrown = false;
        createScorch = false;
        canAttack = false;
        playerControllable = false;
        logicControllable = false;
        useUnitCap = false;
        drawMinimap = false;
        groundLayer = Layer.debris - 1f;
        //hidden = true; // enable once this unit works properly

        outlineColor = CryogenesisPalette.ironOutline;
	}

	@Override
	public ItemStack[] getTotalRequirements(){
		buildTime = scrapTime;
		return scrapValue;
	}

    @Override
    public void update(Unit unit){
        //change to player team if sector is captured
        if(unit.team.isOnlyAI() && state.isCampaign() && state.getSector().isCaptured()){
            //hopefully its fine to hardcode this
            unit.team = Team.sharded;
        }
    }
}