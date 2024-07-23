/*
package com.mcgamer.xadian_magic.entity.client;

import com.mcgamer.xadian_magic.XadianMagic;
import com.mcgamer.xadian_magic.entity.custom.FireballEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FireballEntityRenderer extends EntityRenderer<FireballEntity> {
    private final FireballEntityModel model;

    public FireballEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new FireballEntityModel(pContext.bakeLayer(ModModelLayers.FIREBALL_ENTITY_LAYER));
    }

    protected int getBlockLightLevel(FireballEntity pEntity, BlockPos pPos) {
        return 15;
    }

    public void render(FireballEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.scale(1.2F, 1.2F, 1.2F);
        pPoseStack.translate(0.0F, -0.3F, 0.0F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity)));
        this.model.setupAnim(pEntity, 0.0F, 0.0F, pEntity.tickCount + pPartialTicks, 0.0F, 0.0F);
        this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(FireballEntity pEntity) {
        return new ResourceLocation(XadianMagic.MOD_ID, "textures/entity/fireball_entity.png");
    }
}

 */