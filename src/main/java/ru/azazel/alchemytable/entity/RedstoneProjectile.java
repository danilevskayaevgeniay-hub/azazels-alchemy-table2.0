package ru.azazel.alchemytable.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;

import net.minecraft.server.level.ServerLevel;

import java.util.List;

public class RedstoneProjectile extends ThrowableItemProjectile {

    public static final int REDSTONE_TIMER = 10;

    public RedstoneProjectile(
            EntityType<? extends RedstoneProjectile> entityType,
            Level level
    ) {
        super(entityType, level);
    }

    public RedstoneProjectile(Level level, LivingEntity owner) {
        super(ModEntities.REDSTONE_PROJECTILE, owner, level);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.REDSTONE;
    }

    @Override
        protected void onHit(HitResult result) {
            super.onHit(result);

            if (!level().isClientSide) {

                if (result instanceof BlockHitResult blockHit) {

                    BlockPos hitBlockPos =
                    blockHit.getBlockPos();

                if (level() instanceof ServerLevel serverLevel) {

                    TemporaryRedstoneManager.addRedstone(
                           serverLevel,
                           RedstonePos,
                           REDSTONE_TIMER
                    );
                }
            }
        }
}
