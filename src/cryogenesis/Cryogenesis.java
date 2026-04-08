package cryogenesis;

import mindustry.mod.Mod;
import mindustry.game.Team;
import arc.graphics.Color;

import cryogenesis.content.*;

public class Cryogenesis extends Mod {

    @Override
    public void loadContent() {

        overrideTeam();
        
        CryogenesisUnitCommand.load();
        CryogenesisItems.load();
        CryogenesisUnitTypes.load();
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