package com.mcgamer.xadian_magic.tag;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.util.QuicksandAPI;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class QuicksandTags {
    public static class Blocks {
        public static final TagKey<Block> QUICKSAND = tag("quicksand");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(XadianMagic.MOD_ID, name));
        }
    }

    public static class EntityTypes {
        public static final TagKey<EntityType<?>> SURVIVES_IN_QUICKSAND =
                TagKey.create(Registries.ENTITY_TYPE, QuicksandAPI.loc("survives_in_quicksand"));

        }
    }