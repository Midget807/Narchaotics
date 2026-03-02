package net.midget807.narchaotics.recipe;

import net.midget807.narchaotics.block.entity.PhotoelectricExtractorWorkbenchBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record PhotoelectricExtractorRecipeInput(FluidStack input, ItemStack catalyst) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case PhotoelectricExtractorWorkbenchBlockEntity.FUEL_INPUT_INDEX -> this.catalyst;
            default -> throw new IllegalArgumentException("No item for index " + slot);
        };
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return this.input.isEmpty() && this.catalyst.isEmpty();
    }
}
