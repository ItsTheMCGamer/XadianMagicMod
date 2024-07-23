package com.mcgamer.xadian_magic;

import com.mcgamer.xadian_magic.fluids.MudFluidType;
import com.mcgamer.xadian_magic.fluids.XadianFluidType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.mcgamer.xadian_magic.block.ModBlocks.BLOCKS;
import static com.mcgamer.xadian_magic.item.ModItems.ITEMS;

public class RegistryManager {

    public static final DeferredRegister<FluidType> FLUIDTYPES = DeferredRegister.create(
            ForgeRegistries.Keys.FLUID_TYPES, XadianMagic.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, XadianMagic.MOD_ID);

    public static List<FluidStuff> fluidList = new ArrayList<FluidStuff>();

    public static FluidStuff addFluid(String localizedName, XadianFluidType.FluidInfo info, BiFunction<FluidType.Properties, XadianFluidType.FluidInfo, FluidType> type, BiFunction<Supplier<? extends FlowingFluid>, BlockBehaviour.Properties, LiquidBlock> block, Function<ForgeFlowingFluid.Properties, ForgeFlowingFluid.Source> source, Function<ForgeFlowingFluid.Properties, ForgeFlowingFluid.Flowing> flowing, @Nullable Consumer<ForgeFlowingFluid.Properties> fluidProperties, FluidType.Properties prop) {
        FluidStuff fluid = new FluidStuff(info.name, localizedName, info.color, type.apply(prop, info), block, fluidProperties, source, flowing);
        fluidList.add(fluid);
        return fluid;
    }

    public static FluidStuff addFluid(String localizedName, XadianFluidType.FluidInfo info, BiFunction<FluidType.Properties, XadianFluidType.FluidInfo, FluidType> type, BiFunction<Supplier<? extends FlowingFluid>, BlockBehaviour.Properties, LiquidBlock> block, @Nullable Consumer<ForgeFlowingFluid.Properties> fluidProperties, FluidType.Properties prop) {
        return addFluid(localizedName, info, type, block, ForgeFlowingFluid.Source::new, ForgeFlowingFluid.Flowing::new, fluidProperties, prop);
    }

    public static final FluidStuff MUD = addFluid("Mud", new XadianFluidType.FluidInfo(
            "mud", 0x85280A, 0.1F, 1.25F), MudFluidType::new, LiquidBlock::new,
            prop -> prop.slopeFindDistance(3).levelDecreasePerBlock(3).tickRate(18),
            FluidType.Properties.create()
                    .canSwim(false)
                    .canDrown(true)
                    .pathType(BlockPathTypes.WATER)
                    .adjacentPathType(null)
                    .canPushEntity(true)
                    .canHydrate(true)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .density(50)
                    .viscosity(200)
                    .temperature(20));


    public static class FluidStuff {

        public final ForgeFlowingFluid.Properties PROPERTIES;

        public final RegistryObject<ForgeFlowingFluid.Source> FLUID;
        public final RegistryObject<ForgeFlowingFluid.Flowing> FLUID_FLOW;
        public final RegistryObject<FluidType> TYPE;

        public final RegistryObject<LiquidBlock> FLUID_BLOCK;

        public final RegistryObject<BucketItem> FLUID_BUCKET;

        public final String name;
        public final String localizedName;
        public final int color;

        public FluidStuff(String name, String localizedName, int color, FluidType type, BiFunction<Supplier<? extends FlowingFluid>, BlockBehaviour.Properties, LiquidBlock> block, @Nullable Consumer<ForgeFlowingFluid.Properties> fluidProperties, Function<ForgeFlowingFluid.Properties, ForgeFlowingFluid.Source> source, Function<ForgeFlowingFluid.Properties, ForgeFlowingFluid.Flowing> flowing) {
            this.name = name;
            this.localizedName = localizedName;
            this.color = color;

            FLUID = FLUIDS.register(name, () -> source.apply(getFluidProperties()));
            FLUID_FLOW = FLUIDS.register("flowing_" + name, () -> flowing.apply(getFluidProperties()));
            TYPE = FLUIDTYPES.register(name, () -> type);

            PROPERTIES = new ForgeFlowingFluid.Properties(TYPE, FLUID, FLUID_FLOW);
            if (fluidProperties != null)
                fluidProperties.accept(PROPERTIES);

            FLUID_BLOCK = BLOCKS.register(name + "_block", () -> block.apply(FLUID, Block.Properties.of().liquid().pushReaction(PushReaction.DESTROY).lightLevel((state) -> { return type.getLightLevel(); }).randomTicks().replaceable().strength(100.0F).noLootTable()));
            FLUID_BUCKET = ITEMS.register(name + "_bucket", () -> new BucketItem(FLUID, new BucketItem.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

            PROPERTIES.bucket(FLUID_BUCKET).block(FLUID_BLOCK);
        }

        public ForgeFlowingFluid.Properties getFluidProperties() {
            return PROPERTIES;
        }
    }

}
