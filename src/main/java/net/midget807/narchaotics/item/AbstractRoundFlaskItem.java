package net.midget807.narchaotics.item;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

public class AbstractRoundFlaskItem extends FlaskItem {
    public AbstractRoundFlaskItem(Fluid fluid, Item remainder, Settings settings) {
        super(fluid, remainder, 250, settings);
    }
}
