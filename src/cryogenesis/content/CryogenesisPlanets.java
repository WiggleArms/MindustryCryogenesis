package cryogenesis.content;

import cryogenesis.maps.planet.*;

import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.graphics.g3d.*;
import mindustry.graphics.g3d.PlanetGrid.*;
import mindustry.maps.planet.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import mindustry.content.Planets;

import cryogenesis.content.CryogenesisBlocks;
import cryogenesis.world.graphics.CryogenesisPalette;

public class CryogenesisPlanets{
	public static Planet innelis;

	public static void load(){
		innelis = new Planet("innelis", Planets.sun, 1f, 1){{
			generator = new InnelisPlanetGenerator();
			meshLoader = () -> new HexMesh(this, 4);
			cloudMeshLoader = () -> new MultiMesh(
				//TODO replace with custom cloud parameters, possibly vary colors
                new HexSkyMesh(this, 5, 0.15f, 0.21f, 4, Color.valueOf("3d5957").a(0.75f), 2, 0.49f, 0.7f, 0.30f),
                new HexSkyMesh(this, 10, 0.6f, 0.25f, 4, Color.valueOf("446560").a(0.75f), 2, 0.49f, 0.8f, 0.32f)
			);

			launchCapacityMultiplier = 0.1f;
			sectorSeed = 0;
			allowWaves = true;
			allowSectorInvasion = true;
			allowLaunchSchematics = true;
			enemyCoreSpawnReplace = true;
			allowLaunchLoadout = true;
			ruleSetter = r -> {
				r.waveTeam = Team.green;
				r.placeRangeCheck = false;
				r.showSpawns = false;
				r.coreDestroyClear = true;
			};
			showRtsAIRule = true;
			iconColor = CryogenesisPalette.cryoBase; //TODO maybe vary these slightly
			atmosphereColor = Color.valueOf("37524e"); //TODO same
			atmosphereRadIn = -0.01f;
			atmosphereRadOut = 0.3f;
			startSector = 6;
			alwaysUnlocked = true;
			landCloudColor = CryogenesisPalette.cryoBase; //TODO same
			defaultEnv = Env.terrestrial;
			defaultCore = CryogenesisBlocks.coreThread;
			allowLaunchToNumbered = false; //Re-enable once map is added

			unlockedOnLand.add(CryogenesisBlocks.coreThread);
		}};
	}
}