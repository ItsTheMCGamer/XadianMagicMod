package com.mcgamer.xadian_magic.command;

import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.networking.packet.ManaDataSyncS2CPacket;
import com.mcgamer.xadian_magic.spells.mana.PlayerManaProvider;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Collection;

public class ManaCommand {
    public static void register(CommandDispatcher<CommandSourceStack> pDispatcher) {
        LiteralCommandNode<CommandSourceStack> literalcommandnode = pDispatcher.register(Commands.literal("mana").requires((p_137324_) -> {
            return p_137324_.hasPermission(2);
        }).then(Commands.literal("set").then(Commands.argument("targets", EntityArgument.players())
                .then(Commands.argument("amount", IntegerArgumentType.integer()).executes((p_137335_) -> {
                    return setMana(p_137335_.getSource(), EntityArgument.getPlayers(p_137335_, "targets"),
                            IntegerArgumentType.getInteger(p_137335_, "amount"));

        })))).then(Commands.literal("add").then(Commands.argument("targets", EntityArgument.players())
                .then(Commands.argument("amount", IntegerArgumentType.integer()).executes((p_137341_) -> {
                    return addMana(p_137341_.getSource(), EntityArgument.getPlayers(p_137341_, "targets"),
                            IntegerArgumentType.getInteger(p_137341_, "amount"));

        })))).then(Commands.literal("get").then(Commands.argument("targets", EntityArgument.players())
                .executes((p_137309_) -> {
                    return getMana(p_137309_.getSource(), EntityArgument.getPlayers(p_137309_, "targets"));

        }))));
    }

    private static int setMana(CommandSourceStack pSource, Collection<? extends ServerPlayer> pTargets, int pAmount)
            throws CommandSyntaxException {
        for(ServerPlayer serverplayer : pTargets) {
                if(pAmount >= 0 && pAmount <= 50) {
                    serverplayer.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                        mana.setMana(pAmount);
                        ModPackets.sentToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), serverplayer);
                            });
                    pSource.sendSuccess(() -> {
                        return Component.literal(serverplayer.getName().getString() + "'s mana was set to " + pAmount);
                    }, true);
                } else {
                    pSource.sendFailure(Component.literal(pAmount + " is not a valid input!"));
                }
        }
            return pTargets.size();
    }

    private static int addMana(CommandSourceStack pSource, Collection<? extends ServerPlayer> pTargets, int pAmount)
            throws CommandSyntaxException {
            for (ServerPlayer serverplayer : pTargets) {
                serverplayer.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                if (mana.getMana() + pAmount >= 0 && mana.getMana() + pAmount <= 50) {
                    mana.addMana(pAmount);
                    ModPackets.sentToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), serverplayer);
                    pSource.sendSuccess(() -> {
                        return Component.literal(pAmount + " mana was added to " + serverplayer.getName().getString());
                    }, true);
                } else {
                    pSource.sendFailure(Component.literal("You cannot have " + (pAmount + mana.getMana()) + " mana!"));
                }
                });
            }
        return pTargets.size();
    }

    private static int getMana(CommandSourceStack pSource, Collection<? extends ServerPlayer> pTargets)
            throws CommandSyntaxException {
            for (ServerPlayer serverplayer : pTargets) {
                serverplayer.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    int amount = mana.getMana();
                    pSource.sendSuccess(() -> {
                        return Component.literal(serverplayer.getName().getString() + " has " + amount + " mana.");
                    }, true);
                });
            }
        return pTargets.size();
    }
}
