package cryogenesis.content;

import cryogenesis.world.graphics.CryogenesisPalette;

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

public class CryogenesisUnitTypes{

	//core
	public static UnitType meso;

	public static void load(){

		meso = new UnitType("meso"){{
            constructor = UnitEntity::create;
			controller = u -> u.team.isAI() ? new BuilderAI(true, 400f) : new CommandAI();
            isEnemy = false;

            targetBuildingsMobile = false;
            lowAltitude = true;
            flying = true;
            mineSpeed = 6.5f;
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

            faceTarget = false;
            outlineColor = CryogenesisPalette.ironOutline;
            buildBeamOffset = 5.25f;

            /*
            This might be annoying
            loopSound = loopThoriumReactor;
            loopSoundVolume = 0.1;
            loopSoundPitch = 2;
            */

            weapons.add(new Weapon("cryogenesis-meso-weapon"){{
                reload = 17f;
                x = 4.5f;
                y = -0.375f;
                rotate = true;
                shootSound = Sounds.shootAlpha;
                outlineColor = CryogenesisPalette.ironOutline;

                bullet = new LaserBoltBulletType(2.5f, 11){{
                    keepVelocity = false;
                    width = 1.5f;
                    height = 4.5f;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    trailWidth = 1.2f;
                    trailLength = 3;
                    shootEffect = Fx.shootSmallColor;
                    smokeEffect = Fx.hitLaserColor;
                    backColor = trailColor = Pal.yellowBoltFront;
                    hitColor = Pal.yellowBoltFront;
                    frontColor = Color.white;
                    lightColor = Pal.yellowBoltFront;

                    lifetime = 60f;
                    buildingDamageMultiplier = 0.01f;
                    homingPower = 0.02f;
                }};
            }});
		}};
	}
}