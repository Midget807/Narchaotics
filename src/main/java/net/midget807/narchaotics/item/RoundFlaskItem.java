package net.midget807.narchaotics.item;

import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

public class RoundFlaskItem extends AbstractRoundFlaskItem {
    public RoundFlaskItem(Fluid fluid, Settings settings) {
        super(fluid, ModItems.ROUND_FLASK, settings);
    }
}
