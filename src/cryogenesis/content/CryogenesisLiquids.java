package cryogenesis.content

import cryogenesis.content.CryogenesisStatusEffects;

import arc.graphics.*;
import mindustry.type.*;

public class CryogenesisLiquids{
	public static Liquid soda, steam;

	public static void load(){

		soda = new Liquid("soda", Color.valueOf("1b74b5")){{
			heatCapacity = 0.3f;
			viscosity = 0.4f;
			temperature = 0.4f;
			effect = CryogenesisStatusEffects.boiling;
			boilPoint = 0.15f;
			gasColor = Color.valueOf("59a8dd");
		}};

		steam = new Liquid("steam", Color.valueOf("b5dcfb")){{
			gas = true;
			explosiveness = 0.2f;
		}});
	}
}