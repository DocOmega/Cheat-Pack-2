package com.kodehawa.gui;

import java.util.HashMap;

import net.minecraft.src.GuiIngame;
import net.minecraft.src.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatBase;
import com.kodehawa.core.CheckKey;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModuleFly;
import com.kodehawa.mods.ModuleFullbright;
import com.kodehawa.mods.ModuleXray;
import com.kodehawa.mods.Vars;

public class CGuiIngame extends GuiIngame
{
    public static int tick = 0;
    public static CheatBase cheatbase;
    private Minecraft mc;
    private ModuleXray xray = new ModuleXray(cheatbase, mc);
    private ModuleFly fly = new ModuleFly(cheatbase, mc);
    private ModuleFullbright fullbright = new ModuleFullbright(cheatbase, mc);
    private boolean keyStates[] = new boolean[ 256 ];
    private CheckKey ck;
    public HashMap<Mod, Integer> keyShit;

    public CGuiIngame(Minecraft par1Minecraft)
    {
        super(par1Minecraft);
        // TODO Auto-generated constructor stub
    }

    /**
     * Render the ingame overlay with quick icon bar, ...
     */
    @Override
    public void renderGameOverlay(float par1, boolean par2, int par3, int par4)
    {
        super.renderGameOverlay(par1, par2, par3, par4);

        if (checkKey(Keyboard.KEY_N))
        {
            Vars.ChestESP = !Vars.ChestESP;
        }

        GL11.glPushMatrix();
        GL11.glPopMatrix();
    }

    private boolean checkKey(int i)
    {
        if (Keyboard.isKeyDown(i) != keyStates [ i ])
        {
            return keyStates [ i ] = !keyStates [ i ];
        }
        else
        {
            return false;
        }
    }
}
