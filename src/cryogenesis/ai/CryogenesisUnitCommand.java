package cryogenesis.ai;

import cryogenesis.input.*;
import cryogenesis.ai.types.*;

import arc.*;
import arc.func.*;
import arc.input.*;
import arc.scene.style.*;
import arc.struct.*;
import arc.util.*;
import mindustry.ai.*;
import mindustry.ai.types.*;
import mindustry.ctype.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.input.*;

public class CryogenesisUnitCommand{

	public static UnitCommand scavengeCommand;

	public static void load(){
		scavengeCommand = new UnitCommand("scavenge", "refresh", CryogenesisBinding.unitCommandScavenge, u -> new ScavengeAI());
	}
}