package com.mcgamer.xadian_magic.datagen;

import com.mcgamer.xadian_magic.RegistryManager;
import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, XadianMagic.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryManager.FluidStuff fluid : RegistryManager.fluidList) {
            bucketModel(fluid.FLUID_BUCKET, fluid.FLUID.get());
            //itemWithModel(fluid.FLUID_BUCKET, "item/generated");
        }

        simpleItem(ModItems.SPELLBOOK);

        //simpleItem(ModItems.EARTH_PRIMAL_STONE);
        //simpleItem(ModItems.SKY_PRIMAL_STONE);
        //simpleItem(ModItems.STAR_PRIMAL_STONE);
        //simpleItem(ModItems.SUN_PRIMAL_STONE);
        //simpleItem(ModItems.OCEAN_PRIMAL_STONE);
        //simpleItem(ModItems.MOON_PRIMAL_STONE);

        //simpleItem(ModItems.QUASAR_DIAMOND);
        //simpleItem(ModItems.MOON_OPAL);
        //simpleItem(ModItems.STONEHEART_EMERALD);
        //simpleItem(ModItems.SKY_SAPPHIRE);
        //simpleItem(ModItems.FIRE_RUBY);
        //simpleItem(ModItems.AQUAMARINE);

        //simpleItem(ModItems.MAGE_GILLS_AMULET);

        handheldItem(ModItems.SKY_STAFF);
        handheldItem(ModItems.SUN_STAFF);
        handheldItem(ModItems.OCEAN_STAFF);
        handheldItem(ModItems.MOON_STAFF);
        handheldItem(ModItems.EARTH_STAFF);
        handheldItem(ModItems.STAFF_OF_ZIARD);


    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(XadianMagic.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(XadianMagic.MOD_ID,"item/" + item.getId().getPath()));
    }

    public void bucketModel(RegistryObject<? extends BucketItem> registryObject, Fluid fluid) {
        ModelBuilder<ItemModelBuilder> builder = withExistingParent(registryObject.getId().getPath(), new ResourceLocation(XadianMagic.MOD_ID, "item/bucket_fluid"));
        builder.customLoader(DynamicFluidContainerModelBuilder::begin).fluid(fluid).coverIsMask(false).flipGas(true).end();
    }
    public void itemWithModel(RegistryObject<? extends Item> registryObject, String model) {
        ResourceLocation id = registryObject.getId();
        ResourceLocation textureLocation = new ResourceLocation(id.getNamespace(), "item/" + id.getPath());
        singleTexture(id.getPath(), new ResourceLocation(model), "layer0", textureLocation);
    }


}