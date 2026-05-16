package cryogenesis;

import cryogenesis.content.*;
import cryogenesis.ai.*;
import cryogenesis.graphics.*;

import arc.Events;
import arc.util.*;
import arc.graphics.Color;
import arc.graphics.g2d.TextureAtlas.*;
import mindustry.ctype.*;
import mindustry.mod.*;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.ui.Fonts;

import mindustry.*;

public class Cryogenesis extends Mod {

    @Override
    public void loadContent() {

        overrideTeam();

        CryogenesisShaders.init();

        CryogenesisCacheLayer.load();

        CryogenesisUnitCommand.load();
        CryogenesisItems.load();
        CryogenesisUnitTypes.load();
        CryogenesisStatusEffects.load();
        CryogenesisLiquids.load();
        CryogenesisBlocks.load();
        CryogenesisPlanets.load();
        CryogenesisSectorPresets.load();
        CryogenesisInnelisTechTree.load();
    }

    void overrideTeam() {
        Team team = Team.green;

        team.name = "demise";

        Color color = Color.valueOf("f93faf");

        team.color.set(color);
        team.setPalette(color);
    }

    public Cryogenesis(){
        Events.on(EventType.ClientLoadEvent.class, event -> {
            Log.info("Beginning Cryogenesis emoji registration");
            ContentType[] types = {ContentType.liquid, ContentType.item, ContentType.block, ContentType.status, ContentType.unit};
            int startChar = 0xE000 + 1;

            for(var type : types){
                for(var cont : Vars.content.getBy(type)){
                    if(!cont.isVanilla() && cont instanceof UnlockableContent u && u.uiIcon.found()){
                        int id = startChar;

                        Fonts.registerIcon(u.name, u.uiIcon instanceof AtlasRegion atlas ? atlas.name : u.name, id, u.uiIcon);

                        startChar ++;
                        Log.info("Registered emoji for @: @", u.name, Integer.toHexString(id));
                    }
                }
            }
        });
    }
}