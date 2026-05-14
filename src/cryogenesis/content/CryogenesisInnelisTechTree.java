package cryogenesis.content;

import arc.struct.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;
import mindustry.content.Items;
import mindustry.content.Liquids;

import static mindustry.content.TechTree.*;

import static cryogenesis.content.CryogenesisBlocks.*;
import static cryogenesis.content.CryogenesisSectorPresets.*;
import static cryogenesis.content.CryogenesisUnitTypes.*;

public class CryogenesisInnelisTechTree{

	public static void load(){
		CryogenesisPlanets.innelis.techTree = nodeRoot("innelis", coreTerminal, () -> {

			node(pipe, () -> {
				node(pipeRouter, () -> {
					node(tunnelPipe, () -> {
						node(pipeUnloader, () -> {

						});
					});
				});
			});

			node(scrapper, () -> {
				node(nickelCompactor, () -> {
					node(mechanicalAuger, Seq.with(new SectorComplete(cryogenesis)), () -> {

					});
				});
			});

			node(pelt, () ->{
				node(nickelWall, () -> {
					node(nickelWallLarge, () -> {

					});
				});
			});

			node(cryogenesis, () -> {

			});

			nodeProduce(CryogenesisItems.nickel, () -> {
				nodeProduce(Items.scrap, () -> {

				});

				nodeProduce(Liquids.cryofluid, () -> {
					nodeProduce(Liquids.water, () -> {
					
					});
				});

				nodeProduce(CryogenesisLiquids.sluice, () -> {
					nodeProduce(CryogenesisLiquids.steam, () -> {

					});
				});
			});
		});
	}
}