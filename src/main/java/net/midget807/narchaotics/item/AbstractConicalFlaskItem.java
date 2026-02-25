package net.midget807.narchaotics.item;

import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

public class AbstractConicalFlaskItem extends FlaskItem {
    public AbstractConicalFlaskItem(Fluid fluid, Item remainder, Settings settings) {
        super(fluid, remainder, 250, settings);
    }
}
