package com.craemon.lasertag.item.custom;

import com.craemon.lasertag.entity.custom.LaserProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RedLaserBlasterItem extends LaserBlasterItem{
    public RedLaserBlasterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            LaserProjectileEntity laserProjectile = new LaserProjectileEntity(world, user);
            laserProjectile.setVelocity(user, user.getPitch(),user.getYaw(), 0.0F, 2.5F, 0.0F);
            world.spawnEntity(laserProjectile);
        }
        return super.use(world, user, hand);
    }
}
