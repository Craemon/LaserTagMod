package com.craemon.lasertag.item;

import com.craemon.lasertag.LaserTag;
import com.craemon.lasertag.item.custom.LaserBlasterItem;
import com.craemon.lasertag.item.custom.RedLaserBlasterItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item LASER_BLASTER = registerItem("laserblaster", new LaserBlasterItem(new Item.Settings()));

    public static final Item BLUE_LASER_BLASTER = registerItem("bluelaserblaster", new LaserBlasterItem(new Item.Settings()));
    public static final Item GREEN_LASER_BLASTER = registerItem("greenlaserblaster", new LaserBlasterItem(new Item.Settings()));
    public static final Item PURPLE_LASER_BLASTER = registerItem("purplelaserblaster", new LaserBlasterItem(new Item.Settings()));
    public static final Item RED_LASER_BLASTER = registerItem("redlaserblaster", new RedLaserBlasterItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(LaserTag.MOD_ID, name), item);
    }

    public static void registerModItems() {
    }
}
