package cryogenesis.world.blocks.environment;

import arc.graphics.*;
import arc.util.*;
import arc.math.*;
import mindustry.graphics.*;
import mindustry.world.blocks.environment;

public class ShallowEffectLiquid extends ShallowLiquid{
	public Effect effect = Fx.ventSteam;
	public Color effectColor = Color.valueOf("b5dcfb");
	public float effectSpacing = 600f;

	public ShallowEffectLiquid(String name){
		super(name);
	}

	@Override
	public void renderUpdate(UpdateRenderState state){
		if (state.data += Time.delta) >= effectSpacing){
			effect.at(state.tile.x * tilesize - tilesize, state.tile.y * tilesize - tilesize, effectColor);
			state.data = 0f;
		}
	}
}