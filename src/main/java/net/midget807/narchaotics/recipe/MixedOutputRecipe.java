package net.midget807.narchaotics.recipe;

import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;

public interface MixedOutputRecipe<T extends RecipeInput> {
    //Codecs???

    DefaultedList<MixedStack> craftMultiple(T input, RegistryWrapper.WrapperLookup lookup);
    DefaultedList<MixedStack> getMultipleResults(RegistryWrapper.WrapperLookup lookup);
}
