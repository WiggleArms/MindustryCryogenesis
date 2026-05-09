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

	public static class CryogenesisSurfaceShader extends Shader{
		Texture noiseTex;

		public CryogenesisSurfaceShader(String frag){
			super(
				tree.get("shaders/screenspace.vert"),
				mods.getMod("cryogenesis").root.child("shaders").child(frag + ".frag")
			);
			loadNoise();
		}

        public String textureName(){
            return "noise";
        }

        public void loadNoise(){
            Core.assets.load("sprites/" + textureName() + ".png", Texture.class).loaded = t -> {
                t.setFilter(TextureFilter.linear);
                t.setWrap(TextureWrap.repeat);
            };
        }

        @Override
        public void apply(){
            setUniformf("u_campos", Core.camera.position.x - Core.camera.width / 2, Core.camera.position.y - Core.camera.height / 2);
            setUniformf("u_resolution", Core.camera.width, Core.camera.height);
            setUniformf("u_time", Time.time);

            if(hasUniform("u_noise")){
                if(noiseTex == null){
                    noiseTex = Core.assets.get("sprites/" + textureName() + ".png", Texture.class);
                }

                noiseTex.bind(1);
                renderer.effectBuffer.getTexture().bind(0);

                setUniformi("u_noise", 1);
            }
        }
	}
}