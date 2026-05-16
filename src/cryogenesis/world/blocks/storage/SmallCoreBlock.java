package cryogenesis.world.blocks.storage;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.actions.*;
import arc.scene.event.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.core.*;
import mindustry.ctype.*;
import mindustry.entities.*;
import mindustry.game.EventType.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.io.*;
import mindustry.logic.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.*;
import mindustry.world.modules.*;

import static mindustry.Vars.*;

//import more stuff

public class SmallCoreBlock extends CoreBlock{
	public SmallCoreBlock(String name){
		super(name);
	}

	public class SmallCoreBuild extends CoreBuild{
		@Override
		public void drawLanding(float x, float y){
            float fin = renderer.getLandTimeIn();
            float fout = 1f - fin;

            float scl = Scl.scl(4f) / renderer.getDisplayScale();
            float shake = 0f;
            float s = region.width * region.scl() * scl * 3.6f * Interp.pow2Out.apply(fout);
            float rotation = Interp.pow2In.apply(fout) * 135f;
            x += Mathf.range(shake);
            y += Mathf.range(shake);
            float thrustOpen = 0.25f;
            float thrusterFrame = fin >= thrustOpen ? 1f : fin / thrustOpen;
            float thrusterSize = Mathf.sample(thrusterSizes, fin);

            //when launching, thrusters stay out the entire time.
            if(renderer.isLaunching()){
                Interp i = Interp.pow2Out;
                thrusterFrame = i.apply(Mathf.clamp(fout*13f));
                thrusterSize = i.apply(Mathf.clamp(fout*9f));
            }

            Draw.color(Pal.lightTrail);
            //TODO spikier heat
            Draw.rect("circle-shadow", x, y, s, s);

            Draw.scl(scl);

            //draw thruster flame
            //changed size - 3 to size - 2.5 so it looks correct on size = 2 blocks
            float strength = (1f + (size - 2.5f)/2.5f) * scl * thrusterSize * (0.95f + Mathf.absin(2f, 0.1f));
            float offset = (size - 2.5f) * 3f * scl;

            for(int i = 0; i < 4; i++){
                Tmp.v1.trns(i * 90 + rotation, 1f);

                Tmp.v1.setLength((size * tilesize/2f + 1f)*scl + strength*2f + offset);
                Draw.color(team.color);
                Fill.circle(Tmp.v1.x + x, Tmp.v1.y + y, 6f * strength);

                Tmp.v1.setLength((size * tilesize/2f + 1f)*scl + strength*0.5f + offset);
                Draw.color(Color.white);
                Fill.circle(Tmp.v1.x + x, Tmp.v1.y + y, 3.5f * strength);
            }

            drawLandingThrusters(x, y, rotation, thrusterFrame);

            Drawf.spinSprite(region, x, y, rotation);

            Draw.alpha(Interp.pow4In.apply(thrusterFrame));
            drawLandingThrusters(x, y, rotation, thrusterFrame);
            Draw.alpha(1f);

            if(teamRegions[team.id] == teamRegion) Draw.color(team.color);

            Drawf.spinSprite(teamRegions[team.id], x, y, rotation);

            Draw.color();
            Draw.scl();
            Draw.reset();
		}
	}
}