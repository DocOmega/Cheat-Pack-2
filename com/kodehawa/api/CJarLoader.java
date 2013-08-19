package com.kodehawa.api;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.ModuleManager;
import com.kodehawa.util.FileManager;


public class CJarLoader extends Thread {
    
    /**
     * The directory from which we load modules.
     */
    File jarDir = new File( CheatingEssentials.getCheatingEssentials().getMinecraftInstance( ).mcDataDir + File.separator + "modules"
            + File.separator + "files" );
    private static volatile CJarLoader instance;
    
    public CJarLoader( ) {
        // TODO Auto-generated constructor stub
        instance = this;
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
                                 if(yolo.getName().startsWith("Player_") && yolo.getName().endsWith(".class")){
                                	  Constructor ctr = clazz.getConstructor( );
                                      ModuleBase q = ( ModuleBase ) ctr.newInstance( );
                                      log( "Player Module added: " + yolo.getName( ).replace( "/", "." ) );
                                      ModuleManager.getInstance().addPlayerModule( q );
                                      ModuleManager.getInstance().addModule( q );
                                 }
                                 if(yolo.getName().startsWith("World_") && yolo.getName().endsWith(".class")){
                               	  Constructor ctr = clazz.getConstructor( );
                                     ModuleBase q = ( ModuleBase ) ctr.newInstance( );
                                     log( "World Module added: " + yolo.getName( ).replace( "/", "." ) );
                                     ModuleManager.getInstance().addWorldModule( q );
                                     ModuleManager.getInstance().addModule( q );
                                }
                                if(yolo.getName().startsWith("Util_") && yolo.getName().endsWith(".class")){
                                  	  Constructor ctr = clazz.getConstructor( );
                                        ModuleBase q = ( ModuleBase ) ctr.newInstance( );
                                        log( "Utils Module added: " + yolo.getName( ).replace( "/", "." ) );
                                        ModuleManager.getInstance().addUtilModule( q );
                                        ModuleManager.getInstance().addModule( q );
                                   }
                                else{
                                	log("Can't recognize Module type.");
                                }
                        }
                        jis.close( );
                        fis.close( );
                        ucl.close( );
                    }
                }
            }
        }
     }
   }
            catch( Exception ex ) {
            System.out.println( "Error in CE init: " + ex.toString( ) );
            ex.printStackTrace( );
            
            String logString = "FT|CrashLog\r\n[PLAIN]\r\n---Begin plain text---\r\n";
            logString += "Console Log:\r\n";
            logString += "Error in CE Thread init: " + ex.toString( ) + "\r\n\r\n";
            for( StackTraceElement ele : ex.getStackTrace( ) ) {
                logString += ele.getClassName( ) + " " + ele.toString( ) + "\r\n";
            }
            FileManager.getInstance().writeCrash(logString);
        }
    }
         
    
    /**
     * Logs a message.
     * 
     * @param s
     */
    public void log( String s ) {
        CheatingEssentials.getCheatingEssentials().CELogAgent( "[External Loader] " + s );
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

    public static CJarLoader getInstance(){
        if(instance == null){
            instance = new CJarLoader();
        }
        return instance;
    }
}
