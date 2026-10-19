package ru.azazel.alchemytable.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class ModEntitySpawns {

    public static void register() {

        BiomeModifications.addSpawn(
                BiomeSelectors.spawnsOneOf(
                        EntityType.SPIDER
                ),

                MobCategory.MONSTER,

                ModEntities.CRYSTAL_SPIDER,

                100,
                3,
                3
        );
    }

    private ModEntitySpawns() {
    }
}

