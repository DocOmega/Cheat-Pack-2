package com.kodehawa.mods;

import net.minecraft.src.Minecraft;
import net.minecraft.src.RenderManager;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityChest;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.ChestESP.ChestFinderContainer;
import com.kodehawa.event.Event;
import com.kodehawa.util.Tickable;

public class ModuleTestChestFinder extends Mod implements Tickable
{
    protected Minecraft minecraft;
    protected CheatingEssentials cb;

    /**
     * I need OpenGL for this. Dammit!
     * @param mod
     * @param c
     * @param m
     */

    public ModuleTestChestFinder(CheatingEssentials c, Minecraft m)
    {
        super(Mods.ChestESP);
        cb = c;
        minecraft = m;
        // TODO Auto-generated constructor stub
    }
    
    public void render(){
    	for( Object o : Minecraft.getMinecraft( ).theWorld.loadedTileEntityList ) {
            TileEntity e = ( TileEntity ) o;
            if( e instanceof TileEntityChest ) {
                this.drawESP( ( TileEntityChest ) e, e.xCoord, e.yCoord, e.zCoord, 0 );
            }
        }
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
    
    }

    
    @Override
    public void onEnable()
    {
    	Vars.ChestESP = true;
        // TODO Auto-generated method stub
    }

    
   
    @Override
    public void onDisable()
    {
    	Vars.ChestESP = false;
        // TODO Auto-generated method stub
    	
    }
    /**
     * It just know how get bugged.
     * @param chest
     * @param x
     * @param y
     * @param z
     * @param f
     */
    
    public void drawESP( TileEntityChest chest, double x, double y, double z, float f ) {
        if( isActive( ) ) {
            Minecraft mc = Minecraft.getMinecraft();
            if( !( ( chest.xCoord == 0 ) && ( chest.yCoord == 0 ) && ( chest.zCoord == 0 ) ) ) {
                ChestFinderContainer.drawChestESP(
                		x - RenderManager.renderPosX,
                        y - RenderManager.renderPosY,
                        z - RenderManager.renderPosZ,
                        0.0F,
                        1.0F - ( chest.getDistanceFrom( mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ ) / 20000 ),
                        1.0F );
                         
            }
        }
    }

	
}

