package com.mcgamer.xadian_magic.networking.packet.sun;

import com.mcgamer.xadian_magic.client.mana.ClientManaData;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.client.mana.PlayerManaProvider;
import com.mcgamer.xadian_magic.networking.packet.ManaDataSyncS2CPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FireballSpellC2SPacket {
    //sun
    public FireballSpellC2SPacket() {

    }

    public FireballSpellC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        context.enqueueWork(() -> {
            if(ClientManaData.getPlayerMana() >= 10) {
                Vec3 vec3 = player.getViewVector(1.0F);

                LargeFireball largeFireball = new LargeFireball(player.level(), player, vec3.x, vec3.y, vec3.z,
                        2);
                largeFireball.setPos(player.getX(), player.getY(),
                        player.getZ());
                player.level().addFreshEntity(largeFireball);

                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    mana.subMana(10);
                    ModPackets.sentToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), player);
                            });
            } else {
                player.sendSystemMessage(Component.literal("You don't have enough mana!")
                            .withStyle(ChatFormatting.RED));
                }
        });
        return true;
    }



}
