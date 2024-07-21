package com.mcgamer.xadian_magic.networking;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.networking.packet.*;
import com.mcgamer.xadian_magic.networking.packet.earth.EarthquakeSpellC2SPacket;
import com.mcgamer.xadian_magic.networking.packet.earth.QuicksandSpellC2SPacket;
import com.mcgamer.xadian_magic.networking.packet.sky.*;
import com.mcgamer.xadian_magic.networking.packet.sun.FireballSpellC2SPacket;
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

        net.messageBuilder(LightningSpellC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(LightningSpellC2SPacket::new)
                .encoder(LightningSpellC2SPacket::toBytes)
                .consumerMainThread(LightningSpellC2SPacket::handle)
                .add();

        net.messageBuilder(StopRainSpellC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(StopRainSpellC2SPacket::new)
                .encoder(StopRainSpellC2SPacket::toBytes)
                .consumerMainThread(StopRainSpellC2SPacket::handle)
                .add();

        net.messageBuilder(StormSpellC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(StormSpellC2SPacket::new)
                .encoder(StormSpellC2SPacket::toBytes)
                .consumerMainThread(StormSpellC2SPacket::handle)
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

    net.messageBuilder(IceBlowingSpellC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(IceBlowingSpellC2SPacket::new)
                .encoder(IceBlowingSpellC2SPacket::toBytes)
                .consumerMainThread(IceBlowingSpellC2SPacket::handle)
                .add();

    net.messageBuilder(FireballSpellC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(FireballSpellC2SPacket::new)
                .encoder(FireballSpellC2SPacket::toBytes)
                .consumerMainThread(FireballSpellC2SPacket::handle)
                .add();

   net.messageBuilder(QuicksandSpellC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
           .decoder(QuicksandSpellC2SPacket::new)
           .encoder(QuicksandSpellC2SPacket::toBytes)
           .consumerMainThread(QuicksandSpellC2SPacket::handle)
           .add();
   net.messageBuilder(EarthquakeSpellC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
           .decoder(EarthquakeSpellC2SPacket::new)
           .encoder(EarthquakeSpellC2SPacket::toBytes)
           .consumerMainThread(EarthquakeSpellC2SPacket::handle)
           .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sentToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }


}
