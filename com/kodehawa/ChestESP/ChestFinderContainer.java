package com.kodehawa.ChestESP;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Tessellator;
import net.minecraft.src.TileEntityChest;

public class ChestFinderContainer
{
    /**
     * Dungeons... DUNGEONS EVERYWHERE!
     * @param x
     * @param y
     * @param z
     * @param e 
     * @param f 
     */

    public static void chestFinder( double x, double y, double z )
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
     * New ChestESP drawer. Looks pretty.
     * @param x
     * @param y
     * @param z
     * @param d3
     * @param e
     * @param f
     */
    
    public static void drawChestESP(double x, double y, double z, double d3, double e, float f) {
    	GL11.glPushMatrix();
        GL11.glEnable( GL11.GL_BLEND );
        GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
        GL11.glColor4f( 0.0F,
        		0.0F,
        		0.0F,
        		1F );
        GL11.glLineWidth( 2.0F );
        GL11.glDisable( GL11.GL_TEXTURE_2D );
        GL11.glDepthMask( false );
        GL11.glEnable( GL11.GL_LINE_SMOOTH );
        GL11.glBlendFunc( 770, 771 );
        GL11.glDisable( GL11.GL_TEXTURE_2D );
        GL11.glDisable( GL11.GL_DEPTH_TEST );
        GL11.glDepthMask( false );
        GL11.glEnable( GL11.GL_LINE_SMOOTH );
        drawOutlinedBoundingBox( new AltAxisAlignedBB( x + 1, y + 1, z + 1, x , y , z));
        GL11.glColor4f(0.0F,
        		0.0F,
        		255F,
        		0.4F); 
        drawBoundingBox( new AltAxisAlignedBB( x + 1, y + 1, z + 1, x, y, z ) );
        GL11.glTranslated(x, y, z);
        GL11.glRotated( x, y, z, f );
        GL11.glDepthMask( true );
        GL11.glEnable( GL11.GL_TEXTURE_2D);
        GL11.glEnable( GL11.GL_DEPTH_TEST);
        GL11.glColor4f( 255, 255, 255, 255 );
        GL11.glPopMatrix();
    }
    
    public static void drawEnderChestESP(double x, double y, double z, double d3, double e, float f){
    	GL11.glEnable( GL11.GL_BLEND );
        GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
        GL11.glColor4f( 5.0F,
        		2.0F,
        		3.0F, 
        		1.0F );
        GL11.glLineWidth( 2.0F );
        GL11.glDisable( GL11.GL_TEXTURE_2D );
        GL11.glDepthMask( false );
        GL11.glEnable( GL11.GL_LINE_SMOOTH );
        GL11.glBlendFunc( 770, 771 );
        GL11.glDisable( GL11.GL_TEXTURE_2D );
        GL11.glDisable( GL11.GL_DEPTH_TEST );
        GL11.glDepthMask( false );
        GL11.glEnable( GL11.GL_LINE_SMOOTH );
        drawOutlinedBoundingBox( new AltAxisAlignedBB( x + 1, y + 1, z + 1, x , y , z));
        GL11.glColor4f( 5.0F,
        		2.0F,
        		255F,
        		1.3F );
        drawBoundingBox( new AltAxisAlignedBB( x + 1, y + 1, z + 1, x, y, z ) );
        GL11.glDepthMask( true );
        GL11.glEnable( GL11.GL_TEXTURE_2D);
        GL11.glEnable( GL11.GL_DEPTH_TEST);
        GL11.glColor4f( 255, 255, 255, 255 );
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
    
    /**
     * Used for draw a box in chests. 
     * @param altAxisAlignedBB
     */
    public static void drawBoundingBox( AltAxisAlignedBB altAxisAlignedBB ) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads( ); // starts x
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ );
        tessellator.draw( );
        tessellator.startDrawingQuads( );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ );
        tessellator.draw( ); // ends x
        tessellator.startDrawingQuads( ); // starts y
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ );
        tessellator.draw( );
        tessellator.startDrawingQuads( );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ );
        tessellator.draw( ); // ends y
        tessellator.startDrawingQuads( ); // starts z
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ );
        tessellator.draw( );
        tessellator.startDrawingQuads( );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.minX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.minZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.maxY, altAxisAlignedBB.maxZ );
        tessellator.addVertex( altAxisAlignedBB.maxX, altAxisAlignedBB.minY, altAxisAlignedBB.maxZ );
        tessellator.draw( ); // ends z
    }
}
