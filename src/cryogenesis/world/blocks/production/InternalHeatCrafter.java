package cryogenesis.world.blocks.production;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.graphics.*;
import mindustry.gen.*;
import mindustry.world.blocks.production.*;

/** Generic crafter that uses heat visuals. */
public class InternalHeatCrafter extends GenericCrafter{
	public Color heatColor = new Color(1f, 0.22f, 0.22f, 0.8f);
	public float heatPulse = 0.3f, heatPulseScl = 10f;

	public TextureRegion heat, overlay;

	public InternalHeatCrafter(String name){
		super(name);
	}

	@Override
	public void load(){
		super.load();
		heat = Core.atlas.find(name + "-heat");
		overlay = Core.atlas.find(name + "-overlay");
	}

	public class InternalHeatCrafterBuild extends GenericCrafterBuild{

		@Override
		public void draw(){

			Draw.rect(region, x, y);

			if(warmup > 0){
				Draw.alpha(warmup);
				Draw.rect(overlay, x, y);
				Draw.z(Layer.blockAdditive);
				Draw.blend(Blending.additive);
				Draw.color(heatColor, warmup * (heatColor.a * (1f - heatPulse + Mathf.absin(heatPulseScl, heatPulse))));
				if(heat.found()) Draw.rect(heat, x, y);
				Draw.blend();
				Draw.color();
			}
		}
	}
}