package com.mcgamer.xadian_magic.datagen;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, XadianMagic.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
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

        simpleItem(ModItems.STAFF_OF_ZIARD);
        simpleItem(ModItems.SKY_STAFF);
        simpleItem(ModItems.MOON_STAFF);
        simpleItem(ModItems.EARTH_STAFF);
        simpleItem(ModItems.SUN_STAFF);
        simpleItem(ModItems.OCEAN_STAFF);

        //simpleItem(ModItems.MAGE_GILLS_AMULET);


    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(XadianMagic.MOD_ID,"item/" + item.getId().getPath()));
    }
}