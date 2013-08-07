package com.kodehawa.api;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.console.BaseCommand;
import com.kodehawa.mods.Mod;


public class CJarLoader extends Thread {
    
    /**
     * The directory from which we load modules. Jar files in this directory
     * will have their classes checked for the "@CommandAnnotation" or
     * "@ModuleAnnotation". If the annotation is NOT found, the class is simply
     * injected into the classpath. If the annotation IS found, the class is
     * loaded and instantiated, then added to the corresponding category. Note
     * that this only works if the constructor for the class takes no
     * parameters.
     */
    File jarDir = new File( CheatingEssentials.getCheatingEssentials().getMinecraftInstance( ).mcDataDir + File.separator + "Cheating Essentials"
            + File.separator + "Modules" );
    
    public CJarLoader( ) {
        // TODO Auto-generated constructor stub
    	if( !jarDir.exists( ) ) {
            jarDir.mkdirs( );
            jarDir.mkdir( );
        }
    	run( );
    }
    
    /**
     * Attempts to load all jar files in the module directory. It gets the list
     * of files in the directory, then checks if the file is a jar (Ie, has the
     * .jar extension). It gets the files in the jar, and if the file is a
     * class, loads it. If the file has the CommandAnnotation or
     * ModuleAnnotation, it is also instantiated and added into the
     * Command/Module System.
     * 
     * @UPDATE: 5/23/2013: Zip File support added
     */
    public void loadJars( ) {
     
        try {
            File[ ] flist = jarDir.listFiles( );
            
            URLClassLoader ucl;
            FileInputStream fis;
            ZipInputStream jis;
            for( File f : flist ) {
                log( "File found: " + f.getName( ) );
                if( f.isFile( ) ) {
                    if( f.getName( ).endsWith( ".jar" ) || f.getName( ).endsWith( ".JAR" )
                            || f.getName( ).endsWith( ".zip" ) || f.getName( ).endsWith( ".ZIP" ) ) {
                        log( "Module found: " + f.getName( ) );
                        log( "Attempting to load classes from " + f.getName( ) + "..." );
                        URL[ ] url = {
                            new URL( "jar:file:///" + jarDir + f.getName( ) + "!/" )
                        };
                        ucl = new URLClassLoader( url );
                        fis = new FileInputStream( f );
                        jis = new ZipInputStream( fis );
                        Class clazz;
                        ZipEntry yolo = null;
                        while( ( yolo = jis.getNextEntry( ) ) != null ) {
                            if( !yolo.isDirectory( ) ) {
                                if( !yolo.getName( ).endsWith( ".class" ) ) {
                                    log( "ZipEntry found: " + yolo.getName( ) );
                                }
                                if( yolo.getName( ).endsWith( ".class" ) ) {
                                    log( "Class found: " + yolo.getName( ) );
                                    clazz = ucl.loadClass( yolo.getName( ).substring( 0, yolo.getName( ).length( ) - 6 )
                                            .replace( "/", "." ) );
                                    log( "Class loaded: " + yolo.getName( ).replace( "/", "." ) );
                                    if( clazz.isAnnotationPresent( CommandAnnotation.class ) ) {
                                        Constructor ctr = clazz.getConstructor( );
                                        BaseCommand q = ( BaseCommand ) ctr.newInstance( );
                                        log( "Command added: " + yolo.getName( ).replace( "/", "." ) );
                                        CheatingEssentials.getCheatingEssentials( ).getModWrapper( ).getConsoleManager( ).addCommand( q );
                                    } else if( clazz.isAnnotationPresent( ModuleAnnotation.class ) ) {
                                        Constructor ctr = clazz.getConstructor( );
                                        Mod q = ( Mod ) ctr.newInstance( );
                                        log( "Module added: " + yolo.getName( ).replace( "/", "." ) );
                                        CheatingEssentials.getCheatingEssentials().mainModLoader.f3utils.add( q );
                                    }
                                }
                            }
                        }
                        jis.close( );
                        fis.close( );
                        ucl.close( );
                    }
                }
            }
        } catch( Exception ex ) {
            System.out.println( "Error in CE init: " + ex.toString( ) );
            ex.printStackTrace( );
            
            String logString = "FT|CrashLog\r\n[PLAIN]\r\n---Begin plain text---\r\n";
            logString += "Console Log:\r\n";
            logString += "Error in CE Thread init: " + ex.toString( ) + "\r\n\r\n";
            for( StackTraceElement ele : ex.getStackTrace( ) ) {
                logString += ele.getClassName( ) + " " + ele.toString( ) + "\r\n";
            }
            //CheatingEssentials.getCheatingEssentials().writeCrash(logString);
        }
    }
         
    
    /**
     * Logs a message.
     * 
     * @param s
     */
    public void log( String s ) {
        CheatingEssentials.getCheatingEssentials().CELogAgent( "CJarLoader: " + s );
    }

	private volatile boolean stopRequested = false;
	
	public void requestStop() {
		  stopRequested = true;
		}
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread thread = new Thread("Cheating Essentials Loader Thread");
        thread.setName("Cheating Essentials Loader Thread");
        thread.setPriority(1);
        thread.start();
        CheatingEssentials.getCheatingEssentials().CELogAgent("External Module Loader Thread: Initialization");

		while(!stopRequested){
		loadJars();
		
		requestStop();
		}
	}
}
