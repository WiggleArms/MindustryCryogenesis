package cryogenesis.maps.planet;

import cryogenesis.graphics.CryogenesisPalette;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import arc.util.noise.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.maps.generators.*;
import mindustry.type.*;
import mindustry.world.*;

import static mindustry.Vars.*;

public class InnelisPlanetGenerator extends PlanetGenerator{
	Color c1 = new Color().set(CryogenesisPalette.cryoBase).lerp(Color.black, 0.5f), c2 = new Color().set(CryogenesisPalette.cryoBase).lerp(Color.black, 0.75f);

	@Override
	public float getHeight(Vec3 position){
		return 0;
	}

	@Override
	public void getColor(Vec3 position, Color out){
        float depth = Simplex.noise3d(seed, 2, 0.56, 1.7f, position.x, position.y, position.z) / 2f;
        out.set(c1).lerp(c2, Mathf.clamp(Mathf.round(depth, 0.15f))).a(1f - 0.2f).toFloatBits();
	}
}