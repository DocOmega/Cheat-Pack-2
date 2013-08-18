package com.kodehawa.hooks;

import net.minecraft.src.EntityRenderer;
import org.lwjgl.input.Mouse;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA. User: amarylis Date: 7/5/13 Time: 11:19 AM To
 * change this template use File | Settings | File Templates.
 */
public class HookCallableMouseLocation implements Callable {
        final int field_90026_a;

        final int field_90024_b;

        final Renderer theEntityRenderer;

        public HookCallableMouseLocation(Renderer par1EntityRenderer, int par2, int par3) {
                this.theEntityRenderer = par1EntityRenderer;
                this.field_90026_a = par2;
                this.field_90024_b = par3;
        }

        public String callMouseLocation() {
                return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", new Object[] {
                                Integer.valueOf(this.field_90026_a), Integer.valueOf(this.field_90024_b),
                                Integer.valueOf(Mouse.getX()), Integer.valueOf(Mouse.getY()) });
        }

        public Object call() {
                return this.callMouseLocation();
        }
}