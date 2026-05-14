package cryogenesis.content;

import mindustry.maps.*;
import mindustry.type.*;

import cryogenesis.content.CryogenesisPlanets;

public class CryogenesisSectorPresets{
	public static SectorPreset cryogenesis;

	public static void load(){
		cryogenesis = new SectorPreset("cryogenesis", CryogenesisPlanets.innelis, 6){{
			alwaysUnlocked = true;
			captureWave = 5;
			difficulty = 1;
			overrideLaunchDefaults = true;
			noLighting = true;
		}};
	}
}