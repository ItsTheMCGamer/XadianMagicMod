package com.mcgamer.xadian_magic.networking.packet.sky;

import com.mcgamer.xadian_magic.client.mana.ClientManaData;
import com.mcgamer.xadian_magic.networking.ModPackets;
import com.mcgamer.xadian_magic.client.mana.PlayerManaProvider;
import com.mcgamer.xadian_magic.networking.packet.ManaDataSyncS2CPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class IceBlowingSpellC2SPacket {
    //sky
    public IceBlowingSpellC2SPacket() {

    }

    public IceBlowingSpellC2SPacket(FriendlyByteBuf buf) {

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
                    aabb = new AABB(player.position().add(new Vec3(1, 0, 1)),
                            player.position().add(new Vec3(3, 2, -1)));
                } else if(player.getDirection() == Direction.WEST) {
                    aabb = new AABB(player.position().add(new Vec3(-1, 0, 1)),
                            player.position().add(new Vec3(-3, 2, -1)));
                } else if(player.getDirection() == Direction.NORTH) {
                    aabb = new AABB(player.position().add(new Vec3(1, 0, -1)),
                            player.position().add(new Vec3(-1, 2, -3)));
                } else if(player.getDirection() == Direction.SOUTH) {
                    aabb = new AABB(player.position().add(new Vec3(1, 0, 1)),
                            player.position().add(new Vec3(-1, 2, 3)));
                }
                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class,
                        aabb);

                for(int k2 = 0; k2 < entities.size(); ++k2) {
                    LivingEntity entity = entities.get(k2);
                    MobEffectInstance slownessEffect = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300,
                            2);

                    entity.addEffect(slownessEffect);
                    player.setIsInPowderSnow(true);
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
