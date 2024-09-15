package com.craemon.lasertag;

import com.craemon.lasertag.entity.ModEntities;
import com.craemon.lasertag.entity.client.LaserProjectileModel;
import com.craemon.lasertag.entity.client.LaserProjectileRenderer;
import com.craemon.lasertag.entity.layer.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class LaserTagModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LASER_PROJECTILE, LaserProjectileModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.LASER_PROJECTILE, LaserProjectileRenderer::new);
    }
}
