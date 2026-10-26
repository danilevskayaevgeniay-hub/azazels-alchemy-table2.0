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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RedstoneProjectile extends ThrowableItemProjectile {

    // 10 тиков = 0,5 секунды
    private static final int REDSTONE_TIMER = 10;

    // Место, куда временно поставили блок редстоуна
    private BlockPos redstonePos = null;

    // Сколько тиков уже прошло
    private int timer = 0;

    // Было ли уже попадание
    private boolean activated = false;


    public RedstoneProjectile(
            EntityType<? extends RedstoneProjectile> entityType,
            Level level
    ) {
        super(entityType, level);
    }


    public RedstoneProjectile(
            Level level,
            LivingEntity owner
    ) {
        super(
                ModEntities.REDSTONE_PROJECTILE,
                owner,
                level
        );
    }


    @Override
    protected Item getDefaultItem() {
        return Items.REDSTONE;
    }


    @Override
    protected void onHit(HitResult result) {

        super.onHit(result);

        // Если уже сработал, второй раз ничего не делаем
        if (activated) {
            return;
        }

        // Всё изменение мира делаем только на сервере
        if (level().isClientSide) {
            return;
        }


        if (result instanceof BlockHitResult blockHit) {

            // Блок, в который мы попали
            BlockPos hitPos = blockHit.getBlockPos();

            // Сначала пробуем поставить редстоун
            // прямо перед той гранью, куда попал projectile
            BlockPos possiblePos =
                    hitPos.relative(blockHit.getDirection());


            // Если там уже что-то стоит,
            // ищем другое свободное место рядом
            if (!level().isEmptyBlock(possiblePos)) {

                possiblePos = null;

                for (Direction direction : Direction.values()) {

                    BlockPos checkPos =
                            hitPos.relative(direction);

                    if (level().isEmptyBlock(checkPos)) {

                        possiblePos = checkPos;
                        break;
                    }
                }
            }


            // Если нашли свободное место
            if (possiblePos != null) {

                redstonePos = possiblePos;

                // ВАЖНО:
                // ставим настоящий ванильный блок редстоуна.
                // Он гарантированно выдаёт сигнал 15.
                level().setBlock(
                        redstonePos,
                        Blocks.REDSTONE_BLOCK.defaultBlockState(),
                        Block.UPDATE_ALL
                );

                activated = true;
                timer = 0;

                // После попадания projectile больше не летит
                setDeltaMovement(Vec3.ZERO);
                setNoGravity(true);

                // Сам projectile после попадания не показываем
                setInvisible(true);

                // ВАЖНО:
                // discard() здесь НЕ вызываем.
                // Projectile ещё нужен нам как таймер
                // на следующие 10 тиков.

                return;
            }
        }


        // Если попасть нормально не удалось,
        // просто удаляем projectile
        discard();
    }


    @Override
    public void tick() {

        // Если редстоун уже активирован,
        // projectile теперь работает только как таймер
        if (activated) {

            timer++;

            // Через 10 тиков убираем блок
            if (timer >= REDSTONE_TIMER) {

                if (
                        redstonePos != null
                        && level()
                        .getBlockState(redstonePos)
                        .is(Blocks.REDSTONE_BLOCK)
                ) {

                    // Убираем временный блок.
                    // UPDATE_ALL заставит редстоун-схему
                    // сразу понять, что питание пропало.
                    level().setBlock(
                            redstonePos,
                            Blocks.AIR.defaultBlockState(),
                            Block.UPDATE_ALL
                    );
                }

                discard();
            }

            return;
        }


        // Пока projectile летит,
        // работает обычная физика
        super.tick();
    }
}


