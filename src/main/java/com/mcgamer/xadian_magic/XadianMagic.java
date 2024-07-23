package com.mcgamer.xadian_magic;

import com.mcgamer.xadian_magic.block.ModBlocks;
import com.mcgamer.xadian_magic.command.ManaCommand;
import com.mcgamer.xadian_magic.effect.XadianEffectsRegistry;
//import com.mcgamer.xadian_magic.entity.ModEntities;
//import com.mcgamer.xadian_magic.entity.client.FireballEntityRenderer;
import com.mcgamer.xadian_magic.item.ModCreativeModeTabs;
import com.mcgamer.xadian_magic.item.ModItems;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(XadianMagic.MOD_ID)
public class XadianMagic {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "xadian_magic";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public XadianMagic() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        XadianEffectsRegistry.EFFECTS.register(modEventBus);

        //ModEntities.register(modEventBus);

        RegistryManager.FLUIDTYPES.register(modEventBus);
        RegistryManager.FLUIDS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModPackets.register();
    }

    private void registerCommands(RegisterCommandsEvent evt) {
        ManaCommand.register(evt.getDispatcher());
    }
    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(RegistryManager.MUD.FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(RegistryManager.MUD.FLUID_FLOW.get(), RenderType.translucent());

            //EntityRenderers.register(ModEntities.FIREBALL_ENTITY.get(), FireballEntityRenderer::new);
        }
    }
}
