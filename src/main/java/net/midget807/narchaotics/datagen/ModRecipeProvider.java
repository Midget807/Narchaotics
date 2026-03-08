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
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CONICAL_FLASK, 8)
                .pattern(" X ")
                .pattern(" X ")
                .pattern("XXX")
                .input('X', Items.GLASS)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(recipeExporter, getRecipeName(ModItems.CONICAL_FLASK));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BEAKER, 12)
                .pattern("X X")
                .pattern("X X")
                .pattern("XXX")
                .input('X', Items.GLASS)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(recipeExporter, getRecipeName(ModItems.BEAKER));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ROUND_FLASK, 12)
                .pattern(" X ")
                .pattern("X X")
                .pattern("XXX")
                .input('X', Items.GLASS)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(recipeExporter, getRecipeName(ModItems.ROUND_FLASK));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TEST_TUBE, 8)
                .pattern("X")
                .pattern("X")
                .input('X', Items.GLASS)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(recipeExporter, getRecipeName(ModItems.TEST_TUBE));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BURNER, 1)
                .pattern("INI")
                .pattern("III")
                .input('N', Items.NETHERRACK)
                .input('I', Items.IRON_INGOT)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.NETHERRACK), conditionsFromItem(Items.NETHERRACK))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(recipeExporter, getRecipeName(ModItems.BURNER));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CONDENSER, 1)
                .pattern("XXX")
                .input('X', Items.GLASS)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(recipeExporter, getRecipeName(ModItems.CONDENSER));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FILTER_PAPER, 4)
                .input(Items.PAPER, 3)
                .input(ItemTags.WOOL)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .criterion("has_wool", conditionsFromTag(ItemTags.WOOL))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.FILTER_PAPER)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FUNNEL, 1)
                .pattern("X X")
                .pattern(" X ")
                .pattern(" X ")
                .input('X', Items.GLASS)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(recipeExporter, getRecipeName(ModItems.FUNNEL));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FILTER_FUNNEL, 1)
                .input(ModItems.FILTER_PAPER)
                .input(ModItems.FUNNEL)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(ModItems.FILTER_PAPER), conditionsFromItem(ModItems.FILTER_PAPER))
                .criterion(hasItem(ModItems.FUNNEL), conditionsFromItem(ModItems.FUNNEL))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.FILTER_FUNNEL)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SEPARATORY_FUNNEL, 1)
                .pattern("X X")
                .pattern("X X")
                .pattern(" X ")
                .input('X', Items.GLASS)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(recipeExporter, getRecipeName(ModItems.SEPARATORY_FUNNEL));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CLAMP, 1)
                .pattern(" X ")
                .pattern(" XX")
                .pattern("X  ")
                .input('X', Items.IRON_INGOT)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(recipeExporter, getRecipeName(ModItems.CLAMP));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STAND, 1)
                .pattern(" N ")
                .pattern(" N ")
                .pattern("XXX")
                .input('X', Items.IRON_INGOT)
                .input('N', Items.IRON_NUGGET)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(recipeExporter, getRecipeName(ModItems.STAND));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STAND_AND_CLAMP, 1)
                .input(ModItems.CLAMP)
                .input(ModItems.STAND)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(ModItems.CLAMP), conditionsFromItem(ModItems.CLAMP))
                .criterion(hasItem(ModItems.STAND), conditionsFromItem(ModItems.STAND))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.STAND_AND_CLAMP)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.DISTILLATION_WORKBENCH.asItem(), 1)
                .pattern("FC ")
                .pattern("BSF")
                .pattern("XXX")
                .input('X', ItemTags.WOODEN_SLABS)
                .input('B', ModItems.BURNER)
                .input('F', ModItems.CONICAL_FLASK)
                .input('C', ModItems.CONDENSER)
                .input('S', ModItems.STAND_AND_CLAMP)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion("has_wooden_slab", conditionsFromTag(ItemTags.WOODEN_SLABS))
                .offerTo(recipeExporter, getRecipeName(ModBlocks.DISTILLATION_WORKBENCH.asItem()));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FILTER_WORKBENCH.asItem(), 1)
                .pattern("F ")
                .pattern("YS")
                .pattern("X ")
                .input('X', ItemTags.WOODEN_SLABS)
                .input('Y', ModItems.CONICAL_FLASK)
                .input('F', ModItems.FILTER_FUNNEL)
                .input('S', ModItems.STAND_AND_CLAMP)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion("has_wooden_slab", conditionsFromTag(ItemTags.WOODEN_SLABS))
                .offerTo(recipeExporter, getRecipeName(ModBlocks.FILTER_WORKBENCH.asItem()));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.EVAPORATE_WORKBENCH.asItem(), 1)
                .pattern("F")
                .pattern("B")
                .pattern("X")
                .input('X', ItemTags.WOODEN_SLABS)
                .input('F', ModItems.BEAKER)
                .input('B', ModItems.BURNER)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion("has_wooden_slab", conditionsFromTag(ItemTags.WOODEN_SLABS))
                .offerTo(recipeExporter, getRecipeName(ModBlocks.EVAPORATE_WORKBENCH.asItem()));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.DISSOLVE_WORKBENCH.asItem(), 1)
                .pattern("F")
                .pattern("B")
                .pattern("X")
                .input('X', ItemTags.WOODEN_SLABS)
                .input('F', ModItems.CONICAL_FLASK)
                .input('B', ModItems.BURNER)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion("has_wooden_slab", conditionsFromTag(ItemTags.WOODEN_SLABS))
                .offerTo(recipeExporter, getRecipeName(ModBlocks.DISSOLVE_WORKBENCH.asItem()));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SEPARATE_WORKBENCH.asItem(), 1)
                .pattern("S ")
                .pattern("FY")
                .pattern("X ")
                .input('X', ItemTags.WOODEN_SLABS)
                .input('F', ModItems.CONICAL_FLASK)
                .input('S', ModItems.SEPARATORY_FUNNEL)
                .input('Y', ModItems.STAND_AND_CLAMP)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion("has_wooden_slab", conditionsFromTag(ItemTags.WOODEN_SLABS))
                .offerTo(recipeExporter, getRecipeName(ModBlocks.SEPARATE_WORKBENCH.asItem()));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PHOTOELECTRIC_EXTRACTOR.asItem(), 1)
                .pattern("RDR")
                .pattern("G G")
                .pattern("GGG")
                .input('R', Items.REDSTONE)
                .input('D', Items.DAYLIGHT_DETECTOR)
                .input('G', Items.GLASS)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .criterion(hasItem(Items.DAYLIGHT_DETECTOR), conditionsFromItem(Items.DAYLIGHT_DETECTOR))
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(recipeExporter, getRecipeName(ModBlocks.PHOTOELECTRIC_EXTRACTOR.asItem()));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.TANK.asItem(), 1)
                .pattern("III")
                .pattern("B B")
                .pattern("III")
                .input('B', Items.IRON_BLOCK)
                .input('I', Items.IRON_INGOT)
                .criterion(hasChemistry(), conditionsFromChemistry())
                .criterion(hasItem(Items.IRON_BLOCK), conditionsFromItem(Items.IRON_BLOCK))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(recipeExporter, getRecipeName(ModBlocks.TANK.asItem()));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RICH_SOIL_CLUMP, 4)
                .input(Items.PODZOL, 1)
                .criterion(hasItem(Items.PODZOL), conditionsFromItem(Items.PODZOL))
                .offerTo(recipeExporter, getRecipeName(ModItems.RICH_SOIL_CLUMP) + "_podzol");
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.DIRT, 1)
                .input(ModItems.SOIL_CLUMP, 4)
                .criterion(hasItem(ModItems.SOIL_CLUMP), conditionsFromItem(ModItems.SOIL_CLUMP))
                .offerTo(recipeExporter, getRecipeName(Items.DIRT) + "from_soil_clump");

        createCrushingRecipe(recipeExporter, RecipeCategory.MISC, ModItems.NETHERRACK_DUST, 1, Items.NETHERRACK, 1);
        createCrushingRecipe(recipeExporter, RecipeCategory.MISC, ModItems.RED_PHOSPHORUS_DUST, 1, ModItems.RED_PHOSPHORUS, 1);
        createCrushingRecipe(recipeExporter, RecipeCategory.MISC, ModItems.SODIUM_CARBONATE_DUST, 1, ModItems.SODIUM_CARBONATE, 1);
        createCrushingRecipe(recipeExporter, RecipeCategory.MISC, ModItems.POTASSIUM_CHLORIDE_DUST, 1, ModItems.POTASSIUM_CHLORIDE, 1);
        createCrushingRecipe(recipeExporter, RecipeCategory.MISC, ModItems.IODINE_DUST, 1, ModItems.IODINE, 1);
        createCrushingRecipe(recipeExporter, RecipeCategory.MISC, ModItems.CALCITE_DUST, 1, Items.CALCITE, 8);
        createCrushingRecipe(recipeExporter, RecipeCategory.MISC, ModItems.TUNGSTEN_OXIDE_DUST, 1, ModItems.TUNGSTEN_OXIDE, 1);
        createCrushingRecipe(recipeExporter, RecipeCategory.MISC, ModItems.EPHEDRA_DUST, 1, ModItems.DRIED_EPHEDRA, 1);
        createCrushingRecipe(recipeExporter, RecipeCategory.MISC, ModItems.METHAMPHETAMINE, 1, ModItems.CRYSTAL_METHAMPHETAMINE, 1);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SCHEELITE_PEBBLE, 1)
                .input(ModItems.CALCITE_DUST, 9)
                .criterion(hasItem(ModItems.CALCITE_DUST), conditionsFromItem(ModItems.CALCITE_DUST))
                .offerTo(recipeExporter,  getRecipeName(ModItems.SCHEELITE_PEBBLE));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SCHEELITE_CLUMP, 1)
                .input(ModItems.SCHEELITE_PEBBLE, 9)
                .criterion(hasItem(ModItems.SCHEELITE_PEBBLE), conditionsFromItem(ModItems.SCHEELITE_PEBBLE))
                .offerTo(recipeExporter,  getRecipeName(ModItems.SCHEELITE_CLUMP));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FERTILISER, 1)
                .input(ModItemTagProvider.AMMONIA_SOIL)
                .input(ModItems.AMMONIA_TEST_TUBE)
                .criterion(hasItem(ModItems.AMMONIA_TEST_TUBE), conditionsFromItem(ModItems.AMMONIA_TEST_TUBE))
                .criterion("has_rich_soils", conditionsFromTag(ModItemTagProvider.AMMONIA_SOIL))
                .offerTo(recipeExporter, getRecipeName(ModItems.FERTILISER));

        offerFoodCookingRecipe(recipeExporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new, 600, ModItems.EPHEDRA, ModItems.DRIED_EPHEDRA, 0);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.MORTAR_AND_PESTLE, 1)
                .pattern(" S ")
                .pattern("BSB")
                .pattern("BBB")
                .input('B', Items.BRICK)
                .input('S', Items.STONE)
                .criterion(hasItem(Items.BRICK),  conditionsFromItem(Items.BRICK))
                .criterion(hasItem(Items.STONE),  conditionsFromItem(Items.STONE))
                .offerTo(recipeExporter,  getRecipeName(ModItems.MORTAR_AND_PESTLE));

        offerSmelting(recipeExporter, List.of(ModItems.NETHERRACK_RESIDUE), RecipeCategory.MISC, Items.NETHER_BRICK, 0.1f, 100, "nether_brick_from_residue");

        addDistillationRecipes(recipeExporter);
        addFilterRecipes(recipeExporter);
        addEvaporateRecipes(recipeExporter);
        addDissolveRecipes(recipeExporter);
        addSeparateRecipes(recipeExporter);
        addPhotoelectricRecipes(recipeExporter);
        addAshRecipes(recipeExporter);
        addFermentRecipes(recipeExporter);

    }

    private void createCrushingRecipe(RecipeExporter recipeExporter, RecipeCategory recipeCategory, Item output, int outputCount, Item input, int inputCount) {
        ShapelessRecipeJsonBuilder.create(recipeCategory, output, outputCount)
                .input(input, inputCount)
                .input(ModItems.MORTAR_AND_PESTLE, 1)
                .criterion(hasItem(input), conditionsFromItem(input))
                .criterion(hasChemistry(),  conditionsFromChemistry())
                .offerTo(recipeExporter, Identifier.of(getRecipeName(output)));
    }

    private void addDistillationRecipes(RecipeExporter recipeExporter) {
        DistillationRecipeJsonBuilder.createOneFluid2OneItemOneFluid(
                ModFluids.NETHERRACK_SOLUTION,
                50L,
                40,
                ModItems.NETHERRACK_RESIDUE,
                ModFluids.RED_PHOSPHORUS_SOLUTION,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.RED_PHOSPHORUS_SOLUTION)));
        DistillationRecipeJsonBuilder.createConcentrating(
                ModFluids.RED_PHOSPHORUS_SOLUTION,
                40,
                ModFluids.RED_PHOSPHORUS,
                100L,
                50L

        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.RED_PHOSPHORUS)));

        DistillationRecipeJsonBuilder.createConcentrating(
                ModFluids.SALT_SOLUTION,
                40,
                ModFluids.CRYSTALISED_SALT_SOLUTION,
                100L,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.CRYSTALISED_SALT_SOLUTION)));
        DistillationRecipeJsonBuilder.createConcentrating(
                ModFluids.SODIUM_CARBONATE_SOLUTION,
                40,
                ModFluids.SODIUM_CARBONATE,
                100L,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.SODIUM_CARBONATE)));

        DistillationRecipeJsonBuilder.createConcentrating(
                ModFluids.AMMONIA_SOLUTION,
                40,
                ModFluids.AMMONIA,
                100L,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.AMMONIA)));

        DistillationRecipeJsonBuilder.createConcentrating(
                ModFluids.VOLCANIC_WATER,
                40,
                ModFluids.CONCENTRATED_VOLCANIC_WATER,
                100L,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.CONCENTRATED_VOLCANIC_WATER)));
        DistillationRecipeJsonBuilder.createConcentrating(
                ModFluids.CONCENTRATED_VOLCANIC_WATER,
                80,
                ModFluids.SULPHURIC_ACID_SOLUTION,
                250L,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.SULPHURIC_ACID_SOLUTION)));
        DistillationRecipeJsonBuilder.createConcentrating(
                ModFluids.SULPHURIC_ACID_SOLUTION,
                40,
                ModFluids.SULPHURIC_ACID,
                100L,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.SULPHURIC_ACID)));

        DistillationRecipeJsonBuilder.createConcentrating(
                ModFluids.HYDRAZINE_SOLUTION,
                60,
                ModFluids.HYDRAZINE,
                150L,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.HYDRAZINE)));

        DistillationRecipeJsonBuilder.createConcentrating(
                ModFluids.CLEAN_EPHEDRA_SOLUTION,
                100,
                ModFluids.CONCENTRATED_EPHEDRA_SOLUTION,
                ModFluids.ETHANOL,
                250L,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.CONCENTRATED_EPHEDRA_SOLUTION)));
        DistillationRecipeJsonBuilder.createConcentrating(
                ModFluids.EPHEDRINE_SOLUTION,
                100,
                ModFluids.PSEUDOEPHEDRINE,
                ModFluids.ETHANOL,
                100L,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.PSEUDOEPHEDRINE)));

        DistillationRecipeJsonBuilder.createConcentrating(
                ModFluids.METHAMPHETAMINE_SOLUTION,
                100,
                ModFluids.METHAMPHETAMINE,
                ModFluids.HYDROIODIC_ACID,
                100L,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.METHAMPHETAMINE)));

        DistillationRecipeJsonBuilder.createOneItemOneFluid2OneItemOneFluid(
                Ingredient.ofItems(ModItems.SCHEELITE_CLUMP),
                ModFluids.SODIUM_CARBONATE,
                250L,
                200,
                ModItems.TUNGSTEN_OXIDE,
                ModFluids.SODIUM_CARBONATE_SOLUTION,
                250L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.TUNGSTEN_OXIDE)));
    }

    private void addFilterRecipes(RecipeExporter recipeExporter) {
        FilterRecipeJsonBuilder.create(
                ModFluids.DIRTY_ASH_SOLUTION,
                200,
                ModFluids.CLEAN_ASH_SOLUTION,
                50L,
                ModItems.RESIDUE
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.CLEAN_ASH_SOLUTION)));

        FilterRecipeJsonBuilder.create(
                ModFluids.CRYSTALISED_SALT_SOLUTION,
                200,
                ModFluids.SODIUM_CARBONATE_SOLUTION,
                50L,
                ModItems.POTASSIUM_CHLORIDE
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.SODIUM_CARBONATE_SOLUTION)));

        FilterRecipeJsonBuilder.create(
                ModFluids.AMMONIA_SLUDGE,
                250L,
                200,
                ModFluids.AMMONIA_SOLUTION,
                50L,
                ModItems.SOIL_CLUMP
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.AMMONIA_SOLUTION)));

        FilterRecipeJsonBuilder.create(
                ModFluids.ALKALINE_EPHEDRA_SOLUTION,
                200,
                ModFluids.EPHEDRINE_SOLUTION,
                50L,
                ModItems.RESIDUE
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.EPHEDRINE_SOLUTION)));

    }

    private void addEvaporateRecipes(RecipeExporter recipeExporter) {
        EvaporateRecipeJsonBuilder.create(
                ModFluids.RED_PHOSPHORUS,
                50L,
                100,
                ModItems.RED_PHOSPHORUS
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.RED_PHOSPHORUS)));
        EvaporateRecipeJsonBuilder.create(
                ModFluids.CLEAN_IODINE_SOLUTION,
                50L,
                100,
                ModItems.IODINE
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.IODINE)));
        EvaporateRecipeJsonBuilder.create(
                ModFluids.SALT_WATER,
                50L,
                100,
                ModItems.MIXED_SALTS
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.MIXED_SALTS)));
        EvaporateRecipeJsonBuilder.create(
                ModFluids.SODIUM_CARBONATE,
                50L,
                100,
                ModItems.SODIUM_CARBONATE
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.SODIUM_CARBONATE)));
        EvaporateRecipeJsonBuilder.create(
                ModFluids.SULPHURIC_ACID,
                50L,
                100,
                ModItems.SULPHURIC_ACID
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.SULPHURIC_ACID)));

        EvaporateRecipeJsonBuilder.create(
                ModFluids.METHAMPHETAMINE,
                50L,
                100,
                ModItems.CRYSTAL_METHAMPHETAMINE
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.CRYSTAL_METHAMPHETAMINE)));
    }

    private void addDissolveRecipes(RecipeExporter recipeExporter) {
        DissolveRecipeJsonBuilder.createFromOneItemOneFluid(
                Ingredient.ofItems(ModItems.NETHERRACK_DUST),
                Fluids.WATER,
                50L,
                Ingredient.fromTag(ModItemTagProvider.SOUL_BURNER),
                40,
                ModFluids.NETHERRACK_SOLUTION,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.NETHERRACK_SOLUTION)));

        DissolveRecipeJsonBuilder.createFromOneItemOneFluid(
                Ingredient.ofItems(ModItems.KELP_ASH),
                Fluids.WATER,
                50L,
                40,
                ModFluids.DIRTY_ASH_SOLUTION,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.DIRTY_ASH_SOLUTION)));
        DissolveRecipeJsonBuilder.createFromOneItemTwoFluids(
                Ingredient.ofItems(ModItems.SULPHURIC_ACID),
                ModFluids.CLEAN_ASH_SOLUTION,
                50L,
                ModFluids.HYDROGEN_PEROXIDE,
                50L,
                100,
                ModFluids.DIRTY_IODINE_SOLUTION,
                100L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.DIRTY_IODINE_SOLUTION)));

        DissolveRecipeJsonBuilder.createFromOneItemOneFluid(
                Ingredient.ofItems(ModItems.MIXED_SALTS),
                Fluids.WATER,
                50L,
                Ingredient.fromTag(ModItemTagProvider.SOUL_BURNER),
                40,
                ModFluids.SALT_SOLUTION,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.SALT_SOLUTION)));

        DissolveRecipeJsonBuilder.createFromTwoItemOneFluid(
                Ingredient.ofItems(ModItems.RICH_SOIL_CLUMP),
                Ingredient.ofItems(ModItems.POTASSIUM_CHLORIDE),
                Fluids.WATER,
                50L,
                80,
                ModFluids.AMMONIA_SLUDGE,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.AMMONIA_SLUDGE)));
        DissolveRecipeJsonBuilder.createFromTwoItemOneFluid(
                Ingredient.ofItems(ModItems.RICH_SOIL_CLUMP),
                Ingredient.ofItems(ModItems.POTASSIUM_CHLORIDE_DUST),
                Fluids.WATER,
                50L,
                40,
                ModFluids.AMMONIA_SLUDGE,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.AMMONIA_SLUDGE) + "_kcl_dust"));

        DissolveRecipeJsonBuilder.createFromTwoFluids(
                ModFluids.AMMONIA,
                100L,
                ModFluids.HYDROGEN_PEROXIDE,
                50L,
                60,
                ModFluids.HYDRAZINE_SOLUTION,
                150L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.HYDRAZINE_SOLUTION)));
        DissolveRecipeJsonBuilder.createFromTwoFluids(
                ModFluids.HYDRAZINE,
                50L,
                ModFluids.IODINE,
                100L,
                60,
                ModFluids.HYDROIODIC_ACID,
                150L
        ).offerTo(recipeExporter,  Identifier.of(getRecipeName(ModFluids.HYDROIODIC_ACID)));

        DissolveRecipeJsonBuilder.createFromOneItemOneFluid(
                Ingredient.ofItems(ModItems.EPHEDRA_DUST),
                ModFluids.AMMONIA,
                50L,
                40,
                ModFluids.EPHEDRA_SLUDGE,
                50L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.EPHEDRA_SLUDGE)));
        DissolveRecipeJsonBuilder.createFromTwoFluids(
                ModFluids.EPHEDRA_SLUDGE,
                50L,
                ModFluids.ETHANOL,
                50L,
                40,
                ModFluids.DIRTY_EPHEDRA_SOLUTION,
                100L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.DIRTY_EPHEDRA_SOLUTION)));
        DissolveRecipeJsonBuilder.createFromOneItemOneFluid(
                Ingredient.ofItems(ModItems.SODIUM_CARBONATE),
                ModFluids.CLEAN_EPHEDRA_SOLUTION,
                50L,
                80,
                ModFluids.ALKALINE_EPHEDRA_SOLUTION,
                50
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.ALKALINE_EPHEDRA_SOLUTION)));
        DissolveRecipeJsonBuilder.createFromOneItemOneFluid(
                Ingredient.ofItems(ModItems.SODIUM_CARBONATE_DUST),
                ModFluids.CLEAN_EPHEDRA_SOLUTION,
                50L,
                40,
                ModFluids.ALKALINE_EPHEDRA_SOLUTION,
                50
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.ALKALINE_EPHEDRA_SOLUTION) + "_dust"));

        DissolveRecipeJsonBuilder.createFromOneItemTwoFluids(
                Ingredient.ofItems(ModItems.RED_PHOSPHORUS),
                ModFluids.PSEUDOEPHEDRINE,
                50L,
                ModFluids.HYDROIODIC_ACID,
                50L,
                Ingredient.fromTag(ModItemTagProvider.SOUL_BURNER),
                160,
                ModFluids.METHAMPHETAMINE_ACID_SOLUTION,
                100L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.METHAMPHETAMINE_ACID_SOLUTION)));
        DissolveRecipeJsonBuilder.createFromOneItemTwoFluids(
                Ingredient.ofItems(ModItems.RED_PHOSPHORUS_DUST),
                ModFluids.PSEUDOEPHEDRINE,
                50L,
                ModFluids.HYDROIODIC_ACID,
                50L,
                Ingredient.fromTag(ModItemTagProvider.SOUL_BURNER),
                80,
                ModFluids.METHAMPHETAMINE_ACID_SOLUTION,
                100L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.METHAMPHETAMINE_ACID_SOLUTION) + "_dust"));
        DissolveRecipeJsonBuilder.createFromTwoFluids(
                ModFluids.METHAMPHETAMINE_ACID_SOLUTION,
                50L,
                ModFluids.ETHANOL,
                50L,
                80,
                ModFluids.METHAMPHETAMINE_SOLUTION,
                100L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.METHAMPHETAMINE_SOLUTION)));


    }

    private void addSeparateRecipes(RecipeExporter recipeExporter) {
        SeparateRecipeJsonBuilder.create(
                ModFluids.DIRTY_IODINE_SOLUTION,
                250L,
                200,
                ModFluids.CLEAN_IODINE_SOLUTION,
                50L,
                ModFluids.DIRTY_SOLUTION,
                200L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.CLEAN_IODINE_SOLUTION)));

        SeparateRecipeJsonBuilder.create(
                ModFluids.DIRTY_EPHEDRA_SOLUTION,
                250L,
                200,
                ModFluids.CLEAN_EPHEDRA_SOLUTION,
                50L,
                ModFluids.DIRTY_SOLUTION,
                200L
        ).offerTo(recipeExporter, Identifier.of(getRecipeName(ModFluids.CLEAN_EPHEDRA_SOLUTION)));
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
