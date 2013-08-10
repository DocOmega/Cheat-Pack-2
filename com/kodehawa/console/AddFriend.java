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

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.Vars;
import com.kodehawa.players.FrenemyManager;
import com.kodehawa.util.ChatColour;

public class AddFriend implements BaseCommand
{
    private final String name;
    String endres = "";

    public AddFriend()
    {
        this.name = "friend";
    }

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
        return this.name;
    }

    @Override
    public String showHelp()
    {
        // TODO Auto-generated method stub
        return new String(ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName() + " <username>");
    }

    @Override
    public String output()
    {
        // TODO Auto-generated method stub
        return endres;
    }

    public String output(String[ ] cmd)
    {
        try
        {
            if (cmd.length <= 1)
            {
                throw new NullPointerException();
            }
            else
            {
                Vars.friends.add(cmd [ 1 ]);
                FrenemyManager.getInstance().writeEnemies();
                return cmd [ 1 ] + " is now a friend!";
            }
        }
        catch (Exception e)
        {
            return showHelp();
        }
    }
}
