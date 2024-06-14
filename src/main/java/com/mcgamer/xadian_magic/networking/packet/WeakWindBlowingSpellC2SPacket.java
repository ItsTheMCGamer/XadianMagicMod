package com.mcgamer.xadian_magic.networking.packet;

import com.google.common.collect.Maps;
import com.mcgamer.xadian_magic.client.ClientManaData;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.spells.BasicSpells;
import com.mcgamer.xadian_magic.spells.mana.PlayerManaProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static net.minecraft.world.level.Explosion.getSeenPercent;

public class WeakWindBlowingSpellC2SPacket {
    private final Map<Player, Vec3> hitPlayers = Maps.newHashMap();

    public WeakWindBlowingSpellC2SPacket() {

    }

    public WeakWindBlowingSpellC2SPacket(FriendlyByteBuf buf) {

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

            float f2 = 5.0F;

            if(ClientManaData.getPlayerMana() >= 5) {
                //spell logic here
                AABB aabb = player.getBoundingBox();

                if(player.getDirection() == Direction.EAST) {
                    aabb = new AABB(player.position().add(new Vec3(0, 0, 1)),
                            player.position().add(new Vec3(3, 1, -1)));
                } else if(player.getDirection() == Direction.WEST) {
                    aabb = new AABB(player.position().add(new Vec3(0, 0, 1)),
                            player.position().add(new Vec3(-3, 1, -1)));
                } else if(player.getDirection() == Direction.NORTH) {
                    aabb = new AABB(player.position().add(new Vec3(0, 0, 1)),
                            player.position().add(new Vec3(-1, 1, -3)));
                } else if(player.getDirection() == Direction.SOUTH) {
                    aabb = new AABB(player.position().add(new Vec3(0, 0, 1)),
                            player.position().add(new Vec3(-1, 1, 3)));
                }
                Vec3 vec3 = new Vec3(player.getX(), player.getY(), player.getZ());
                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class,
                        aabb);

                for(int k2 = 0; k2 < entities.size(); ++k2) {
                    Entity entity = entities.get(k2);

                    double d12 = Math.sqrt(entity.distanceToSqr(vec3)) / (double)f2;
                    if (d12 <= 1.0D) {
                        double d5 = entity.getX() - player.getX();
                        double d7 = (entity instanceof PrimedTnt ? entity.getY() : entity.getEyeY()) - player.getY();
                        double d9 = entity.getZ() - player.getZ();
                        double d13 = Math.sqrt(d5 * d5 + d7 * d7 + d9 * d9);
                        if (d13 != 0.0D) {
                            d5 /= d13;
                            d7 /= d13;
                            d9 /= d13;
                            double d14 = (double)getSeenPercent(vec3, entity);
                            double d10 = (1.0D - d12) * d14;
                            double d11 = d10;
                            if (entity instanceof LivingEntity) {
                                d11 = ProtectionEnchantment.getExplosionKnockbackAfterDampener((LivingEntity)entity, d10);
                            }
                            entity.setDeltaMovement(entity.getDeltaMovement().add(d5 * d11 * 1.4F, 0.6F,
                                    d9 * d11 * 1.4F));
                            if (entity instanceof Player) {
                                Player player1 = (Player)entity;
                                if (!player1.isSpectator() && (!player1.isCreative() || !player1.getAbilities().flying)) {
                                    this.hitPlayers.put(player1, new Vec3(d5 * d10, d7 * d10, d9 * d10));
                                }
                            }
                        }
                    }
                }

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
