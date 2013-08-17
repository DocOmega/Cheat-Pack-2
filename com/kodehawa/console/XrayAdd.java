package com.kodehawa.console;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.ModManager;
import com.kodehawa.mods.ModuleXray;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.FileManager;

public class XrayAdd implements BaseCommand{

    public int id;
    public String endres = "";

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
       return endres;
    }
		
        String output(String[ ] cmd){
        
        	try
            {
        		Integer blockID = Integer.parseInt( cmd[ 1 ] );

        		if(ModuleXray.xrayBlocks.contains(blockID)){
        			return "The following ID it's in the list: " + blockID + " and we can't add it due to this.";
        		}
        		
            	ModuleXray.xrayBlocks.add( blockID );
            	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().renderGlobal.loadRenderers();
                FileManager.saveXrayList();
            	CheatingEssentials.getCheatingEssentials().CELogAgent("You've added a block to the X-Ray list: " + blockID);
                return "Block ID added to Int: " + blockID;
            }
            catch (Exception e)
            {
               return showHelp();
            }
        }
   }

