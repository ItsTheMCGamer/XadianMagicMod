package com.mcgamer.xadian_magic.item;

import com.mcgamer.xadian_magic.RegistryManager;
import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, XadianMagic.MOD_ID);

    public static final RegistryObject<CreativeModeTab> XADIAN_MAGIC_TAB = CREATIVE_MODE_TABS.register("xadian_magic_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SPELLBOOK.get()))
                    .title(Component.translatable("creativetab.xadian_magic_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SPELLBOOK.get());

                        pOutput.accept(ModItems.SKY_PRIMAL_STONE.get());
                        pOutput.accept(ModItems.STAR_PRIMAL_STONE.get());
                        pOutput.accept(ModItems.SUN_PRIMAL_STONE.get());
                        pOutput.accept(ModItems.EARTH_PRIMAL_STONE.get());
                        pOutput.accept(ModItems.MOON_PRIMAL_STONE.get());
                        pOutput.accept(ModItems.OCEAN_PRIMAL_STONE.get());

                        pOutput.accept(ModItems.STONEHEART_EMERALD.get());
                        pOutput.accept(ModItems.SKY_SAPPHIRE.get());
                        pOutput.accept(ModItems.AQUAMARINE.get());
                        pOutput.accept(ModItems.QUASAR_DIAMOND.get());
                        pOutput.accept(ModItems.FIRE_RUBY.get());
                        pOutput.accept(ModItems.MOON_OPAL.get());

                        pOutput.accept(ModItems.STONEHEART_EMERALD_ORE.get());
                        pOutput.accept(ModItems.SKY_SAPPHIRE_ORE.get());
                        pOutput.accept(ModItems.SKY_SAPPHIRE_DEEPSLATE_ORE.get());
                        pOutput.accept(ModItems.AQUAMARINE_ORE.get());
                        pOutput.accept(ModItems.AQUAMARINE_DEEPSLATE_ORE.get());
                        pOutput.accept(ModItems.QUASAR_DIAMOND_ORE.get());
                        pOutput.accept(ModItems.FIRE_RUBY_ORE.get());
                        pOutput.accept(ModItems.MOON_OPAL_ORE.get());

                        pOutput.accept(ModItems.SKY_STAFF.get());
                        pOutput.accept(ModItems.SUN_STAFF.get());
                        pOutput.accept(ModItems.MOON_STAFF.get());
                        pOutput.accept(ModItems.EARTH_STAFF.get());
                        pOutput.accept(ModItems.OCEAN_STAFF.get());
                        pOutput.accept(ModItems.STAFF_OF_ZIARD.get());

                        pOutput.accept(ModItems.MAGE_GILLS_AMULET.get());

                        pOutput.accept(ModBlocks.QUICKSAND_BLOCK.get());

                        pOutput.accept(RegistryManager.MUD.FLUID_BUCKET.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}