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
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;

public class FileManager {
    public static File colonyDir;
    public static File crashDir;

    private Minecraft mc;
    
    public FileManager( ) {
        colonyDir = new File( CheatingEssentials.getCheatingEssentials().getMinecraftInstance().mcDataDir + File.separator + ".cfg" );
        crashDir = new File( CheatingEssentials.getCheatingEssentials().getMinecraftInstance().mcDataDir + File.separator + "log");
        
        if( !colonyDir.exists( ) ) {
            colonyDir.mkdirs( );
        }
        
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
            for( Mod mod : ModManager.getModules( ) ) {
                
                out.write( "<tr>\r\n" );
                out.write( "<td>" + mod.name + "</td>\r\n" );
                out.write( "<td>" + Keyboard.getKeyName( mod.getKeybind( ) ).charAt( 0 )
                        + Keyboard.getKeyName( mod.getKeybind( ) ).substring( 1 ).toLowerCase( ) + "</td>\r\n" );
           //     out.write( "<td>" + mod.getDesc( ) + "</td>\r\n" );
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
    
    public static void writeCrash( String alah ) {
        try {
            DateFormat format = new SimpleDateFormat( "MM_dd_yyyy-HH_mm_ss" );
            Date date = new Date( );
            File file = new File( crashDir.getAbsolutePath( ), "crashlog-".concat( format.format( date ) ).concat(
                    ".log" ) );
            BufferedWriter outWrite = new BufferedWriter( new FileWriter( file ) );
            outWrite.write( alah );
            outWrite.close( );
        } catch( Exception error ) {
            System.out.println( "Can't write a crash log. Ohh the irony." );
        }
    }

}
