package net.midget807.narchaotics.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record EvaporateRecipeInput(FluidStack input, ItemStack fuelType) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 1 -> this.fuelType;
            default -> throw new IllegalArgumentException("No item for index " + slot);
        };
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return this.input.isEmpty() && this.fuelType.isEmpty();
    }
}
