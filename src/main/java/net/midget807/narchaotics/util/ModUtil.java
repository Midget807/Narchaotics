package net.midget807.narchaotics.util;

import net.midget807.narchaotics.datagen.ModItemTagProvider;
import net.midget807.narchaotics.item.BeakerItem;
import net.midget807.narchaotics.item.ConicalFlaskItem;
import net.midget807.narchaotics.item.FlaskItem;
import net.midget807.narchaotics.item.RoundFlaskItem;
import net.midget807.narchaotics.item.TestTubeItem;
import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.StringHelper;
import net.minecraft.util.math.MathHelper;

public class ModUtil {
    public static Text getDurationText(int ticks, float multiplier, float tickRate) {
        if (ticks == -1) {
            return Text.translatable("effect.duration.infinite");
        } else {
            int i = MathHelper.floor(ticks * multiplier);
            return Text.literal(StringHelper.formatTicks(i, tickRate));
        }
    }

    public static Text getDurationText(int ticks) {
        if (MinecraftClient.getInstance().world != null) {
            if (ticks == -1) {
                return Text.translatable("effect.duration.infinite");
            } else {
                int i = MathHelper.floor(ticks * 1.0f);
                return Text.literal(StringHelper.formatTicks(i, MinecraftClient.getInstance().world.getTickManager().getTickRate()));
            }
        } else {
            return Text.empty();
        }
    }
    public static ItemStack exchangeWholeStack(ItemStack inputStack, PlayerEntity player, ItemStack outputStack, boolean creativeOverride) {
        boolean bl = player.isInCreativeMode();
        int count = inputStack.getCount();
        outputStack.setCount(count);
        if (creativeOverride && bl) {
            player.getInventory().insertStack(outputStack);
        } else {
            if (!player.getInventory().insertStack(outputStack)) {
                player.dropItem(outputStack, false);
                inputStack.decrementUnlessCreative(count, player);
                return inputStack;
            }
            return outputStack;
        }
        return inputStack;
    }
    public static ItemStack exchangeWholeStack(ItemStack inputStack, PlayerEntity player, ItemStack outputStack) {
        return exchangeWholeStack(inputStack, player, outputStack, true);
    }

    /** Takes {@link Items#BUCKET} and {@link ModItemTagProvider#FLUID_BOTTLES}*/
    @SuppressWarnings("deprecation")
    public static Item getItemForFluid(Fluid fluid, Item container) {
        for (Item item : Registries.ITEM) {
            if (item.getRegistryEntry().isIn(ModItemTagProvider.FLUID_REMOVE_ITEMS)) continue;
            switch (item) {
                case BucketItem bucketItem when container == Items.BUCKET -> {
                    if (bucketItem.fluid == fluid) {
                        return item;
                    }
                }
                case ConicalFlaskItem flaskItem when container == ModItems.CONICAL_FLASK -> {
                    if (flaskItem.fluid == fluid) {
                        return item;
                    }
                }
                case RoundFlaskItem flaskItem when container == ModItems.ROUND_FLASK -> {
                    if (flaskItem.fluid == fluid) {
                        return item;
                    }
                }
                case BeakerItem flaskItem when container == ModItems.BEAKER -> {
                    if (flaskItem.fluid == fluid) {
                        return item;
                    }
                }
                case TestTubeItem flaskItem when container == ModItems.TEST_TUBE -> {
                    if (flaskItem.fluid == fluid) {
                        return item;
                    }
                }
                default -> {
                    return Items.AIR;
                }
            }
        }
        return Items.AIR;
    }
}
