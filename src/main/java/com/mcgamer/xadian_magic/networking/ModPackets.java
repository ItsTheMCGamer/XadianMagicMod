package com.mcgamer.xadian_magic.networking;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.networking.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPackets {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(XadianMagic.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ExampleC2SPacket::new)
                .encoder(ExampleC2SPacket::toBytes)
                .consumerMainThread(ExampleC2SPacket::handle)
                .add();

        net.messageBuilder(StopRainSpellC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(StopRainSpellC2SPacket::new)
                .encoder(StopRainSpellC2SPacket::toBytes)
                .consumerMainThread(StopRainSpellC2SPacket::handle)
                .add();


        net.messageBuilder(ManaDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ManaDataSyncS2CPacket::new)
                .encoder(ManaDataSyncS2CPacket::toBytes)
                .consumerMainThread(ManaDataSyncS2CPacket::handle)
                .add();

    net.messageBuilder(MasteredWindBlowingSpellC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(MasteredWindBlowingSpellC2SPacket::new)
                .encoder(MasteredWindBlowingSpellC2SPacket::toBytes)
                .consumerMainThread(MasteredWindBlowingSpellC2SPacket::handle)
                .add();

    net.messageBuilder(WeakWindBlowingSpellC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(WeakWindBlowingSpellC2SPacket::new)
                .encoder(WeakWindBlowingSpellC2SPacket::toBytes)
                .consumerMainThread(WeakWindBlowingSpellC2SPacket::handle)
                .add();

    net.messageBuilder(FireballSpellC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(FireballSpellC2SPacket::new)
                .encoder(FireballSpellC2SPacket::toBytes)
                .consumerMainThread(FireballSpellC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sentToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }


}
