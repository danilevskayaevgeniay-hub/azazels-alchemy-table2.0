package ru.azazel.alchemytable.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import ru.azazel.alchemytable.block.ModBlocks;

public class RedstoneProjectile extends ThrowableItemProjectile {

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

        if (level().isClientSide) {
            return;
        }

        if (result instanceof BlockHitResult blockHit) {

            // Блок, в который попала палочка
            BlockPos hitPos = blockHit.getBlockPos();

            // Сначала пробуем поставить источник
            // со стороны, куда попал projectile
            BlockPos signalPos =
                    hitPos.relative(blockHit.getDirection());

            // Если эта клетка занята,
            // ищем любую свободную клетку рядом
            if (!level().isEmptyBlock(signalPos)) {

                signalPos = null;

                for (Direction direction : Direction.values()) {

                    BlockPos checkPos =
                            hitPos.relative(direction);

                    if (level().isEmptyBlock(checkPos)) {
                        signalPos = checkPos;
                        break;
                    }
                }
            }

            // Если нашли свободное место,
            // ставим временный источник Redstone
            if (signalPos != null) {

                level().setBlock(
                        signalPos,
                        ModBlocks.REDSTONE_PULSE.defaultBlockState(),
                        Block.UPDATE_ALL
                );

                // Дополнительно обновляем сам блок,
                // в который попали
                level().updateNeighborsAt(
                        hitPos,
                        ModBlocks.REDSTONE_PULSE
                );
            }
        }

        discard();
    }
}


