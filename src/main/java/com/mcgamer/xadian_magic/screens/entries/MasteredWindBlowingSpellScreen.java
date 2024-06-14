package com.mcgamer.xadian_magic.screens.entries;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.networking.packet.MasteredWindBlowingSpellC2SPacket;
import com.mcgamer.xadian_magic.networking.packet.StopRainSpellC2SPacket;
import com.mcgamer.xadian_magic.screens.SpellBookScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class MasteredWindBlowingSpellScreen extends Screen {

    private static final ResourceLocation SPELLBOOK_GUI = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/spellbook_gui.png");
    private static final ResourceLocation MASTERED_WIND_BLOWING_RUNE = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/stop_rain/mastered_wind_blowing_rune.png");

    public MasteredWindBlowingSpellScreen(Component pTitle) {
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
            ModPackets.sendToServer(new MasteredWindBlowingSpellC2SPacket());
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

        graphics.drawString(Minecraft.getInstance().font, Component.literal("This is spell represents "),
                324, 87, 0, false);
        graphics.drawString(Minecraft.getInstance().font, Component.literal("complete mastery of the "),
                324, 98, 0, false);
        graphics.drawString(Minecraft.getInstance().font, Component.literal("Aspiro spell."),
                324, 109, 0, false);

        graphics.blit(MASTERED_WIND_BLOWING_RUNE, x - 120, y - 200, 0, 0, 100, 50,
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
