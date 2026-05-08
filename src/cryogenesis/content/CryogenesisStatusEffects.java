package cryogenesis.content;

import arc.*;
import arc.graphics.*;
import arc.math.*;
import mindustry.game.EventType.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.content.StatusEffects;

public class CryogenesisStatusEffects{
	public static StatusEffect boiling;

	public static void load(){

		boiling = new StatusEffect("boiling"){{
			color.valueOf("bbe3ff");
			damage = 0.083f;
			//effect = Fx.boiling;

			init(() -> {
				opposite(StatusEffects.freezing, StatusEffects.melting);
			});
		}};
	}
}