package com.kodehawa.gui.componentbase;

import net.minecraft.src.GuiScreen;

import org.lwjgl.input.Keyboard;

/**
 * Tabbed GUI for the Cheating Essentials mod for replace godshawk's one.
 * @author Kodehawa
 *
 */

public class CheatingEssentialsTGui extends GuiScreen {
	
	/**
	 * There it's also a big posibility to allow searching. WIP GUI
	 */
	
    public CheatingEssentialsTGui()
    {
        
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return true;
    }

    @Override
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
    }

    @Override
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(true);
    }

    @Override
    protected void keyTyped(char c, int i)
    {
    	
    }

    @Override
    public void drawScreen(int i, int j, float f)
    {
    	
    }

    @Override
    protected void mouseClicked(int i, int j, int k)
    {
    	
    }

    public void mouseDragged(int i, int j)
    {
    	
    }

    @Override
    protected void mouseMovedOrUp(int par1, int par2, int par3)
    {
    	
    }

}
