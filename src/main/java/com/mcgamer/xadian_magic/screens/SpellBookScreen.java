package com.mcgamer.xadian_magic.screens;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.screens.entries.StopRainSpellScreen;
import com.mcgamer.xadian_magic.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class SpellBookScreen extends Screen {

    public static String CURRENT_ENTRY = "";

    private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();

    private static final ResourceLocation SPELLBOOK_GUI = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/spellbook_gui.png");
    private static final ResourceLocation STOP_RAIN_SPELL = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/gui/entries/widgets/stop_rain_spell.png");

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
            CURRENT_ENTRY = "stop_rain_spell_entry";
            Minecraft.getInstance().setScreen(new StopRainSpellScreen(Component.literal("Stop Rain Spell")));
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
        CURRENT_ENTRY = "";
        super.onClose();
    }
}
