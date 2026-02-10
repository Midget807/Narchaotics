package net.midget807.narchaotics.util;

import net.midget807.narchaotics.util.inject.FlaskStorable;
import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
}
