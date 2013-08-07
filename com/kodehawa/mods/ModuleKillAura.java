/*
* Copyright (c) 2013 David Rubio
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

package com.kodehawa.mods;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.util.EntityUtils;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Watcher;

public class ModuleKillAura extends Mod implements Tickable
{

    public ModuleKillAura( )
    {
        super("Mob Aura", "Kill every mob!", Keyboard.KEY_Y);
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

    @Override
    public void tick()
    {
        for (int i = 0; i < CheatingEssentials.getCheatingEssentials().getMinecraftInstance().theWorld.loadedEntityList.size(); i++)
        {
            Entity ent = (Entity) CheatingEssentials.getCheatingEssentials().getMinecraftInstance().theWorld.loadedEntityList.get(i);
            int id = ent.entityId;
            long now = System.currentTimeMillis();
            Watcher tracked = EntityUtils.getLastAffected(id);

            if (tracked != null)
            {
                if (tracked.matches(ent, now))
                {
                    continue;
                }
            }

            EntityUtils.setLastAffected(id, ent);

            if ((ent == CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer) || !(ent instanceof EntityLiving) || ent.isDead)
            {
                continue;
            }

            if ((CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.getDistanceSqToEntity(ent) <= 90D) && !ent.isDead && CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.canEntityBeSeen(ent))
            {
                
            	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().playerController.attackEntity(CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer, ent);
            	
            }
        }
    }

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
