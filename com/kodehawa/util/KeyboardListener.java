package com.kodehawa.util;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.core.CModLoader;
import com.kodehawa.event.EventHandler;
import com.kodehawa.event.events.EventKey;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;

public class KeyboardListener {

	private volatile static KeyboardListener instance;
    private boolean[ ] keymap;

	
	public KeyboardListener(){
		
        keymap = new boolean[ 256 ];
        CModLoader.loadModulesforKB();
        handleKeys( );
	}
	

	/**
	 * Module keybinding.
	 * It handles the events and toggle the specified mod with the keybinding specified in the module class and CModLoader.
	 * Also it handles the GUI key, that shows a hacked-client-style GUI. Sorry for that.
	 * I'm making another GUI, promise :)
	 */
	
    public void handleKeys( ) {
    	//TODO: Module Keys
    	
        for( Mod m : ModManager.getInstance().mods ) {
            int key = m.getKeybind( );
            if( getKeyStateFromMap( key ) ) {
                EventHandler.getInstance().call( new EventKey( this, m.getKeybind( ) ) );
                m.toggle();
                break;
            }
          }
        }
    
        /**
        * Get key things.
        * Like the old CheckKey :)
        */
       public boolean getKeyStateFromMap( int i ) {
        if( CheatingEssentials.getCheatingEssentials().getMinecraftInstance().currentScreen != null ) {
            return false;
        }
        if( Keyboard.isKeyDown( i ) != keymap[ i ] ) {
            return keymap[ i ] = !keymap[ i ];
        } else {
            return false;
        }
    }

	
	public static KeyboardListener getInstance(){
		if(instance == null){
			instance = new KeyboardListener();
		}
		return instance;
	}
	
}
