package cryogenesis;

import mindustry.mod.Mod;
import mindustry.game.Team;
import arc.graphics.Color;

import cryogenesis.content.*;
import cryogenesis.ai.*;
import cryogenesis.graphics.*;

import mindustry.mod.*;

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
}