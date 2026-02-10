package net.midget807.narchaotics.item;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

public class BeakerItem extends FlaskItem {
    public BeakerItem(Fluid fluid, Item remainder, Settings settings) {
        super(fluid, remainder, 250, settings);
    }
}
