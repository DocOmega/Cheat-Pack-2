package com.kodehawa.newgui;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.StringTranslate;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.kodehawa.CBOriginal;
import com.kodehawa.CheatPack;

public class GuiBrewing extends GuiContainer
{
    private GuiScreen parentScreen;
    private GuiButton pressedbutton;
    private InventoryBrewing invBrew;
    private static GuiListControl effect;
    private static GuiValueField level;
    private static GuiValueField minutes;
    private static GuiValueField seconds;
    private boolean brewpossible;

    public GuiBrewing(GuiScreen guiscreen, ContainerBrewing containerbrewing)
    {
        super(containerbrewing);
        brewpossible = true;
        xSize = 218;
        ySize = 224;
        parentScreen = guiscreen;
        invBrew = containerbrewing.invBrew;
        effect = new GuiListControl(Potion.potionTypes);
        effect.prev = CheatPack.translate("ListControl.Previous");
        effect.next = CheatPack.translate("ListControl.Next");
        effect.focused = true;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        super.initGui();
        Keyboard.enableRepeatEvents(true);
        int i = (width - xSize) / 2;
        int j = (height - ySize) / 2;
        buttonList.addAll(effect.initGui(0, 1, 2, StringTranslate.getInstance().translateKey(Potion.potionTypes[effect.index].getName()), i + 9, j + 91 + 0, 200));
        level = new GuiValueField(new IntBuilder(0, 0), fontRenderer, i + 9, j + 91 + 26, 200, 20);
        minutes = new GuiValueField(new IntBuilder(0, 0, 0, 0x1b4e46), fontRenderer, i + 9, j + 91 + 52, 200, 20);
        seconds = new GuiValueField(new IntBuilder(0, 0, 0, 59), fontRenderer, i + 9, j + 91 + 78, 200, 20);
        buttonList.add(new GuiButton(200, i + 9, j + 91 + 104, 97, 20, CheatPack.translate("GuiPotionEffects.ButtonSet")));
        buttonList.add(new GuiButton(201, i + 112, j + 91 + 104, 97, 20, CheatPack.translate("GuiPotionEffects.ButtonUnset")));
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    public void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        drawDefaultBackground();
        int k = mc.renderEngine.getTexture("/CheatPackRessources/guibrewing.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(k);
        int l = (width - xSize) / 2;
        int i1 = (height - ySize) / 2;
        drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
        level.drawScreen(i, j, f);
        minutes.drawScreen(i, j, f);
        seconds.drawScreen(i, j, f);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int i, int j, float f)
    {
        effect.setInfo(StringTranslate.getInstance().translateKey(Potion.potionTypes[effect.index].getName()));
        super.drawScreen(i, j, f);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everythin in front of the items)
     */
    protected void drawGuiContainerForegroundLayer()
    {
        drawCenteredString(fontRenderer, CheatPack.translate("GuiBrewing.LabelTitle"), xSize / 2, -((height - ySize) / 2) + 20, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiBrewing.LabelLevel"), xSize / 2, 122, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiBrewing.LabelMinutes"), xSize / 2, 148, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiBrewing.LabelSeconds"), xSize / 2, 174, 0xffffff);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton guibutton)
    {
        effect.actionPerformed(guibutton, Potion.potionTypes);

        if (guibutton.id == 200 || guibutton.id == 201)
        {
            brewpossible = !brewpossible;

            if (brewpossible)
            {
                applyChanges(guibutton.id == 200);
            }
        }
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        super.onGuiClosed();

        for (int i = 0; i < 4; i++)
        {
            ItemStack itemstack = invBrew.getStackInSlot(i);

            if (itemstack != null)
            {
                CheatPack.coreBase.getPlayer().dropItem(itemstack.itemID, 1);
                CheatPack.coreBase.getPlayer().inventory.setInventorySlotContents(i, null);
            }
        }

        Keyboard.enableRepeatEvents(false);
    }

    private void applyChanges(boolean flag)
    {
        if (flag && level.toInt() >= level.getIntBuilder().min && minutes.toInt() <= minutes.getIntBuilder().min && seconds.toInt() <= seconds.getIntBuilder().min)
        {
            minutes.getIntBuilder().setValue(Integer.valueOf(minutes.getIntBuilder().max));
            seconds.getIntBuilder().setValue(Integer.valueOf(seconds.getIntBuilder().max));
        }

        for (int i = 0; i < 4; i++)
        {
            if (invBrew.getStackInSlot(i) == null)
            {
                continue;
            }

            if (flag)
            {
                CheatPack.getBase(mc).setPotionBrewing(invBrew.getStackInSlot(i), new PotionEffect(effect.index, CheatPack.timeToTicks(minutes.toInt(), seconds.toInt()), level.toInt()));
            }
            else
            {
                CheatPack.getBase(mc).unsetPotionBrewing(invBrew.getStackInSlot(i), effect.index);
            }
        }

        if (!flag)
        {
            level.getIntBuilder().setInt(0);
            minutes.getIntBuilder().setInt(0);
            seconds.getIntBuilder().setInt(0);
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    public void keyTyped(char c, int i)
    {
        if (!CheatPack.escapeGui(mc, i, parentScreen))
        {
            super.keyTyped(c, i);
            level.keyTyped(c, i);
            minutes.keyTyped(c, i);
            seconds.keyTyped(c, i);
            effect.keyTyped(c, i, Potion.potionTypes, CheatPack.prevKey, CheatPack.nextKey, mc.sndManager);

            if (i == CheatPack.setKey || i == CheatPack.unsetKey)
            {
                mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
                applyChanges(i == CheatPack.setKey);
            }
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    public void mouseClicked(int i, int j, int k)
    {
        if (k == 0)
        {
            for (int l = 0; l < buttonList.size(); l++)
            {
                GuiButton guibutton = (GuiButton)buttonList.get(l);

                if (guibutton.mousePressed(mc, i, j))
                {
                    pressedbutton = guibutton;
                    mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
                    actionPerformed(guibutton);
                }
            }
        }

        level.mouseClicked(i, j, k);
        minutes.mouseClicked(i, j, k);
        seconds.mouseClicked(i, j, k);
        super.mouseClicked(i, j, k);
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    protected void mouseMovedOrUp(int i, int j, int k)
    {
        if (pressedbutton != null && k == 0)
        {
            pressedbutton.mouseReleased(i, j);
            pressedbutton = null;
        }

        super.mouseMovedOrUp(i, j, k);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        level.updateScreen();
        minutes.updateScreen();
        seconds.updateScreen();
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return CheatPack.pausegame;
    }
}
