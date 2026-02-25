package net.midget807.narchaotics.item;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

public class AbstractTestTubeItem extends FlaskItem {
    public AbstractTestTubeItem(Fluid fluid, Item remainder, Settings settings) {
        super(fluid, remainder, 50, settings);
    }
}
