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

package com.kodehawa.console;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.src.Enchantment;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Minecraft;
import net.minecraft.src.Packet;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.ChatColour;

public class Enchant implements BaseCommand
{
    String endres = "";
    private Minecraft mc;

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
        return "enchant";
    }

    @Override
    public String showHelp()
    {
        // TODO Auto-generated method stub
        return new String(ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName() + " <enchantment> <level>");
    }

    private void enchantStack(ItemStack i, String ench, int level)
    {
        for (Enchantment e : Enchantment.enchantmentsList)
        {
            if (e == null)
            {
                continue;
            }

            String en = e.getName();

            if (en != null)
            {
                String ename = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().func_135016_M().func_135041_c().func_135034_a();

                if (ename.replaceAll(" ", "").equalsIgnoreCase(ench))
                {
                    i.addEnchantment(e, level);
                }
            }
        }

        this.writeStack(i);
    }

    private void writeStack(ItemStack i)
    {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        DataOutputStream datastream = new DataOutputStream(bytestream);

        try
        {
            Packet.writeItemStack(i, datastream);
        }
        catch (Exception e)
        {
        	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.addChatMessage(e.toString());
        }
    }

    @Override
    public String output()
    {
        // TODO Auto-generated method stub
        return endres;
    }

    String output(String[ ] cmd)
    {
        try {
        	EntityClientPlayerMP thePlayer = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer;
        	{
        		ItemStack stack = thePlayer.inventory.getCurrentItem( );
        		enchantStack( stack, cmd [ 1 ], Integer.parseInt( cmd [ 2 ] ) );
        		//writeStack(stack);
        		return "Item enchanted successfully! Will not work due to the Integrated Server.";
        	}
        }
          catch ( Exception e ) {
        	  e.printStackTrace();
        	return showHelp( );
        }
       
    }
}
