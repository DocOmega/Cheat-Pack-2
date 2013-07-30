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

import java.util.ArrayList;

import com.kodehawa.CheatingEssentials;

public class ModManager
{
    public ArrayList<Mod> mods;
    public ArrayList<Mod> worldMods;
    public ArrayList<Mod> playerMods;
    public ArrayList<Mod> f3utils;

    public ModManager(CheatingEssentials c)
    {
        mods = new ArrayList<Mod>();
        worldMods = new ArrayList<Mod>();
        playerMods = new ArrayList<Mod>();
        f3utils = new ArrayList<Mod>();
    }

    public void addMod(Mod m)
    {
        mods.add(m);
    }
    
    public void addUtils(Mod m){
    	f3utils.add(m);
    }

    public void addWMod(Mod m)
    {
        worldMods.add(m);
    }

    public void addPMod(Mod m)
    {
        playerMods.add(m);
    }

    public Mod getCheatsByName(String name)
    {
        for (int i = 0; i < mods.size(); i++)
        {
            if (name.equalsIgnoreCase(mods.get(i).name))
            {
                return mods.get(i);
            }
        }

        return null;
    }
}
