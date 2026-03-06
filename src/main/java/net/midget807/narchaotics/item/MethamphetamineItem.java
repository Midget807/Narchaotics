package net.midget807.narchaotics.item;

import net.midget807.narchaotics.registry.ModEffects;
import net.midget807.narchaotics.util.inject.MethHigh;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class MethamphetamineItem extends Item {
    public MethamphetamineItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.success(stack);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 40;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        int currentAmplifier = -1;
        if (user.hasStatusEffect(ModEffects.METH_HIGH) && user.getStatusEffect(ModEffects.METH_HIGH) != null) {
            currentAmplifier = user.getStatusEffect(ModEffects.METH_HIGH).getAmplifier();
        }
        return ((MethHigh)user).narchaotics$eatMeth(world, stack, currentAmplifier);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
}
