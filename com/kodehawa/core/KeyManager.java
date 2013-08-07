
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
        keybindsFile = new File(CheatingEssentials.modinstance.minecraft.mcDataDir, "/config/Cheating Essentials/CKeybinds.cfg");

        if (!keybindsFile.exists())
        {
            try
            {
                // Create file
                keybindsFile.getParentFile().mkdirs();
                keybindsFile.createNewFile();
                FileWriter fstream = new FileWriter(keybindsFile);
                BufferedWriter out = new BufferedWriter(fstream);
                for (Mod m : CheatingEssentials.getCheatingEssentials().mods){
                out.write(m.keybind);
                }
                out.close();
            }
            catch (Exception e)       // Catch exception if any
            {
            	System.out.println("Debug");           }

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
    }

    public void writeNewKeybinds()
    {
        try
        {
            FileWriter fstream = new FileWriter(keybindsFile);
            BufferedWriter out = new BufferedWriter(fstream);

            // OOPish
            for (Mod m : CheatingEssentials.getCheatingEssentials().mods)
            {
                out.write(m.name + "=" + m.keybind + "\r\n");
            }

            // Close the output stream
            out.close();

            if (CheatingEssentials.modinstance.minecraft.theWorld != null)
            {
                ConsoleHelper.addMessage("Keybinds written!");
            }
        }
        catch (Exception e)
        {
            System.err.println("Error while writting keybinding: " + e.toString());
            e.printStackTrace();
        }
    }
}
