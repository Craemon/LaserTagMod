package com.craemon.lasertag.block;

import com.craemon.lasertag.LaserTag;
import com.craemon.lasertag.block.custom.BoostPad;
import com.craemon.lasertag.block.custom.JumpPad;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block BOOST_PAD = registerBlock("boost_pad", new BoostPad(AbstractBlock.Settings.create().strength(1f).requiresTool()));
    public static final Block JUMP_PAD = registerBlock("jump_pad", new JumpPad(AbstractBlock.Settings.create().strength(1f).requiresTool()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(LaserTag.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(LaserTag.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        LaserTag.LOGGER.info("Registering Mod Blocks for "+ LaserTag.MOD_ID);
    }
}

