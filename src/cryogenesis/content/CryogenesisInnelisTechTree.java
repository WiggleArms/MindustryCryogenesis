package cryogenesis.content;

import arc.struct.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;
import mindustry.content.Items;

import static mindustry.content.TechTree.*;

import static cryogenesis.content.CryogenesisBlocks.*;
import static cryogenesis.content.CryogenesisSectorPresets.*;
import static cryogenesis.content.CryogenesisUnitTypes.*;

public class CryogenesisInnelisTechTree{

	public static void load(){
		CryogenesisPlanets.innelis.techTree = nodeRoot("innelis", coreThread, () -> {

			node(pipeUnloader, () -> {
				node(pipe, () -> {
					node(tunnelPipe, () -> {

					});
				});
			});

			node(nickelCompactor);

			node(pelt, () ->{
				node(nickelWall, () -> {
					node(nickelWallLarge, () ->{

					});
				});
			});

			node(zero, () -> {

			});

			nodeProduce(Items.scrap, () -> {
				nodeProduce(CryogenesisItems.nickel, () -> {

				});
			});
		});
	}
}