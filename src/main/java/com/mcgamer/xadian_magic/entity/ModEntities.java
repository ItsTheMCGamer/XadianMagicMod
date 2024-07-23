/*
package com.mcgamer.xadian_magic.entity;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.entity.custom.FireballEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, XadianMagic.MOD_ID);

    public static final RegistryObject<EntityType<FireballEntity>> FIREBALL_ENTITY =
            ENTITY_TYPES.register("fireball_entity", () ->
                    EntityType.Builder.<FireballEntity>of(FireballEntity::new, MobCategory.MISC).sized(0.75F, 0.75F)
                            .clientTrackingRange(4).updateInterval(10).build("fireball_entity"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }


}
*/