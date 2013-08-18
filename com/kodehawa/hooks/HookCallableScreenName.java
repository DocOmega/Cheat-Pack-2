package com.kodehawa.hooks;

import net.minecraft.src.EntityRenderer;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA. User: amarylis Date: 7/5/13 Time: 11:16 AM To
 * change this template use File | Settings | File Templates.
 */
public class HookCallableScreenName implements Callable {
        final Renderer entityRender;

        public HookCallableScreenName(Renderer par1EntityRenderer) {
                this.entityRender = par1EntityRenderer;
        }

        public String callScreenName() {
                return Renderer.getRendererMinecraft(this.entityRender).currentScreen.getClass().getCanonicalName();
        }

        public Object call() {
                return this.callScreenName();
        }
}
