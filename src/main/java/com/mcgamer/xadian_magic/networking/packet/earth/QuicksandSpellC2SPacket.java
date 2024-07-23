package com.mcgamer.xadian_magic.networking.packet.earth;

import com.mcgamer.xadian_magic.block.ModBlocks;
import com.mcgamer.xadian_magic.client.mana.ClientManaData;
import com.mcgamer.xadian_magic.client.mana.PlayerManaProvider;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.networking.packet.ManaDataSyncS2CPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class QuicksandSpellC2SPacket {

    public QuicksandSpellC2SPacket() {

    }

    public QuicksandSpellC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        ServerLevel level = context.getSender().serverLevel();
        context.enqueueWork(() -> {
            if(ClientManaData.getPlayerMana() >= 30) {

                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    mana.subMana(30);
                    ModPackets.sentToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), player);
                });

                HitResult hitResult = player.pick(4, 0.0F, false);

                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    Vec3 loc = hitResult.getLocation();
                    BlockPos pos = new BlockPos((int) Math.floor(loc.x), (int) Math.floor(loc.y) - 1, (int) Math.floor(loc.z));
                    if (level.getBlockState(pos) == Blocks.STONE.defaultBlockState()) {

                        level.setBlockAndUpdate(pos, ModBlocks.QUICKSAND_BLOCK.get().defaultBlockState());
                        level.setBlockAndUpdate(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()),
                                ModBlocks.QUICKSAND_BLOCK.get().defaultBlockState());
                        level.setBlockAndUpdate(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()),
                                ModBlocks.QUICKSAND_BLOCK.get().defaultBlockState());
                        level.setBlockAndUpdate(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1),
                                ModBlocks.QUICKSAND_BLOCK.get().defaultBlockState());
                        level.setBlockAndUpdate(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1),
                                ModBlocks.QUICKSAND_BLOCK.get().defaultBlockState());
                    } else {
                        player.sendSystemMessage(Component.literal("You are unable to transform this block into quicksand!"));
                    }
                }

            } else {
            player.sendSystemMessage(Component.literal("You don't have enough mana!")
                    .withStyle(ChatFormatting.RED));
            }
        });
        return true;
    }
}
