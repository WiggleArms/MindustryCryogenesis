package cryogenesis.content;

import cryogenesis.graphics.CryogenesisPalette;
import cryogenesis.content.CryogenesisItems;
import cryogenesis.type.unit.*;
import cryogenesis.type.weapons.*;
import cryogenesis.ai.*;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.ai.*;
import mindustry.ai.types.*;
//import mindustry.annotations.Annotations.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

public class CryogenesisUnitTypes{

	public static UnitType 
    
    //core
    meso,

    //hovering
    vice, lathe;

    public static TankUnitType

    //rolling
    eluma, schizi;

	public static void load(){

		vice = new UnitType("vice"){
        
            @Override
            public void init(){
                super.init();

                commands.add(CryogenesisUnitCommand.scavengeCommand);
            }

            {
            constructor = PayloadUnit::create;
            defaultCommand = CryogenesisUnitCommand.scavengeCommand;
            isEnemy = false;

            lowAltitude = true;
            flying = true;
            drag = 0.04f;
            speed = 2f;
            rotateSpeed = 8f;
            accel = 0.08f;
            itemCapacity = 20;
            health = 150f;
            engineSize = 1.5f;
            engineOffset = 4f;
            wreckSoundVolume = deathSoundVolume = 0.6f;
            payloadCapacity = 0.25f * 0.25f * tilesize * tilesize;
            outlineColor = CryogenesisPalette.ironOutline;

            /*
            This might be annoying
            loopSound = loopThoriumReactor;
            loopSoundVolume = 0.1;
            loopSoundPitch = 2;
            */
        }};

		lathe = new UnitType("lathe"){
        
            @Override
            public void init(){
                super.init();

                commands.add(CryogenesisUnitCommand.scavengeCommand);
            }

            {
            constructor = PayloadUnit::create;
            defaultCommand = CryogenesisUnitCommand.scavengeCommand;
            isEnemy = false;

            lowAltitude = true;
            flying = true;
            drag = 0.06f;
            speed = 2.5f;
            rotateSpeed = 12f;
            accel = 0.12f;
            itemCapacity = 40;
            health = 500f;
            engineOffset = 6f;
            wreckSoundVolume = deathSoundVolume = 0.9f;
            payloadCapacity = 0.5f * 0.5f * tilesize * tilesize;
            outlineColor = CryogenesisPalette.ironOutline;
        }};

        eluma = new TankUnitType("eluma"){{
            constructor = TankUnit::create;
            hitSize = 8f;
            treadPullOffset = 6;
            speed = 1f;
            rotateSpeed = 6f;
            health = 125;
            armor = 1f;
            itemCapacity = 0;
            floorMultiplier = 0.95f;
            treadRects = new Rect[]{new Rect(-7, -16, 7, 32)};

            tankMoveVolume *= 0.32f;
            tankMoveSound = Sounds.tankMoveSmall;
            outlineColor = CryogenesisPalette.ironOutline;

            weapons.add(new Weapon("cryogenesis-eluma-weapon"){{
                ejectEffect = Fx.casing1;
                outlineColor = CryogenesisPalette.ironOutline;
                reload = 25f;
                shootY = 4.25f;
                recoil = 1f;
                mirror = false;
                rotate = true;
                x = 0f;
                y = -0.75f;
                //heatColor = Color.valueOf("f9350f");
                //cooldownTime = 30f;

                bullet = new BasicBulletType(2.5f, 16){{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                }};
            }});

            weapons.add(new Weapon("cryogenesis-spawn-scrap"){{
                x = 0f;
                shootY = 0f;
                shootOnDeath = true;
                reload = 60f;
                shootCone = 180f;
                mirror = false;
                controllable = false;
                aiControllable = false;
                useAttackRange = false;
                display = false;

                bullet = new BulletType(){{
                    spawnUnit = new ScrapUnitType("eluma-scrap"){{
                        scrapValue = with(Items.scrap, 15, CryogenesisItems.nickel, 1);
                        scrapTime = 180f;
                        hitSize = 1f;
                    }};
                }};
            }});
        }};

        schizi = new TankUnitType("schizi"){{
            constructor = TankUnit::create;
            hitSize = 12f;
            treadPullOffset = 0;
            speed = 0.8f;
            rotateSpeed = 4f;
            //rotateMoveFirst = true;
            //omniMovement = true;
            //strafePenalty = -1f;
            health = 500;
            armor = 3f;
            itemCapacity = 0;
            floorMultiplier = 0.95f;
            treadRects = new Rect[]{new Rect(-8, -24, 8, 48)};

            tankMoveVolume *= 0.32f;
            tankMoveSound = Sounds.tankMoveSmall;
            outlineColor = CryogenesisPalette.ironOutline;

            weapons.add(new Weapon("cryogenesis-schizi-weapon"){{
                ejectEffect = Fx.casing1;
                outlineColor = CryogenesisPalette.ironOutline;
                top = false;
                reload = 50f;
                shootY = 4.5f;
                recoil = 1f;
                alternate = false;
                x = 6f;
                y = -0.75f;
                //heatColor = Color.valueOf("f9350f");
                //cooldownTime = 30f;

                bullet = new BasicBulletType(4f, 40){{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                }};
            }});

            weapons.add(new Weapon("cryogenesis-spawn-scrap"){{
                x = 0f;
                shootY = 0f;
                shootOnDeath = true;
                reload = 60f;
                shootCone = 180f;
                mirror = false;
                controllable = false;
                aiControllable = false;
                useAttackRange = false;
                display = false;

                bullet = new BulletType(){{
                    spawnUnit = new ScrapUnitType("schizi-scrap"){{
                        scrapValue = with(Items.scrap, 15, CryogenesisItems.nickel, 1);
                        scrapTime = 180f;
                        hitSize = 2f;
                    }};
                }};
            }});
        }};
        
		meso = new UnitType("meso"){
        
            @Override
            public void init(){
                super.init();

                commands.add(CryogenesisUnitCommand.scavengeCommand);
            }

            {
            constructor = PayloadUnit::create;
			controller = u -> u.team.isAI() ? new BuilderAI(true, 400f) : new CommandAI();
            defaultCommand = CryogenesisUnitCommand.scavengeCommand;
            isEnemy = false;

            targetBuildingsMobile = false;
            lowAltitude = true;
            flying = true;
            mineSpeed = 2f;
            mineTier = 1;
            buildSpeed = 0.5f;
            drag = 0.05f;
            speed = 3f;
            rotateSpeed = 15f;
            accel = 0.1f;
            fogRadius = 0f;
            itemCapacity = 30;
            health = 150f;
            engineOffset = 6f;
            hitSize = 8f;
            alwaysUnlocked = true;
            wreckSoundVolume = 0.8f;
            deathSoundVolume = 0.7f;
            payloadCapacity = 0.25f * 0.25f * tilesize * tilesize;

            faceTarget = false;
            rotateToBuilding = false;
            drawBuildBeam = false;
            //drawMineBeam = false;
            outlineColor = CryogenesisPalette.ironOutline;
            //buildBeamOffset = 5.25f;
            mineBeamOffset = 6f;

            /*
            This might be annoying
            loopSound = loopThoriumReactor;
            loopSoundVolume = 0.1;
            loopSoundPitch = 2;
            */

            // laser weapon, better thematically but more difficult to use
            weapons.add(new MixedWeapon("cryogenesis-meso-weapon"){{
                reload = 15f;
                x = 0f;
                y = -1f;
                rotate = true;
                shootSound = Sounds.shootAlpha;
                outlineColor = CryogenesisPalette.ironOutline;
                mirror = false;
                inaccuracy = 1f;

                bullet = new LaserBulletType(16f){{
                    length = 150f;
                    width = 10f;
                    pierceCap = 1;
                    hitEffect = Fx.hitLaserBlast;
                    colors = new Color[]{Pal.yellowBoltFront.cpy().mul(1f, 1f, 1f, 0.4f), Pal.yellowBoltFront, Color.white};
                    //trailWidth = 0.8f;
                    //trailLength = 2;
                    shootEffect = Fx.shootSmallColor;
                    //smokeEffect = Fx.hitLaserColor;
                    //backColor = trailColor = Pal.yellowBoltFront;
                    //hitColor = Pal.yellowBoltFront;
                    //frontColor = Color.white;
                    //lightColor = Pal.yellowBoltFront;

                    lifetime = 10f;
                    buildingDamageMultiplier = 0.01f;
                    //homingPower = 0.02f;
                }};
            }});
            
            /*
            // lightning weapon, use this for a noob handicap
            weapons.add(new MixedWeapon("cryogenesis-meso-weapon"){{
                reload = 24f;
                x = 0f;
                y = -1f;
                rotate = true;
                shootSound = Sounds.shootAlpha;
                outlineColor = CryogenesisPalette.ironOutline;
                mirror = false;
                inaccuracy = 25f;

                shoot.shots = 2;

                bullet = new LightningBulletType(){{
                    lightningColor = hitColor = Pal.yellowBoltFront;
                    damage = 16f;

                    lightningLength = 15;
                    lightningLengthRand = 5;
                    shootEffect = Fx.shootSmallColor;

                    lightningType = new BulletType(0.0001f, 0f){{
                        lifetime = Fx.lightning.lifetime;
                        hitEffect = Fx.hitLancer;
                        despawnEffect = Fx.none;
                        hittable = false;
                    }};
                }};
            }});
            */
		}};
	}
}