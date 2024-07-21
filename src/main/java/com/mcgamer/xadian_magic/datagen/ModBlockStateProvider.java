package com.mcgamer.xadian_magic.datagen;

import com.mcgamer.xadian_magic.RegistryManager;
import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, XadianMagic.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for(RegistryManager.FluidStuff fluid : RegistryManager.fluidList) {
            fluid(fluid.FLUID_BLOCK, fluid.name);
        }

        blockWithItem(ModBlocks.QUICKSAND_BLOCK);

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    public void fluid(RegistryObject<? extends Block> fluid, String name) {
            simpleBlock(fluid.get(), models().cubeAll(name, new ResourceLocation(XadianMagic.MOD_ID, ModelProvider.BLOCK_FOLDER + "/fluid/" + name + "_still")));
    }
}