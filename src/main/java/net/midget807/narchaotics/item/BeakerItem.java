package net.midget807.narchaotics.item;

import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

public class BeakerItem extends AbstractBeakerItem {
    public BeakerItem(Fluid fluid, Settings settings) {
        super(fluid, ModItems.BEAKER, settings);
    }
}
