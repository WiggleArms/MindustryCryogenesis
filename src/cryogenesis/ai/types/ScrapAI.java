package cryogenesis.ai.types;

import cryogenesis.type.unit.*;

import arc.math.*;
import arc.util.*;
import mindustry.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.game.*;

public class ScrapAI extends AIController{

	@Override
    public void updateUnit() {
        super.updateUnit();

        // Find nearest valid unit (non-scrap, real team)
        Unit nearest = Units.closest(
            null,          // search from current team (derelict initially)
            unit.x, unit.y,     // position
            80f,                // range (tweak this)
            u -> u.team != unit.team && !(u.type instanceof ScrapUnitType)
        );

        if(nearest != null){
            unit.team = nearest.team;
        }
    }
}