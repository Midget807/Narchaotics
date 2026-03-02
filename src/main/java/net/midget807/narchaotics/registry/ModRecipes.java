package net.midget807.narchaotics.registry;

import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.recipe.DissolveRecipe;
import net.midget807.narchaotics.recipe.DistillationRecipe;
import net.midget807.narchaotics.recipe.EvaporateRecipe;
import net.midget807.narchaotics.recipe.FilterRecipe;
import net.midget807.narchaotics.recipe.PhotoelectricExtractorRecipe;
import net.midget807.narchaotics.recipe.SeparateRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRecipes {

    public static final RecipeType<DistillationRecipe> DISTILLATION_TYPE = registerType("distillation");
    public static final RecipeType<FilterRecipe> FILTER_TYPE = registerType("filter");
    public static final RecipeType<EvaporateRecipe> EVAPORATE_TYPE = registerType("evaporate");
    public static final RecipeType<DissolveRecipe> DISSOLVE_TYPE = registerType("dissolve");
    public static final RecipeType<SeparateRecipe> SEPARATE_TYPE = registerType("separate");
    public static final RecipeType<PhotoelectricExtractorRecipe> PHOTOELECTRIC_TYPE = registerType("photoelectric");

    public static final RecipeSerializer<DistillationRecipe> DISTILLATION_SERIALIZER = registerSerializer("distillation", new DistillationRecipe.Serializer());
    public static final RecipeSerializer<FilterRecipe> FILTER_SERIALIZER = registerSerializer("filter", new FilterRecipe.Serializer());
    public static final RecipeSerializer<EvaporateRecipe> EVAPORATE_SERIALIZER = registerSerializer("evaporate", new EvaporateRecipe.Serializer());
    public static final RecipeSerializer<DissolveRecipe> DISSOLVE_SERIALIZER = registerSerializer("dissolve", new DissolveRecipe.Serializer());
    public static final RecipeSerializer<SeparateRecipe> SEPARATE_SERIALIZER = registerSerializer("separate", new SeparateRecipe.Serializer());
    public static final RecipeSerializer<PhotoelectricExtractorRecipe> PHOTOELECTRIC_SERIALIZER = registerSerializer("photoelectric", new PhotoelectricExtractorRecipe.Serializer());

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
