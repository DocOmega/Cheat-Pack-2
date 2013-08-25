package com.kodehawa.hooks;


import java.util.concurrent.Callable;

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
