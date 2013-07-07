package com.kodehawa.ChestESP;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Tessellator;

public class ChestFinderContainer {

	
/**
 * Dungeons... DUNGEONS EVERYWHERE!
 * @param x
 * @param y
 * @param z
 */
	
	public static void chestFinder( double x, double y, double z ){
		
		GL11.glBlendFunc(770, 771);
		GL11.glColor3d(247, 244, 57);
		GL11.glLineWidth(2F);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		drawOutlinedBoundingBox(new AltAxisAlignedBB( x + 1, y + 1, z + 1, x, y, z ));
		GL11.glDepthMask(true);
		GL11.glEnable(3553);
		GL11.glEnable(2929);
		
}
	
	
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
