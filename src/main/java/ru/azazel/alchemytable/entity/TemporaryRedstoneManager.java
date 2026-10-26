package ru.azazel.alchemytable.entity;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class TemporaryRedstoneManager {

    private static final Map<ServerLevel, Map<BlockPos, Long>> REDSTONE_BLOCKS =
            new HashMap<>();

    public static void addRedstone(
            ServerLevel level,
            BlockPos pos,
            int duration
    ) {

        long removeTime =
                level.getGameTime() + duration;

        REDSTONE_BLOCKS
                .computeIfAbsent(
                        level,
                        key -> new HashMap<>()
                )
                .put(
                        pos.immutable(),
                        removeTime
                );
    }

    public static void register() {

        ServerTickEvents.END_WORLD_TICK.register(level -> {

            Map<BlockPos, Long> worldRedstone =
                    REDSTONE_BLOCKS.get(level);

            if (worldRedstone == null) {
                return;
            }

            long currentTime =
                    level.getGameTime();

            Iterator<Map.Entry<BlockPos, Long>> iterator =
                    worldRedstone.entrySet().iterator();

            while (iterator.hasNext()) {

                Map.Entry<BlockPos, Long> entry =
                        iterator.next();

                if (currentTime >= entry.getValue()) {

                    BlockPos pos =
                            entry.getKey();

                    if (level
                            .getBlockState(pos)
                            .is(Blocks.REDSTONE_BLOCK)) {

                        level.removeBlock(
                                pos,
                                false
                        );
                    }

                    iterator.remove();
                }
            }
        });
    }

    private TemporaryRedstoneManager() {
    }
}


