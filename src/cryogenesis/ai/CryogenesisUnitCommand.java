package cryogenesis.ai;

import cryogenesis.input.*;
import cryogenesis.ai.types.*;

import arc.*;
import arc.func.*;
import arc.input.*;
import arc.scene.style.*;
import arc.struct.*;
import arc.util.*;
import mindustry.ai.*;
import mindustry.ai.types.*;
import mindustry.ctype.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.input.*;

public class CryogenesisUnitCommand extends MappableContent{

	public static UnitCommand scavengeCommand;
	
    /** Name of UI icon (from Icon class). */
    public final String icon;
    /** Controller that this unit will use when this command is used. Return null for "default" behavior. */
    public final Func<Unit, AIController> controller;
    /** If true, this unit will automatically switch away to the move command when given a position. */
    public boolean switchToMove = true;
    /** Whether to draw the movement/attack target. */
    public boolean drawTarget = false;
    /** Whether to reset targets when switching to or from this command. */
    public boolean resetTarget = true;
    /** Whether to snap the command destination to ally buildings. */
    public boolean snapToBuilding = false;
    /** If true, the unit will arrive at this command's exact endpoint. */
    public boolean exactArrival = false;
    /** If true, this command refreshes the list of stances when selected TODO: do not use, this will likely be removed later!*/
    public boolean refreshOnSelect = false;
    /** Key to press for this command. */
    public @Nullable KeyBind keybind = null;
    /** Extra stances that are available when this command is selected. These ignore incompatibleStances. */
    public Seq<UnitStance> extraStances = new Seq<>();

    public UnitCommand(String name, String icon, Func<Unit, AIController> controller){
        super(name);

        this.icon = icon;
        this.controller = controller == null ? u -> null : controller;
    }

    public UnitCommand(String name, String icon, KeyBind keybind, Func<Unit, AIController> controller){
        this(name, icon, controller);
        this.keybind = keybind;
    }

    public String localized(){
        return Core.bundle.get("command." + name);
    }

    public TextureRegionDrawable getIcon(){
        return Icon.icons.get(icon, Icon.cancel);
    }

    public char getEmoji() {
        return (char)Iconc.codes.get(icon, Iconc.cancel);
    }

    @Override
    public ContentType getContentType(){
        return ContentType.unitCommand;
    }

    @Override
    public String toString(){
        return "UnitCommand:" + name;
    }

	public static void load(){

		scavengeCommand = new UnitCommand("scavenge", "refresh", CryogenesisBinding.unitCommandScavenge, u -> new ScavengeAI());
	}
}