package net.midget807.narchaotics.datagen.json_builder;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.midget807.narchaotics.recipe.DistillationRecipe;
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

import static net.midget807.narchaotics.datagen.ModRecipeProvider.conditionsFromChemistry;
import static net.midget807.narchaotics.datagen.ModRecipeProvider.hasChemistry;

public class FilterRecipeJsonBuilder {
    private final FluidStack input;
    private final int cookingTime;
    private final FluidStack filtrate;
    private final Item residue;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap();

    public FilterRecipeJsonBuilder(FluidStack input, int cookingTime, FluidStack filtrate, ItemConvertible residue) {
        this.input = input;
        this.cookingTime = cookingTime;
        this.filtrate = filtrate;
        this.residue = residue.asItem();
    }



    public static FilterRecipeJsonBuilder create(Fluid input, int cookingTime, Fluid filtrate, long fluidAmount, ItemConvertible residue) {
        return new FilterRecipeJsonBuilder(
                new FluidStack(FluidVariant.of(input), fluidAmount),
                cookingTime,
                new FluidStack(FluidVariant.of(filtrate), fluidAmount),
                residue
        ).criterion(hasChemistry(), conditionsFromChemistry());
    }

    public FilterRecipeJsonBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion) {
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
        FilterRecipe filterRecipe = new FilterRecipe(
                this.input,
                this.cookingTime,
                this.filtrate,
                this.residue == Items.AIR ? ItemStack.EMPTY : new ItemStack(this.residue)
        );
        exporter.accept(recipeId, filterRecipe, builder.build(recipeId.withPrefixedPath("recipes/")));
    }

    private void validate(Identifier recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }
}
