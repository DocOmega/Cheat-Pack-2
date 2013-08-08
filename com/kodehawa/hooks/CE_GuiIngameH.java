package com.kodehawa.hooks;

import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.Minecraft;
import net.minecraft.src.ScaledResolution;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.InGameRadar.Radar;
import com.kodehawa.gui.api.components.Frame;
import com.kodehawa.gui.api.render.ModGuiUtils;
import com.kodehawa.util.ChatColour;

public class CE_GuiIngameH extends GuiIngame {

    private CheatingEssentials cheatingEssentials;
	private ModGuiUtils utils;
    private Radar radar;
    private boolean radarActive;
    private boolean activese;
	
	public CE_GuiIngameH(Minecraft par1Minecraft) {
		super(par1Minecraft);
        cheatingEssentials = new CheatingEssentials(par1Minecraft);
        utils = new ModGuiUtils();
		radar = new Radar();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void renderGameOverlay(float par1, boolean par2, int par3, int par4){
		super.renderGameOverlay(par1, par2, par3, par4);
		
		CheatingEssentials.getCheatingEssentials().tick();
        {
    	for(Frame e : CheatingEssentials.getCheatingEssentials().MainGui.frames) {
    		if(e.pinned) {
    		e.update();
    		e.draw();
    		}
    		}
        }
        
		
		if(CheatingEssentials.getCheatingEssentials().getKeyStateFromMap(Keyboard.KEY_I)){
    		radarActive = !radarActive;
    		
    	}
    	
    	if(CheatingEssentials.getCheatingEssentials().getKeyStateFromMap(Keyboard.KEY_Z)){
    		activese = !activese;
    	}
    	
        
        /**
         * Start the radar thread and draw the Radar.
         */
    	if(this.radarActive){
    		radar.run();
    	}
    	
    	ScaledResolution var5 = new ScaledResolution(CheatingEssentials.getCheatingEssentials().getMinecraftInstance().gameSettings,
    			CheatingEssentials.getCheatingEssentials().getMinecraftInstance().displayWidth,
    			CheatingEssentials.getCheatingEssentials().getMinecraftInstance().displayHeight);
    	
        int var6 = var5.getScaledWidth();
        int var7 = var5.getScaledHeight();
        FontRenderer var8 = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().fontRenderer;
    	
        /**
         * This it's self-explanatory :)
         */
    	if(this.activese){
   		 this.drawRect( utils.getWidth() - 100, 150, var6, 150 + ( ( CheatingEssentials.getCheatingEssentials().enabledMods.size( ) + 1 ) * 10 ) + 3, 0x77000000 );
   			this.drawString( var8, ChatColour.DARK_GRAY + "Enabled Modules", var6 - 98, 151, 0xffffff );
   			for ( int i = 0; i < CheatingEssentials.getCheatingEssentials().enabledMods.size( ); i++ ) {
   				this.drawString( var8, CheatingEssentials.getCheatingEssentials().enabledMods.get( i ), var6 - 98, 150 + CheatingEssentials.getCheatingEssentials().enabledMods.size( ) + ( ( 12 * ( i + 1 ) ) - ( i * 3 ) ), 0x00ff00 );
   			}
   	}
	}

}
