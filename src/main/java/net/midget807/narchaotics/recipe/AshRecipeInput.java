package net.midget807.narchaotics.recipe;

import net.midget807.narchaotics.block.entity.DistillationWorkbenchBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record AshRecipeInput(ItemStack input) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 4 -> this.input;
            default -> throw new IllegalArgumentException("No item for index " + slot);
        };
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return input.isEmpty();
    }
}
