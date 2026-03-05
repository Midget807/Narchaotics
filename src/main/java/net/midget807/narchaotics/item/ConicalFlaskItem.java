package net.midget807.narchaotics.item;

import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

public class ConicalFlaskItem extends AbstractConicalFlaskItem {
    public ConicalFlaskItem(Fluid fluid, Settings settings) {
        super(fluid, ModItems.CONICAL_FLASK, settings.recipeRemainder(ModItems.CONICAL_FLASK));
    }
}
