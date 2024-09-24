package com.craemon.lasertag.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;


public class LaserBlasterItem extends Item {
    public LaserBlasterItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack =user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        user.getItemCooldownManager().set(this, 80);

        if (!world.isClient()) {
            ChickenEntity chicken = new ChickenEntity(EntityType.CHICKEN, world);
            chicken.setPosition(user.getPos());
            world.spawnEntity(chicken);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));

        return super.use(world, user, hand);
    }
}
