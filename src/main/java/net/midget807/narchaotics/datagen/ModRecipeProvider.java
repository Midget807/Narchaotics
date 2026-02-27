package net.midget807.narchaotics.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.midget807.narchaotics.datagen.json_builder.DistillationRecipeJsonBuilder;
import net.midget807.narchaotics.datagen.json_builder.EvaporateRecipeJsonBuilder;
import net.midget807.narchaotics.datagen.json_builder.FilterRecipeJsonBuilder;
import net.midget807.narchaotics.registry.ModBlocks;
import net.midget807.narchaotics.registry.ModFluids;
import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {


        addDistillationRecipes(recipeExporter);
        addFilterRecipes(recipeExporter);
        addEvaporateRecipes(recipeExporter);
        addDissolveRecipes(recipeExporter);
        addSeparateRecipes(recipeExporter);
        addPhotoelectricRecipes(recipeExporter);
        addAshRecipes(recipeExporter);

    }

    private void addDistillationRecipes(RecipeExporter recipeExporter) {
        DistillationRecipeJsonBuilder.create(
                        Ingredient.ofItems(Items.IRON_INGOT),
                        ModFluids.SULPHURIC_ACID,
                        Ingredient.fromTag(ModItemTagProvider.SOUL_BURNER),
                        40,
                        ModItems.EPHEDRA_DUST,
                        ModFluids.AMMONIA,
                        250L
                ).criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.SOUL_SAND), conditionsFromItem(Items.SOUL_SAND))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.EPHEDRA_DUST)));

        DistillationRecipeJsonBuilder.create(
                        Ingredient.ofItems(Items.GOLD_INGOT),
                        ModFluids.SULPHURIC_ACID,
                        Ingredient.EMPTY,
                        40,
                        ModItems.NETHERRACK_DUST,
                        ModFluids.AMMONIA,
                        250L
                ).criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.SOUL_SAND), conditionsFromItem(Items.SOUL_SAND))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.NETHERRACK_DUST)));

    }

    private void addFilterRecipes(RecipeExporter recipeExporter) {
        FilterRecipeJsonBuilder.create(
                        ModFluids.AMMONIA,
                        40,
                        ModFluids.HYDRAZINE,
                        250L,
                        ModItems.RED_PHOSPHORUS_DUST
                ).criterion(hasItem(ModItems.CONICAL_FLASK), conditionsFromItem(ModItems.CONICAL_FLASK))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.RED_PHOSPHORUS_DUST)));

    }

    private void addEvaporateRecipes(RecipeExporter recipeExporter) {
        EvaporateRecipeJsonBuilder.create(
                ModFluids.SODIUM_CARBONATE,
                250L,
                Ingredient.fromTag(ModItemTagProvider.SOUL_BURNER),
                40,
                ModItems.SODIUM_CARBONATE
        ).criterion(hasItem(ModItems.CONICAL_FLASK), conditionsFromItem(ModItems.CONICAL_FLASK))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.SODIUM_CARBONATE)));
    }

    private void addDissolveRecipes(RecipeExporter recipeExporter) {

    }

    private void addSeparateRecipes(RecipeExporter recipeExporter) {

    }

    private void addPhotoelectricRecipes(RecipeExporter recipeExporter) {

    }

    private void addAshRecipes(RecipeExporter recipeExporter) {

    }
}
