package net.midget807.narchaotics.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.midget807.narchaotics.datagen.json_builder.DissolveRecipeJsonBuilder;
import net.midget807.narchaotics.datagen.json_builder.DistillationRecipeJsonBuilder;
import net.midget807.narchaotics.datagen.json_builder.EvaporateRecipeJsonBuilder;
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
                ).criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
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
        ).criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
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
        ).criterion(hasChemistry(), conditionsFromChemistry())
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.RED_PHOSPHORUS_DUST)));
    }

    private void addEvaporateRecipes(RecipeExporter recipeExporter) {
        EvaporateRecipeJsonBuilder.create(
                ModFluids.SODIUM_CARBONATE,
                250L,
                Ingredient.fromTag(ModItemTagProvider.SOUL_BURNER),
                40,
                ModItems.SODIUM_CARBONATE
        ).criterion(hasChemistry(), conditionsFromChemistry())
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.SODIUM_CARBONATE)));
    }

    private void addDissolveRecipes(RecipeExporter recipeExporter) {
        DissolveRecipeJsonBuilder.create(
                        Ingredient.ofItems(Items.COPPER_INGOT),
                        Ingredient.EMPTY,
                        ModFluids.SULPHURIC_ACID_SOLUTION,
                        250L,
                        ModFluids.VOLCANIC_WATER,
                        250L,
                        Ingredient.fromTag(ModItemTagProvider.SOUL_BURNER),
                        40,
                        ModFluids.SULPHURIC_ACID,
                        250L
        ).criterion(hasChemistry(), conditionsFromChemistry())
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.SULPHURIC_ACID)));
    }

    private void addSeparateRecipes(RecipeExporter recipeExporter) {
        SeparateRecipeJsonBuilder.create(
                ModFluids.METHAMPHETAMINE_ACID_SOLUTION,
                40,
                ModFluids.METHAMPHETAMINE_SOLUTION,
                ModFluids.RED_PHOSPHORUS,
                50L
        ).criterion(hasChemistry(), conditionsFromChemistry())
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.METHAMPHETAMINE_SOLUTION)));
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
