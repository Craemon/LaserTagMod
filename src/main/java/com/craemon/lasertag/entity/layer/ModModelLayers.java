package com.craemon.lasertag.entity.layer;

import com.craemon.lasertag.LaserTag;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer MAGIC_PROJECTILE =
            new EntityModelLayer(Identifier.of(LaserTag.MOD_ID, "magic_projectile"), "main");
}
