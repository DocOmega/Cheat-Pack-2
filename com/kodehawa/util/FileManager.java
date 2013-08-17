package com.kodehawa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.ModManager;
import com.kodehawa.mods.ModuleXray;

public class FileManager {
    public static File mainDir;
    public static File crashDir;
    private static volatile FileManager instance;

    private Minecraft mc;
    
    public FileManager( ) {
        crashDir = new File( CheatingEssentials.getCheatingEssentials().getMinecraftInstance().mcDataDir + File.separator + "log");
		mainDir = new File( CheatingEssentials.getCheatingEssentials().getMinecraftInstance().mcDataDir, "/config/Cheating Essentials/CEXrayBlockList.txt");

        loadXrayList();
        if(!mainDir.exists()){
            mainDir.getParentFile().mkdirs();
            try {
				mainDir.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        saveXrayList();
        }
    }
        

    public static void writeCrash( String alah ) {
        try {
            crashDir.getParentFile().mkdirs();
            crashDir.createNewFile();
            DateFormat format = new SimpleDateFormat( "MM_dd_yyyy-HH_mm_ss" );
            Date date = new Date( );
            File file = new File( crashDir.getAbsolutePath( ), "CECrashLog-".concat( format.format( date ) ).concat(
                    ".log" ) );
            BufferedWriter outWrite = new BufferedWriter( new FileWriter( file ) );
            outWrite.write( alah );
            outWrite.close( );
        } catch( Exception error ) {
           CheatingEssentials.getCheatingEssentials().CELogErrorAgent( "Can't write a crash log. Ohh the irony." );
        }
    }
    
	
	/**
	 * Now with a configurable Xray :D
	 * Write the entire file again when a block it's changed in-game
	 */
	
    public static void saveXrayList( ) {
        try {
        	CheatingEssentials.getCheatingEssentials().CELogAgent("Writting X-Ray block list configuration file...");
            File file = new File( mainDir, "" );
            BufferedWriter bufferedwritter = new BufferedWriter( new FileWriter( file ) );
            for( int i : ModuleXray.xrayBlocks ) {
            	bufferedwritter.write( i + "\r\n" );
            }
            bufferedwritter.close( );
        	
        } catch( Exception ex ) {
        	CheatingEssentials.getCheatingEssentials().CELogErrorAgent("Can't write X-Ray configuration file! Custom blocks for X-Ray will be disabled!");
        	 ex.printStackTrace( );
        	 CheatingEssentials.getCheatingEssentials().CELogErrorAgent("Error in CE init: " + ex.toString( ) );
	            ex.printStackTrace( );
        }
    }
    
    /**
     * Load the integers of the Xray list. Only readed once
     * If a error it's finded it saves the X-Ray list again for prevent errors.
     */
    
    public static void loadXrayList( ) {
        try {
        	File file = new File( mainDir, "" );
            FileInputStream fstream = new FileInputStream( file.getAbsolutePath( ) );
            DataInputStream in = new DataInputStream( fstream );
            BufferedReader br = new BufferedReader( new InputStreamReader( in ) );
            String line;
            while( ( line = br.readLine( ) ) != null ) {
                String curLine = line.toLowerCase( ).trim( );
                int id = Integer.parseInt( curLine );
                ModuleXray.xrayBlocks.add( id );
            }
            br.close( );
        } catch( Exception ex ) {
        	CheatingEssentials.getCheatingEssentials().CELogErrorAgent("Can't load X-Ray list. Unreliable results!");
        	CheatingEssentials.getCheatingEssentials().CELogErrorAgent( "Error in CE init: " + ex.toString( ) );
            ex.printStackTrace( );
            saveXrayList( );
    } 
    }
    
    public static FileManager getInstance() {
        if (instance == null) {
                instance = new FileManager();
        }
        return instance;
}
    

}
