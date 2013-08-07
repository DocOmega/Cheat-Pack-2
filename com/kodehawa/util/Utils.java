package com.kodehawa.util;

import net.minecraft.src.Minecraft;

public class Utils
{
    public Utils(Minecraft mc)
    {
        minecraft = mc;
    }

    public void addChatMessage(String message)
    {
        String toSend = message;
        minecraft.thePlayer.addChatMessage(toSend);
    }

    private Minecraft minecraft;
}
