package com.kodehawa.console;

import java.util.ArrayList;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;

import net.minecraft.src.Minecraft;

public class ConsoleHelper
{
    public static ArrayList<BaseCommand> commands;
    private static volatile ConsoleHelper instance;

    public ConsoleHelper()
    {
        commands = new ArrayList<BaseCommand>();
        addCommand(new Help());
        addCommand(new Enchant());
        addCommand(new Speed());
        addCommand(new AddFriend());
        addCommand(new AddEnemy());
        addCommand(new FlySpeed());
        addCommand(new XrayAdd());
        addCommand(new XrayRemove());
        
        for (BaseCommand b : ConsoleHelper.commands)
        {
        	CheatingEssentials.getCheatingEssentials().CELogAgent("Mod command loaded: " + b + " (Name:" + b.getName() + ")" );
        }
        

    }

    public void addCommand(BaseCommand cmd)
    {
        commands.add(cmd);
    }

    /**
     * Runs a command
     *
     * @param cmd
     */
    public static String parse(String[ ] cmd)
    {
        for (int i = 0; i < commands.size(); i++)
        {
            if (cmd [ 0 ].equalsIgnoreCase(commands.get(i).getName()))
            {
                commands.get(i).onRun(cmd);
                return commands.get(i).output();
            }
        }

        return null;
    }

    public ArrayList getCommands()
    {
        return this.commands;
    }


    public static void addMessage(String msg)
    {
        Minecraft.getMinecraft().thePlayer.addChatMessage(msg);
    }
     
    public static ConsoleHelper getInstance( ) {
        if (instance == null) {
                instance = new ConsoleHelper( );
        }
        return instance;
}
}
