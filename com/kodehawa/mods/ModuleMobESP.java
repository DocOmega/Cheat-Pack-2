package com.kodehawa.mods;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.chestfinder.AltAxisAlignedBB;
import com.kodehawa.mods.Mod.ModuleInformation;
import com.kodehawa.render.GLHelper;
import com.kodehawa.util.Tickable;
import net.minecraft.src.EntityMob;
import net.minecraft.src.RenderManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import static com.kodehawa.render.GLHelper.drawBoundingBox;

public class ModuleMobESP extends Mod implements Tickable {

	@ModuleInformation(
			credits = "Kodehawa",
			desc = "Bugged: Show mobs through walls",
			name = "Mob ESP")
	
    public ModuleMobESP(){
        super("Mob ESP", "Spy the mobs", Keyboard.KEY_C);
    }

    @Override
    public void onEnable(){
        CheatingEssentials.getCheatingEssentials().addToTick(this);
    }

    @Override
    public void render() {
        if(super.isActive()) {
            for(Object o : CheatingEssentials.getMinecraftInstance().theWorld.loadedEntityList) {
                if(o instanceof EntityMob) {
                    EntityMob ep = (EntityMob) o;
                    double d = ep.lastTickPosX + (ep.posX - ep.lastTickPosX) * (double)CheatingEssentials.getMinecraftInstance().timer.renderPartialTicks;
                    double d1 = ep.lastTickPosY + (ep.posY - ep.lastTickPosY) * (double)CheatingEssentials.getMinecraftInstance().timer.renderPartialTicks;
                    double d2 = ep.lastTickPosZ + (ep.posZ - ep.lastTickPosZ) * (double)CheatingEssentials.getMinecraftInstance().timer.renderPartialTicks;
                    drawESP(d - RenderManager.renderPosX, d1 - RenderManager.renderPosY, d2 - RenderManager.renderPosZ, ep, ep.height - 0.1, ep.width - 0.12);
                }
            }
        }
    }

    public void drawESP(double d, double d1, double d2, EntityMob ep, double e, double f)
    {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glColor4f(0.27F, 0.70F, 0.92F, 0.15F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1.8F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GLHelper.drawBoundingBox(new AltAxisAlignedBB(d - f, d1 + 0.1, d2 - f, d + f, d1 + e + 0.25, d2 + f));
        GL11.glColor4f(0.27F, 0.70F, 0.92F, 1.0F);
        GLHelper.drawOutlinedBoundingBox(new AltAxisAlignedBB(d - f, d1 + 0.1, d2 - f, d + f, d1 + e + 0.25, d2 + f));
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    @Override
    public void onDisable(){
      CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
}


    @Override
    public void tick() {
        this.render();
    }
}
