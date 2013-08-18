package com.kodehawa.hooks;

import net.minecraft.src.*;

import java.io.IOException;

public class NetworkHandler extends NetClientHandler {

    public NetworkHandler(Minecraft par1Minecraft, String par2Str, int par3) throws IOException {
        super(par1Minecraft, par2Str, par3);
    }

    public NetworkHandler(Minecraft par1Minecraft, String par2Str, int par3, GuiScreen par4GuiScreen) throws IOException
    {
        super(par1Minecraft, par2Str, par3, par4GuiScreen);
    }

    public NetworkHandler(Minecraft par1Minecraft, IntegratedServer par2IntegratedServer) throws IOException
    {
        super(par1Minecraft, par2IntegratedServer);
    }

}
