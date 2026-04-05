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

public class CryogenesisPlanets{
	public static Planet innelis;

	public static void load(){
		innelis = new Planet("innelis", sun, 1f, 2){{
			generator = new ErekirPlanetGenerator();
			meshLoader = () -> new HexMesh(this, 4);
			cloudMeshLoader = () -> new MultiMesh(
				//TODO replace with custom cloud parameters, especially change color to cryofluid-esque
                new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(Pal.spore).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(Pal.spore, 0.55f).a(0.75f), 2, 0.45f, 1f, 0.41f)
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
			iconColor = Color.valueOf("ffffff"); //TODO replace with cryofluid color
			atmosphereColor = Color.valueOf("ffffff"); //TODO same
			atmosphereRadIn = 0.02f;
			atmosphereRadOut = 0.3f;
			startSector = 6;
			alwaysUnlocked = true;
			landCloudColor = Color.valueOf("ffffff"); //TODO same
			defaultEnv = Env.terrestrial;
			defaultCore = CryogenesisBlocks.coreThread;
			//allowLaunchToNumbered = false; Re-enable once map is added

			unlockedOnLand.add(CryogenesisBlocks.coreThread);
		}};
	}
}