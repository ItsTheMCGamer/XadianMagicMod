package com.mcgamer.xadian_magic.gui;

import com.mcgamer.xadian_magic.XadianMagic;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class RadialGuiMenu extends Screen {
    private static final ResourceLocation RADIAL_SEGMENT = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/radial_segment.png");


    protected RadialGuiMenu(Component pTitle) {
        super(pTitle);
    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        int x = width / 2;
        int y = height;

        this.renderBackground(graphics);

        graphics.blit(RADIAL_SEGMENT, x, y / 2, 0, 0, 100, 100,
                100, 100);
        graphics.blit(RADIAL_SEGMENT, x, y / 2, 0, 0, 100, 100,
                100, 100);

        super.render(graphics, pMouseX, pMouseY, pPartialTick);
    }
}
