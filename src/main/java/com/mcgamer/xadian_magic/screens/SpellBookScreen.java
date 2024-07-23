package com.mcgamer.xadian_magic.screens;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.screens.entries.*;
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
            "textures/gui/entries/stop_rain_spell.png");
    private static final ResourceLocation MASTERED_WIND_BLOWING_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/mastered_wind_blowing_rune.png");
    private static final ResourceLocation WEAK_WIND_BLOWING_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/weak_wind_blowing_rune.png");
    private static final ResourceLocation ICE_BLOWING_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/ice_blowing_rune.png");
    private static final ResourceLocation FIREBALL_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/fireball_rune.png");
    private static final ResourceLocation LIGHTNING_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/lightning_rune.png");
    private static final ResourceLocation STORM_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/storm_rune.png");
    private static final ResourceLocation QUICKSAND_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/quicksand_rune.png");
    private static final ResourceLocation EARTHQUAKE_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/earthquake_rune.png");
    private static final ResourceLocation MUD_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/mud_rune.png");
    private static final ResourceLocation STRENGTH_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/strength_rune.png");


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
            Minecraft.getInstance().setScreen(new StopRainSpellScreen(Component.literal("Vocare Solem")));
        }));
        this.addRenderableWidget(new ImageButton( x + 84, y - 100, 20, 18,
                0, 0, 19, MASTERED_WIND_BLOWING_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new MasteredWindBlowingSpellScreen(Component.literal("Aspiro Maximus")));
        }));
        this.addRenderableWidget(new ImageButton( x + 44, y - 100, 20, 18,
                0, 0, 19, WEAK_WIND_BLOWING_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new WeakWindBlowingSpellScreen(Component.literal("Aspiro")));
        }));
        this.addRenderableWidget(new ImageButton( x + 64, y - 100, 20, 18,
                0, 0, 19, FIREBALL_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new FireballSpellScreen(Component.literal("Missilem Ignem")));
        }));
        this.addRenderableWidget(new ImageButton( x + 24, y - 100, 20, 18,
                0, 0, 19, ICE_BLOWING_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new IceBlowingSpellScreen(Component.literal("Aspiro Frigis")));
        }));
        this.addRenderableWidget(new ImageButton( x + 4, y - 100, 20, 18,
                0, 0, 19, LIGHTNING_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new LightningSpellScreen(Component.literal("Fulminis")));
        }));
        this.addRenderableWidget(new ImageButton( x + 104, y - 118, 20, 18,
                0, 0, 19, STORM_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new StormSpellScreen(Component.literal("Vocare Nimbum")));
        }));
        this.addRenderableWidget(new ImageButton( x + 84, y - 118, 20, 18,
                0, 0, 19, QUICKSAND_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new QuicksandSpellScreen(Component.literal("Creatura Lenta")));
        }));
        this.addRenderableWidget(new ImageButton( x + 64, y - 118, 20, 18,
                0, 0, 19, EARTHQUAKE_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new EarthquakeSpellScreen(Component.literal("Earthquake")));
        }));
        this.addRenderableWidget(new ImageButton( x + 44, y - 118, 20, 18,
                0, 0, 19, MUD_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new MudSpellScreen(Component.literal("Lutum Facio")));
        }));
        this.addRenderableWidget(new ImageButton( x + 24, y - 118, 20, 18,
                0, 0, 19, STRENGTH_SPELL, (p_289631_) -> {
            Minecraft.getInstance().setScreen(new StrengthSpellScreen(Component.literal("Vires Ardens")));
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
