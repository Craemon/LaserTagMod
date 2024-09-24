package com.craemon.lasertag;

import com.craemon.lasertag.entity.ModEntities;
import com.craemon.lasertag.entity.client.MagicProjectileModel;
import com.craemon.lasertag.entity.client.MagicProjectileRenderer;
import com.craemon.lasertag.entity.layer.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;


public class LaserTagModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MAGIC_PROJECTILE, MagicProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MAGIC_PROJECTILE, MagicProjectileRenderer::new);
    }
}
