package cryogenesis.content;

import cryogenesis.content.*;
import cryogenesis.world.blocks.pipes.*;
import cryogenesis.world.blocks.environment.*;
import cryogenesis.world.blocks.production.*;
import cryogenesis.world.blocks.payloads.*;
import cryogenesis.world.blocks.storage.*;
import cryogenesis.graphics.*;
//import cryogenesis.world.meta.*;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
//import mindustry.*;
import mindustry.content.*;
import mindustry.ctype.UnlockableContent;
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
	

	//environment

	//liquids
	shallowCryofluid, deepSluice, shallowSluice, sulfurSluice,
	
	//floors
	cryoIce, rock, darkIce, cryoSnow, duneSand, duneStone, sulfur, travertine,

	//walls
	cryoIceWall, rockWall, darkIceWall, cryoSnowWall, duneSandWall, duneStoneWall, sulfurWall, travertineWall,

	//props
	cryoSnowBoulder, rockBoulder,

	//ores
	oreNickel,


	//artificial

	//crafting
	nickelCompactor,

	//walls
	nickelWall, nickelWallLarge,

	//defense
	
	//transport
	pipe, /*armored-pipe,*/ tunnelPipe, pipeRouter, pipeUnloader,

	//liquid

	//power

	//production
	mechanicalAuger,
	
	//storage
	coreTerminal,
	
	//turrets
	pelt,

	//units

	//payloads
	scrapper

	//logic

	//campaign

	;

	public static void load(){

		int wallHealthMultiplier = 4;

		/*
		testWall = new Wall("test-wall"){{
			requirements(Category.defense, with());
			health = 80 * wallHealthMultiplier;
		}};
		*/

		shallowCryofluid = new Floor("shallow-cryofluid"){{
            supportsOverlay = true;
            overlayAlpha = 0.35f;
            status = StatusEffects.freezing;
            statusDuration = 240f;
            speedMultiplier = 0.7f;
            variants = 0;
            liquidDrop = Liquids.cryofluid;
            liquidMultiplier = 0.4f;
            isLiquid = true;
            cacheLayer = CacheLayer.cryofluid;

            emitLight = true;
            lightRadius = 25f;
            lightColor = Color.cyan.cpy().a(0.19f);
            obstructsLight = true;
            forceDrawLight = true;
		}};

		deepSluice = new EffectFloor("deep-sluice"){{
            supportsOverlay = true;
			drownTime = 240f;
            status = CryogenesisStatusEffects.boiling;
            statusDuration = 240f;
            speedMultiplier = 0.3f;
            variants = 0;
            liquidDrop = CryogenesisLiquids.sluice;
            liquidMultiplier = 1.5f;
            isLiquid = true;
            cacheLayer = CryogenesisCacheLayer.sluice;

            emitLight = true;
            lightRadius = 25f;
            lightColor = Color.blue.cpy().a(0.19f);
            obstructsLight = true;
            forceDrawLight = true;
		}};

		shallowSluice = new EffectFloor("shallow-sluice"){{
            supportsOverlay = true;
            status = CryogenesisStatusEffects.boiling;
            statusDuration = 200f;
            speedMultiplier = 0.7f;
            liquidDrop = CryogenesisLiquids.sluice;
            isLiquid = true;
            cacheLayer = CryogenesisCacheLayer.sluice;

            emitLight = true;
            lightRadius = 25f;
            lightColor = Color.blue.cpy().a(0.19f);
            obstructsLight = true;
            forceDrawLight = true;
		}};

		cryoIce = new Floor("cryo-ice"){{
			dragMultiplier = 0.2f;
			speedMultiplier = 0.9f;
			albedo = 0.65f;
		}};

		cryoSnow = new Floor("cryo-snow"){{
			dragMultiplier = 0.2f;
			speedMultiplier = 0.9f;
			albedo = 0.7f;
		}};

		duneSand = new Floor("dune-sand");

		duneStone = new Floor("dune-stone");

		rock = new Floor("rock");

		darkIce = new Floor("dark-ice");

		sulfur = new Floor("sulfur");

		sulfurSluice = new ShallowEffectLiquid("sulfur-sluice"){{
			/*
			liquidBase = shallowsluice.asFloor();
			floorBase = sulfur.asFloor();
			isLiquid = true;
			variants = floorBase.variants;
			status = liquidBase.status;
			liquidDrop = liquidBase.liquidDrop;
			cacheLayer = liquidBase.cacheLayer;
			shallow = true;
			*/

			mapColor = Color.valueOf("86bbac");

			statusDuration = 160f;
			speedMultiplier = 0.85f;
			emitLight = true;
			lightRadius = 25f;
			lightColor = Color.blue.cpy().a(0.19f);
			obstructsLight = true;
			forceDrawLight = true;
		}};

		travertine = new Floor("travertine");

		cryoIceWall = new StaticWall("cryo-ice-wall"){{
			cryoIce.asFloor().wall = this;
			albedo = 0.6f;
		}};

		cryoSnowWall = new StaticWall("cryo-snow-wall");

		duneSandWall = new StaticWall("dune-sand-wall");

		duneStoneWall = new StaticWall("dune-stone-wall");

		rockWall = new StaticWall("rock-wall");

		darkIceWall = new StaticWall("dark-ice-wall"){{
			variants = 3;
		}};

		sulfurWall = new StaticWall("sulfur-wall");

		travertineWall = new StaticWall("travertine-wall");

		((ShallowEffectLiquid)sulfurSluice).set(CryogenesisBlocks.shallowSluice, CryogenesisBlocks.sulfur);

		cryoSnowBoulder = new Prop("cryo-snow-boulder"){{
			variants = 2;
			cryoSnow.asFloor().decoration = this;
		}};

		rockBoulder = new Prop("rock-boulder"){{
			variants = 2;
			rock.asFloor().decoration = this;
		}};

		oreNickel = new OreBlock("ore-nickel", CryogenesisItems.nickel);

		nickelCompactor = new GenericCrafter("nickel-compactor"){
		
		@Override
		public void getDependencies(Cons<UnlockableContent> cons){
			for(ItemStack stack : requirements) cons.get(stack.item);
		}
		
		
		{
			requirements(Category.crafting, with(CryogenesisItems.nickel, 10));

			craftEffect = Fx.pulverizeMedium;
			outputItem = new ItemStack(CryogenesisItems.nickel, 1);
			craftTime = 50f;
			size = 2;
			hasItems = true;

			consumeItem(Items.scrap, 2);

			researchCost = with(CryogenesisItems.nickel, 4);
		}};

		nickelWall = new Wall("nickel-wall"){{
			requirements(Category.defense, with(CryogenesisItems.nickel, 6));
			health = 160 * wallHealthMultiplier;

			researchCost = with(CryogenesisItems.nickel, 3);
		}};

		nickelWallLarge = new Wall("nickel-wall-large"){{
			requirements(Category.defense, ItemStack.mult(nickelWall.requirements, 4));
			health = 160 * 4 * wallHealthMultiplier;
			size = 2;

			researchCost = with(CryogenesisItems.nickel, 12);
		}};

		pipe = new Pipe("pipe"){{
			requirements(Category.distribution, with(CryogenesisItems.nickel, 1));
			health = 100;
			speed = 20f;

			researchCost = with(CryogenesisItems.nickel, 3);
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
			health = 80;
			speed = 74f;
			bufferCapacity = 14;
			solid = false;

			researchCost = with(CryogenesisItems.nickel, 20);
		}};

		pipeRouter = new PipeRouter("pipe-router"){{
			requirements(Category.distribution, with(CryogenesisItems.nickel, 3));
			health = 80;
			liquidCapacity = 60f;
			underBullets = true;
			solid = false;
			researchCost = with(CryogenesisItems.nickel, 10);
		}};

		pipeUnloader = new Unloader("pipe-unloader"){{
			requirements(Category.distribution, with(CryogenesisItems.nickel, 5));
			health = 160;
			speed = 60f / 15f; //Second value should always be equal to max liquid-boosted Pipe throughput
			group = BlockGroup.transportation;

			researchCost = with(CryogenesisItems.nickel, 10);
		}};

		mechanicalAuger = new Auger("mechanical-auger"){{
			requirements(Category.production, with(CryogenesisItems.nickel, 14));
			health = 360;
			tier = 2;
			drillTime = 600f;
			size = 2;
			rotateSpeed = -1.5f;
			liquidBoostIntensity = 1.5f;
			researchCost = with(CryogenesisItems.nickel, 12);

			consumeLiquid(CryogenesisLiquids.steam, 2f / 60f).boost();
		}};

		coreTerminal = new SmallCoreBlock("core-terminal"){{
			requirements(Category.effect, with(CryogenesisItems.nickel, 100));
			alwaysUnlocked = true;

			isFirstTier = true;
			unitType = CryogenesisUnitTypes.meso;
			health = 1000;
			itemCapacity = 1000;
			size = 2;

			thrusterLength = 10f/4f;

			unitCapModifier = 4;
		}};

		pelt = new ItemTurret("pelt"){{
			requirements(Category.turret, with(CryogenesisItems.nickel, 40));
			
			ammo(
				 CryogenesisItems.nickel,  new BasicBulletType(2.5f, 24){{
					width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    ammoMultiplier = 6;

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
			maxAmmo = 40;
            shootSound = Sounds.shootStell;
            recoil = 2f;
            shootY = 7f;
            reload = 12f;
            range = 160;
            shootCone = 15f;
            ammoUseEffect = Fx.casing1;
            health = 2200;
            inaccuracy = 3f;
            rotateSpeed = 10f;
			researchCost = with(CryogenesisItems.nickel, 10);
            //researchCostMultiplier = 0.05f;
            //coolant = consume(new ConsumeLiquid(Liquids.cryofluid, 15f / 60f));
            depositCooldown = 2.0f;
		}};

		scrapper = new PayloadScrapper("scrapper"){{
			requirements(Category.units, with(CryogenesisItems.nickel, 18));

			onlyOutputDefault = true;

			squareSprite = false;
			regionSuffix = "-dark";
			itemCapacity = 25;
			size = 3;
			deconstructSpeed = 1f;
			hasPower = false;
			//flags = EnumSet.of(CryogenesisBlockFlag.unitScrapper);

			researchCost = with(CryogenesisItems.nickel, 7);
		}};
	}
}