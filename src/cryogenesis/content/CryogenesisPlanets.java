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
import mindustry.content.Items;

import cryogenesis.content.CryogenesisBlocks;
import cryogenesis.graphics.CryogenesisPalette;

public class CryogenesisPlanets{
	public static Planet innelis;

	public static void load(){
		innelis = new Planet("innelis", Planets.sun, 1f, 1){{
			generator = new InnelisPlanetGenerator();
			meshLoader = () -> new HexMesh(this, 4);
			cloudMeshLoader = () -> new MultiMesh(
				//TODO replace with custom cloud parameters, possibly vary colors
                new HexSkyMesh(this, 11, 0.15f, 0.06f, 4, new Color().set(CryogenesisPalette.cryoBase).lerp(Color.black, 0.5f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                new HexSkyMesh(this, 1, 0.6f, 0.08f, 4, new Color().set(CryogenesisPalette.cryoCloud).lerp(Color.black, 0.5f).a(0.75f), 2, 0.45f, 1f, 0.41f)
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
				r.hideSpawns = true;
				r.coreDestroyClear = true;
			};
			showRtsAIRule = true;
			allowCampaignRules = true;
			iconColor = CryogenesisPalette.cryoBase; //TODO maybe vary these slightly
			atmosphereColor = new Color().set(CryogenesisPalette.cryoBase).lerp(Color.black, 0.5f); //TODO same
			atmosphereRadIn = -0.01f;
			atmosphereRadOut = 0.3f;
			startSector = 6;
			alwaysUnlocked = true;
			landCloudColor = CryogenesisPalette.cryoBase; //TODO same
			defaultEnv = Env.terrestrial;
			defaultCore = CryogenesisBlocks.coreTerminal;
			allowLaunchToNumbered = false; //Re-enable once map is added

			unlockedOnLand.add(CryogenesisBlocks.coreTerminal);
			unlockedOnLand.add(Items.scrap);
		}};
	}
}