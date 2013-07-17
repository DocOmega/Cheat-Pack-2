package com.kodehawa.gui.api.components;

import java.util.List;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityBat;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySlime;
import net.minecraft.src.EntitySquid;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Minecraft;

import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.gui.CGuiIngame;
import com.kodehawa.gui.api.render.ModGuiUtils;
import com.kodehawa.mods.Vars;

public class Radar
{
    Minecraft mc = CheatingEssentials.modinstance.minecraft;

    public void drawRadar(int x, int y)
    {
        CGuiIngame.tick++ ;

        if (CGuiIngame.tick >= 50)
        {
            CGuiIngame.tick = 0;
        }

        GL11.glLineWidth(2.0F);
        ModGuiUtils.drawFilledCircle(x, y, 50, 0x77007700);
        ModGuiUtils.drawCircle(x, y, 50, 0xff000000);
        ModGuiUtils.drawCircle(x, y, 38, 0xff000000);
        ModGuiUtils.drawCircle(x, y, 25, 0xff000000);
        ModGuiUtils.drawCircle(x, y, 13, 0xff000000);
        ModGuiUtils.drawCircle(x, y, CGuiIngame.tick, 0xff00ffff);
        ModGuiUtils.drawCircle(x, y, 1, 0xffffffff);   // Player
        List list1 = this.mc.theWorld.loadedEntityList;
        GL11.glLineWidth(1.0F);

        for (int i = 0; i < list1.size(); i++)
        {
            Entity entity = (Entity) list1.get(i);
            double xdis = this.mc.thePlayer.posX - entity.posX;
            double zdis = this.mc.thePlayer.posZ - entity.posZ;
            double tdis = Math.sqrt((xdis * xdis) + (zdis * zdis));
            double difInAng = MathHelper.wrapAngleTo180_double(this.mc.thePlayer.rotationYaw - ((Math.atan2(zdis, xdis) * 180.0D) / Math.PI));
            double finalX = Math.cos(Math.toRadians(difInAng)) * tdis;
            double finalY = -Math.sin(Math.toRadians(difInAng)) * tdis;
            GL11.glPushMatrix();
            GL11.glTranslatef(x, y, 0);

            if (tdis <= 100)
            {
                if (!(entity instanceof EntityClientPlayerMP))
                {
                    if (entity instanceof EntityPlayer)
                    {
                        
                        ModGuiUtils.drawTriangle(entity, finalX, finalY, 0xff0000ff);
                        GL11.glScalef(0.5F, 0.5F, 0.5F);
                        EntityPlayer p = (EntityPlayer) entity;
                        String u = mc.thePlayer.username;
                        int color = 0xffffff;

                        if (Vars.friends.contains(u))
                        {
                            color = 0x00ff00;
                        }
                        else if (Vars.enemies.contains(u))
                        {
                            color = 0xff0000;
                        }
                        else
                        {
                            color = 0xffffff;
                        }

                        this.mc.fontRenderer.drawString(u, (int)(finalX) - (this.mc.fontRenderer.getStringWidth(u) / 2), (int) finalY - 10, color);
                        
                        GL11.glScalef(1F, 1F, 1F);
                    }

                    if (entity instanceof EntityAnimal)
                    {
                        
                        ModGuiUtils.drawTriangle(entity, finalX, finalY, 0xff00ff00);
                    }

                    if (entity instanceof EntityMob)
                    {
                        
                        ModGuiUtils.drawTriangle(entity, finalX, finalY, 0xffff0000);
                    }

                    if (entity instanceof EntitySlime)
                    {
                        
                        ModGuiUtils.drawTriangle(entity, finalX, finalY, 0xffff88cc);
                    }

                    if (entity instanceof EntityVillager)
                    {
                        
                        ModGuiUtils.drawTriangle(entity, finalX, finalY, 0xff8b4513);
                    }

                    if (entity instanceof EntityBat)
                    {
                        
                        ModGuiUtils.drawTriangle(entity, finalX, finalY, 0xfff4a460);
                    }

                    if (entity instanceof EntitySquid)
                    {
                        
                        ModGuiUtils.drawTriangle(entity, finalX, finalY, 0xff003399);
                    }
                }
            }

            GL11.glPopMatrix();
        }
    }
}
