package cryogenesis.type.unit;

import mindustry.ai.types.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.meta.*;

import static mindustry.type.ItemStack.*;

public class ScrapUnitType extends UnitType{

	public ItemStack[] scrapValue;
	public float scrapTime;

	public ScrapUnitType(String name){
		super(name);

        constructor = UnitEntity::create;
        isEnemy = false;
        speed = 0f;
        rotateSpeed = 0f;
        hitSize = 1f;
        fogRadius = 0f;
        targetable = false;
        physics = false;
        canDrown = false;
        createScorch = false;
        canAttack = false;
        playerControllable = false;
        logicControllable = false;
        useUnitCap = false;
        drawMinimap = false;
        //hidden = true; // enable once this unit works properly
	}

	@Override
	public ItemStack[] getTotalRequirements(){
		buildTime = scrapTime;
		return scrapValue;
	}
}