package com.craemon.lasertag.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.world.World;


public class LaserBlastEntity extends SpectralArrowEntity {

    public LaserBlastEntity(EntityType<? extends SpectralArrowEntity> entityType, World world) {
        super(entityType, world);
    }
}
