package com.craemon.lasertag.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class LaserBlasterItem extends Item {
    public LaserBlasterItem(Settings settings) {
        super(settings.maxCount(1));
    }

    float damage = 1000f;
    double range = 10000;

    @Nullable
    public static EntityHitResult hitscan(Entity entity, double range) {
        Vec3d vec3d = entity.getCameraPosVec(0);
        Vec3d vec3d2 = entity.getRotationVec(0);
        Vec3d vec3d3 = vec3d.add(vec3d2.x * (range+3), vec3d2.y * (range+3), vec3d2.z * (range+3));
        return ProjectileUtil.raycast(entity,vec3d,vec3d3,entity.getBoundingBox().expand(range+3),Entity::canBeHitByProjectile,range);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        user.getItemCooldownManager().set(this, 20);

        if (!world.isClient()) {
            EntityHitResult hitResult = hitscan(user,range);
            if (hitResult!=null && hitResult.getEntity()!=null){
                hitResult.getEntity().setGlowing(true);
                hitResult.getEntity().damage(user.getDamageSources().genericKill(), damage);}
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));

        return super.use(world, user, hand);
    }
}
