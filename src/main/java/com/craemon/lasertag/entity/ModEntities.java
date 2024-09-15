package com.craemon.lasertag.entity;

import com.craemon.lasertag.LaserTag;
import com.craemon.lasertag.entity.custom.LaserProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<LaserProjectileEntity> LASER_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(LaserTag.MOD_ID, "laserprojectile"),
            EntityType.Builder.create(LaserProjectileEntity::new, SpawnGroup.CREATURE).dimensions(0.5f, 0.5f).build());

    public static void registerModEntities() {

    }
}
