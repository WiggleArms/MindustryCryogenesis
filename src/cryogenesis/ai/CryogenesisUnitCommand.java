package cryogenesis.ai;

import cryogenesis.input.*;

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

public class CryogenesisUnitCommand extends UnitCommand{

	public static UnitCommand scavengeCommand;

	@Override
	public static void loadAll(){
		super();

		scavengeCommand = new UnitCommand("scavenge", "refresh", CryogenesisBinding.unitCommandScavenge, u -> new ScrapperAI());
	}
}