package net.midget807.narchaotics.recipe;

import net.midget807.narchaotics.block.entity.DissolveWorkbenchBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record DissolveRecipeInput(ItemStack item1, ItemStack item2, FluidStack fluid1, FluidStack fluid2, ItemStack fuelType) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> this.item1;
            case 1 -> this.item2;
            case DissolveWorkbenchBlockEntity.FUEL_INPUT_INDEX -> this.fuelType;
            default -> throw new IllegalArgumentException("No item for index " + slot);
        };
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public boolean isEmpty() {
        return this.item1.isEmpty() && this.item2.isEmpty() && this.fluid1.isEmpty() && this.fluid2.isEmpty() && this.fuelType.isEmpty();
    }
}
