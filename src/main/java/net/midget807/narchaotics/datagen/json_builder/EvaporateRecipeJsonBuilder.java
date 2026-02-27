package net.midget807.narchaotics.datagen.json_builder;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.midget807.narchaotics.recipe.EvaporateRecipe;
import net.midget807.narchaotics.recipe.FilterRecipe;
import net.midget807.narchaotics.recipe.FluidStack;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class EvaporateRecipeJsonBuilder {
    private final FluidStack input;
    private final Ingredient fuelType;
    private final int cookingTime;
    private final Item output;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap();

    public EvaporateRecipeJsonBuilder(FluidStack input, Ingredient fuelType, int cookingTime, ItemConvertible residue) {
        this.input = input;
        this.fuelType = fuelType;
        this.cookingTime = cookingTime;
        this.output = residue.asItem();
    }



    public static EvaporateRecipeJsonBuilder create(Fluid input, long fluidAmount, Ingredient fuel, int cookingTime, ItemConvertible output) {
        return new EvaporateRecipeJsonBuilder(
                new FluidStack(FluidVariant.of(input), fluidAmount),
                fuel,
                cookingTime,
                output
        );
    }

    public EvaporateRecipeJsonBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion) {
        this.criteria.put(string, advancementCriterion);
        return this;
    }

    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        this.validate(recipeId);
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        this.criteria.forEach(builder::criterion);
        EvaporateRecipe evaporateRecipe = new EvaporateRecipe(
                this.input,
                this.fuelType,
                this.cookingTime,
                this.output == Items.AIR ? ItemStack.EMPTY : new ItemStack(this.output)
        );
        exporter.accept(recipeId, evaporateRecipe, builder.build(recipeId.withPrefixedPath("recipes/")));
    }

    private void validate(Identifier recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }
}
