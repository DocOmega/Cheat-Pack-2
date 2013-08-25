package com.kodehawa;

import com.kodehawa.api.CJarLoader;
import com.kodehawa.core.Strings;
import com.kodehawa.module.ModuleManager;
import com.kodehawa.module.loader.BaseLoader;
import com.kodehawa.playerrelations.Enemy;
import com.kodehawa.playerrelations.Friend;
import net.minecraft.src.Minecraft;

import com.kodehawa.util.FileManager;
import com.kodehawa.util.KeyboardListener;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Utils;
import com.kodehawa.util.wrapper.Wrapper;
import com.reeszrbteam.ce.console.CommandManager;

public final class CheatingEssentials {

    public volatile static CheatingEssentials modinstance;

	public CheatingEssentials( ) {
        CELogAgent("Starting Cheating Essentials " + Strings.MOD_VERSION + "...");
        modinstance = this;
        initializeSingletons();
        CELogAgent("Cheating Essentials " + Strings.MOD_VERSION +  " started in Minecraft 1.6.2");
	}

	public static void onStart(){
        CommandManager.getInstance();
	}

    private void initializeSingletons(){
        ModuleManager.getInstance();
        CJarLoader.getInstance();
        BaseLoader.getInstance();
        Enemy.getInstance();
        Friend.getInstance();
        FileManager.getInstance();
    }

	public static CheatingEssentials getCheatingEssentials(){
		return modinstance;
	}

	public Wrapper getModWrapper(){
		return Wrapper.getWInstance();
	}

    public Utils getUtils() {
		return Utils.getInstance();
	}

	public static Minecraft getMinecraftInstance(){
		return Minecraft.getMinecraft();
	}

	public void tick() {

		for(Tickable tickable : ModuleManager.getInstance().modInternalTicksArray){
			tickable.tick();
		}

		KeyboardListener.getInstance().handleKeys();
	}
	
	public static void CELogAgent(String log){
		getMinecraftInstance().field_94139_O.logInfo("[Cheating Essentials] " + log);
	}
	
	public static void CELogErrorAgent(String elog){
		getMinecraftInstance().field_94139_O.logSevere( "[Cheating Essentials] " + elog);
	}

}
