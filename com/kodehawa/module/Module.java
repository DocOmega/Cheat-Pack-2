package com.kodehawa.module;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.event.EventHandler;
import com.kodehawa.event.Listener;
import com.kodehawa.event.events.EventAlert;
import com.kodehawa.event.events.EventKey;
import com.kodehawa.event.events.EventRender3D;
import com.kodehawa.event.events.EventTick;



public abstract class Module implements Listener {
    private String name;
    private String desc;
    private int keybind;
    
    /**
     * Yes, I know that booleans default to false, but I don't want any
     * NullPointerExceptions.
     */
    private boolean state = false;
    
    /**
     * Whether it is an orthagonal render
     */
    private boolean ortho;
    
    /**
     * Where in the GUI to place this hack
     */
    private EnumGuiCategory type;
    public EventHandler random;
    
    public Module( String name, String desc, EnumGuiCategory type ) {
        this( name, desc, 0, type );
    }
    
    public Module( String name, String desc, int keybind, EnumGuiCategory type ) {
        this.name = name;
        this.desc = desc;
        this.keybind = keybind;
        this.type = type;
        random.registerListener( EventKey.class, this );
        random.registerListener( EventRender3D.class, this );
    }
    
    public String getName( ) {
        return name;
    }
    
    public String getDesc( ) {
        return desc;
    }
    
    public int getKeybind( ) {
        return keybind;
    }
    
    public void setKeybind( int key ) {
        this.keybind = key;
    }
    
    public boolean getOrtho( ) {
        return ortho;
    }
    
    public void setOrtho( boolean state ) {
        ortho = state;
    }
    
    public void setActive( boolean state ) {
        this.state = state;
    }
    
    public boolean getActive( ) {
        return state;
    }
    
    public void toggle( ) {
        setActive( !state );
        //CheatingEssentials.getCheatingEssentials( ).getModWrapper( ).getFileManager( ).saveHacks( );
        
        CheatingEssentials.getCheatingEssentials( ).getModWrapper( )
                .getEventHandler( )
                .call( new EventAlert( this, "Cheat toggled:\n" + ( this.getActive( ) ? "\u00a7a" : "\u00a7c" )
                        + this.getName( ) ) );
        
        if( this.getActive( ) ) {
        	random.registerListener( EventTick.class, this );
            if( this.getOrtho( ) ) {
            	random.registerListener( EventRender3D.class, this );
            }
        } else {
        	random.unRegisterListener( EventTick.class, this );
            if( this.getOrtho( ) ) {
            	random.unRegisterListener( EventRender3D.class, this );
            }
        }
    }
    
    public EnumGuiCategory getType( ) {
        return this.type;
    }
    
    /**
     * Called for non-ortho renders; ie tracers, esp...
     */
    public abstract void render( );
    
    /**
     * Updates.
     */
    public abstract void tick( );
    
    @Override
    public void onEvent( Event e ) {
        if( e instanceof EventTick ) {
            tick( );
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
