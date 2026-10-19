package ru.azazel.alchemytable.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;

public class CrystalSpider extends Spider {

  private static final float REFLECTED_DAMAGE = 2.0F;

  public CrystalSpider(
            EntityType<? extends Spider> entityType,
            Level level
    ) {
        super(entityType, level);
    }
  public static boolean checkCrystalSpiderSpawnRules(
        EntityType<CrystalSpider> entityType,
        ServerLevelAccessor level,
        MobSpawnType spawnType,
        BlockPos pos,
        RandomSource random
  ) {

    boolean isCaveLevel =
            pos.getY() <= 35;

    boolean vanillaMonsterRules =
            Monster.checkMonsterSpawnRules(
                    entityType,
                    level,
                    spawnType,
                    pos,
                    random
            );

    return isCaveLevel
            && vanillaMonsterRules;
  }

  @Override
  public boolean hurt(DamageSource source, float amount) {
    boolean reflecteddmg = super.hurt(source, amount);

    if (!reflecteddmg || this.level().isClientSide() || source.is(DamageTypes.THORNS)) {
        return reflecteddmg;
  }

  Entity attacker = source.getEntity();
  Entity directEntity = source.getDirectEntity();

  boolean isMeleeAttack =
            attacker instanceof LivingEntity
                    && directEntity == attacker
                    && attacker != this;

    if (isMeleeAttack) {

        LivingEntity livingAttacker = (LivingEntity) attacker;

        livingAttacker.hurt(
                this.level().damageSources().thorns(this),
                2.0F
        );
    }

    return reflecteddmg;

}}
