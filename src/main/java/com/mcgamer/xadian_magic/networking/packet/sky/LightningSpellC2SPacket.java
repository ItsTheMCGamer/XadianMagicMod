package com.mcgamer.xadian_magic.networking.packet.sky;

import com.mcgamer.xadian_magic.client.mana.ClientManaData;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.client.mana.PlayerManaProvider;
import com.mcgamer.xadian_magic.networking.packet.ManaDataSyncS2CPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class LightningSpellC2SPacket {
    //sky
    public LightningSpellC2SPacket() {

    }

    public LightningSpellC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        context.enqueueWork(() -> {
            if(ClientManaData.getPlayerMana() >= 20) {
                spawnLightningAtPos(player.position(), player.level());

                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    mana.subMana(20);
                    ModPackets.sentToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), player);
                            });
            } else {
                player.sendSystemMessage(Component.literal("You don't have enough mana!")
                            .withStyle(ChatFormatting.RED));
                }
        });
        return true;
    }


    public void spawnLightningAtPos(Vec3 sourcePos, Level world) {
        // Get the player's horizontal facing direction
        Vec3 playerLook = Minecraft.getInstance().player.getLookAngle();

        // Normalize the direction vector
        playerLook = playerLook.normalize();

        // Calculate the horizontal offset (adjust this value for desired distance)
        double horizontalDistance = 5.0; // Adjust this value for the desired distance
        Vec3 horizontalOffset = playerLook.scale(horizontalDistance);

        // Determine the target position
        Vec3 targetPos = sourcePos.add(horizontalOffset);

        // Spawn the lightning bolt horizontally
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
        lightningBolt.setPos(targetPos.x, targetPos.y, targetPos.z);
        world.addFreshEntity(lightningBolt);
    }

}
