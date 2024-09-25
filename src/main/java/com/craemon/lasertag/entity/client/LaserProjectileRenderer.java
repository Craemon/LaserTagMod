package com.craemon.lasertag.entity.client;

import com.craemon.lasertag.LaserTag;
import com.craemon.lasertag.entity.custom.LaserProjectileEntity;
import com.craemon.lasertag.entity.layer.ModModelLayers;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class LaserProjectileRenderer extends EntityRenderer<LaserProjectileEntity> {
    public static final Identifier TEXTURE = Identifier.of(LaserTag.MOD_ID, "textures/entity/laser_projectile.png");
    protected LaserProjectileModel model;

    public LaserProjectileRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        model = new LaserProjectileModel(ctx.getPart(ModModelLayers.LASER_PROJECTILE));
    }

    @Override
    public void render(LaserProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.model.getLayer(TEXTURE), false, false);
        int color = (255 << 24) | (144 << 16) | (238 << 8) | 144;
        this.model.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV, color);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(LaserProjectileEntity entity) {
        return TEXTURE;
    }
}
