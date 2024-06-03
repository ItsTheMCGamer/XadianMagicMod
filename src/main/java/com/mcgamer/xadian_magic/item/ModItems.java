package com.mcgamer.xadian_magic.item;

import com.mcgamer.xadian_magic.XadianMagic;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TridentItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, XadianMagic.MOD_ID);

    public static final RegistryObject<Item> SPELLBOOK = ITEMS.register("spellbook",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EARTH_PRIMAL_STONE = ITEMS.register("earth_primal_stone",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SKY_PRIMAL_STONE = ITEMS.register("sky_primal_stone",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STAR_PRIMAL_STONE = ITEMS.register("star_primal_stone",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOON_PRIMAL_STONE = ITEMS.register("moon_primal_stone",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SUN_PRIMAL_STONE = ITEMS.register("sun_primal_stone",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OCEAN_PRIMAL_STONE = ITEMS.register("ocean_primal_stone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> QUASAR_DIAMOND_ORE = ITEMS.register("quasar_diamond_ore",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOON_OPAL_ORE = ITEMS.register("moon_opal_ore",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIRE_RUBY_ORE = ITEMS.register("fire_ruby_ore",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AQUAMARINE_ORE = ITEMS.register("aquamarine_ore",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AQUAMARINE_DEEPSLATE_ORE = ITEMS.register("aquamarine_deepslate_ore",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STONEHEART_EMERALD_ORE = ITEMS.register("stoneheart_emerald_ore",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SKY_SAPPHIRE_ORE = ITEMS.register("sky_sapphire_ore",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SKY_SAPPHIRE_DEEPSLATE_ORE = ITEMS.register("sky_sapphire_deepslate_ore",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> QUASAR_DIAMOND = ITEMS.register("quasar_diamond",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOON_OPAL = ITEMS.register("moon_opal",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIRE_RUBY = ITEMS.register("fire_ruby",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AQUAMARINE = ITEMS.register("aquamarine",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STONEHEART_EMERALD = ITEMS.register("stoneheart_emerald",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SKY_SAPPHIRE = ITEMS.register("sky_sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SKY_STAFF = ITEMS.register("sky_staff",
            () -> new SwordItem(Tiers.WOOD, 2, 2, new Item.Properties()));
    public static final RegistryObject<Item> MOON_STAFF = ITEMS.register("moon_staff",
            () -> new SwordItem(Tiers.WOOD, 2, 2, new Item.Properties()));
    public static final RegistryObject<Item> SUN_STAFF = ITEMS.register("sun_staff",
            () -> new SwordItem(Tiers.WOOD, 2, 2, new Item.Properties()));
    public static final RegistryObject<Item> OCEAN_STAFF = ITEMS.register("ocean_staff",
            () -> new SwordItem(Tiers.WOOD, 2, 2, new Item.Properties()));
    public static final RegistryObject<Item> EARTH_STAFF = ITEMS.register("earth_staff",
            () -> new SwordItem(Tiers.WOOD, 2, 2, new Item.Properties()));
    public static final RegistryObject<Item> STAFF_OF_ZIARD = ITEMS.register("staff_of_ziard",
            () -> new SwordItem(Tiers.WOOD, 2, 2, new Item.Properties()));

    public static final RegistryObject<Item> MAGE_GILLS_AMULET = ITEMS.register("mage_gills_amulet",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}