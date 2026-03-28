package cryogenesis.content;

import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
import mindustry.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

public class CryogenesisBlocks{

	public static Block testWall, coreThread;

	public static void load(){

		int wallHealthMultiplier = 4;

		testWall = new Wall("test-wall"){{
			requirements(Category.defense, with());
			health = 80 * wallHealthMultiplier;
		}};

		coreThread = new CoreBlock("core-thread"){{
			requirements(Category.effect, with(/*Items.silicon, 500*/));
			alwaysUnlocked = true;

			acceptsItems = true;
			isFirstTier = true;
			//unitType = UnitTypes.alpha;
			health = 500;
			itemCapacity = 1000;
			size = 2;

			unitCapModifier = 4;
		}};
	}
}
