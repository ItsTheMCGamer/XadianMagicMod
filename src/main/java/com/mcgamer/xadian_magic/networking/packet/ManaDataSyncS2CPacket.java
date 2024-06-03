package com.mcgamer.xadian_magic.networking.packet;

import com.mcgamer.xadian_magic.client.ClientManaData;
import com.mcgamer.xadian_magic.spells.BasicSpells;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaDataSyncS2CPacket {
    private final int mana;

    public ManaDataSyncS2CPacket(int mana) {
        this.mana = mana;
    }

    public ManaDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.mana = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(mana);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //HERE IS ON CLIENT
            ClientManaData.set(mana);

        });
        return true;
    }

}
