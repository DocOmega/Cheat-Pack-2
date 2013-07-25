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
import com.kodehawa.module.Module;
import com.kodehawa.module.ModuleManager;

/**
 * Dynamically loads custom Modules/Commands into the client. Allows for
 * customization beyond what the client itself contains.
 * 
 * @author godshawk
 * 
 */
public class CJarLoader {
    
    /**
     * The directory from which we load modules. Jar files in this directory
     * will have their classes checked for the "@CommandAnnotation" or
     * "@ModuleAnnotation". If the annotation is NOT found, the class is simply
     * injected into the classpath. If the annotation IS found, the class is
     * loaded and instantiated, then added to the corresponding category. Note
     * that this only works if the constructor for the class takes no
     * parameters.
     */
    File jarDir = new File( CheatingEssentials.getCheatingEssentials().getMinecraftInstance( ).mcDataDir + File.separator + "colony"
            + File.separator + "modules" );
    
    public CJarLoader( ) {
        // TODO Auto-generated constructor stub
        if( !jarDir.exists( ) ) {
            jarDir.mkdirs( );
            jarDir.mkdir( );
        }
        loadJars( );
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
                                        Module q = ( Module ) ctr.newInstance( );
                                        log( "Module added: " + yolo.getName( ).replace( "/", "." ) );
                                        ModuleManager.addModule( q );
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
        } catch( Exception e ) {
            e.printStackTrace( );
        }
    }
    
    /**
     * Logs a message.
     * 
     * @param s
     */
    public void log( String s ) {
        CheatingEssentials.getCheatingEssentials().CELogAgent.logInfo( "CJarLoader" + s );
    }
}
