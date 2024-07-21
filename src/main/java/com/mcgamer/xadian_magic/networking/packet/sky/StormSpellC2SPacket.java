package com.mcgamer.xadian_magic.networking.packet.sky;

import com.mcgamer.xadian_magic.client.mana.ClientManaData;
import com.mcgamer.xadian_magic.client.mana.PlayerManaProvider;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.networking.packet.ManaDataSyncS2CPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StormSpellC2SPacket {
    //sky
    public StormSpellC2SPacket() {

    }

    public StormSpellC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        context.enqueueWork(() -> {
            //HERE IS ON SERVER
            CommandSourceStack commandSourceStack = context.getSender().createCommandSourceStack();

            if(ClientManaData.getPlayerMana() >= 10) {
                //stop rain
                setThunder(commandSourceStack, 1000);


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

    private static int getDuration(CommandSourceStack pSource, int pTime, IntProvider pTimeProvider) {
        return pTime == -1 ? pTimeProvider.sample(pSource.getLevel().getRandom()) : pTime;
    }

    public static int setThunder(CommandSourceStack pSource, int pTime) {
        pSource.getLevel().setWeatherParameters(0, getDuration(pSource, pTime, ServerLevel.THUNDER_DURATION),
                true, true);
        return pTime;
    }

}
