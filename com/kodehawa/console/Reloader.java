package com.kodehawa.console;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.ChatColour;

public class Reloader implements BaseCommand
{
    @Override
    public void onRun(String[ ] cmd)
    {
        // TODO Auto-generated method stub
        try
        {
        	CheatingEssentials.getCheatingEssentials().minecraft.thePlayer.addChatMessage(ChatColour.RED + "Reloading. Please hold still.");
            //CGuiIngame.cheatbase.reload();
        	CheatingEssentials.getCheatingEssentials().minecraft.renderGlobal.loadRenderers();
        	CheatingEssentials.getCheatingEssentials().minecraft.thePlayer.addChatMessage(ChatColour.RED + "Reload complete!");
        }
        catch (Exception e)
        {
            showHelp();
        }
    }

    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return "reload";
    }

    @Override
    public String showHelp()
    {
        // TODO Auto-generated method stub
        return new String(ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName());
    }

    @Override
    public String output()
    {
        // TODO Auto-generated method stub
        return "Reloaded!";
    }
}
