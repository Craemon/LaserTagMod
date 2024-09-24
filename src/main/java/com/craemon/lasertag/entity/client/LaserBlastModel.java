package com.craemon.lasertag.entity.client;

import com.craemon.lasertag.entity.custom.LaserBlastEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class LaserBlastModel extends SinglePartEntityModel<LaserBlastEntity> {
    private final ModelPart bb_main;
    public LaserBlastModel(EntityRendererFactory.Context root) {
        this.bb_main = root.getPart(null);
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -5.0F, -3.0F, 4.0F, 4.0F, 8.0F, new Dilation(0.0F))
                .uv(16, 12).cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 0.0F, 6.0F, new Dilation(0.0F))
                .uv(8, 12).cuboid(-2.0F, -6.0F, -3.0F, 4.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(10, 0).cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -3.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 12).cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -3.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(0, 18).cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, -4.0F, -1.5708F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(LaserBlastEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        bb_main.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return bb_main;
    }
}
