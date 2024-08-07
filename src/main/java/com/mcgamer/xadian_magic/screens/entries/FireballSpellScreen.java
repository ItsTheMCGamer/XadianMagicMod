package com.mcgamer.xadian_magic.screens.entries;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.networking.packet.sun.FireballSpellC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class FireballSpellScreen extends Screen {

    private static final ResourceLocation SPELLBOOK_GUI = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/spellbook_gui.png");
    private static final ResourceLocation FIREBALL_RUNE = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/fireball_rune.png");

    public FireballSpellScreen(Component pTitle) {
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
            ModPackets.sendToServer(new FireballSpellC2SPacket());
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

        graphics.drawString(Minecraft.getInstance().font, Component.literal("This spell summons "),
                324, 87, 0, false);
        graphics.drawString(Minecraft.getInstance().font, Component.literal("a powerful fireball in "),
                324, 98, 0, false);
        graphics.drawString(Minecraft.getInstance().font, Component.literal("front of the sender. "),
                324, 109, 0, false);

        graphics.blit(FIREBALL_RUNE, x - 120, y - 200, 0, 0, 100, 50,
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
