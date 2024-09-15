package com.craemon.lasertag.entity.custom;

import com.craemon.lasertag.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LaserProjectileEntity extends ProjectileEntity {

    public LaserProjectileEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
    }
    @Override
    public void tick() {
        super.tick();

        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        boolean isInPortal = false;
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = ((BlockHitResult)hitResult).getBlockPos();
            BlockState blockState = this.getWorld().getBlockState(blockPos);

        }
        if (hitResult.getType() != HitResult.Type.MISS && !isInPortal) {
            this.onCollision(hitResult);
        }
        this.checkBlockCollision();

        Vec3d vec3d = this. getVelocity();
        double d = this.getX() + vec3d.x;
        double e = this.getY() + vec3d.y;
        double f = this.getZ() + vec3d.z;
        this.setPosition(d, e, f);

        if (this.age > (20 * 3)) {
            this.discard();
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity edge = entityHitResult.getEntity();
        if (this.getOwner() instanceof PlayerEntity player) {
            edge.damage(edge.getDamageSources().playerAttack(player), 10);
            spawnParticles(1);
        }
    }

    private void spawnParticles(int amount) {
        for(int i = 0; i < 360; i++) {
            if(i % 20 == 0) {
                this.getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME,
                        getX() + 0d, getY() + 0d, getZ() + 0d,
                        Math.cos(i) * 0.25d, 0.15d, Math.sin(i) * 0.25d);
            }
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        this.discard();
        if(!this.getWorld().isClient()) {
            playSound(ModSounds.LASERPROJECTILE_HIT, 1.5f, 0.5f);
            this.remove(getRemovalReason());
        }
    }

    @Override
    public void onRemoved() {
        spawnParticles(1);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

}
