package net.midget807.narchaotics.registry;

import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.recipe.DistillationRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRecipes {

    public static final RecipeType<DistillationRecipe> DISTILLATION_TYPE = registerType("distillation");

    public static final RecipeSerializer<DistillationRecipe> DISTILLATION_SERIALIZER = registerSerializer("distillation", new DistillationRecipe.Serializer());

    private static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String name, S serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, NarchaoticsMain.id(name), serializer);
    }

    private static <T extends Recipe<?>> RecipeType<T> registerType(String name) {
        return Registry.register(Registries.RECIPE_TYPE, NarchaoticsMain.id(name), new RecipeType<T>() {
            public String toString() {
                return name;
            }
        });
    }

    public static void registerModRecipes() {
        NarchaoticsMain.LOGGER.info("Registering Mod Recipes");
    }
}
