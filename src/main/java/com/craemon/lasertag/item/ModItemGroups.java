package com.craemon.lasertag.item;

import com.craemon.lasertag.LaserTag;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup LaserTag_Group = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LaserTag.MOD_ID, "lasertag"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.lasertag"))
                    .icon(() -> new ItemStack(ModItems.PURPLE_LASER_BLASTER)).entries((displayContext, entries) -> {
                        entries.add(ModItems.BLUE_LASER_BLASTER);
                        entries.add(ModItems.GREEN_LASER_BLASTER);
                        entries.add(ModItems.PURPLE_LASER_BLASTER);
                        entries.add(ModItems.RED_LASER_BLASTER);
                    }).build());

    public static void registerItemGroups() {

    }
}
