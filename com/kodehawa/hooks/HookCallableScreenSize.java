package com.kodehawa.hooks;

import net.minecraft.src.EntityRenderer;
import net.minecraft.src.ScaledResolution;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA. User: amarylis Date: 7/5/13 Time: 11:17 AM To
 * change this template use File | Settings | File Templates.
 */
public class HookCallableScreenSize implements Callable {
        final ScaledResolution theScaledResolution;

        final Renderer theEntityRenderer;

        public HookCallableScreenSize(Renderer par1EntityRenderer, ScaledResolution par2ScaledResolution) {
                this.theEntityRenderer = par1EntityRenderer;
                this.theScaledResolution = par2ScaledResolution;
        }

        public String callScreenSize() {
                /*return String.format(
                             /*   "Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d",
                               /* new Object[] { Integer.valueOf(this.theScaledResolution.getScaledWidth()),
                                                Integer.valueOf(this.theScaledResolution.getScaledHeight()),
                                                Integer.valueOf(Renderer.getRendererMinecraft(this.theEntityRenderer).displayWidth),
                                                Integer.valueOf(Renderer.getRendererMinecraft(this.theEntityRenderer).displayHeight),
                                                Integer.valueOf(this.theScaledResolution.getScaleFactor()) });    */
            return null;
        }

        public Object call() {
                return this.callScreenSize();
        }
}