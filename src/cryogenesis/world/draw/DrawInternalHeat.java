package cryogenesis.world.draw;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.draw.*;
import mindustry.world.blocks.production.GenericCrafter.*;

public class DrawInternalHeat extends DrawBlock{
	public Color heatColor = new Color(1f, 0.22f, 0.22f, 0.8f);
	public float heatPulse = 0.3f, heatPulseScl = 10f;
	public float layer = Layer.blockAdditive;

	public TextureRegion heat, overlay;
	public String heatSuffix = "-heat", overlaySuffix = "-overlay";

	public DrawInternalHeat(float layer){
		this.layer = layer;
	}

	public DrawInternalHeat(String heatSuffix){
		this.heatSuffix = heatSuffix;
	}

	public DrawInternalHeat(String heatSuffix, String overlaySuffix){
		this.heatSuffix = heatSuffix;
		this.overlaySuffix = overlaySuffix;
	}

	public DrawInternalHeat(){
	}

	@Override
	public void draw(Building build){
		Draw.z(Layer.blockAdditive);

		if(build instanceof GenericCrafterBuild gc && gc.warmup > 0){

			float z = Draw.z();
			if(layer > 0) Draw.z(layer);
			Draw.alpha(gc.warmup);
			if(overlay.found()) Draw.rect(overlay, build.x, build.y);
			Draw.blend(Blending.additive);
			Draw.color(heatColor, gc.warmup * (heatColor.a * (1f - heatPulse + Mathf.absin(heatPulseScl, heatPulse))));
			Draw.rect(heat, build.x, build.y);
			Draw.blend();
			Draw.color();
		}
	}

	@Override
	public void load(Block block){
		heat = Core.atlas.find(block.name + heatSuffix);
		overlay = Core.atlas.find(block.name + overlaySuffix);
	}
}