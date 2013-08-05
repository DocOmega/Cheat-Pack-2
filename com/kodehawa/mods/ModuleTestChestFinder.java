package com.kodehawa.mods;

import net.minecraft.src.Minecraft;
import net.minecraft.src.RenderManager;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityChest;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.ChestESP.ChestFinderContainer;
import com.kodehawa.event.Event;
import com.kodehawa.event.events.EventBlockRender;
import com.kodehawa.event.events.EventRender3D;
import com.kodehawa.util.Tickable;

public class ModuleTestChestFinder extends Mod implements Tickable
{
    protected Minecraft minecraft;

    /**
     * I need OpenGL for this. Dammit!
     * @param mod
     */

    public ModuleTestChestFinder( )
    {
        super("ChestESP", "Shows the hidden chests throught everything!", Keyboard.KEY_N, Mods.ChestESP);
        this.setOrtho( true );

        // TODO Auto-generated constructor stub
    }
    

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
    	this.setOrtho(true);
    }
    

    
    @Override
    public void onEnable()
    {
    	Vars.ChestESP = true;
    	CheatingEssentials.getCheatingEssentials().eventHandler.registerListener( EventRender3D.class, this );
    	CheatingEssentials.getCheatingEssentials().addToTick(this);
        // TODO Auto-generated method stub
    }

    
   
    @Override
    public void onDisable()
    {
        // TODO Auto-generated method stub
    	Vars.ChestESP = false;
    	CheatingEssentials.getCheatingEssentials().eventHandler.unRegisterListener( EventRender3D.class, this );
        CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
        
    }
    
    
    
    public void drawESP( TileEntityChest chest, double x, double y, double z, float f ) {
        if( isActive( ) ) {
            Minecraft mc = Minecraft.getMinecraft();
            if( !( ( chest.xCoord == 0 ) && ( chest.yCoord == 0 ) && ( chest.zCoord == 0 ) ) ) {
                ChestFinderContainer.drawChestESP(
                		x - RenderManager.renderPosX,
                        y - RenderManager.renderPosY,
                        z - RenderManager.renderPosZ );
                         
            }
        }
    }



	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void render() {
		// TODO Auto-generated method stub
		for( Object o : CheatingEssentials.getCheatingEssentials().getMinecraftInstance( ).theWorld.loadedTileEntityList ) {
            TileEntity e = ( TileEntity ) o;
            if( e instanceof TileEntityChest ) {
                drawESP(( TileEntityChest ) e, e.xCoord, e.yCoord, e.zCoord, 1.5F);
            }
        }
	}

	
}

