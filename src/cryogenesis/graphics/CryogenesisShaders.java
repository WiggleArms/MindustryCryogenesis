package cryogenesis.graphics;

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
	public static Shaders.SurfaceShader soda;

	public static void init(){
        soda = new Shaders.SurfaceShader("soda");
	}

	public static class CryogenesisSurfaceShader extends Shader{
		public CryogenesisSurfaceShader(String frag){
			super(
				getShaderFi("screenspace.vert"),
				Vars.mods.getMod("cryogenesis").root.child("shaders").child(frag + ".frag"))
			);
		}
	}
}