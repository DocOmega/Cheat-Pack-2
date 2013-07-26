package com.kodehawa.console;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.Vars;
import com.kodehawa.util.ChatColour;

public class Speed implements BaseCommand
{
    String endres = "";

    @Override
    public void onRun(String[ ] cmd)
    {
        // TODO Auto-generated method stub
        endres = output(cmd);
    }

    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return "speed";
    }

    @Override
    public String showHelp()
    {
        // TODO Auto-generated method stub
        return new String(ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName() + " <0-9>");
    }

    @Override
    public String output()
    {
        // TODO Auto-generated method stub
        return endres;
    }

    String output(String[ ] cmd)
    {
    	try{
    		Float result = Float.parseFloat(cmd [ 1 ]);
    		
    		CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.capabilities.setPlayerWalkSpeed(result);
    		return "Player speed changed to " + result + "!";
    		}
    		
    		catch (Exception e)
            {
              e.printStackTrace();
                return showHelp();
            }
    		
    		}
    }

