package com.kodehawa.core;

import com.kodehawa.CheatingEssentials;

import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.ModuleManager;
import org.apache.commons.lang3.text.WordUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.text.NumberFormat;

public class DebugInfo {


    public static final void debugInfo() {



        final Runtime r = Runtime.getRuntime();
        final NumberFormat n = NumberFormat.getInstance();
        CheatingEssentials.getCheatingEssentials().CELogAgent("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
        CheatingEssentials.getCheatingEssentials().CELogAgent("OS: " + WordUtils.capitalizeFully(System.getProperty("os.name")));

        CheatingEssentials.getCheatingEssentials().CELogAgent("Loaded " + ModuleManager.getInstance().modules.size() + " module(s)!");
        CheatingEssentials.getCheatingEssentials().CELogAgent("Loaded " + ModuleManager.getInstance().worldModules.size() + " World module(s)!");
        CheatingEssentials.getCheatingEssentials().CELogAgent("Loaded " + ModuleManager.getInstance().playerModules.size() + " Player module(s)!");
        CheatingEssentials.getCheatingEssentials().CELogAgent("Loaded " + ModuleManager.getInstance().utilsModules.size() + " Utils module(s)!");

    }
}
