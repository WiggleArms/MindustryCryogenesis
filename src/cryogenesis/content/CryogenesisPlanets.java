package cryogenesis.content;

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
			generator = new SerpuloPlanetGenerator();
			meshLoader = () -> new HexMesh(this, 4);
			cloudMeshLoader = () -> new MultiMesh(
				//TODO replace with custom cloud parameters, possibly vary colors
                new HexSkyMesh(this, 11, 0.15f, 0.13f, 4, new Color().set(Pal.spore).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                new HexSkyMesh(this, 1, 0.6f, 0.16f, 4, Color.white.cpy().lerp(Pal.spore, 0.55f).a(0.75f), 2, 0.45f, 1f, 0.41f)
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
			atmosphereColor = Color.valueOf("3c1b8f"); //TODO same
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