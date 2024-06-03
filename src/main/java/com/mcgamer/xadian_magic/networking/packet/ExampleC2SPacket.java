package com.mcgamer.xadian_magic.networking.packet;

import com.mcgamer.xadian_magic.spells.BasicSpells;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ExampleC2SPacket {
    public ExampleC2SPacket() {

    }

    public ExampleC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //HERE IS ON SERVER
            CommandSourceStack commandSourceStack = context.getSender().createCommandSourceStack();
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Cast a spell... sort of."));
            //stop the raining for 50 per 10 mana
            //restart the raining when player runs out of mana
            BasicSpells.setClear(commandSourceStack, 100);

        });
        return true;
    }

}
