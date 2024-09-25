package com.craemon.lasertag.entity.layer;

import com.craemon.lasertag.LaserTag;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer LASER_PROJECTILE =
            new EntityModelLayer(Identifier.of(LaserTag.MOD_ID, "laser_projectile"), "main");
}
