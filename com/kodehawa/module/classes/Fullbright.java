package com.kodehawa.module.classes;

import net.minecraft.src.Minecraft;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.enums.EnumGuiCategory;

public class Fullbright extends ModuleBase {

	public Fullbright( ) {
		super("Full Bright", "No more darkness!", "1.6.2",
				Keyboard.KEY_F, EnumGuiCategory.WORLD, true);
		// TODO Auto-generated constructor stub
        super.setTick(true);
	}

	@Override
	public void onEnableModule() {
	}

	@Override
	public void onDisableModule() {
        CheatingEssentials.getMinecraftInstance().theWorld.provider.registerWorld(Minecraft.getMinecraft().theWorld);
    }

    @Override
    public void tick() {
        float[] brightness = CheatingEssentials.getMinecraftInstance().theWorld.provider.lightBrightnessTable;
        for(int i = 0; i < brightness.length; i++) {
            brightness[i] = 1.0F;
        }
    }
}
