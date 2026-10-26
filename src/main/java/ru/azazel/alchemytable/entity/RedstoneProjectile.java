package ru.azazel.alchemytable.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class RedstoneProjectile extends ThrowableItemProjectile {

    // 10 тиков = 0,5 секунды
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

                // ОШИБКА БЫЛА ЗДЕСЬ:
                // level().setBlock(...) возвращает boolean,
                // поэтому его нельзя записывать в BlockPos.
                //
                // Получаем позицию блока, в который попал projectile.
                BlockPos hitBlockPos = blockHit.getBlockPos();

                // ОШИБКА БЫЛА ЗДЕСЬ:
                // redstonePos в старом коде вообще не был создан.
                //
                // Берём соседнюю клетку со стороны попадания.
                BlockPos redstonePos =
                        hitBlockPos.relative(blockHit.getDirection());

                if (level() instanceof ServerLevel serverLevel) {

                    TemporaryRedstoneManager.addRedstone(
                            serverLevel,

                            // ОШИБКА БЫЛА ЗДЕСЬ:
                            // раньше передавался hitBlockPos,
                            // то есть сам блок, в который попали.
                            // Нам нужна свободная клетка рядом с ним.
                            redstonePos,

                            REDSTONE_TIMER
                    );
                }
            }

            // ОШИБКА БЫЛА ЗДЕСЬ:
            // discard() нельзя передавать внутрь setBlock().
            // discard() ничего не возвращает и просто удаляет projectile.
            discard();
        }
    }
}


