package com.kodehawa.ChestESP;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Tessellator;

public class ChestFinderContainer
{
    /**
     * Dungeons... DUNGEONS EVERYWHERE!
     * @param x
     * @param y
     * @param z
     */

    public static void chestFinder(double x, double y, double z)
    {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor3f(843, 721, 90);
        GL11.glLineWidth(2F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        drawOutlinedBoundingBox(new AltAxisAlignedBB(x + 1, y + 1, z + 1, x, y, z));
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
    /**
     * Used for draw lines in chests. Alt for not modify RenderGlobal.
     * @param altAxisAlignedBB
     */

    private static void drawOutlinedBoundingBox(AltAxisAlignedBB altAxisAlignedBB)
    {
        Tessellator var2 = Tessellator.instance;
        var2.startDrawing(3);
        var2.addVertex(altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ);
        var2.addVertex(altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ);
        var2.addVertex(altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ);
        var2.addVertex(altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ);
        var2.addVertex(altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ);
        var2.draw();
        var2.startDrawing(3);
        var2.addVertex(altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ);
        var2.addVertex(altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ);
        var2.addVertex(altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ);
        var2.addVertex(altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ);
        var2.addVertex(altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ);
        var2.draw();
        var2.startDrawing(1);
        var2.addVertex(altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ);
        var2.addVertex(altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ);
        var2.addVertex(altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ);
        var2.addVertex(altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ);
        var2.addVertex(altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ);
        var2.addVertex(altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ);
        var2.addVertex(altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ);
        var2.addVertex(altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ);
        var2.draw();
    }
}
