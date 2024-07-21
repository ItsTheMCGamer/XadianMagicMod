package com.mcgamer.xadian_magic.block.custom;

import com.google.common.collect.Multimap;
import com.mcgamer.xadian_magic.effect.XadianEffectsRegistry;
import com.mcgamer.xadian_magic.tag.QuicksandTags;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(Dist.CLIENT)
public class QuicksandBlock extends SandBlock {
    int b = 0;

    private static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0.0, 0.0, 0.0, 1.0, 0.9f, 1.0);
    private static final UUID SPEED_MODIFIER_POWDER_SNOW_UUID = UUID.fromString("1eaf83ff-7207-4596-b37a-d7a07b3ec4ce");
    private  Multimap<Attribute, AttributeModifier> defaultModifiers;

    public QuicksandBlock(Properties settings, int color) {
        super(color, settings);
    }

    public static void spawnParticles(Level world, BlockState state, Vec3 pos) {
        if (world.isClientSide) {
            RandomSource random = world.getRandom();

            for (int i = 0; i < random.nextInt(3); ++i) {
                world.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, state), pos.x+(Mth.randomBetween(random, -1.0f, 1.0f)), pos.y+(Mth.randomBetween(random, -1.0f, 1.0f)), pos.z+(Mth.randomBetween(random, -1.0f, 1.0f)), (-1.0F + random.nextFloat() * 2.0F) / 12.0F, 0.05, (-1.0F + random.nextFloat() * 2.0F) / 12.0F);
            }
        }
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.is(this) || super.skipRendering(state, stateFrom, direction);
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
        return Shapes.empty();
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity || entity.getFeetBlockState().is(this)) {
            entity.makeStuckInBlock(state, new Vec3((double) 0.9F, 1.5D, (double)0.9F));
        }
        if(entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(XadianEffectsRegistry.XadianEffects.QUICKSAND_EFFECT, 1,
                    4, false, false, false));
        }
        if (!entity.isSpectator() && hasEntityMoved(entity)) {
            if (entity instanceof LivingEntity living &&
                    !canSurviveInQuicksand(living)) {
                living.hurt(Minecraft.getInstance().level.damageSources().inWall(), 1F);

            }
            if(world.getRandom().nextBoolean())
                spawnParticles(world, state, new Vec3(entity.getX(), pos.getY(), entity.getZ()));
        }
    }

    public boolean hasEntityMoved(Entity entity) {
        return entity.xOld - entity.getX() >= 0.001 ||
                entity.yOld - entity.getY() >= 0.001 ||
                entity.zOld - entity.getZ() >= 0.001;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityShapeContext) {
            Entity entity = entityShapeContext.getEntity();
            if(entity != null) {
                 if (entity.fallDistance > 2.5f) {
                    return FALLING_COLLISION_SHAPE;
                }
            }

            }
        return Shapes.empty();
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    public static boolean canSurviveInQuicksand(LivingEntity living) {
        return living.getMobType() == MobType.UNDEAD || living.getType().is(QuicksandTags.EntityTypes.SURVIVES_IN_QUICKSAND);
    }

    @Override
    protected void falling(FallingBlockEntity entity) {
        entity.dropItem = false;
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
        return type == PathComputationType.LAND;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {

        Player player = Minecraft.getInstance().player;
        if(player != null) {
            Vec3 playerPos = player.position();
            BlockPos playerFeet = new BlockPos((int)Math.floor(playerPos.x), (int)Math.floor(playerPos.y),
                    (int)Math.floor(playerPos.z));

            if(playerFeet.equals(pPos) && b == 50) {
                player.hurt(pLevel.damageSources().cramming(), 1);
                b = 0;
            } else {
                b++;
            }
        }

        super.tick(pState, pLevel, pPos, pRandom);
    }
}
