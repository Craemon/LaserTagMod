package com.craemon.lasertag;

import com.craemon.lasertag.entity.custom.LaserBlastEntity;
import com.craemon.lasertag.item.ModItemGroups;
import com.craemon.lasertag.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaserTag implements ModInitializer{
    public static final String MOD_ID = "lasertag";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final EntityType<LaserBlastEntity> LaserBlastEntityType = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "laser_blast"),
            FabricEntityTypeBuilder.<LaserBlastEntity>create(SpawnGroup.MISC, LaserBlastEntity::new).dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeBlocks(4).trackedUpdateRate(20).build()
    );

    @Override
    public void onInitialize() {
        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
    }
}
