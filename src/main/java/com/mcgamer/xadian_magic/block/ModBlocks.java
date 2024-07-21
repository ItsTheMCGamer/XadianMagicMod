package com.mcgamer.xadian_magic.block;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.block.custom.QuicksandBlock;
import com.mcgamer.xadian_magic.item.ModItems;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    private static AttributeMap attributes;

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, XadianMagic.MOD_ID);

    public static final RegistryObject<Block> QUICKSAND_BLOCK = registerBlock("quicksand",
        () -> new QuicksandBlock(BlockBehaviour.Properties.of().noOcclusion().noCollission().sound(SoundType.SAND)
                .strength(7.0F), 57));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}