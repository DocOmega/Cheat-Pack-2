package com.kodehawa.mods;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleSprint extends Mod implements Tickable
{

    public ModuleSprint( )
    {
        super(Mods.Sprint);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {

        // TODO Auto-generated method stub
        if (!CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.isSprinting())
        {
        	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.setSprinting(true);

            
            }
        }
   

    @Override
    public void onEnable()
    {
    	CheatingEssentials.getCheatingEssentials().addToTick(this);
    }

    @Override
    public void onDisable()
    {
    	CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
    }
}
