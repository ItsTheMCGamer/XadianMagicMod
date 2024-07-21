package com.mcgamer.xadian_magic.effect;

import com.mcgamer.xadian_magic.XadianMagic;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

public class QuicksandEffect extends MobEffect {

    public static final ResourceLocation ICON = new ResourceLocation(XadianMagic.MOD_ID,
            "textures/mob_effect/quicksand_effect.png");

    public static final IClientMobEffectExtensions EFFECT_RENDERER = new IClientMobEffectExtensions() {

        @Override
        public boolean renderInventoryIcon(MobEffectInstance instance, EffectRenderingInventoryScreen<?> screen, GuiGraphics guiGraphics, int x, int y, int blitOffset) {
            guiGraphics.blit(ICON, x + 6, y + 7, 18, 18, 0, 0, 255, 255, 256, 256);
            return false;
        }


        @Override
        public boolean renderGuiIcon(MobEffectInstance instance, Gui gui, GuiGraphics guiGraphics, int x, int y, float z, float alpha) {
            guiGraphics.blit(ICON, x + 3, y + 3, 18, 18, 0, 0, 255, 255, 256, 256);
            return false;
        }
    };

    public QuicksandEffect() {
        super(MobEffectCategory.HARMFUL, 0xffff00);
    }


    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        super.applyEffectTick(entityLivingBaseIn, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean isInstantenous() {
        return false;
    }

    @Override
    public MobEffect addAttributeModifier(Attribute pAttribute, String pUuid, double pAmount, AttributeModifier.Operation pOperation) {
        return super.addAttributeModifier(pAttribute, pUuid, pAmount, pOperation);
    }
}