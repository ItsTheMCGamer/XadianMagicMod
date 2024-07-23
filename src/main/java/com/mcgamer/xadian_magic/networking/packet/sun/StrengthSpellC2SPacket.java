package com.mcgamer.xadian_magic.networking.packet.sun;

import com.mcgamer.xadian_magic.client.mana.ClientManaData;
import com.mcgamer.xadian_magic.client.mana.PlayerManaProvider;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.networking.packet.ManaDataSyncS2CPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StrengthSpellC2SPacket {

    public StrengthSpellC2SPacket() {

    }

    public StrengthSpellC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        context.enqueueWork(() -> {
            if(ClientManaData.getPlayerMana() >= 5) {

                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    mana.subMana(5);
                    ModPackets.sentToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), player);
                            });

                MobEffectInstance strengthEffect = new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600,
                        1);
                player.addEffect(strengthEffect);

            } else {
                player.sendSystemMessage(Component.literal("You don't have enough mana!")
                            .withStyle(ChatFormatting.RED));
                }
        });
        return true;
    }



}
