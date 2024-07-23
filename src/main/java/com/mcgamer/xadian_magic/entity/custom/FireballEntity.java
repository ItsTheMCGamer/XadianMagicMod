/*
package com.mcgamer.xadian_magic.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class FireballEntity extends AbstractHurtingProjectile {
    private int explosionPower = 1;

    public FireballEntity(EntityType<? extends FireballEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
            //Minecraft.getInstance().player.sendSystemMessage(Component.literal("level is client side"));
        }
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
            //Minecraft.getInstance().player.sendSystemMessage(Component.literal("idleAnimationTimeout is less than 0"));
            //Minecraft.getInstance().player.sendSystemMessage(Component.literal("" + this.idleAnimationTimeout));
        } else {
            --this.idleAnimationTimeout;
            //Minecraft.getInstance().player.sendSystemMessage(Component.literal("idleAnimationTimeout is decreasing"));
            //Minecraft.getInstance().player.sendSystemMessage(Component.literal("" + this.idleAnimationTimeout));
        }
    }


    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), this.getOwner());
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower, flag, Level.ExplosionInteraction.MOB);
            this.discard();
        }

    }

    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level().isClientSide) {
            Entity entity = pResult.getEntity();
            Entity entity1 = this.getOwner();
            entity.hurt(this.damageSources().magic(), 6.0F);
            if (entity1 instanceof LivingEntity) {
                this.doEnchantDamageEffects((LivingEntity)entity1, entity);
            }

        }
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putByte("ExplosionPower", (byte)this.explosionPower);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("ExplosionPower", 99)) {
            this.explosionPower = pCompound.getByte("ExplosionPower");
        }

    }

    public boolean isOnFire() {
        return false;
    }
}
*/