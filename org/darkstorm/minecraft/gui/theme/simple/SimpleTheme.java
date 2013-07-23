package org.darkstorm.minecraft.gui.theme.simple;

import net.minecraft.src.FontRenderer;

import org.darkstorm.minecraft.gui.theme.AbstractTheme;

import com.kodehawa.CheatingEssentials;

public class SimpleTheme extends AbstractTheme
{
    private final FontRenderer fontRenderer;

    public SimpleTheme()
    {
        fontRenderer = CheatingEssentials.getCheatingEssentials().getModWrapper().getMinecraft().
                                                                                                 fontRenderer;
        installUI(new SimpleFrameUI(this));
        installUI(new SimplePanelUI(this));
        installUI(new SimpleLabelUI(this));
        installUI(new SimpleButtonUI(this));
        installUI(new SimpleCheckButtonUI(this));
        installUI(new SimpleComboBoxUI(this));
    }

    public FontRenderer getFontRenderer()
    {
        return fontRenderer;
    }
}
