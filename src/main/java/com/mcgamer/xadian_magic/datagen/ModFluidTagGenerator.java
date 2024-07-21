package com.mcgamer.xadian_magic.datagen;

import com.mcgamer.xadian_magic.RegistryManager;
import com.mcgamer.xadian_magic.XadianMagic;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagGenerator extends FluidTagsProvider {

    public static final TagKey<Fluid> MUD = FluidTags.create(new ResourceLocation("forge", "mud"));

    public ModFluidTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, XadianMagic.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {

        tag(MUD).add(RegistryManager.MUD.FLUID.get(), RegistryManager.MUD.FLUID_FLOW.get());
    }
}