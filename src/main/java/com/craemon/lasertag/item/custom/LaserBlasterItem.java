package com.craemon.lasertag.item.custom;

import com.craemon.lasertag.entity.custom.LaserProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class LaserBlasterItem extends Item {
    public LaserBlasterItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack =user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        user.getItemCooldownManager().set(this, 40);

        if (!world.isClient()) {
            LaserProjectileEntity laserProjectile = new LaserProjectileEntity(world, user);
            laserProjectile.setVelocity(user, user.getPitch(),user.getYaw(), 0.0F, 2.5F, 0.0F);
            world.spawnEntity(laserProjectile);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));

        return super.use(world, user, hand);
    }
}
