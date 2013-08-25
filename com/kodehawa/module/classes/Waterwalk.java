package com.kodehawa.module.classes;

import com.kodehawa.api.reflection.ReflectorHelper;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.enums.EnumGuiCategory;

import java.util.HashMap;

public class Waterwalk extends ModuleBase {

	public Waterwalk( ) {
		super("Water Walk", "", "1.6.2", Keyboard.KEY_J, EnumGuiCategory.WORLD, true);
        super.setTick(true);
	}

    static boolean isObfuscated = false; /*Change it to true when reobfuscating in MCP*/

	@Override
	public void tick() {
		if (getPlayer().isInWater())
        {
            getPlayer().setSprinting(false);
            try{
                for(Object o : getMinecraft().theWorld.loadedEntityList)  {
                    if(o instanceof EntityPlayerSP || o instanceof EntityClientPlayerMP){
                        if(!isObfuscated){
                        ReflectorHelper.getPrivateMethod(EntityLivingBase.class, o, "jump");
                        if(isObfuscated){
                        ReflectorHelper.getPrivateMethod(EntityLivingBase.class, o, "bd");
                    }
                }
            }
                }
            }
            catch (Exception e){
                CheatingEssentials.CELogAgent("First failures in reflection code.");
            }
            getPlayer().motionY /= 2;
        }
	}

	@Override
	public void onEnableModule() {
	}

	@Override
	public void onDisableModule() {
	}

}
