package net.midget807.narchaotics.item;

import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class MortarAndPestleItem extends Item {
    public static Map<ItemStack, ItemStack> RECIPE = Map.of(
            new ItemStack(Items.NETHERRACK), new ItemStack(ModItems.NETHERRACK_DUST),
            new ItemStack(Items.CALCITE, 8), new ItemStack(ModItems.CALCITE_DUST),
            new ItemStack(ModItems.RED_PHOSPHORUS), new ItemStack(ModItems.RED_PHOSPHORUS_DUST),
            new ItemStack(Items.NETHERRACK), new ItemStack(ModItems.NETHERRACK_DUST),
            new ItemStack(Items.NETHERRACK), new ItemStack(ModItems.NETHERRACK_DUST),
            new ItemStack(Items.NETHERRACK), new ItemStack(ModItems.NETHERRACK_DUST),
            new ItemStack(Items.NETHERRACK), new ItemStack(ModItems.NETHERRACK_DUST),
            new ItemStack(Items.NETHERRACK), new ItemStack(ModItems.NETHERRACK_DUST)
    );

    public MortarAndPestleItem(Settings settings) {
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
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        Hand otherHand = user.getActiveHand() == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;
        ItemStack otherStack = user.getStackInHand(otherHand);
        if (user instanceof PlayerEntity player) {
            RECIPE.forEach((input, output) -> {
                if (otherStack.isOf(input.getItem())) {
                    ItemUsage.exchangeStack(otherStack, player, output);
                    return;
                }
            });
        }
        return stack;
    }

    public static void addCrushRecipe(ItemStack input, ItemStack output) {
        RECIPE.put(input, output);
    }
}
