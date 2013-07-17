/**
 * This it's just a test!
 */

package com.kodehawa.api.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public class Reflector {
	
	public static void GetEntityPlayer(){
		
		Class entityplayer = net.minecraft.src.EntityPlayer.class;
		String className = entityplayer.getName();
		System.out.print(className + "\n");
		int classModifier = entityplayer.getModifiers();
		System.out.print(Modifier.isPublic(classModifier) + "\n");
        Class[] interfaces = entityplayer.getInterfaces();
        Class classSuper = entityplayer.getSuperclass();
        Method[] classMethods = entityplayer.getMethods();
        
        for(Method method : classMethods){
        	
            if(method.getName().startsWith("get")){
        		
            	System.out.println("Getter Method");
            	
        	}
            else if(method.getName().startsWith("set")){
        		
            	System.out.println("Setter Method");

        	}
            
            System.out.println("Return Type" + " " + method.getReturnType());
            
            Class[] parameterType = method.getParameterTypes();
            
            System.out.println("Parameters");
            
            for(Class parameter : parameterType){
            	System.out.println(parameter.getName());
            }
            
            System.out.println("");
        }
        
        Constructor constructor = null;
        Object constructor2 = null;
        try {
			constructor = entityplayer.getConstructor(new Class[]{World.class});
			constructor2 = entityplayer.getConstructor(int.class, String.class).newInstance(12, "Random String");
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Class[] constructParameters = constructor.getParameterTypes();
        
        for(Class parameter : constructParameters){
        	
        	System.out.print(parameter);
        	
        }
        
        EntityPlayer newEntityPlayer = null;
        World world = null;
        
        try {
			newEntityPlayer = (EntityPlayer) constructor.newInstance(world);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Method privateStringName = null;
        
        String someString = "jump";
        try {
			privateStringName = EntityPlayer.class.getDeclaredMethod("jump");
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        privateStringName.setAccessible(true);
        try {
			privateStringName.invoke(constructor2, constructParameters);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	

}
