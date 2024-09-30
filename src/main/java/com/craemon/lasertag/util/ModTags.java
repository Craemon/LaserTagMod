package com.craemon.lasertag.util;

import com.craemon.lasertag.LaserTag;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static TagKey<Block> LASER_TRANSPARENT_BLOCKS =
                createTag("laser_transparent_blocks");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(LaserTag.MOD_ID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(LaserTag.MOD_ID, name));
        }
    }
}
