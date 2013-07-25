package com.kodehawa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.minecraft.src.Minecraft;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.Module;
import com.kodehawa.module.ModuleManager;
import com.kodehawa.module.Xray;

public class FileManager {
    public File colonyDir;
    private Minecraft mc;
    
    public FileManager( ) {
        colonyDir = new File( mc.mcDataDir + File.separator + "cfg" );
        
        if( !colonyDir.exists( ) ) {
            colonyDir.mkdirs( );
        }
        loadKeybinds( );
        loadXrayList( );
        
        try {
            File file = new File( colonyDir.getAbsolutePath( ), "help.html" );
            BufferedWriter out = new BufferedWriter( new FileWriter( file ) );
            out.write( "<div align=\"center\">" );
            out.write( "<font face = \"Verdana\">\r\n" );
            out.write( "<table border=\"1\">\r\n" );
            out.write( "<tr>\r\n" );
            out.write( "<th>Mod</th>\r\n" );
            out.write( "<th>Key Bind</th>\r\n" );
            out.write( "<th>Description</th>\r\n" );
            out.write( "</tr>\r\n" );
            for( Module mod : ModuleManager.getModules( ) ) {
                if( mod.getDesc( ).equals( "" ) ) {
                    continue;
                }
                out.write( "<tr>\r\n" );
                out.write( "<td>" + mod.getName( ) + "</td>\r\n" );
                out.write( "<td>" + Keyboard.getKeyName( mod.getKeybind( ) ).charAt( 0 )
                        + Keyboard.getKeyName( mod.getKeybind( ) ).substring( 1 ).toLowerCase( ) + "</td>\r\n" );
                out.write( "<td>" + mod.getDesc( ) + "</td>\r\n" );
                out.write( "</tr>\r\n" );
            }
            out.write( "</tr>\r\n" );
            out.write( "</table>\r\n" );
            out.write( "<br>" );
            out.write( "<table border=\"1\">\r\n" );
            out.write( "<tr>\r\n" );
            out.write( "<th>Command</th>\r\n" );
            out.write( "<th>Usage</th>\r\n" );
            out.write( "<th>Description</th>\r\n" );
            out.write( "</tr>\r\n" );
            
            out.write( "</tr>\r\n" );
            out.write( "</table>\r\n" );
            out.write( "</font></div>" );
            out.close( );
        } catch( Exception e ) {
            e.printStackTrace( );
        }
    }
    
    public void saveKeybinds( ) {
        try {
            File file = new File( colonyDir.getAbsolutePath( ), "keys.txt" );
            BufferedWriter out = new BufferedWriter( new FileWriter( file ) );
            for( Module xMod : ModuleManager.getModules( ) ) {
                out.write( "key-" + xMod.getName( ).toLowerCase( ).replace( " ", "" ) + ":"
                        + Keyboard.getKeyName( xMod.getKeybind( ) ) );
                out.write( "\r\n" );
            }
            out.close( );
        } catch( Exception e ) {
        }
    }
    
    public void loadKeybinds( ) {
        try {
            File file = new File( colonyDir.getAbsolutePath( ), "keys.txt" );
            FileInputStream fstream = new FileInputStream( file.getAbsolutePath( ) );
            DataInputStream in = new DataInputStream( fstream );
            BufferedReader br = new BufferedReader( new InputStreamReader( in ) );
            String line;
            while( ( line = br.readLine( ) ) != null ) {
                String curLine = line.toLowerCase( ).trim( );
                String[ ] s = curLine.split( ":" );
                String hack = s[ 0 ];
                int id = Keyboard.getKeyIndex( s[ 1 ].toUpperCase( ) );
                for( Module mod : ModuleManager.getModules( ) ) {
                    if( hack.equalsIgnoreCase( "key-" + mod.getName( ).toLowerCase( ).replace( " ", "" ) ) ) {
                        mod.setKeybind( id );
                    }
                }
            }
            br.close( );
        } catch( Exception err ) {
            err.printStackTrace( );
            saveKeybinds( );
            System.out.println( "Failed to initialize Cheating Essentials. Damn :(. " + err.toString( ) );
            err.printStackTrace( );
            
            String logString = "FT|CrashLog\r\n[PLAIN]\r\n---Begin plain text---\r\n";
            logString += "Console Log:\r\n";
            logString += "Failed to initialize Cheating Essentials. Damn :( " + err.toString( ) + "\r\n\r\n";
            for( StackTraceElement ele : err.getStackTrace( ) ) {
                logString += ele.getClassName( ) + " " + ele.toString( ) + "\r\n";
            }
            writeCrash( logString );
        }
    }
    
    
    public void saveXrayList( ) {
        try {
            File file = new File( colonyDir.getAbsolutePath( ), "xray.txt" );
            BufferedWriter out = new BufferedWriter( new FileWriter( file ) );
            for( int i : Xray.xrayBlocks ) {
                out.write( i + "\r\n" );
            }
            out.close( );
        } catch( Exception e ) {
        }
    }
    
    public void loadXrayList( ) {
        try {
            File file = new File( colonyDir.getAbsolutePath( ), "xray.txt" );
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
        } catch( Exception e ) {
            e.printStackTrace( );
            saveXrayList( );
        }
    }

    
    public void writeCrash( String alah ) {
        try {
            DateFormat format = new SimpleDateFormat( "MM_dd_yyyy-HH_mm_ss" );
            Date date = new Date( );
            File file = new File( colonyDir.getAbsolutePath( ), "crashlog-".concat( format.format( date ) ).concat(
                    ".xen" ) );
            BufferedWriter outWrite = new BufferedWriter( new FileWriter( file ) );
            outWrite.write( alah );
            outWrite.close( );
        } catch( Exception error ) {
            System.out.println( "Ohh the irony." );
        }
    }

}
