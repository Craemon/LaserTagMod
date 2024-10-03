package com.craemon.lasertag.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class RaycastHelper {

    private static final Set<Block> TRANSPARENT_BLOCKS = new HashSet<>(Arrays.asList(
            Blocks.GLASS,
            Blocks.GLASS_PANE,
            Blocks.SHORT_GRASS,
            Blocks.TALL_GRASS,
            Blocks.FERN,
            Blocks.LARGE_FERN,
            Blocks.VINE
    ));

    public static void spawnParticleTrail(World world, Vec3d startPos, Vec3d endPos, double range) {
        // Only proceed if we're on the server side
        if (!world.isClient()) {
            // Offset distance for the trail
            double offsetDistance = 0.5; // Adjust this value for how far out you want the particles to spawn

            // Get the direction of the ray
            Vec3d direction = endPos.subtract(startPos).normalize();

            // Calculate the new start position with the offset
            Vec3d offsetStartPos = startPos.add(direction.multiply(offsetDistance));

            double distance = offsetStartPos.distanceTo(endPos);

            // If endPos is too far (like aiming at the sky), set a max distance for particle trail
            if (distance > range) {
                distance = range;
                endPos = offsetStartPos.add(direction.multiply(range));  // Clamp the endPos to max range
            }

            // Number of particles to spawn based on distance
            int particlesCount = (int) (distance * 10); // Adjust for desired density of particles

            // Spawn particles along the ray on the server
            for (int i = 0; i <= particlesCount; i++) {
                Vec3d particlePos = offsetStartPos.add(direction.multiply(i * (distance / particlesCount)));

                // Make sure world is an instance of ServerWorld before casting
                if (world instanceof ServerWorld) {
                    // Spawn the particles server-side, so they are visible to all players
                    ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD,
                            particlePos.x, particlePos.y, particlePos.z,
                            1, 0, 0, 0, 0);
                }
            }
        }
    }


    @Nullable
    public static HitResult performRaycast(Entity entity, double range) {
        Vec3d startPos = entity.getCameraPosVec(0);
        Vec3d direction = entity.getRotationVec(0);
        Vec3d endPos = startPos.add(direction.multiply(range));
        World world = entity.getWorld();

        // Perform entity raycast
        EntityHitResult entityHitResult = ProjectileUtil.raycast(entity, startPos, endPos,
                entity.getBoundingBox().expand(range), Entity::canBeHitByProjectile, range);

        // Perform block raycast, ignoring fluids
        BlockHitResult blockHitResult = world.raycast(new RaycastContext(
                startPos, endPos, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, entity));

        // Handle transparent blocks or missed hits
        if (blockHitResult != null && blockHitResult.getType() == HitResult.Type.BLOCK) {
            BlockState hitBlockState = world.getBlockState(blockHitResult.getBlockPos());

            // If the block is transparent (or not solid), ignore the hit
            if (TRANSPARENT_BLOCKS.contains(hitBlockState.getBlock())) {
                blockHitResult = null; // Skip this hit and continue raycasting
            }
        }

        // Choose the closest valid hit
        HitResult closestHit = (entityHitResult != null && blockHitResult != null) ?
                (entityHitResult.getPos().squaredDistanceTo(startPos) < blockHitResult.getPos().squaredDistanceTo(startPos) ? entityHitResult : blockHitResult) :
                (entityHitResult != null) ? entityHitResult : blockHitResult;

        // Particle trail
        Vec3d finalEndPos = (closestHit != null) ? closestHit.getPos() : endPos;
        spawnParticleTrail(world, startPos, finalEndPos, range);

        return closestHit;
    }
}





