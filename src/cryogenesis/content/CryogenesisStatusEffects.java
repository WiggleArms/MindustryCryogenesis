package cryogenesis.content;

import arc.*;
import arc.graphics.*;
import arc.math.*;
import mindustry.game.EventType.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.type.*;

public class CryogenesisStatusEffects{
	public static StatusEffect boiling;

	boiling = new StatusEffect("boiling"){{
		color.valueOf("bbe3ff");
		damage = 0.083;
		//effect = Fx.boiling;

		init(() -> {
			opposite(freezing, melting);
		});
	}};
}