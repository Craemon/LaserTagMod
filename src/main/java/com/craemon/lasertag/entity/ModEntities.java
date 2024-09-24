package com.craemon.lasertag.entity;

import com.craemon.lasertag.LaserTag;
import com.craemon.lasertag.entity.custom.MagicProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<MagicProjectileEntity> MAGIC_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(LaserTag.MOD_ID, "magic_projectile"),
            EntityType.Builder.<MagicProjectileEntity>create(MagicProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .build()
    );

    public static void registerEntities() {
        LaserTag.LOGGER.info("Registering entities for " + LaserTag.MOD_ID);
    }
}
