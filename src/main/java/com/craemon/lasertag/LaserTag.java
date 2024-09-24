package com.craemon.lasertag;

import com.craemon.lasertag.entity.ModEntities;
import com.craemon.lasertag.item.ModItemGroups;
import com.craemon.lasertag.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaserTag implements ModInitializer{
    public static final String MOD_ID = "lasertag";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModEntities.registerEntities();
    }
}
