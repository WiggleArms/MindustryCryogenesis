package cryogenesis.graphics;

import cryogenesis.Cryogenesis;

import arc.*;
import arc.assets.loaders.TextureLoader.*;
import arc.files.*;
import arc.graphics.*;
import arc.graphics.Texture.*;
import arc.graphics.g2d.*;
import arc.graphics.g3d.*;
import arc.graphics.gl.*;
import arc.math.geom.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.type.*;
import mindustry.graphics.Shaders;

import static mindustry.Vars.*;

public class CryogenesisShaders{
	public static CryogenesisSurfaceShader soda;

	public static void init(){
        soda = new CryogenesisSurfaceShader("soda");
	}

	public static class CryogenesisSurfaceShader extends Shaders.SurfaceShader{
		public CryogenesisSurfaceShader(String frag){
			super(
				mods.getMod("cryogenesis").root.child("shaders").child(frag + ".frag")
			);
		}
	}
}