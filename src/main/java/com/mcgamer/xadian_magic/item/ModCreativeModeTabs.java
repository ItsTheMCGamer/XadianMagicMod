package com.mcgamer.xadian_magic.item;

import com.mcgamer.xadian_magic.XadianMagic;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}