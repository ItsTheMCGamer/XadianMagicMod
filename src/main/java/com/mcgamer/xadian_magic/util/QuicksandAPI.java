package com.mcgamer.xadian_magic.util;

import com.mcgamer.xadian_magic.block.custom.QuicksandBlock;
import net.minecraft.core.Holder;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class QuicksandAPI {

    public static final String ID = "quicksand";

    public static final Map<Item, CauldronInteraction> QUICKSAND_INTERACTIONS =
            CauldronInteraction.newInteractionMap();
    public static final Map<Item, CauldronInteraction> RED_QUICKSAND_INTERACTIONS =
            CauldronInteraction.newInteractionMap();

    public static final RegistryObject<QuicksandBlock> QUICKSAND_BLOCK =
            RegistryObject.create(loc("quicksand"), ForgeRegistries.BLOCKS);


    public static ResourceLocation loc(String id) {
        return new ResourceLocation(ID, id);
    }

}