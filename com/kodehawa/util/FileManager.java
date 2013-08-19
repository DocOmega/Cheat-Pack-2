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

import com.kodehawa.module.classes.BlockESP;
import com.kodehawa.module.classes.Xray;
import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
public class FileManager {
    public static File mainDir;
    public static File someDir;
    public static File crashDir;
    private static volatile FileManager instance;

    private Minecraft mc;
    
    public FileManager( ) {
        crashDir = new File( CheatingEssentials.getCheatingEssentials().getMinecraftInstance().mcDataDir + File.separator + "log");
		mainDir = new File( CheatingEssentials.getCheatingEssentials().getMinecraftInstance().mcDataDir, "/config/Cheating Essentials/CEXrayBlockList.txt");
        someDir = new File( CheatingEssentials.getCheatingEssentials().getMinecraftInstance().mcDataDir, "/config/Cheating Essentials/CEBlockESPList.txt");

        if(!mainDir.exists()){
            mainDir.getParentFile().mkdirs();
            try {
				mainDir.createNewFile();
                saveXrayList();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if(!someDir.exists()){
            someDir.getParentFile().mkdirs();
            try{
                someDir.createNewFile();
                saveBlockESPList();
            }
            catch(IOException e){
              e.printStackTrace();
            }
        }
        loadXrayList();
        loadBlockESPList();
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
        	CheatingEssentials.getCheatingEssentials().CELogAgent("Writting X-Ray list configuration file...");
            File file = new File( mainDir, "" );
            BufferedWriter bufferedwritter = new BufferedWriter( new FileWriter( file ) );
            for( int i : Xray.xrayBlocks ) {
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
     * Save BlockESP configs. inb4flamewar
     */
    public static void saveBlockESPList(){
        try {
            CheatingEssentials.getCheatingEssentials().CELogAgent("Writting BlockESP block list configuration file...");
            File file = new File( someDir, "" );
            BufferedWriter bufferedwritter = new BufferedWriter( new FileWriter( file ) );
            for( int i : BlockESP.espList ) {
                bufferedwritter.write( i + "\r\n" );
            }
            bufferedwritter.close( );

        } catch( Exception ex ) {
            CheatingEssentials.getCheatingEssentials().CELogErrorAgent("Can't write BlockESP configuration file! Custom blocks for X-Ray will be disabled!");
            ex.printStackTrace( );
            CheatingEssentials.getCheatingEssentials().CELogErrorAgent("Error in CE init: " + ex.toString( ) );
            ex.printStackTrace( );
        }
    }

    /**
     * Loads BlockESP list
     */
    public static void loadBlockESPList(){
        try {
            File file = new File( someDir, "" );
            FileInputStream fstream = new FileInputStream( file.getAbsolutePath( ) );
            DataInputStream in = new DataInputStream( fstream );
            BufferedReader br = new BufferedReader( new InputStreamReader( in ) );
            String line;
            while( ( line = br.readLine( ) ) != null ) {
                String curLine = line.toLowerCase( ).trim( );
                int id = Integer.parseInt( curLine );
                BlockESP.espList.add( id );
            }
            br.close( );
        } catch( Exception ex ) {
            CheatingEssentials.getCheatingEssentials().CELogErrorAgent("Can't load Block ESP list. Unreliable results!");
            CheatingEssentials.getCheatingEssentials().CELogErrorAgent( "Error in CE init: " + ex.toString( ) );
            ex.printStackTrace( );
            saveBlockESPList( );
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
                Xray.xrayBlocks.add( id );
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
