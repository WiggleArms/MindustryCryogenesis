package cryogenesis.type.weapons;

import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.entities.bullet.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.type.*;

/** Turret that can both shoot and build. */
public class MixedWeapon extends Weapon{

    public MixedWeapon(){
        super();
    }

    public MixedWeapon(String name){
        super(name);

        rotate = true;
    }

    @Override
    public void update(Unit unit, WeaponMount mount){

        //always aim at build plan
        if(unit.activelyBuilding()){
            mount.shoot = false;
            mount.rotate = true;

            mount.aimX = unit.buildPlan().drawx();
            mount.aimY = unit.buildPlan().drawy();
        }

        super.update(unit, mount);
    }

    @Override
    public void draw(Unit unit, WeaponMount mount){
        super.draw(unit, mount);

        if(unit.activelyBuilding()){
            float
            z = Draw.z(),
            rotation = unit.rotation - 90,
            weaponRotation  = rotation + (rotate ? mount.rotation : 0),
            wx = unit.x + Angles.trnsx(rotation, x, y) + Angles.trnsx(weaponRotation, 0, -mount.recoil),
            wy = unit.y + Angles.trnsy(rotation, x, y) + Angles.trnsy(weaponRotation, 0, -mount.recoil),
            px = wx + Angles.trnsx(weaponRotation, this.shootX, this.shootY),
            py = wy + Angles.trnsy(weaponRotation, this.shootX, this.shootY);

            unit.drawBuildingBeam(px, py);
            Draw.z(z);
        }
    }
}