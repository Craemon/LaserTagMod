package com.craemon.lasertag.item.custom;

import com.craemon.lasertag.util.RaycastHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class TestLaserBlasterItem extends Item {

    float damage = 1000f;
    double range = 10000;

    public TestLaserBlasterItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        user.getItemCooldownManager().set(this, 20);

        if (!world.isClient()) {
            HitResult hitResult = RaycastHelper.performRaycast(user, range);

            if (hitResult.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHit = (EntityHitResult) hitResult;
                if (entityHit.getEntity() != null) {
                    entityHit.getEntity().setGlowing(true);
                    entityHit.getEntity().damage(user.getDamageSources().genericKill(), damage);
                }
            } else if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHit = (BlockHitResult) hitResult;
                world.playSound(null, blockHit.getBlockPos(), SoundEvents.BLOCK_STONE_HIT, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }

        if (world.isClient()) {
            // The performRaycast now handles particle spawning on both valid and invalid hits
            RaycastHelper.performRaycast(user, range);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return super.use(world, user, hand);
    }
}



