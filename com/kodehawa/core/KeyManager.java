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

package com.kodehawa.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.mods.Mod;
import com.kodehawa.util.ChatColour;

public class KeyManager
{
    public File keybindsFile;

    public KeyManager()
    {
        keybindsFile = new File(CheatingEssentials.modinstance.minecraft.mcDataDir, "/config/Cheating Essentials/keybinds.cfg");

        if (!keybindsFile.exists())
        {
            try
            {
                // Create file
                keybindsFile.getParentFile().mkdirs();
                keybindsFile.createNewFile();
                FileWriter fstream = new FileWriter(keybindsFile);
                BufferedWriter out = new BufferedWriter(fstream);
                //Bugs, what bugs? :(
                out.write("ModuleFullbright=" + Keyboard.KEY_F + "\r\n");
                out.write("ModuleKillaura=" + Keyboard.KEY_P + "\r\n");
                out.write("ModuleSprint=" + Keyboard.KEY_K + "\r\n");
                out.write("ModuleFastPlace=" + Keyboard.KEY_N + "\r\n");
                out.write("ModuleWaterwalk=" + Keyboard.KEY_L + "\r\n");
                out.write("ModuleXray=" + Keyboard.KEY_X + "\r\n");
                out.write("ModuleFly=" + Keyboard.KEY_R + "\r\n");
                // Close the output stream
                out.close();
            }
            catch (Exception e)       // Catch exception if any
            {
                //System.err.println("[Cheat Pack 2.3] Error writing keybinds!: " + e.getMessage());
            }

            readKeysAndBind();
        }
        else
        {
            readKeysAndBind();
        }
    }

    public void readKeysAndBind()
    {
        // Initialize variables
        int binding;
        String str = "";
        String name;
        InputStream fis = null;
        BufferedReader br;
        String line;

        // Try to create FIS
        try
        {
            fis = new FileInputStream(keybindsFile);
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Create BR
        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));

        try
        {
            while ((line = br.readLine()) != null)
            {
                str = line.replaceAll("[^\\d.]", "");
                name = line.replaceAll("[\\d.]", "");
                binding = 0;

                if (!str.equals(""))
                {
                    binding = Integer.parseInt(str);
                }

                name = name.replace("=", "");

                //System.out.println( "binding==" + binding );
                for (Mod m : CheatingEssentials.modinstance.mainModLoader.mods)
                {
                    //System.out.println( "Attempting to print keybinding: " + m.name );
                    //System.out.println( "m.name==" + m.name + "|||" + "name==" + name );
                    if (name.equalsIgnoreCase(m.name) || m.name.equalsIgnoreCase(name))
                    {
                        if (binding != 0)
                        {
                            m.keyBind = binding;
                            if(CheatingEssentials.debugMode){
                            CheatingEssentials.getCheatingEssentials().CELogAgent.logInfo( "Mod " + m.name + " was sucefully bound to key ID " + binding + "!" );
                            }
                            break;
                        }
                        else
                        {
                        	 if(CheatingEssentials.debugMode){
                        	CheatingEssentials.getCheatingEssentials().CELogAgent.logInfo("Null binding info found.");                        }
                    }}
                        
                    else
                    {
                    	 if(CheatingEssentials.debugMode){
                    		 System.out.println("I'm bored and because of it I put this :)");
                    }}
                }
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void writeNewKeybinds()
    {
        try
        {
            FileWriter fstream = new FileWriter(keybindsFile);
            BufferedWriter out = new BufferedWriter(fstream);

            // OOPish
            for (Mod m : CheatingEssentials.modinstance.mainModLoader.mods)
            {
                out.write(m.name + "=" + m.keyBind + "\r\n");
            }

            // Close the output stream
            out.close();

            if (CheatingEssentials.modinstance.minecraft.theWorld != null)
            {
                ConsoleHelper.addMessage(ChatColour.RED + "[Cheat Pack 2]" + " " + ChatColour.DARK_GRAY + "Keybinds written!");
            }
        }
        catch (Exception e)
        {
            System.err.println("[Cheat Pack 2.3] Error while writting keybinding D: : ");
            e.printStackTrace();
        }
    }
}
