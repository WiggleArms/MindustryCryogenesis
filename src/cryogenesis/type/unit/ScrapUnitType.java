package cryogenesis.type.unit;

import mindustry.ai.types.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.meta.*;

import static mindustry.type.ItemStack.*;

public class ScrapUnitType extends UnitType{

	public ItemStack[] scrapValue;

	public ScrapUnitType(String name){
		super(name);
	}

	@Override
	public ItemStack[] getTotalRequirements(){
		return scrapValue;
	}
}