package com.kodehawa.module;

import net.minecraft.src.Minecraft;
import net.minecraft.src.RenderManager;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityChest;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.ChestESP.ChestFinderContainer;
import com.kodehawa.render.GLHelper;

public class ChestESP extends Module {
    
    public ChestESP( ) {
        super( "Chestfinder", "Shows you where all the chests are.", Keyboard.KEY_C, EnumGuiCategory.VISION );
        // TODO Auto-generated constructor stub
        this.setOrtho( true );
    }
    
    @Override
    public void render( ) {
        // TODO Auto-generated method stub
        for( Object o : CheatingEssentials.getMinecraftInstance( ).theWorld.loadedTileEntityList ) {
            TileEntity e = ( TileEntity ) o;
            if( e instanceof TileEntityChest ) {
                this.drawESP( ( TileEntityChest ) e, e.xCoord, e.yCoord, e.zCoord, 0 );
            }
        }
    }
    
    
    public void drawESP( TileEntityChest chest, double x, double y, double z, float f ) {
        if( getActive( ) ) {
            Minecraft mc = CheatingEssentials.getMinecraftInstance( );
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
    
    @Override
    public void tick( ) {
        // TODO Auto-generated method stub
        this.setOrtho( true );
    }
  
    
}
