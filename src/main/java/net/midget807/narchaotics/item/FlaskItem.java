package net.midget807.narchaotics.item;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FlaskItem extends Item {
    public final Fluid fluid;
    public final Item remainder;

    public FlaskItem(Fluid fluid, Item remainder, Settings settings) {
        super(settings);
        this.fluid = fluid;
        this.remainder = remainder;
    }

    public ItemStack getRemainderStack() {
        return new ItemStack(remainder);
    }
}
