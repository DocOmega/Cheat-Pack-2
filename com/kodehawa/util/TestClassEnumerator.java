package com.kodehawa.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.kodehawa.module.annotations.ModuleExperimental;


/**
 * Enumerates all classes in a package. Originally implemented in DarkBot by
 * DarkStorm_.
 * 
 * @see {@link https://github.com/DarkStorm652/DarkBot}
 * 
 * @author DarkStorm_
 * 
 */
@ModuleExperimental(setAs = "Unchecked")
public class TestClassEnumerator {
	
        @SuppressWarnings("resource")
		public static List<Class<?>> getClassesInPackage(String packageName) throws IOException {

                final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                final List<Class<?>> classes = new ArrayList<Class<?>>();
                URL packageURL;

                packageURL = classLoader.getResource(packageName.replace(".", "/"));

                if (packageURL.getProtocol().equals("jar")) {
                        packageName = packageName.replace(".", "/");
                        String jarFileName;
                        JarFile jf;
                        Enumeration<JarEntry> jarEntries;
                        String entryName;

                        // build jar file name, then loop through zipped entries
                        jarFileName = URLDecoder.decode(packageURL.getFile(), "UTF-8");
                        jarFileName = jarFileName.substring(5, jarFileName.indexOf("!"));
                        jf = new JarFile(jarFileName);
                        jarEntries = jf.entries();
                        while (jarEntries.hasMoreElements()) {
                                entryName = jarEntries.nextElement().getName();
                                if (entryName.startsWith(packageName) && (entryName.length() > (packageName.length() + 5))) {
                                        entryName = entryName.replace('/', '.');
                                        entryName = entryName.substring(0, entryName.lastIndexOf('.'));
                                        try {
                                                final Class c = Class.forName(entryName);
                                                classes.add(c);
                                        } catch (final ClassNotFoundException exception) {
                                                exception.printStackTrace();
                                        }
                                }
                        }

                        // loop through files in classpath
                } else {
                        try {
                                final Iterable<Class<?>> classesFromFile = getClasses(packageName);
                                for (final Class<?> c : classesFromFile) {
                                        classes.add(c);
                                }
                        } catch (final ClassNotFoundException exception) {
                                exception.printStackTrace();
                        }
                }
                return classes;
        }

        /**
         * Scans all classes accessible from the context class loader which belong
         * to the given package and sub packages.
         * 
         * @author Paulius Matulionis - stackoverflow.com
         * @param packageName
         *            the base package
         * @return The classes
         * @throws ClassNotFoundException
         *             if class not found exception occurs
         * @throws IOException
         *             if IO error occurs
         */
        private static Iterable<Class<?>> getClasses(final String packageName) throws ClassNotFoundException, IOException {
                final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                final String path = packageName.replace('.', '/');
                final Enumeration<URL> resources = classLoader.getResources(path);
                final LinkedList<File> dirs = new LinkedList<File>();
                while (resources.hasMoreElements()) {
                        final URL resource = resources.nextElement();
                        dirs.add(new File(resource.getFile()));
                }
                final LinkedList<Class<?>> classes = new LinkedList<Class<?>>();
                for (final File directory : dirs) {
                        classes.addAll(findClasses(directory, packageName));
                }
                return classes;
        }

        /**
         * Recursive method used to find all classes in a given directory and sub
         * directories.
         * 
         * @author Paulius Matulionis - stackoverflow.com
         * @param directory
         *            the base directory
         * @param packageName
         *            the package name for classes found inside the base directory
         * @return the classes
         * @throws ClassNotFoundException
         *             if class not found exception occurrs
         */
        private static LinkedList<Class<?>> findClasses(final File directory, final String packageName)
                        throws ClassNotFoundException {
                final LinkedList<Class<?>> classes = new LinkedList<Class<?>>();
                if (!directory.exists()) {
                        return classes;
                }
                final File[] files = directory.listFiles();
                if (files != null) {
                        for (final File file : files) {
                                if (file.isDirectory()) {
                                        classes.addAll(findClasses(file, packageName + "." + file.getName()));
                                } else if (file.getName().endsWith(".class")) {
                                        classes.add(Class.forName(packageName + '.'
                                                        + file.getName().substring(0, file.getName().length() - 6)));
                                }
                        }
                }
                return classes;
        }
}
