package cryogenesis.world.blocks.environment;

import arc.graphics.*;
import arc.util.*;
import arc.math.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.graphics.*;
import mindustry.world.blocks.environment.*;

import static mindustry.Vars.*;

public class EffectFloor extends Floor{
	public Effect effect = Fx.ventSteam;
	public Color effectColor = Color.valueOf("b5dcfb");
	public float effectSpacing = 600f;

	public EffectFloor(String name){
		super(name);
	}

	@Override
	public boolean updateRender(Tile tile){
        return true;
    }

	@Override
	public void renderUpdate(UpdateRenderState state){
		if ((state.data += Time.delta) >= effectSpacing){
			effect.at(state.tile.x * tilesize, state.tile.y * tilesize, effectColor);
			state.data = 0f;
		}
	}
}