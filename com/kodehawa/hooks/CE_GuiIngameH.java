package com.kodehawa.hooks;

import com.kodehawa.gui.componentbase.CheatingEssentialsTGui;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.Minecraft;
import net.minecraft.src.ScaledResolution;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.gui.api.components.Frame;
import com.kodehawa.gui.api.components.ModuleGui;
import com.kodehawa.gui.api.render.ModGuiUtils;
import com.kodehawa.mods.ModManager;
import com.kodehawa.radar.Radar;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.KeyboardListener;

public class CE_GuiIngameH extends GuiIngame {

    private CheatingEssentials cheatingEssentials;
	private ModGuiUtils utils;
    private Radar radar;
    private boolean radarActive;
    private boolean activese;
    private ModuleGui Gui;
    private CheatingEssentialsTGui Gui2;
	public static int tick = 0;

	
	public CE_GuiIngameH(Minecraft par1Minecraft) {
		super(par1Minecraft);
        cheatingEssentials = new CheatingEssentials( );
        utils = new ModGuiUtils();
		radar = new Radar();
		Gui = new ModuleGui( );
        Gui2 = new CheatingEssentialsTGui(null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void renderGameOverlay(float par1, boolean par2, int par3, int par4){
		super.renderGameOverlay(par1, par2, par3, par4);
		
		CheatingEssentials.getCheatingEssentials().tick();
        {
        	

    		if( KeyboardListener.getInstance().getKeyStateFromMap(Keyboard.KEY_G)){
    			CheatingEssentials.getCheatingEssentials().getMinecraftInstance().displayGuiScreen(Gui);
    		}
    		
    	for(Frame e : Gui.frames) {
    		if(e.pinned) {
    		e.update();
    		e.draw();
    		}
    		}
        }
        
		
		if(KeyboardListener.getInstance().getKeyStateFromMap(Keyboard.KEY_I)){
    		radarActive = !radarActive;
    		
    	}
    	
    	if(KeyboardListener.getInstance().getKeyStateFromMap(Keyboard.KEY_Z)){
    		activese = !activese;
    	}
    	
        
        /**
         * Start the radar thread and draw the Radar.
         */
    	if(this.radarActive){
    		radar.run();
    	}
    	
    	ScaledResolution var5 = new ScaledResolution(CheatingEssentials.getMinecraftInstance().gameSettings,
    			CheatingEssentials.getMinecraftInstance().displayWidth,
    			CheatingEssentials.getMinecraftInstance().displayHeight);
    	
        int var6 = var5.getScaledWidth();
        int var7 = var5.getScaledHeight();
        FontRenderer var8 = CheatingEssentials.getMinecraftInstance().fontRenderer;
    	
        /**
         * This it's self-explanatory :)
         */
    	if(this.activese){
   		 this.drawRect( utils.getWidth() - 100, 150, var6, 150 + ( ( ModManager.getInstance().enabledMods.size( ) + 1 ) * 10 ) + 3, 0x77000000 );
   			this.drawString( var8, ChatColour.DARK_GRAY + "Enabled Modules", var6 - 98, 151, 0xffffff );
   			for ( int i = 0; i < ModManager.getInstance().enabledMods.size( ); i++ ) {
   				this.drawString( var8, ModManager.getInstance().enabledMods.get( i ), var6 - 98, 150 + ModManager.getInstance().enabledMods.size( ) + ( ( 12 * ( i + 1 ) ) - ( i * 3 ) ), 0x00ff00 );
   			}
   	}
	}

}
