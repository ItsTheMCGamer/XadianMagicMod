package com.mcgamer.xadian_magic.screens.entries;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.networking.packet.sky.StopRainSpellC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class StopRainSpellScreen extends Screen {

    private static final ResourceLocation SPELLBOOK_GUI = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/spellbook_gui.png");
    private static final ResourceLocation STOP_RAIN_RUNE = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/stop_rain_rune.png");

    public StopRainSpellScreen(Component pTitle) {
        super(pTitle);
    }

    @Override
    protected void init() {
        int x = width / 2;
        int y = height;
        super.init();
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if(pKeyCode == 66) {
            ModPackets.sendToServer(new StopRainSpellC2SPacket());
            return true;
        } else if(pKeyCode == 256) {
            Minecraft.getInstance().setScreen((Screen) null);
        }
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        // Add ticking logic for EditBox in editBox
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        int x = width / 2;
        int y = height;

        this.renderBackground(graphics);
        graphics.blit(SPELLBOOK_GUI, x - 145, y - 260, 0, 0, 288, 188,
                288, 188);

        graphics.drawString(Minecraft.getInstance().font, Component.literal("This is a simple spell to "),
                324, 87, 0, false);
        graphics.drawString(Minecraft.getInstance().font, Component.literal("make the rain go away!"),
                324, 98, 0, false);

        graphics.blit(STOP_RAIN_RUNE, x - 120, y - 200, 0, 0, 100, 50,
                100, 50);

        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void onClose() {
        super.onClose();
    }
}
