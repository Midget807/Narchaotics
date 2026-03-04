package net.midget807.narchaotics.datagen.json_builder;

import net.midget807.narchaotics.recipe.AshRecipe;
import net.midget807.narchaotics.recipe.FluidStack;
import net.midget807.narchaotics.recipe.PhotoelectricExtractorRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class AshRecipeJsonBuilder {
    private final Ingredient input;
    private final int ashTime;
    private final Item output;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap();

    public AshRecipeJsonBuilder(Ingredient input, int ashTime, ItemConvertible output) {
        this.input = input;
        this.ashTime = ashTime;
        this.output = output.asItem();
    }

    public static AshRecipeJsonBuilder create(Ingredient input, int ashTime, ItemConvertible output) {
        return new AshRecipeJsonBuilder(
                input,
                ashTime,
                output
        );
    }

    public AshRecipeJsonBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion) {
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
        AshRecipe ashRecipe = new AshRecipe(
                this.input,
                this.ashTime,
                new ItemStack(this.output)
        );
        exporter.accept(recipeId, ashRecipe, builder.build(recipeId.withPrefixedPath("recipes/")));
    }

    private void validate(Identifier recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }
}
