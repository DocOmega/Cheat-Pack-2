package com.kodehawa.util.wrapper;

import java.lang.reflect.Field;
import java.nio.IntBuffer;

import net.minecraft.src.Minecraft;
import net.minecraft.src.Packet;
import net.minecraft.src.ScaledResolution;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.event.EventHandler;
import com.kodehawa.gui.api.components.ModuleGui;
import com.kodehawa.mods.ModManager;

public class Wrapper
{
	private Minecraft mc;
	
    public Minecraft getMinecraft()
    {
       return mc;
    }

   
    public ScaledResolution getScaledResolution()
    {
        Minecraft mc = getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        return sr;
    }

    public int getWidth()
    {
        return getScaledResolution().getScaledWidth();
    }

    public int getHeight()
    {
        return getScaledResolution().getScaledHeight();
    }

    
    
    public ModManager getModuleManager()
    {
        try
        {
            Field mang = CheatingEssentials.class.getDeclaredField("mmanager");
            mang.setAccessible(true);
            ModManager mm = (ModManager) mang.get(null);
            return mm;
        }
        catch (Exception e)
        {
            System.err.println("********COULD NOT GET MODULE MANAGER********");
            e.printStackTrace();
            System.err.println("********SHUTTING DOWN********");
            System.exit(1);
            return null;
        }
    }

    public EventHandler getEventHandler()
    {
        try
        {
            Field mang = CheatingEssentials.class.getDeclaredField("eventHandler");
            mang.setAccessible(true);
            EventHandler gm = (EventHandler) mang.get(null);
            return gm;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("Y0L0sw4q");
            return null;
        }
    }

    public ConsoleHelper getConsoleManager()
    {
        try
        {
            Field mang = CheatingEssentials.class.getDeclaredField("cmanager");
            mang.setAccessible(true);
            ConsoleHelper cm = (ConsoleHelper) mang.get(null);
            return cm;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("Y0L0sw4q");
            return null;
        }
    }

    public ModuleGui getGui()
    {
        try
        {
            Field mang = CheatingEssentials.class.getDeclaredField("modgui");
            mang.setAccessible(true);
            ModuleGui gm = (ModuleGui) mang.get(null);
            return gm;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("Y0L0sw4q");
            return null;
        }
    }

    public int getScreenWidth()
    {
        IntBuffer viewport = BufferUtils.createIntBuffer(16);
        GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);
        return(Math.round(viewport.get(2)));
    }

    public int getScreenHeight()
    {
        IntBuffer viewport = BufferUtils.createIntBuffer(16);
        GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);
        return(Math.round(viewport.get(3)));
    }

    public void sendPacket(Packet yoloswaq)
    {
        getMinecraft().thePlayer.sendQueue.addToSendQueue(yoloswaq);
    }
}