package ru.azazel.alchemytable.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class CrystalSpider extends Spider {

  private static final float REFLECTED_DAMAGE = 2.0F;

  public CrystalSpider(
            EntityType<? extends Spider> entityType,
            Level level
    ) {
        super(entityType, level);
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
