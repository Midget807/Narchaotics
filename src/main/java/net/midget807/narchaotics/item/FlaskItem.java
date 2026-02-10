package net.midget807.narchaotics.item;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class FlaskItem extends Item {
    public final Fluid fluid;
    public final Item remainder;
    public final int capacity;

    public FlaskItem(Fluid fluid, @Nullable Item remainder, int capacity, Settings settings) {
        super(settings);
        this.fluid = fluid;
        this.remainder = remainder;
        this.capacity = capacity;
    }

    public ItemStack getRemainderStack() {
        if (remainder == null) return ItemStack.EMPTY;
        return new ItemStack(remainder);
    }
}
