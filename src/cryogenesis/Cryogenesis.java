package cryogenesis;

import mindustry.mod.Mod;
import mindustry.game.Team;
import arc.graphics.Color;

import cryogenesis.content.*;
import cryogenesis.ai.*;
import cryogenesis.graphics.CryogenesisCacheLayer;

public class Cryogenesis extends Mod {
    public static Mods.LoadedMod MOD;

    @Override
    public void loadContent() {

        MOD = mods.getMod(getClass());

        overrideTeam();

        CryogenesisCacheLayer.load();
        CryogenesisUnitCommand.load();
        CryogenesisItems.load();
        CryogenesisUnitTypes.load();
        CryogenesisStatusEffects.load();
        CryogenesisBlocks.load();
        CryogenesisLiquids.load();
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