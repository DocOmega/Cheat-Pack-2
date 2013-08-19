package com.kodehawa.module;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityRenderer;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.Minecraft;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet3Chat;
import net.minecraft.src.PlayerControllerMP;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.event.EventHandler;
import com.kodehawa.event.Listener;
import com.kodehawa.event.events.EventKey;
import com.kodehawa.event.events.EventRender3D;
import com.kodehawa.event.events.EventTick;
import com.kodehawa.module.annotations.ModuleRetention;
import com.kodehawa.module.enums.EnumGuiCategory;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.Tickable;

/**
 * Main module class. All modules should extends it.
 * @author Kodehawa
 */
public abstract class ModuleBase implements Listener, Tickable {


    /**
     * Where in the GUI to place this cheat
     */
    public EnumGuiCategory type;
    
    /**
     * Is module enabled?
     */
    public boolean enabled;
	
	/**
	 * Unlocalized name
	 */
	public String name;
	
	/**
	 * Module Description
	 */
	public String desc;
	
	/**
	 * Module Credits
	 */
	public String credits;
	
	/**
	 * Module version
	 */
	public String version;
	
	/**
	 * Module keybinding
	 */
	public int keybind;
	
	/**
	 * Is module active?
	 */
	private boolean active;
	
	/**
	 * Tickable method, that define the mod ticks.
	 */
    private Tickable Tickable;

    /**
    * It's this tickable ?
    */
    private boolean tick;

    /**
     * Defines color, mostly used by WIP modules
     */
    public int color;
    
    /**
     * Render 3D
     */
    private boolean ortho;

    
    /**
     * Incompatible modules
     */
    private final LinkedList<Class<? extends ModuleBase>> incompat = new LinkedList<Class<? extends ModuleBase>>();

	/**
	 * Main constructor. Defines all thing that a module needs
	 */
    
    @ModuleRetention( type = "Base" )
    public ModuleBase(final String name, final String desc, final int key) {
       this(name, desc, "1.6.2", key, EnumGuiCategory.UTILS, true);
        EventHandler.getInstance().registerListener( EventKey.class, this );
        EventHandler.getInstance().registerListener( EventRender3D.class, this );
      }

    public ModuleBase(final String name, final String desc, final EnumGuiCategory type) {
       this(name, desc, "1.6.2", -1, type, true);
        EventHandler.getInstance().registerListener( EventKey.class, this );
        EventHandler.getInstance().registerListener( EventRender3D.class, this );
       }

    public ModuleBase(final String name, final String desc, final String version, final int key, final EnumGuiCategory type, final boolean enabled) {
       this.name = name;
       this.desc = desc;
       this.keybind = key;
       this.type = type;
       this.enabled = enabled;
       this.version = version;
       EventHandler.getInstance().registerListener( EventKey.class, this );
       EventHandler.getInstance().registerListener( EventRender3D.class, this );
    }




    /**
     * Set module as on.
     */
    public void setOn( ){
    	active = true;
    	onEnableModule();
        this.disableIncompat();
    }
    
    /**
     * Set module as off.
     */
    public void setOff( ){
    	active = false;
    	onDisableModule();
    }
    
    /**
     * Toggle the specified module
     */
    public void toggleModule( ){
    	try{
    	active = !active;
    	if (active) {
    		onEnableModule();
    		ModuleManager.getInstance().enabledModules.add(name);
            if(this.getTick())   {
            ModuleManager.getInstance().addToTick(this);  }
        }
    	else{
    		onDisableModule();
    		ModuleManager.getInstance().enabledModules.remove(name);
            if(this.getTick())   {
                ModuleManager.getInstance().removeFromCurrentTick(this);  }
        }
    	  if( this.isActive( ) ) {
              if( this.getRender( ) ) {
              	EventHandler.getInstance().registerListener( EventRender3D.class, this );
              }
          } else {
              if( this.getRender( ) ) {
              	EventHandler.getInstance().unRegisterListener( EventRender3D.class, this );
              }
          }
    	}
    	catch( Exception e ){
    		for(ModuleBase m : ModuleManager.getInstance().modules){
    		CheatingEssentials.getCheatingEssentials().CELogAgent("Can't load module " + m.getName() + " - Because of " + e.toString());
    		}
    		e.printStackTrace();
    	}
    }
    
    /**
     * Get module state. Mostly for debugging
     * @return
     */
    public boolean isActive( ){
    	return active;
    }
    
    /**
     * Return the module name for the state.
     * @return
     */
    public String getName( ){
    	return this.name;
    }
    
    /**
     * Set module name
     */
    public void setName( String s ){
    	this.name = s;
    }
    
    /**
     * Return the value of the module keybinding for the state.
     * @return
     */
    public int getKeybinding( ){
    	return this.keybind;
    }
    
    /**
     * Set module keybinding for the specified module
     * @param key
     */
    public void setKeybinding( Integer key ){
    	this.keybind = key;
    }
    
    public void setAuthor( String s ){
    	this.credits = s;
    }
    
    /**
     * It's usable the module?
     */
    public void setActive( boolean state ){
    	this.enabled = state;
    }
    
    /**
     * It's the module having a specific color?
     */
    public void setColor( int color ){
    	this.color = color;
    }
    
    /**
     * Gets the state.
     */
    public boolean getEnabled( ){
    	return this.enabled;
    }
    
    /**
     * Gets module specific color
     */
    public int getColor( ){
    	return this.color;
    }
    
    /**
     * Sets module description
     */
    public void setDesc( String s ){
    	this.desc = s;
    }
    
    /**
     * Get Minecraft version
     */
    public String getMCVersion(){
    	return this.version;
    }

    public EnumGuiCategory getType() {
        return type;
}
    
    public boolean getRender( ) {
        return ortho;
    }

    public boolean getTick(){
        return tick;
    }

    public void setTick(boolean shit){
       tick = shit;
    }
    
    public void setRender( boolean state ) {
        ortho = state;
    }
    
    
    /**
     * Set Minecraft specific version
     */
    public void setVersion( String s ){
    	this.version = s;
    }
    /**
     * When a event it's fired
     */
    @Override
    public void onEvent( Event e ) {
        if( e instanceof EventTick ) {
            Tickable.tick( );
        }
        if( e instanceof EventKey ) {
            if( ( ( EventKey ) e ).getKey( ) == this.getKeybinding( ) ) {
                toggleModule( );
            }
        }
        if( this.getRender( ) ) {
            if( e instanceof EventRender3D ) {
                this.onRenderInModule( );
            }
        }
    }
    


     protected static EntityClientPlayerMP getPlayer() {
        return getMinecraft().thePlayer;
     }

     protected static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
     }

     protected static World getWorld() {
        return getMinecraft().theWorld;
     }

     protected static List<TileEntity> getTileEntitiesInWorld() {
        return getWorld().loadedTileEntityList;
     }

     protected static List<EntityPlayer> getPlayersInWorld() {
        return getWorld().playerEntities;
    }

     protected static void displayGuiScreen(final GuiScreen e) {
        getMinecraft().displayGuiScreen(e);
     }

     protected static void sendPacket(final Packet packet) {
        getSendQueue().addToSendQueue(packet);
     }

     protected static void sendChatMessage(final String message) {
        sendPacket(new Packet3Chat(message));
     }

     protected static NetClientHandler getSendQueue() {
        return getPlayer().sendQueue;
     }


     protected static double getDistanceToEntity(final Entity e) {
        return getPlayer().getDistanceToEntity(e);
     }


     protected static double getDistanceSqToEntity(final Entity e) {
        return getPlayer().getDistanceSqToEntity(e);
     }

     protected static List<Entity> getLoadedEntities() {
        return getWorld().loadedEntityList;
         }

     protected static EntityRenderer getEntityRenderer() {
        return getMinecraft().entityRenderer;
         }

     protected static PlayerControllerMP getPlayerController() {
        return getMinecraft().playerController;
         }

     protected static boolean getCanEntityBeSeen(final Entity e) {
        return getPlayer().canEntityBeSeen(e);
         }
    
     protected void setFly(boolean state){
        getMinecraft().thePlayer.capabilities.allowFlying = state;
		getMinecraft().thePlayer.sendPlayerAbilities();
	}

     protected static List<Entity> getEntitiesInRange(final double range) {
        final List<Entity> list = new LinkedList<Entity>();

        for (final Entity e : getLoadedEntities()) {
                if (getDistanceToEntity(e) <= range) {
                        if (e instanceof EntityLiving) {
                                list.add(e);
                        } else {
                                continue;
                        }
                } else {
                        // Totally useless :D
                        continue;
                }
        }

        return list;
           }


         /**
        * Marks a Module as incompatible with this one
        * 
        *@param module
 */
     protected void incompat(final Class<? extends ModuleBase> module) {
        incompat.add(module);
        }

       /**
       * Disables incompatible modules. Only when this module is toggled on.
       */
        private void disableIncompat() {
        for (final Class<? extends ModuleBase> e : incompat) {
                this.disableIncompat(e);
        }
         }

        /**
        * Disables a Module for incompatibility
        */
      private void disableIncompat(final Class<? extends ModuleBase> module) {
      final ModuleBase incompat = ModuleManager.getInstance().getModuleByClass(module);

        if (!incompat.isActive()) {
                return;
        }

        if ((getWorld() != null) && (getMinecraft() != null) && (getPlayer() != null)) {
               CheatingEssentials.getCheatingEssentials().getUtils().addChatMessage(ChatColour.YELLOW + "[CE v3] Disabling " + incompat.getName() + " because it is incompatible with " + getName());
        }

        incompat.setActive(false);
         }

     /**
      * Sets module as enabled and change their state.
      */
      public abstract void onEnableModule( );
	
	 /**
	  * Sets module as disabled and change their state.
	  */
	  public abstract void onDisableModule( );
    
	 /**
	  * Sets module as renderizable one and gets render state.
	  */
	  public void onRenderInModule( ){}

    @Override
    public void tick(){}
}