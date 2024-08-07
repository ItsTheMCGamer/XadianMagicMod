package com.mcgamer.xadian_magic.event;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.client.mana.ManaHudOverlay;
import com.mcgamer.xadian_magic.networking.packet.sky.WeakWindBlowingSpellC2SPacket;
import com.mcgamer.xadian_magic.screens.SpellBookScreen;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = XadianMagic.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.CAST_SPELL_KEY.consumeClick()) {
                ModPackets.sendToServer(new WeakWindBlowingSpellC2SPacket());
            }
            if(KeyBinding.OPEN_GUI_KEY.consumeClick()) {
                Minecraft.getInstance().setScreen(new SpellBookScreen(Component.literal("Spellbook")));
            }
        }
    }

    @Mod.EventBusSubscriber(modid = XadianMagic.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.CAST_SPELL_KEY);
            event.register(KeyBinding.OPEN_GUI_KEY);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("mana", ManaHudOverlay.HUD_MANA);
        }

    }
}
