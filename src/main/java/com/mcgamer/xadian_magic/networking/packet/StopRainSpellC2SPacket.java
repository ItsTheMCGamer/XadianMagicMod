package com.mcgamer.xadian_magic.networking.packet;

import com.mcgamer.xadian_magic.client.ClientManaData;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.spells.BasicSpells;
import com.mcgamer.xadian_magic.spells.mana.PlayerMana;
import com.mcgamer.xadian_magic.spells.mana.PlayerManaProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StopRainSpellC2SPacket {
    public StopRainSpellC2SPacket() {

    }

    public StopRainSpellC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        context.enqueueWork(() -> {
            //HERE IS ON SERVER
            CommandSourceStack commandSourceStack = context.getSender().createCommandSourceStack();
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Cast a spell... sort of."));

            if(ClientManaData.getPlayerMana() >= 5) {
                //stop rain
                BasicSpells.setClear(commandSourceStack, 1000);
                //remove 10 mana
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    mana.subMana(5);
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
