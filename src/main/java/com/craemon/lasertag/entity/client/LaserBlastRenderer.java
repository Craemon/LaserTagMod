package com.craemon.lasertag.entity.client;

import com.craemon.lasertag.entity.custom.LaserBlastEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class LaserBlastRenderer extends ProjectileEntityRenderer<LaserBlastEntity> {
    public LaserBlastRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(LaserBlastEntity entity) {
        return null;
    }
}
