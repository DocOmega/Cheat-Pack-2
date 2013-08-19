package com.kodehawa.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.core.Strings;
import com.kodehawa.module.annotations.ModuleExperimental;
import com.kodehawa.module.classes.ChestESP;
import com.kodehawa.module.loader.BaseLoader;
import com.kodehawa.util.TestClassEnumerator;
import com.kodehawa.util.Tickable;

public class ModuleManager {

    public volatile ArrayList<ModuleBase> modules;
    public volatile ArrayList<ModuleBase> worldModules;
    public volatile ArrayList<ModuleBase> playerModules;
    public volatile ArrayList<ModuleBase> utilsModules;
    public ArrayList<String> enabledModules = new ArrayList<String>();
    public ArrayList<Tickable> modInternalTicksArray = new ArrayList<Tickable>();

    private volatile static ModuleManager instance;
	
	public ModuleManager( ){
        modules = new ArrayList<ModuleBase>();
        worldModules = new ArrayList<ModuleBase>();
        playerModules = new ArrayList<ModuleBase>();
        utilsModules = new ArrayList<ModuleBase>();

        CheatingEssentials.getCheatingEssentials().CELogAgent(
        		"Module System: Starting in Cheating Essentials " + Strings.MOD_VERSION + " for Minecraft 1.6.2...");
        
        List<Class<?>> modulesFound = new ArrayList<Class<?>>();
        
        try {
            modulesFound = TestClassEnumerator.getClassesInPackage(ChestESP.class.getPackage().getName());
    } catch (final IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
    }

        for (final Class<?> e : modulesFound) {
            try {
                    if (e.getName().contains("$")) {
                            continue;
                    }
                    final ModuleBase f = (ModuleBase) e.newInstance();
                    if (f.getClass().isAnnotationPresent(ModuleExperimental.class)) {
                    	 CheatingEssentials.getCheatingEssentials().CELogAgent("Module \"" + f.getName() + "\" is experimental! Use at own risk!");
                    }
                    addModule(f);
            } catch (final InstantiationException e1) {
                    e1.printStackTrace(); 
            } catch (final IllegalAccessException e1) {
                    e1.printStackTrace(); 
            }
    }

	}
	
	public void addModule(final ModuleBase e) {
        synchronized (modules) {
            modules.add( e );
            if (e.getClass().isAnnotationPresent(ModuleExperimental.class)) {
           	 CheatingEssentials.getCheatingEssentials().CELogAgent("Module \"" + e.getName() + "\" is WIP! Use at own risk!");
           }
        }
	}
	
	public void addWorldModule( final ModuleBase e ){
		synchronized(worldModules){
			worldModules.add( e );
			if (e.getClass().isAnnotationPresent(ModuleExperimental.class)) {
	           	 CheatingEssentials.getCheatingEssentials().CELogAgent("Module \"" + e.getName() + "\" is WIP! Use at own risk!");
	           }
		}
	}
	
	public void addPlayerModule( final ModuleBase e ){
		synchronized(playerModules){
			playerModules.add( e );
			if (e.getClass().isAnnotationPresent(ModuleExperimental.class)) {
	           	 CheatingEssentials.getCheatingEssentials().CELogAgent("Module \"" + e.getName() + "\" is WIP! Use at own risk!");
	           }
		}
	}
	
	public void addUtilModule( final ModuleBase e ){
		synchronized(utilsModules){
			utilsModules.add( e );
			if (e.getClass().isAnnotationPresent(ModuleExperimental.class)) {
	           	 CheatingEssentials.getCheatingEssentials().CELogAgent("Module \"" + e.getName() + "\" is WIP! Use at own risk!");
	           }
		}
	}

	public void removeModule(final ModuleBase e) {
        synchronized (modules) {
                modules.remove( e );
        }
	}
	
    public final ModuleBase getModuleByClass(final Class module) {
                synchronized (modules) {
                        for (final ModuleBase e : modules) {
                                if (e.getClass().equals(module)) {
                                        return e;
                                }
                        }
                }
                return null;
        }

     public final List<ModuleBase> getModules() {
                synchronized (modules) {
                        return Collections.unmodifiableList(modules);
                }
        }

    public void addToTick(Tickable tickable) {
        if (!modInternalTicksArray.contains(tickable))
        {
            modInternalTicksArray.add(tickable);
        }

    }

    public void removeFromCurrentTick(Tickable tickable) {
        if (modInternalTicksArray.contains(tickable))
        {
            modInternalTicksArray.remove(tickable);
        }
    }

 	public static ModuleManager getInstance(){
 		if(instance == null){
 			instance = new ModuleManager();
 		}
 		return instance;
 	}
 	
}
