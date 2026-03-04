package net.midget807.narchaotics.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.midget807.narchaotics.datagen.json_builder.AshRecipeJsonBuilder;
import net.midget807.narchaotics.datagen.json_builder.DissolveRecipeJsonBuilder;
import net.midget807.narchaotics.datagen.json_builder.DistillationRecipeJsonBuilder;
import net.midget807.narchaotics.datagen.json_builder.EvaporateRecipeJsonBuilder;
import net.midget807.narchaotics.datagen.json_builder.FermentRecipeJsonBuilder;
import net.midget807.narchaotics.datagen.json_builder.FilterRecipeJsonBuilder;
import net.midget807.narchaotics.datagen.json_builder.PhotoelectricExtractorRecipeJsonBuilder;
import net.midget807.narchaotics.datagen.json_builder.SeparateRecipeJsonBuilder;
import net.midget807.narchaotics.registry.ModBlocks;
import net.midget807.narchaotics.registry.ModFluids;
import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
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
        addFermentRecipes(recipeExporter);

    }

    private void addDistillationRecipes(RecipeExporter recipeExporter) {
        DistillationRecipeJsonBuilder.createConcentrating(
                ModFluids.VOLCANIC_WATER,
                40,
                ModFluids.CONCENTRATED_VOLCANIC_WATER,
                100L,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.CONCENTRATED_VOLCANIC_WATER)));
    }

    private void addFilterRecipes(RecipeExporter recipeExporter) {
    }

    private void addEvaporateRecipes(RecipeExporter recipeExporter) {
    }

    private void addDissolveRecipes(RecipeExporter recipeExporter) {
    }

    private void addSeparateRecipes(RecipeExporter recipeExporter) {
    }

    private void addPhotoelectricRecipes(RecipeExporter recipeExporter) {
        PhotoelectricExtractorRecipeJsonBuilder.create(
                ModFluids.SALT_WATER,
                50L,
                Ingredient.ofItems(ModItems.TUNGSTEN_OXIDE),
                80,
                ModFluids.HYDROGEN_PEROXIDE,
                Items.AIR
        ).criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(ModItems.TUNGSTEN_OXIDE), conditionsFromItem(ModItems.TUNGSTEN_OXIDE))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.HYDROGEN_PEROXIDE)));
        PhotoelectricExtractorRecipeJsonBuilder.create(
                ModFluids.SALT_WATER,
                50L,
                Ingredient.ofItems(ModItems.TUNGSTEN_OXIDE_DUST),
                40,
                ModFluids.HYDROGEN_PEROXIDE,
                Items.AIR
        ).criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(ModItems.TUNGSTEN_OXIDE_DUST), conditionsFromItem(ModItems.TUNGSTEN_OXIDE_DUST))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.HYDROGEN_PEROXIDE) + "_dust"));
    }

    private void addAshRecipes(RecipeExporter recipeExporter) {
        AshRecipeJsonBuilder.create(
                Ingredient.ofItems(Items.DRIED_KELP),
                100,
                ModItems.KELP_ASH
        ).criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.DRIED_KELP), conditionsFromItem(Items.DRIED_KELP))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.KELP_ASH)));
    }

    private void addFermentRecipes(RecipeExporter recipeExporter) {
        FermentRecipeJsonBuilder.create(
                Fluids.WATER,
                Ingredient.fromTag(ModItemTagProvider.FERMENTER),
                240,
                ModFluids.ETHANOL,
                50L
        ).criterion(hasChemistry(), conditionsFromChemistry())
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.ETHANOL)));;
    }

    public static String getRecipeName(Fluid fluid) {
        return "fluid/" + Registries.FLUID.getId(fluid).getPath();
    }

    public static String hasChemistry() {
        return "has_chemistry";
    }
    public static AdvancementCriterion<InventoryChangedCriterion.Conditions> conditionsFromChemistry() {
        return conditionsFromPredicates(
                ItemPredicate.Builder.create().tag(ModItemTagProvider.UNLOCK_NARCHAOTICS_RECIPES)
        );
    }
}
