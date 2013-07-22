package com.kodehawa.console;

import java.util.ArrayList;

import net.minecraft.src.Minecraft;

public class ConsoleHelper
{
    public static ArrayList<BaseCommand> commands;

    public ConsoleHelper()
    {
        commands = new ArrayList<BaseCommand>();
        addCommand(new Help());
        addCommand(new Enchant());
        addCommand(new Speed());
        addCommand(new AddFriend());
        addCommand(new AddEnemy());
        addCommand(new Reloader());
        //addCommand(new FlySpeed());
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

    @Deprecated
    public static void addMessage(String msg)
    {
        Minecraft.getMinecraft().thePlayer.addChatMessage(msg);
    }
}
