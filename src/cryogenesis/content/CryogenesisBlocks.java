package cryogenesis.content;

import cryogenesis.content.*;
import cryogenesis.world.blocks.pipes.*;

import arc.*;
import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
//import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

public class CryogenesisBlocks{

	public static Block 
	
	//test
	testWall, 	

	//crafting
	nickelCompactor,

	//walls
	nickelWall,
	
	//transport
	pipe, /*armored-pipe,*/ tunnelPipe, pipeUnloader,
	
	//storage
	coreThread,
	
	//turrets
	pelt;

	public static void load(){

		int wallHealthMultiplier = 4;

		testWall = new Wall("test-wall"){{
			requirements(Category.defense, with());
			health = 80 * wallHealthMultiplier;
		}};

		nickelCompactor = new GenericCrafter("nickel-compactor"){{
			requirements(Category.crafting, with(Items.scrap, 30));

			craftEffect = Fx.pulverizeMedium;
			outputItem = new ItemStack(CryogenesisItems.nickel, 1);
			craftTime = 50f;
			size = 2;
			hasItems = true;

			consumeItem(Items.scrap, 2);
		}};

		nickelWall = new Wall("nickel-wall"){{
			requirements(Category.defense, with(CryogenesisItems.nickel, 6));

			health = 80 * wallHealthMultiplier;
		}};

		pipe = new Pipe("pipe"){{
			requirements(Category.distribution, with(CryogenesisItems.nickel, 1));
			health = 75;
			speed = 20f;
		}};

		/*
		pipe = new Pipe("armored-pipe"){{
			requirements(Category.distribution, with(Items.scrap, 1));
			health = 75;
			speed = 30f;
			armored = true;
		}};
		*/

		tunnelPipe = new TunnelPipe("tunnel-pipe"){{
			requirements(Category.distribution, with(CryogenesisItems.nickel, 8));
			range = 4;
			health = 100;
			speed = 74f;
			bufferCapacity = 14;
			solid = false;
		}};

		pipeUnloader = new Unloader("pipe-unloader"){{
			requirements(Category.distribution, with(CryogenesisItems.nickel, 15));
			health = 100;
			speed = 60f / 15f; //Second value should always be equal to max liquid-boosted Pipe throughput
			group = BlockGroup.transportation;
		}};

		coreThread = new CoreBlock("core-thread"){{
			requirements(Category.effect, with(CryogenesisItems.nickel, 1000, Items.silicon, 500));
			alwaysUnlocked = true;

			isFirstTier = true;
			unitType = CryogenesisUnitTypes.meso;
			health = 500;
			itemCapacity = 1000;
			size = 2;

			unitCapModifier = 4;
		}};

		pelt = new ItemTurret("pelt"){{
			requirements(Category.turret, with(CryogenesisItems.nickel, 50));
			
			ammo(
				 CryogenesisItems.nickel,  new BasicBulletType(2.5f, 24){{
					width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    ammoMultiplier = 3;

                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    hitColor = backColor = trailColor = Pal.copperAmmoBack;
                    frontColor = Pal.copperAmmoFront;
				 }}
			);

			drawer = new DrawTurret("insulated-");
			size = 2;

            shootSound = Sounds.shootStell;
            recoil = 2f;
            shootY = 7f;
            reload = 35f;
            range = 160;
            shootCone = 15f;
            ammoUseEffect = Fx.casing1;
            health = 250;
            inaccuracy = 2f;
            rotateSpeed = 10f;
            coolant = consumeCoolant(0.1f);
            coolantMultiplier = 10f;
            researchCostMultiplier = 0.05f;
            depositCooldown = 2.0f;
		}};
	}
}