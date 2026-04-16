package cryogenesis.content;

import cryogenesis.content.*;
import cryogenesis.world.blocks.pipes.*;
import cryogenesis.world.graphics.*;
//import cryogenesis.world.meta.*;

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
	//testWall, 	

	//environment

	//walls
	cryoIceWall, magmaticStoneWall,

	//floors
	cryoIce, magmaticStone,

	//ores
	oreNickel,

	//crafting
	nickelCompactor,

	//walls
	nickelWall, nickelWallLarge,
	
	//transport
	pipe, /*armored-pipe,*/ tunnelPipe, pipeUnloader,
	
	//storage
	coreThread,
	
	//turrets
	pelt,

	//payloads
	payloadScrapper;

	public static void load(){

		int wallHealthMultiplier = 4;

		/*
		testWall = new Wall("test-wall"){{
			requirements(Category.defense, with());
			health = 80 * wallHealthMultiplier;
		}};
		*/

		cryoIce = new Floor("cryo-ice"){{
			dragMultiplier = 0.2f;
			speedMultiplier = 0.9f;
			albedo = 0.65f;
		}};

		cryoIceWall = new StaticWall("cryo-ice-wall"){{
			cryoIce.asFloor().wall = this;
			albedo = 0.6f;
		}};

		magmaticStone = new Floor("magmatic-stone");

		magmaticStoneWall = new StaticWall("magmatic-stone-wall"){{
			variants = 3;
		}};

		oreNickel = new OreBlock("ore-nickel", CryogenesisItems.nickel);

		nickelCompactor = new GenericCrafter("nickel-compactor"){{
			requirements(Category.crafting, with(Items.scrap, 30));

			craftEffect = Fx.pulverizeMedium;
			outputItem = new ItemStack(CryogenesisItems.nickel, 1);
			craftTime = 50f;
			size = 2;
			hasItems = true;

			consumeItem(Items.scrap, 2);

			researchCost = with(Items.scrap, 50);
		}};

		nickelWall = new Wall("nickel-wall"){{
			requirements(Category.defense, with(CryogenesisItems.nickel, 6));
			health = 80 * wallHealthMultiplier;

			researchCostMultiplier = 0.1f;
		}};

		nickelWallLarge = new Wall("nickel-wall-large"){{
			requirements(Category.defense, ItemStack.mult(nickelWall.requirements, 4));
			health = 80 * 4 * wallHealthMultiplier;
			size = 2;
		}};

		pipe = new Pipe("pipe"){{
			requirements(Category.distribution, with(CryogenesisItems.nickel, 1));
			health = 75;
			speed = 20f;

			researchCost = with(CryogenesisItems.nickel, 5);
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
			requirements(Category.distribution, with(CryogenesisItems.nickel, 5));
			health = 100;
			speed = 60f / 15f; //Second value should always be equal to max liquid-boosted Pipe throughput
			group = BlockGroup.transportation;

			researchCost = with(CryogenesisItems.nickel, 10);
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
				 CryogenesisItems.nickel,  new BasicBulletType(2.5f, 12){{
					width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    ammoMultiplier = 3;

                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    hitColor = backColor = trailColor = Pal.copperAmmoBack;
                    frontColor = Pal.copperAmmoFront;
				 }}
			);

			drawer = new DrawTurret("insulated-")/*{{
				parts.add(new RegionPart("-mid"){{
                    progress = PartProgress.recoil;
                    under = false;
                    moveY = -1.25f;
                }});
            }}*/;

			outlineColor = CryogenesisPalette.ironOutline;
			squareSprite = false;
			size = 2;

            shootSound = Sounds.shootStell;
            recoil = 2f;
            shootY = 7f;
            reload = 12f;
            range = 160;
            shootCone = 15f;
            ammoUseEffect = Fx.casing1;
            health = 1100;
            inaccuracy = 3f;
            rotateSpeed = 10f;
            researchCostMultiplier = 0.05f;
            //coolant = consume(new ConsumeLiquid(Liquids.cryofluid, 15f / 60f));
            depositCooldown = 2.0f;
		}};

		payloadScrapper = new PayloadDeconstructor("scrapper"){{
			requirements(Category.units, with(Items.scrap, 30));

			squareSprite = false;
			regionSuffix = "-dark";
			itemCapacity = 25;
			size = 3;
			deconstructSpeed = 1f;
			hasPower = false;
			//flags = EnumSet.of(CryogenesisBlockFlag.unitScrapper);
		}};
	}
}