package com.kodehawa.console;

import com.kodehawa.mods.Vars;
import com.kodehawa.util.ChatColour;

public class XrayAdd implements BaseCommand
{
    @Override
    public void onRun(String[ ] cmd)
    {
        // TODO Auto-generated method stub
        try
        {
        	
            if (cmd.length <= 1)
            {
                throw new NullPointerException();
            }
            else if (cmd.length == 2)
            {
            }
            else
            {
                throw new NullPointerException();
            }
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
        return "xrayAddBlock";
    }

    @Override
    public String showHelp()
    {
        // TODO Auto-generated method stub
        return new String(ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName() + " <block id>");
    }

    @Override
    public String output()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
