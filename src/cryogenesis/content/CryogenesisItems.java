package cryogenesis.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

public class CryogenesisItems {

	public static Item nickel;

	public static final Seq<Item> innelisItems = new Seq<>();

	public static void load(){
		nickel = new Item("nickel", Color.valueOf("ac8675")){{
			hardness = 1;
			cost = 1.2f;
			healthScaling = 1f;
		}};
	}
}