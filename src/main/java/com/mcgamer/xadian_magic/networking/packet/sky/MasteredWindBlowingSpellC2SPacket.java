package com.mcgamer.xadian_magic.networking.packet.sky;

import com.google.common.collect.Maps;
import com.mcgamer.xadian_magic.client.mana.ClientManaData;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.client.mana.PlayerManaProvider;
import com.mcgamer.xadian_magic.networking.packet.ManaDataSyncS2CPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static net.minecraft.world.level.Explosion.getSeenPercent;

public class MasteredWindBlowingSpellC2SPacket {
    //sky
    private final Map<Player, Vec3> hitPlayers = Maps.newHashMap();

    public MasteredWindBlowingSpellC2SPacket() {

    }

    public MasteredWindBlowingSpellC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        context.enqueueWork(() -> {

            float f2 = 6.0F;

            if(ClientManaData.getPlayerMana() >= 20) {
                //remove 15 mana
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    mana.subMana(20);
                    ModPackets.sentToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), player);
                });

                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class,
                        player.getBoundingBox().inflate(2));

                Vec3 vec3 = new Vec3(player.getX(), player.getY(), player.getZ());

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
                            entity.setDeltaMovement(entity.getDeltaMovement().add(d5 * d11 * 2.5F, d7 * d11 * 1.5F,
                                    d9 * d11 * 2.5F));
                            if (entity instanceof Player) {
                                Player player1 = (Player)entity;
                                if (!player1.isSpectator() && (!player1.isCreative() || !player1.getAbilities().flying)) {
                                    this.hitPlayers.put(player1, new Vec3(d5 * d10, d7 * d10, d9 * d10));
                                }
                            }
                        }
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
