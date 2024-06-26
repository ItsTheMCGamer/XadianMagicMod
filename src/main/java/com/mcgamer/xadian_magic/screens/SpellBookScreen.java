package com.mcgamer.xadian_magic.screens;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.screens.entries.FireballSpellScreen;
import com.mcgamer.xadian_magic.screens.entries.MasteredWindBlowingSpellScreen;
import com.mcgamer.xadian_magic.screens.entries.StopRainSpellScreen;
import com.mcgamer.xadian_magic.screens.entries.WeakWindBlowingSpellScreen;
import com.mcgamer.xadian_magic.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class SpellBookScreen extends Screen {
    private static final ResourceLocation SPELLBOOK_GUI = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/spellbook_gui.png");
    private static final ResourceLocation STOP_RAIN_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/stop_rain/stop_rain_spell.png");
    private static final ResourceLocation MASTERED_WIND_BLOWING_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/weak_wind_blowing/mastered_wind_blowing_rune.png");
    private static final ResourceLocation WEAK_WIND_BLOWING_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/weak_wind_blowing/weak_wind_blowing_rune.png");
    private static final ResourceLocation FIREBALL_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/fireball/fireball_rune.png");


    public SpellBookScreen(Component pTitle) {
        super(pTitle);
    }

    @Override
    protected void init() {
        int x = width / 2;
        int y = height;

        super.init();
        
        this.addRenderableWidget(new ImageButton( x + 104, y - 100, 20, 18,
                0, 0, 19, STOP_RAIN_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new StopRainSpellScreen(Component.literal("Stop Rain Spell")));
        }));
        this.addRenderableWidget(new ImageButton( x + 84, y - 100, 20, 18,
                0, 0, 19, MASTERED_WIND_BLOWING_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new MasteredWindBlowingSpellScreen(Component.literal("Stop Rain Spell")));
        }));
        this.addRenderableWidget(new ImageButton( x + 44, y - 100, 20, 18,
                0, 0, 19, WEAK_WIND_BLOWING_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new WeakWindBlowingSpellScreen(Component.literal("Weak Aspiro Spell")));
        }));
        this.addRenderableWidget(new ImageButton( x + 64, y - 100, 20, 18,
                0, 0, 19, FIREBALL_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new FireballSpellScreen(Component.literal("Fireball Spell")));
        }));
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if(pKeyCode == 66) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("You don't have a spell " +
                    "selected!"));
            Minecraft.getInstance().setScreen(null);
            return false;
        } else if(pKeyCode == 256) {
            Minecraft.getInstance().setScreen(null);
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
