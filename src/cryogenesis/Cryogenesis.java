package cryogenesis;

import mindustry.mod.Mod;
import mindustry.game.Team;
import arc.graphics.Color;

public class Cryogenesis extends Mod {

    @Override
    public void loadContent() {
        overrideTeam();
    }

    void overrideTeam() {
        Team team = Team.green;

        team.name = "myteam";
        team.emoji = "⚙";

        Color color = Color.valueOf("ff00ff");

        team.color.set(color);
        team.setPalette(color);
    }
}