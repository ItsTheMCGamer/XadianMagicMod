package com.mcgamer.xadian_magic.client.mana;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ManaHudOverlay {

    private static final ResourceLocation EMPTY_MANA = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/mana/primal_empty_mana.png");
    private static final ResourceLocation FIVE_MANA_BAR = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/mana/5_mana_bar.png");
    private static final ResourceLocation TEN_MANA_BAR = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/mana/10_mana_bar.png");
    private static final ResourceLocation FIFTEEN_MANA_BAR = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/mana/15_mana_bar.png");
    private static final ResourceLocation TWENTY_MANA_BAR = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/mana/20_mana_bar.png");
    private static final ResourceLocation TWENTY_FIVE_MANA_BAR = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/mana/25_mana_bar.png");
    private static final ResourceLocation THIRTY_MANA_BAR = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/mana/30_mana_bar.png");
    private static final ResourceLocation THIRTY_FIVE_MANA_BAR = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/mana/35_mana_bar.png");
    private static final ResourceLocation FORTY_MANA_BAR = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/mana/40_mana_bar.png");
    private static final ResourceLocation FORTY_FIVE_MANA_BAR = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/mana/45_mana_bar.png");
    private static final ResourceLocation FIFTY_MANA_BAR = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/mana/50_mana_bar.png");


    public static final IGuiOverlay HUD_MANA = ((gui, poseStack, partialTick, width, height) -> {
        int x = width / 2;
        int y = height;

        poseStack.drawString(Minecraft.getInstance().font, String.valueOf(ClientManaData.getPlayerMana()),
                x + 304, y - 74, 14737632, true);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_MANA);
        poseStack.blit(EMPTY_MANA, x + 303, y - 227,0,0,13,151,
                    13,151);

        RenderSystem.setShaderTexture(0, FIVE_MANA_BAR);
        if(ClientManaData.getPlayerMana() > 4) {
            poseStack.blit(FIVE_MANA_BAR, x + 306, y - 215, 0, 0, 7, 135,
                    7, 135);
        }

        RenderSystem.setShaderTexture(0, TEN_MANA_BAR);
        if(ClientManaData.getPlayerMana() > 9 && ClientManaData.getPlayerMana() < 15) {
            poseStack.blit(TEN_MANA_BAR, x + 306, y - 215, 0, 0, 7, 135,
                    7, 135);

        }

        RenderSystem.setShaderTexture(0, FIFTEEN_MANA_BAR);
        if(ClientManaData.getPlayerMana() > 14 && ClientManaData.getPlayerMana() < 20) {
            poseStack.blit(FIFTEEN_MANA_BAR, x + 306, y - 215, 0, 0, 7, 135,
                    7, 135);

        }

        RenderSystem.setShaderTexture(0, TWENTY_MANA_BAR);
        if(ClientManaData.getPlayerMana() > 19 && ClientManaData.getPlayerMana() < 25) {
            poseStack.blit(TWENTY_MANA_BAR, x + 306, y - 215, 0, 0, 7, 135,
                    7, 135);

        }

        RenderSystem.setShaderTexture(0, TWENTY_FIVE_MANA_BAR);
        if(ClientManaData.getPlayerMana() > 24 && ClientManaData.getPlayerMana() < 30) {
            poseStack.blit(TWENTY_FIVE_MANA_BAR, x + 306, y - 215, 0, 0, 7, 135,
                    7, 135);

        }

        RenderSystem.setShaderTexture(0, THIRTY_MANA_BAR);
        if(ClientManaData.getPlayerMana() > 29 && ClientManaData.getPlayerMana() < 35) {
            poseStack.blit(THIRTY_MANA_BAR, x + 306, y - 215, 0, 0, 7, 135,
                    7, 135);

        }

        RenderSystem.setShaderTexture(0, THIRTY_FIVE_MANA_BAR);
        if(ClientManaData.getPlayerMana() > 34 && ClientManaData.getPlayerMana() < 40) {
            poseStack.blit(THIRTY_FIVE_MANA_BAR, x + 306, y - 215, 0, 0, 7, 135,
                    7, 135);

        }

        RenderSystem.setShaderTexture(0, FORTY_MANA_BAR);
        if(ClientManaData.getPlayerMana() > 39 && ClientManaData.getPlayerMana() < 45) {
            poseStack.blit(FORTY_MANA_BAR, x + 306, y - 215, 0, 0, 7, 135,
                    7, 135);

        }

        RenderSystem.setShaderTexture(0, FORTY_FIVE_MANA_BAR);
        if(ClientManaData.getPlayerMana() > 44 && ClientManaData.getPlayerMana() < 50) {
            poseStack.blit(FORTY_FIVE_MANA_BAR, x + 306, y - 215, 0, 0, 7, 135,
                    7, 135);

        }

        RenderSystem.setShaderTexture(0, FIFTY_MANA_BAR);
        if(ClientManaData.getPlayerMana() == 50) {
            poseStack.blit(FIFTY_MANA_BAR, x + 306, y - 215, 0, 0, 7, 135,
                    7, 135);

        }
    });
}