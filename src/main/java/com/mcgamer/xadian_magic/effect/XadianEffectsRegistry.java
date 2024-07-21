package com.mcgamer.xadian_magic.effect;

import com.mcgamer.xadian_magic.XadianMagic;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XadianEffectsRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, XadianMagic.MOD_ID);

    public static final RegistryObject<QuicksandEffect> QUICKSAND_EFFECT_OBJECT = EFFECTS
            .register("quicksand_effect_object", QuicksandEffect::new);

    public class XadianEffects extends MobEffects {
        public static final MobEffect QUICKSAND_EFFECT = QUICKSAND_EFFECT_OBJECT.get()
                .addAttributeModifier(Attributes.MOVEMENT_SPEED, "e2f8c006-a251-43c2-9975-c1683d1faf34",
                        (double) -0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    public static class ModEffect extends MobEffect {

        private ModEffect(MobEffectCategory category, int color) {
            super(category, color);
        }

    }
}