package net.midget807.narchaotics.item;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

public class AbstractBeakerItem extends FlaskItem {
    public AbstractBeakerItem(Fluid fluid, Item remainder, Settings settings) {
        super(fluid, remainder, 250, settings);
    }
}
