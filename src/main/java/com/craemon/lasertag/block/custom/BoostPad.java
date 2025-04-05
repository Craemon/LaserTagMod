package com.craemon.lasertag.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BoostPad extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public BoostPad(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING); // Register the property
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()); // Face the player when placed
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (state.contains(BoostPad.FACING)) {
            Direction blockFacing = state.get(BoostPad.FACING);

            // Convert facing to velocity direction
            Vec3d boostDirection = new Vec3d(blockFacing.getOffsetX(), 0, blockFacing.getOffsetZ()).normalize();

            // Apply velocity boost
            entity.addVelocity(boostDirection.x * 2, 0, boostDirection.z * 2);

            // Apply speed effect
            if (!world.isClient() && entity instanceof LivingEntity le) {
                StatusEffectInstance speedEffect = new StatusEffectInstance(StatusEffects.SPEED, 50, 3, true, false);
                le.addStatusEffect(speedEffect);
            }
        }
        //Vec3d Look = entity.getRotationVector();
        //
        //entity.addVelocity(Look.x*2,0.2,Look.z*2);
        //if (!world.isClient()) {
        //    if (entity instanceof LivingEntity) {
        //        LivingEntity le = (LivingEntity) entity;
        //        StatusEffectInstance BoostpadStatusEffectInstance = new StatusEffectInstance(StatusEffects.SPEED, 50, 3 , true, false);
        //        le.addStatusEffect(BoostpadStatusEffectInstance);
        //    }
        //}
    }
}
