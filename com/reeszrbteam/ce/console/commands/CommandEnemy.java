package com.reeszrbteam.ce.console.commands;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.Vars;
import com.kodehawa.players.FrenemyManager;
import com.kodehawa.util.ChatColour;

import com.reeszrbteam.ce.console.BaseCommand;

public class CommandEnemy extends BaseCommand{

	public CommandEnemy() {
		super("enemy", "Kodehawa", "1.6.2");
	}

	@Override
	public void runCommand(String s, String[] args) {
		try
		{
			if(args[0].equalsIgnoreCase("add"))
			{
				String name = args[1];
				if(!Vars.enemies.contains(name))
				{
					Vars.enemies.add(name);
					FrenemyManager.getInstance().writeEnemies();
					CheatingEssentials.getCheatingEssentials().getUtils().addChatMessage("Enemied " + name + ".");
				}else
				{
					CheatingEssentials.getCheatingEssentials().getUtils().addChatMessage(name + " is already your enemy.");
				}
			}
			if(args[0].equalsIgnoreCase("del"))
			{
				String name = args[1];
				if(Vars.enemies.contains(name))
				{
					Vars.enemies.remove(name);
					FrenemyManager.getInstance().writeEnemies();
					CheatingEssentials.getCheatingEssentials().getUtils().addChatMessage("Removed " + name + " from enemies.");
				}else
				{
					CheatingEssentials.getCheatingEssentials().getUtils().addChatMessage(name + " is not your enemy.");
				}
			}
			if(args[0].equalsIgnoreCase("clear"))
			{
				try
				{
					Vars.enemies.clear();
					FrenemyManager.getInstance().writeEnemies();
					CheatingEssentials.getCheatingEssentials().getUtils().addChatMessage("Cleared friends.");
				}catch(Exception e) {}
			}
		}catch(Exception e)
		{
			CheatingEssentials.getCheatingEssentials().getUtils().addChatMessage("Usage: " + getSyntax());
		}
	}

	@Override
	public String getDescription() {
		return "Adds and Removes Enemy to Show Red Username in Radar";
	}

	@Override
	public String getSyntax() {
		return "enemy add/del <Username>, enemy clear";
	}

}
