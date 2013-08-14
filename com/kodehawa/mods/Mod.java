/**
 * I've does my most because I can  *Portal reference :)
 */

package com.kodehawa.mods;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.event.EventHandler;
import com.kodehawa.event.Listener;
import com.kodehawa.event.events.EventKey;
import com.kodehawa.event.events.EventRender3D;
import com.kodehawa.event.events.EventTick;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.Tickable;

public abstract class Mod implements Listener
{
    private String desc;
    public int keybind;
    private boolean ortho;
    public int keyBind;
    private Tickable tick;

    
    public Mod( String name, String desc, int keybind ) {
        this.name = name;
        this.desc = desc;
        this.keybind = keybind;
        EventHandler.getInstance().registerListener( EventKey.class, this );
        EventHandler.getInstance().registerListener( EventRender3D.class, this );
    }
    
    /**
     * Information for ppl that are reading the code.
     * @author Kodehawa
     */
    
    public @interface ModuleInformation {
    	public String name = "";
    	public String desc = "";
    	public int keybind = 0;
    	
		public String name();
    	String desc();
    	String credits();
    	}
    

    public void turnOn()
    {
        active = true;
        onEnable();
    }

    public void turnOff()
    {
        active = false;
        onDisable();
    }
    
    public boolean getOrtho( ) {
        return ortho;
    }
    
    public void setOrtho( boolean state ) {
        ortho = state;
    }
    
    public int getKeybind( ) {
        return keybind;
    }
    
    public void setKeybind( int key ) {
        this.keybind = key;
    }

    public void toggle()
    {
        active = !active;

        if (active)
        {
            onEnable();
            CheatingEssentials.getCheatingEssentials().enabledMods.add(name);
        }
        else
        {
            onDisable();
            CheatingEssentials.getCheatingEssentials().enabledMods.remove(name);

        }
        
        if( this.isActive( ) ) {
            if( this.getOrtho( ) ) {
            	EventHandler.getInstance().registerListener( EventRender3D.class, this );
            }
        } else {
            if( this.getOrtho( ) ) {
            	EventHandler.getInstance().unRegisterListener( EventRender3D.class, this );
            }
        }
    }

    public boolean isActive()
    {
        return active;
    }

    public String getActive()
    {
        if (active)
        {
            return ChatColour.WHITE + name + ChatColour.GREEN + " Active";
        }
        else
        {
            return ChatColour.WHITE + name + ChatColour.RED + " Inactive";
        }
    }

    public abstract void onEnable( );
    public abstract void render( );
    public abstract void onDisable( );

    public String name;
    private boolean active;
 
    
    @Override
    public void onEvent( Event e ) {
        if( e instanceof EventTick ) {
            tick.tick( );
        }
        if( e instanceof EventKey ) {
            if( ( ( EventKey ) e ).getKey( ) == this.getKeybind( ) ) {
                toggle( );
            }
        }
        if( this.getOrtho( ) ) {
            if( e instanceof EventRender3D ) {
                this.render( );
            }
        }
    }
}
