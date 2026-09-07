package ru.azazel.alchemytable.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public class ChargedMagicWandItem extends Item {

    public ChargedMagicWandItem(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            LightProjectile projectile = new LightProjectile(level, player);
            projectile.shootFromRotation(
                player,
                player.getXRot(),
                player.getYRot(),
                0.0F,
                1.5F,
                1.0F
            );
            level.addFreshEntity(projectile);
        }
        return InteractionResuldHolder.sidedSucces(
            stack,
            level.isClientSide()
        );
    }

}


