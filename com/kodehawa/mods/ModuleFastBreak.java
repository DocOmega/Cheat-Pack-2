package com.kodehawa.mods;

import net.minecraft.src.NetClientHandler;
import net.minecraft.src.PotionEffect;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.util.Tickable;

public class ModuleFastBreak extends Mod implements Tickable {

    @ModuleInformation(
    		credits = "Kodehawa, Godshawk",
    		desc = "Allows to the player to break blocks more fast",
    		name = "Fast Break"
    		)
	
	
	public ModuleFastBreak( ) {
		super("Fast Break", "You know :D", Keyboard.KEY_B);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		//CheatingEssentials.getCheatingEssentials().addToTick(this);
		CheatingEssentials.getCheatingEssentials( ).getMinecraftInstance( ).thePlayer.addPotionEffect( new PotionEffect( 3, 999999999,
                3, true ) );
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		CheatingEssentials.getCheatingEssentials( ).getMinecraftInstance( ).thePlayer.removePotionEffect( 3 ) ;
	}
	
	@Override
	public void tick(){
		// TODO Auto-generated method stub
        
	}

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
	

}
