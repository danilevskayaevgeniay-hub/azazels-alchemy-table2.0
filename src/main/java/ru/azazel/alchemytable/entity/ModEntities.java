package ru.azazel.alchemytable.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import ru.azazel.alchemytable.AzazelSAlchemyTable;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;

public final class ModEntities {

    public static final EntityType<LightProjectile> LIGHT_PROJECTILE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            AzazelSAlchemyTable.id("light_projectile"),
            EntityType.Builder.<LightProjectile>of(LightProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build()
    );
    public static final EntityType<FlameProjectile> FLAME_PROJECTILE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            AzazelSAlchemyTable.id("flame_projectile"),
            EntityType.Builder.<FlameProjectile>of(FlameProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build()
    );
    public static final EntityType<WindProjectile> WIND_PROJECTILE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            AzazelSAlchemyTable.id("wind_projectile"),
            EntityType.Builder.<WindProjectile>of(WindProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build()
    );
     public static final EntityType<RedstoneProjectile> REDSTONE_PROJECTILE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            AzazelSAlchemyTable.id("redstone_projectile"),
            EntityType.Builder.<RedstoneProjectile>of(RedstoneProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build()
    );
    public static final EntityType<CrystalSpider> CRYSTAL_SPIDER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            AzazelSAlchemyTable.id("crystal_spider"),
            FabricEntityType.Builder.createMob(
                CrystalSpider::new,
                MobCategory.MONSTER,
                builder ->
                        builder.spawnRestriction(
                                SpawnPlacementTypes.ON_GROUND,
                                Heightmap.Types
                                        .MOTION_BLOCKING_NO_LEAVES,
                                CrystalSpider
                                        ::checkCrystalSpiderSpawnRules
                                )
                .sized(
                                1.4F,
                                0.9F
                        )
                        .clientTrackingRange(4)
                        .build()
                )
    );


    public static void registerModEntities() {
        AzazelSAlchemyTable.LOGGER.info("Registering projectile entities");
    }

    private ModEntities() {
    }
}

