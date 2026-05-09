package cryogenesis.graphics;

import cryogenesis.graphics.CryogenesisShaders;

import arc.graphics.*;
import arc.graphics.gl.*;
import arc.math.*;
import arc.util.*;
import mindustry.graphics.CacheLayer;

import static mindustry.Vars.*;

public class CryogenesisCacheLayer{
	public static CacheLayer soda;

	public static void load(){
		CacheLayer.add(soda = new CacheLayer.ShaderLayer(CryogenesisShaders.soda));
	}
}