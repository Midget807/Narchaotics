package net.midget807.narchaotics.emi;

import dev.emi.emi.api.FabricEmiStack;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.midget807.narchaotics.recipe.DissolveRecipe;
import net.midget807.narchaotics.registry.ModEmiPlugin;
import net.midget807.narchaotics.util.ModEmiUtils;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DissolveEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final List<EmiIngredient> inputs;
    private final List<EmiStack> outputs;
    private final DissolveRecipe recipe;

    public DissolveEmiRecipe(RecipeEntry<DissolveRecipe> recipe) {
        this.id = recipe.id();
        this.inputs = List.of(
                EmiIngredient.of(recipe.value().item1),
                EmiIngredient.of(recipe.value().item2),
                FabricEmiStack.of(recipe.value().fluid1.variant(), recipe.value().fluid1.amount() * 81),
                FabricEmiStack.of(recipe.value().fluid2.variant(), recipe.value().fluid2.amount() * 81),
                EmiIngredient.of(recipe.value().fuelType)
        );
        this.outputs = List.of(
                FabricEmiStack.of(recipe.value().product.variant(), recipe.value().product.amount() * 81)
        );
        this.recipe = recipe.value();
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return ModEmiPlugin.DISSOLVE_CATEGORY;
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
        return 97;
    }

    @Override
    public int getDisplayHeight() {
        return 70;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addFillingArrow(47, 17, 50 * recipe.getDissolveTime()).tooltip((mx, my) -> {
            return List.of(TooltipComponent.of(ModEmiUtils.ordered(ModEmiUtils.translatable("emi.cooking.time", recipe.getDissolveTime() / 20f))));
        });
        widgets.addTexture(EmiTexture.FULL_FLAME, 52, 35);

        widgets.addSlot(inputs.get(0), 23, 1);
        widgets.addSlot(inputs.get(1), 23, 31);
        widgets.addTank(inputs.get(2), 1, 1, 18, 18, 250 * 81);
        widgets.addTank(inputs.get(3), 1, 31, 18, 18, 250 * 81);
        widgets.addSlot(inputs.get(4), 50, 51);

        widgets.addTank(outputs.getFirst(), 78, 17, 18, 18, 250 * 81).recipeContext(this);
    }
}
