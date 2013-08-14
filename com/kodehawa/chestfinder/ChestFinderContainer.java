package com.kodehawa.chestfinder;

import org.lwjgl.opengl.GL11;

import com.kodehawa.render.GLHelper;


public class ChestFinderContainer
{
    
    public static void drawChestESP(double x, double y, double z) {
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
        GLHelper.drawOutlinedBoundingBox( new AltAxisAlignedBB( x + 1, y + 1, z + 1, x , y , z));
        GL11.glColor4f(0.0F,
        		0.0F,
        		255F,
        		0.4F); 
        GLHelper.drawBoundingBox( new AltAxisAlignedBB( x + 1, y + 1, z + 1, x, y, z ) );
        GL11.glDepthMask( true );
        GL11.glEnable( GL11.GL_TEXTURE_2D);
        GL11.glEnable( GL11.GL_DEPTH_TEST);
        GL11.glColor4f( 255, 255, 255, 255 );
        GL11.glPopMatrix();
    }

}
