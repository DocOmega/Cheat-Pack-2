package com.kodehawa.core;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.console.BaseCommand;
import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.mods.Mod;
import org.apache.commons.lang3.text.WordUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.text.NumberFormat;

public class DebugInfo {


    public static final void debugInfo() {



        CheatingEssentials.getCheatingEssentials().CELogAgent(Strings.MOD_NAME + " " + Strings.MOD_VERSION + " starting in " + Strings.MINECRAFT_VERSION + "..." );
        CheatingEssentials.getCheatingEssentials().CELogAgent("Version folder: " + CheatingEssentials.getMinecraftInstance().func_110431_a(CheatingEssentials.getMinecraftInstance()));
        final Runtime r = Runtime.getRuntime();
        final NumberFormat n = NumberFormat.getInstance();
        CheatingEssentials.getCheatingEssentials().CELogAgent("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
        CheatingEssentials.getCheatingEssentials().CELogAgent("OS: " + WordUtils.capitalizeFully(System.getProperty("os.name")));
        CheatingEssentials.getCheatingEssentials().CELogAgent("Max Memory: " + n.format(r.maxMemory() / 1024));
        CheatingEssentials.getCheatingEssentials().CELogAgent("Free Memory: " + n.format(r.freeMemory() / 1024));
        CheatingEssentials.getCheatingEssentials().CELogAgent("Total Memory: " + n.format(r.totalMemory() / 1024));
        CheatingEssentials.getCheatingEssentials().CELogAgent("Number of available processors: "  + n.format(r.availableProcessors()));
        CheatingEssentials.getCheatingEssentials().CELogAgent("JVM Version: " + System.getProperty("java.version"));



        for(Mod m: CheatingEssentials.getCheatingEssentials().mods){
            CheatingEssentials.getCheatingEssentials().CELogAgent("Module Loaded: " + m + " (Name: " + m.name + ") " + "(Key: " + Keyboard.getKeyName(m.getKeybind()) + ")");
        }

        for (BaseCommand b : ConsoleHelper.commands)
        {
            CheatingEssentials.getCheatingEssentials().CELogAgent("Mod command loaded: " + b + " (Name: " + b.getName() + ")" );
        }

        CheatingEssentials.getCheatingEssentials().CELogAgent(Strings.MOD_NAME + " " + Strings.MOD_VERSION + " started succefully in " + Strings.MINECRAFT_VERSION);


    }
}
