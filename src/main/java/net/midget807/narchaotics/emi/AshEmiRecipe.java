package net.midget807.narchaotics.emi;

import dev.emi.emi.api.FabricEmiStack;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.midget807.narchaotics.recipe.AshRecipe;
import net.midget807.narchaotics.registry.ModEmiPlugin;
import net.midget807.narchaotics.util.ModEmiUtils;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AshEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final List<EmiIngredient> inputs;
    private final List<EmiStack> outputs;
    private final AshRecipe recipe;

    public AshEmiRecipe(RecipeEntry<AshRecipe> recipe) {
        this.id = recipe.id();
        this.inputs = List.of(
                EmiIngredient.of(recipe.value().input)
        );
        this.outputs = List.of(
                EmiStack.of(recipe.value().output)
        );
        this.recipe = recipe.value();
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return ModEmiPlugin.ASH_CATEGORY;
    }

    @Override
    public @Nullable Identifier getId() {
        return this.id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return this.inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return this.outputs;
    }

    @Override
    public int getDisplayWidth() {
        return 75;
    }

    @Override
    public int getDisplayHeight() {
        return 20;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addFillingArrow(26, 1, 50 * recipe.getAshTime()).tooltip((mx, my) -> {
            return List.of(TooltipComponent.of(ModEmiUtils.ordered(ModEmiUtils.translatable("emi.cooking.time", recipe.getAshTime() / 20f))));
        });

        widgets.addSlot(inputs.getFirst(), 1, 1);


        widgets.addSlot(outputs.getFirst(), 56, 1).recipeContext(this);
    }
}
