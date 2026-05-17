package cryogenesis.content;

import cryogenesis.content.CryogenesisStatusEffects;

import arc.graphics.*;
import mindustry.type.*;

public class CryogenesisLiquids{
	public static Liquid sluice, steam;

	public static void load(){

		sluice = new Liquid("sluice", Color.valueOf("7fa7c8")){{
			heatCapacity = 0.2f;
			viscosity = 0.4f;
			temperature = 0.4f;
			effect = CryogenesisStatusEffects.boiling;
			boilPoint = 0.15f;
			gasColor = Color.valueOf("59a8dd");
		}};

		steam = new Liquid("steam", Color.valueOf("b5dcfb")){{
			gas = true;
			explosiveness = 0.2f;
		}};
	}
}