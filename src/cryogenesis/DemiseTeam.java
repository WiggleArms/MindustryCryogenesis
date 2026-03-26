/**package yourmod;

import mindustry.mod.Mod;
import mindustry.game.Team;
import arc.graphics.Color;

public class YourMod extends Mod {

    public YourMod() {
        // Constructor runs when mod is loaded
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
}*/