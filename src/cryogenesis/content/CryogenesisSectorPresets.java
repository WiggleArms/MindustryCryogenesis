package cryogenesis.content;

import mindustry.maps.*;
import mindustry.type.*;

import static cryogenesis.content.CryogenesisPlanets;

public class CryogenesisSectorPresets{
	public static SectorPreset zero;

	public static void load(){
		zero = new SectorPreset("zero", innelis, 6){{
			alwaysUnlocked = true;
			captureWave = 10;
			difficulty = 1;
			overrideLaunchDefaults = true;
			noLighting = true;
		}};
	}
}