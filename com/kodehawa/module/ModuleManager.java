package com.kodehawa.module;

import java.util.ArrayList;

import net.minecraft.src.Gui;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Console;

public class ModuleManager {
    
    /**
     * Because I can, dammit.
     */
    public static int i = 0;
    
    private static ArrayList< Module > modules;
    
    public ModuleManager( ) {
        modules = new ArrayList< Module >( );
        i = 0;
    }
    
    public static void addModule( Module e ) {
        i++ ;
        CheatingEssentials.getCheatingEssentials().CELogAgent.logInfo( "CheatingEssentials " + "Module #" + i + " loaded: " + e.getName( ) + "!" );
        modules.add( e );
    }
    
    public void addModules( Module... e ) {
        for( Module m : e ) {
            addModule( m );
        }
    }
    
    public static ArrayList< Module > getModules( ) {
        return modules;
    }
    
    public Module getModuleByName( String s ) {
        for( Module e : modules ) {
            if( e.getName( ).toLowerCase( ).equalsIgnoreCase( s.toLowerCase( ) ) ) {
                return e;
            }
        }
        return null;
    }

	public static void addModules(Console console, Fly fly,
			FastPlace fastPlace, Gui gui, ChestESP chestESP, Xray xray,
			FullBright fullBright, KillAura killAura, Sprint sprint,
			AntiKnockback antiKnockback) {
		// TODO Auto-generated method stub
		
	}

	
	}

