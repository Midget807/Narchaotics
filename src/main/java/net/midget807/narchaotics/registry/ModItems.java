package net.midget807.narchaotics.registry;

import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.item.AbstractBeakerItem;
import net.midget807.narchaotics.item.AbstractConicalFlaskItem;
import net.midget807.narchaotics.item.AbstractRoundFlaskItem;
import net.midget807.narchaotics.item.AbstractTestTubeItem;
import net.midget807.narchaotics.item.BeakerItem;
import net.midget807.narchaotics.item.ConicalFlaskItem;
import net.midget807.narchaotics.item.RoundFlaskItem;
import net.midget807.narchaotics.item.TestTubeItem;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModItems {
    public static final Map<Item, Identifier> BUCKETS = new LinkedHashMap<>();
    public static final Map<Item, Identifier> CONICAL_FLASKS = new LinkedHashMap<>();
    public static final Map<Item, Identifier> ROUND_FLASKS = new LinkedHashMap<>();
    public static final Map<Item, Identifier> BEAKERS = new LinkedHashMap<>();
    public static final Map<Item, Identifier> TEST_TUBES = new LinkedHashMap<>();

    public static final Identifier ENTITY_INTERACTION_RANGE_MODIFIER_ID = NarchaoticsMain.id("entity_interaction_range");
    public static final Identifier BLOCK_INTERACTION_RANGE_MODIFIER_ID = NarchaoticsMain.id("block_interaction_range");

    public static final Item ICON = registerItem("itemgroup_icon", new Item(new Item.Settings()));

    public static final Item BURNER = registerItem("burner", new Item(new Item.Settings()));
    public static final Item CONICAL_FLASK = registerItem("conical_flask", new AbstractConicalFlaskItem(Fluids.EMPTY, null, new Item.Settings()));
    public static final Item BEAKER = registerItem("beaker", new AbstractBeakerItem(Fluids.EMPTY, null, new Item.Settings()));
    public static final Item FILTER_PAPER = registerItem("filter_paper", new Item(new Item.Settings()));
    public static final Item FUNNEL = registerItem("funnel", new Item(new Item.Settings()));
    public static final Item FILTER_FUNNEL = registerItem("filter_funnel", new Item(new Item.Settings()));
    public static final Item STAND = registerItem("stand", new Item(new Item.Settings()));
    public static final Item CLAMP = registerItem("clamp", new Item(new Item.Settings()));
    public static final Item STAND_AND_CLAMP = registerItem("stand_and_clamp", new Item(new Item.Settings()));
    public static final Item CONDENSER = registerItem("condenser_column", new Item(new Item.Settings()));
    public static final Item SEPARATORY_FUNNEL = registerItem("separatory_funnel", new Item(new Item.Settings()));
    public static final Item ROUND_FLASK = registerItem("round_flask", new AbstractRoundFlaskItem(Fluids.EMPTY, null, new Item.Settings()));
    public static final Item TEST_TUBE = registerItem("test_tube", new AbstractTestTubeItem(Fluids.EMPTY, null, new Item.Settings()));

    public static final Item RESIDUE = registerItem("residue", new Item(new Item.Settings()));

    public static final Item NETHERRACK_DUST = registerItem("dust/netherrack", new Item(new Item.Settings()));
    public static final Item NETHERRACK_RESIDUE = registerItem("nether_residue", new Item(new Item.Settings()));
    public static final Item RED_PHOSPHORUS = registerItem("red_phosphorus", new Item(new Item.Settings()));
    public static final Item RED_PHOSPHORUS_DUST = registerItem("dust/red_phosphorus", new Item(new Item.Settings()));

    public static final Item KELP_ASH = registerItem("kelp_ash", new Item(new Item.Settings()));
    public static final Item IODINE = registerItem("iodine", new Item(new Item.Settings()));
    public static final Item IODINE_DUST = registerItem("dust/iodine", new Item(new Item.Settings()));

    public static final Item MIXED_SALTS = registerItem("mixed_salts", new Item(new Item.Settings()));
    public static final Item SODIUM_CARBONATE = registerItem("sodium_carbonate", new Item(new Item.Settings()));
    public static final Item POTASSIUM_CHLORIDE = registerItem("potassium_chloride", new Item(new Item.Settings()));
    public static final Item SODIUM_CARBONATE_DUST = registerItem("dust/sodium_carbonate", new Item(new Item.Settings()));
    public static final Item POTASSIUM_CHLORIDE_DUST = registerItem("dust/potassium_chloride", new Item(new Item.Settings()));

    public static final Item RICH_SOIL_CLUMP = registerItem("rich_soil_clump", new Item(new Item.Settings()));
    public static final Item SOIL_CLUMP = registerItem("soil_clump", new Item(new Item.Settings()));

    public static final Item CALCITE_DUST = registerItem("dust/calcite", new Item(new Item.Settings()));
    public static final Item SCHEELITE_PEBBLE = registerItem("scheelite_pebble", new Item(new Item.Settings()));
    public static final Item SCHEELITE_CLUMP = registerItem("scheelite_clump", new Item(new Item.Settings()));
    public static final Item TUNGSTEN_OXIDE = registerItem("tungsten_oxide", new Item(new Item.Settings()));
    public static final Item TUNGSTEN_OXIDE_DUST = registerItem("dust/tungsten_oxide", new Item(new Item.Settings()));

    public static final Item FERTILISER = registerItem("fertiliser", new Item(new Item.Settings()));

    public static final Item EPHEDRA_SEEDS = registerItem("ephedra_seeds", new AliasedBlockItem(ModBlocks.EPHEDRA_CROP, new Item.Settings()));
    public static final Item EPHEDRA = registerItem("ephedra", new Item(new Item.Settings()));
    public static final Item DRIED_EPHEDRA = registerItem("dried_ephedra", new Item(new Item.Settings()));
    public static final Item EPHEDRA_DUST = registerItem("dust/ephedra", new Item(new Item.Settings()));

    public static final Item CRYSTAL_METHAMPHETAMINE = registerItem("crystal_methamphetamine", new Item(new Item.Settings()));
    public static final Item METHAMPHETAMINE = registerItem("methamphetamine", new Item(new Item.Settings()));



    public static final Item DIRTY_SOLUTION_BUCKET = registerItem("bucket/dirty_solution", new BucketItem(ModFluids.DIRTY_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item ETHANOL_BUCKET = registerItem("bucket/ethanol", new BucketItem(ModFluids.ETHANOL, new Item.Settings().maxCount(1)));
    public static final Item SALT_WATER_BUCKET = registerItem("bucket/salt_water", new BucketItem(ModFluids.SALT_WATER, new Item.Settings().maxCount(1)));
    public static final Item VOLCANIC_WATER_BUCKET = registerItem("bucket/volcanic_water", new BucketItem(ModFluids.VOLCANIC_WATER, new Item.Settings().maxCount(1)));
    public static final Item NETHERRACK_SOLUTION_BUCKET = registerItem("bucket/netherrack_solution", new BucketItem(ModFluids.NETHERRACK_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item RED_PHOSPHORUS_SOLUTION_BUCKET = registerItem("bucket/red_phosphorus_solution", new BucketItem(ModFluids.RED_PHOSPHORUS_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item RED_PHOSPHORUS_BUCKET = registerItem("bucket/red_phosphorus", new BucketItem(ModFluids.RED_PHOSPHORUS, new Item.Settings().maxCount(1)));
    public static final Item DIRTY_ASH_SOLUTION_BUCKET = registerItem("bucket/dirty_ash_solution", new BucketItem(ModFluids.DIRTY_ASH_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item CLEAN_ASH_SOLUTION_BUCKET = registerItem("bucket/clean_ash_solution", new BucketItem(ModFluids.CLEAN_ASH_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item DIRTY_IODINE_SOLUTION_BUCKET = registerItem("bucket/dirty_iodine_solution", new BucketItem(ModFluids.DIRTY_IODINE_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item CLEAN_IODINE_SOLUTION_BUCKET = registerItem("bucket/clean_iodine_solution", new BucketItem(ModFluids.CLEAN_IODINE_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item IODINE_BUCKET = registerItem("bucket/iodine", new BucketItem(ModFluids.IODINE, new Item.Settings().maxCount(1)));
    public static final Item SALT_SOLUTION_BUCKET = registerItem("bucket/salt_solution", new BucketItem(ModFluids.SALT_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item CRYSTALISED_SALT_SOLUTION_BUCKET = registerItem("bucket/crystalised_salt_solution", new BucketItem(ModFluids.CRYSTALISED_SALT_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item SODIUM_CARBONATE_SOLUTION_BUCKET = registerItem("bucket/sodium_carbonate_solution", new BucketItem(ModFluids.SODIUM_CARBONATE_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item SODIUM_CARBONATE_BUCKET = registerItem("bucket/sodium_carbonate", new BucketItem(ModFluids.SODIUM_CARBONATE, new Item.Settings().maxCount(1)));
    public static final Item AMMONIA_SLUDGE_BUCKET = registerItem("bucket/ammonia_sludge", new BucketItem(ModFluids.AMMONIA_SLUDGE, new Item.Settings().maxCount(1)));
    public static final Item AMMONIA_SOLUTION_BUCKET = registerItem("bucket/ammonia_solution", new BucketItem(ModFluids.AMMONIA_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item AMMONIA_BUCKET = registerItem("bucket/ammonia", new BucketItem(ModFluids.AMMONIA, new Item.Settings().maxCount(1)));
    public static final Item HYDROGEN_PEROXIDE_BUCKET = registerItem("bucket/hydrogen_peroxide", new BucketItem(ModFluids.HYDROGEN_PEROXIDE, new Item.Settings().maxCount(1)));
    public static final Item BRINE_BUCKET = registerItem("bucket/brine", new BucketItem(ModFluids.BRINE, new Item.Settings().maxCount(1)));
    public static final Item CONCENTRATED_VOLCANIC_WATER_BUCKET = registerItem("bucket/concentrated_volcanic_water", new BucketItem(ModFluids.CONCENTRATED_VOLCANIC_WATER, new Item.Settings().maxCount(1)));
    public static final Item SULPHURIC_ACID_SOLUTION_BUCKET = registerItem("bucket/sulphuric_acid_solution", new BucketItem(ModFluids.SULPHURIC_ACID_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item SULPHURIC_ACID_BUCKET = registerItem("bucket/sulphuric_acid", new BucketItem(ModFluids.SULPHURIC_ACID, new Item.Settings().maxCount(1)));
    public static final Item HYDRAZINE_SOLUTION_BUCKET = registerItem("bucket/hydrazine_solution", new BucketItem(ModFluids.HYDRAZINE_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item HYDRAZINE_BUCKET = registerItem("bucket/hydrazine", new BucketItem(ModFluids.HYDRAZINE, new Item.Settings().maxCount(1)));
    public static final Item HYDROIODIC_ACID_BUCKET = registerItem("bucket/hydroiodic_acid", new BucketItem(ModFluids.HYDROIODIC_ACID, new Item.Settings().maxCount(1)));
    public static final Item EPHEDRA_SLUDGE_BUCKET = registerItem("bucket/ephedra_sludge", new BucketItem(ModFluids.EPHEDRA_SLUDGE, new Item.Settings().maxCount(1)));
    public static final Item DIRTY_EPHEDRA_SOLUTION_BUCKET = registerItem("bucket/dirty_ephedra_solution", new BucketItem(ModFluids.DIRTY_EPHEDRA_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item CLEAN_EPHEDRA_SOLUTION_BUCKET = registerItem("bucket/clean_ephedra_solution", new BucketItem(ModFluids.CLEAN_EPHEDRA_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item CONCENTRATED_EPHEDRA_SOLUTION_BUCKET = registerItem("bucket/concentrated_ephedra_solution", new BucketItem(ModFluids.CONCENTRATED_EPHEDRA_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item ALKALINE_EPHEDRA_SOLUTION_BUCKET = registerItem("bucket/alkaline_ephedra_solution", new BucketItem(ModFluids.ALKALINE_EPHEDRA_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item EPHEDRINE_SOLUTION_BUCKET = registerItem("bucket/ephedrine_solution", new BucketItem(ModFluids.EPHEDRINE_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item PSEUDOEPHEDRINE_BUCKET = registerItem("bucket/pseudoephedrine", new BucketItem(ModFluids.PSEUDOEPHEDRINE, new Item.Settings().maxCount(1)));
    public static final Item METHAMPHETAMINE_ACID_SOLUTION_BUCKET = registerItem("bucket/methamphetamine_acid", new BucketItem(ModFluids.METHAMPHETAMINE_ACID_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item METHAMPHETAMINE_SOLUTION_BUCKET = registerItem("bucket/methamphetamine_solution", new BucketItem(ModFluids.METHAMPHETAMINE_SOLUTION, new Item.Settings().maxCount(1)));
    public static final Item METHAMPHETAMINE_BUCKET = registerItem("bucket/methamphetamine", new BucketItem(ModFluids.METHAMPHETAMINE, new Item.Settings().maxCount(1)));


    public static final Item DIRTY_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/dirty_solution", new ConicalFlaskItem(ModFluids.DIRTY_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item WATER_CONICAL_FLASK = registerItem("conical_flask/water", new ConicalFlaskItem(Fluids.WATER, new Item.Settings().maxCount(4)));
    public static final Item ETHANOL_CONICAL_FLASK = registerItem("conical_flask/ethanol", new ConicalFlaskItem(ModFluids.ETHANOL, new Item.Settings().maxCount(4)));
    public static final Item SALT_WATER_CONICAL_FLASK = registerItem("conical_flask/salt_water", new ConicalFlaskItem(ModFluids.SALT_WATER, new Item.Settings().maxCount(4)));
    public static final Item VOLCANIC_WATER_CONICAL_FLASK = registerItem("conical_flask/volcanic_water", new ConicalFlaskItem(ModFluids.VOLCANIC_WATER, new Item.Settings().maxCount(4)));
    public static final Item NETHERRACK_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/netherrack_solution", new ConicalFlaskItem(ModFluids.NETHERRACK_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item RED_PHOSPHORUS_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/red_phosphorus_solution", new ConicalFlaskItem(ModFluids.RED_PHOSPHORUS_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item RED_PHOSPHORUS_CONICAL_FLASK = registerItem("conical_flask/red_phosphorus", new ConicalFlaskItem(ModFluids.RED_PHOSPHORUS, new Item.Settings().maxCount(4)));
    public static final Item DIRTY_ASH_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/dirty_ash_solution", new ConicalFlaskItem(ModFluids.DIRTY_ASH_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CLEAN_ASH_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/clean_ash_solution", new ConicalFlaskItem(ModFluids.CLEAN_ASH_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item DIRTY_IODINE_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/dirty_iodine_solution", new ConicalFlaskItem(ModFluids.DIRTY_IODINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CLEAN_IODINE_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/clean_iodine_solution", new ConicalFlaskItem(ModFluids.CLEAN_IODINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item IODINE_CONICAL_FLASK = registerItem("conical_flask/iodine", new ConicalFlaskItem(ModFluids.IODINE, new Item.Settings().maxCount(4)));
    public static final Item SALT_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/salt_solution", new ConicalFlaskItem(ModFluids.SALT_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CRYSTALISED_SALT_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/crystalised_salt_solution", new ConicalFlaskItem(ModFluids.CRYSTALISED_SALT_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item SODIUM_CARBONATE_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/sodium_carbonate_solution", new ConicalFlaskItem(ModFluids.SODIUM_CARBONATE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item SODIUM_CARBONATE_CONICAL_FLASK = registerItem("conical_flask/sodium_carbonate", new ConicalFlaskItem(ModFluids.SODIUM_CARBONATE, new Item.Settings().maxCount(4)));
    public static final Item AMMONIA_SLUDGE_CONICAL_FLASK = registerItem("conical_flask/ammonia_sludge", new ConicalFlaskItem(ModFluids.AMMONIA_SLUDGE, new Item.Settings().maxCount(4)));
    public static final Item AMMONIA_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/ammonia_solution", new ConicalFlaskItem(ModFluids.AMMONIA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item AMMONIA_CONICAL_FLASK = registerItem("conical_flask/ammonia", new ConicalFlaskItem(ModFluids.AMMONIA, new Item.Settings().maxCount(4)));
    public static final Item HYDROGEN_PEROXIDE_CONICAL_FLASK = registerItem("conical_flask/hydrogen_peroxide", new ConicalFlaskItem(ModFluids.HYDROGEN_PEROXIDE, new Item.Settings().maxCount(4)));
    public static final Item BRINE_CONICAL_FLASK = registerItem("conical_flask/brine", new ConicalFlaskItem(ModFluids.BRINE, new Item.Settings().maxCount(4)));
    public static final Item CONCENTRATED_VOLCANIC_WATER_CONICAL_FLASK = registerItem("conical_flask/concentrated_volcanic_water", new ConicalFlaskItem(ModFluids.CONCENTRATED_VOLCANIC_WATER, new Item.Settings().maxCount(4)));
    public static final Item SULPHURIC_ACID_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/sulphuric_acid_solution", new ConicalFlaskItem(ModFluids.SULPHURIC_ACID_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item SULPHURIC_ACID_CONICAL_FLASK = registerItem("conical_flask/sulphuric_acid", new ConicalFlaskItem(ModFluids.SULPHURIC_ACID, new Item.Settings().maxCount(4)));
    public static final Item HYDRAZINE_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/hydrazine_solution", new ConicalFlaskItem(ModFluids.HYDRAZINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item HYDRAZINE_CONICAL_FLASK = registerItem("conical_flask/hydrazine", new ConicalFlaskItem(ModFluids.HYDRAZINE, new Item.Settings().maxCount(4)));
    public static final Item HYDROIODIC_ACID_CONICAL_FLASK = registerItem("conical_flask/hydroiodic_acid", new ConicalFlaskItem(ModFluids.HYDROIODIC_ACID, new Item.Settings().maxCount(4)));
    public static final Item EPHEDRA_SLUDGE_CONICAL_FLASK = registerItem("conical_flask/ephedra_sludge", new ConicalFlaskItem(ModFluids.EPHEDRA_SLUDGE, new Item.Settings().maxCount(4)));
    public static final Item DIRTY_EPHEDRA_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/dirty_ephedra_solution", new ConicalFlaskItem(ModFluids.DIRTY_EPHEDRA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CLEAN_EPHEDRA_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/clean_ephedra_solution", new ConicalFlaskItem(ModFluids.CLEAN_EPHEDRA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CONCENTRATED_EPHEDRA_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/concentrated_ephedra_solution", new ConicalFlaskItem(ModFluids.CONCENTRATED_EPHEDRA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item ALKALINE_EPHEDRA_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/alkaline_ephedra_solution", new ConicalFlaskItem(ModFluids.ALKALINE_EPHEDRA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item EPHEDRINE_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/ephedrine_solution", new ConicalFlaskItem(ModFluids.EPHEDRINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item PSEUDOEPHEDRINE_CONICAL_FLASK = registerItem("conical_flask/pseudoephedrine", new ConicalFlaskItem(ModFluids.PSEUDOEPHEDRINE, new Item.Settings().maxCount(4)));
    public static final Item METHAMPHETAMINE_ACID_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/methamphetamine_acid", new ConicalFlaskItem(ModFluids.METHAMPHETAMINE_ACID_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item METHAMPHETAMINE_SOLUTION_CONICAL_FLASK = registerItem("conical_flask/methamphetamine_solution", new ConicalFlaskItem(ModFluids.METHAMPHETAMINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item METHAMPHETAMINE_CONICAL_FLASK = registerItem("conical_flask/methamphetamine", new ConicalFlaskItem(ModFluids.METHAMPHETAMINE, new Item.Settings().maxCount(4)));


    public static final Item DIRTY_SOLUTION_ROUND_FLASK = registerItem("round_flask/dirty_solution", new RoundFlaskItem(ModFluids.DIRTY_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item WATER_ROUND_FLASK = registerItem("round_flask/water", new RoundFlaskItem(Fluids.WATER, new Item.Settings().maxCount(4)));
    public static final Item ETHANOL_ROUND_FLASK = registerItem("round_flask/ethanol", new RoundFlaskItem(ModFluids.ETHANOL, new Item.Settings().maxCount(4)));
    public static final Item SALT_WATER_ROUND_FLASK = registerItem("round_flask/salt_water", new RoundFlaskItem(ModFluids.SALT_WATER, new Item.Settings().maxCount(4)));
    public static final Item VOLCANIC_WATER_ROUND_FLASK = registerItem("round_flask/volcanic_water", new RoundFlaskItem(ModFluids.VOLCANIC_WATER, new Item.Settings().maxCount(4)));
    public static final Item NETHERRACK_SOLUTION_ROUND_FLASK = registerItem("round_flask/netherrack_solution", new RoundFlaskItem(ModFluids.NETHERRACK_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item RED_PHOSPHORUS_SOLUTION_ROUND_FLASK = registerItem("round_flask/red_phosphorus_solution", new RoundFlaskItem(ModFluids.RED_PHOSPHORUS_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item RED_PHOSPHORUS_ROUND_FLASK = registerItem("round_flask/red_phosphorus", new RoundFlaskItem(ModFluids.RED_PHOSPHORUS, new Item.Settings().maxCount(4)));
    public static final Item DIRTY_ASH_SOLUTION_ROUND_FLASK = registerItem("round_flask/dirty_ash_solution", new RoundFlaskItem(ModFluids.DIRTY_ASH_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CLEAN_ASH_SOLUTION_ROUND_FLASK = registerItem("round_flask/clean_ash_solution", new RoundFlaskItem(ModFluids.CLEAN_ASH_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item DIRTY_IODINE_SOLUTION_ROUND_FLASK = registerItem("round_flask/dirty_iodine_solution", new RoundFlaskItem(ModFluids.DIRTY_IODINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CLEAN_IODINE_SOLUTION_ROUND_FLASK = registerItem("round_flask/clean_iodine_solution", new RoundFlaskItem(ModFluids.CLEAN_IODINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item IODINE_ROUND_FLASK = registerItem("round_flask/iodine", new RoundFlaskItem(ModFluids.IODINE, new Item.Settings().maxCount(4)));
    public static final Item SALT_SOLUTION_ROUND_FLASK = registerItem("round_flask/salt_solution", new RoundFlaskItem(ModFluids.SALT_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CRYSTALISED_SALT_SOLUTION_ROUND_FLASK = registerItem("round_flask/crystalised_salt_solution", new RoundFlaskItem(ModFluids.CRYSTALISED_SALT_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item SODIUM_CARBONATE_SOLUTION_ROUND_FLASK = registerItem("round_flask/sodium_carbonate_solution", new RoundFlaskItem(ModFluids.SODIUM_CARBONATE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item SODIUM_CARBONATE_ROUND_FLASK = registerItem("round_flask/sodium_carbonate", new RoundFlaskItem(ModFluids.SODIUM_CARBONATE, new Item.Settings().maxCount(4)));
    public static final Item AMMONIA_SLUDGE_ROUND_FLASK = registerItem("round_flask/ammonia_sludge", new RoundFlaskItem(ModFluids.AMMONIA_SLUDGE, new Item.Settings().maxCount(4)));
    public static final Item AMMONIA_SOLUTION_ROUND_FLASK = registerItem("round_flask/ammonia_solution", new RoundFlaskItem(ModFluids.AMMONIA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item AMMONIA_ROUND_FLASK = registerItem("round_flask/ammonia", new RoundFlaskItem(ModFluids.AMMONIA, new Item.Settings().maxCount(4)));
    public static final Item HYDROGEN_PEROXIDE_ROUND_FLASK = registerItem("round_flask/hydrogen_peroxide", new RoundFlaskItem(ModFluids.HYDROGEN_PEROXIDE, new Item.Settings().maxCount(4)));
    public static final Item BRINE_ROUND_FLASK = registerItem("round_flask/brine", new RoundFlaskItem(ModFluids.BRINE, new Item.Settings().maxCount(4)));
    public static final Item CONCENTRATED_VOLCANIC_WATER_ROUND_FLASK = registerItem("round_flask/concentrated_volcanic_water", new RoundFlaskItem(ModFluids.CONCENTRATED_VOLCANIC_WATER, new Item.Settings().maxCount(4)));
    public static final Item SULPHURIC_ACID_SOLUTION_ROUND_FLASK = registerItem("round_flask/sulphuric_acid_solution", new RoundFlaskItem(ModFluids.SULPHURIC_ACID_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item SULPHURIC_ACID_ROUND_FLASK = registerItem("round_flask/sulphuric_acid", new RoundFlaskItem(ModFluids.SULPHURIC_ACID, new Item.Settings().maxCount(4)));
    public static final Item HYDRAZINE_SOLUTION_ROUND_FLASK = registerItem("round_flask/hydrazine_solution", new RoundFlaskItem(ModFluids.HYDRAZINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item HYDRAZINE_ROUND_FLASK = registerItem("round_flask/hydrazine", new RoundFlaskItem(ModFluids.HYDRAZINE, new Item.Settings().maxCount(4)));
    public static final Item HYDROIODIC_ACID_ROUND_FLASK = registerItem("round_flask/hydroiodic_acid", new RoundFlaskItem(ModFluids.HYDROIODIC_ACID, new Item.Settings().maxCount(4)));
    public static final Item EPHEDRA_SLUDGE_ROUND_FLASK = registerItem("round_flask/ephedra_sludge", new RoundFlaskItem(ModFluids.EPHEDRA_SLUDGE, new Item.Settings().maxCount(4)));
    public static final Item DIRTY_EPHEDRA_SOLUTION_ROUND_FLASK = registerItem("round_flask/dirty_ephedra_solution", new RoundFlaskItem(ModFluids.DIRTY_EPHEDRA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CLEAN_EPHEDRA_SOLUTION_ROUND_FLASK = registerItem("round_flask/clean_ephedra_solution", new RoundFlaskItem(ModFluids.CLEAN_EPHEDRA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CONCENTRATED_EPHEDRA_SOLUTION_ROUND_FLASK = registerItem("round_flask/concentrated_ephedra_solution", new RoundFlaskItem(ModFluids.CONCENTRATED_EPHEDRA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item ALKALINE_EPHEDRA_SOLUTION_ROUND_FLASK = registerItem("round_flask/alkaline_ephedra_solution", new RoundFlaskItem(ModFluids.ALKALINE_EPHEDRA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item EPHEDRINE_SOLUTION_ROUND_FLASK = registerItem("round_flask/ephedrine_solution", new RoundFlaskItem(ModFluids.EPHEDRINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item PSEUDOEPHEDRINE_ROUND_FLASK = registerItem("round_flask/pseudoephedrine", new RoundFlaskItem(ModFluids.PSEUDOEPHEDRINE, new Item.Settings().maxCount(4)));
    public static final Item METHAMPHETAMINE_ACID_SOLUTION_ROUND_FLASK = registerItem("round_flask/methamphetamine_acid", new RoundFlaskItem(ModFluids.METHAMPHETAMINE_ACID_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item METHAMPHETAMINE_SOLUTION_ROUND_FLASK = registerItem("round_flask/methamphetamine_solution", new RoundFlaskItem(ModFluids.METHAMPHETAMINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item METHAMPHETAMINE_ROUND_FLASK = registerItem("round_flask/methamphetamine", new RoundFlaskItem(ModFluids.METHAMPHETAMINE, new Item.Settings().maxCount(4)));


    public static final Item DIRTY_SOLUTION_BEAKER = registerItem("beaker/dirty_solution", new BeakerItem(ModFluids.DIRTY_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item WATER_BEAKER = registerItem("beaker/water", new BeakerItem(Fluids.WATER, new Item.Settings().maxCount(4)));
    public static final Item ETHANOL_BEAKER = registerItem("beaker/ethanol", new BeakerItem(ModFluids.ETHANOL, new Item.Settings().maxCount(4)));
    public static final Item SALT_WATER_BEAKER = registerItem("beaker/salt_water", new BeakerItem(ModFluids.SALT_WATER, new Item.Settings().maxCount(4)));
    public static final Item VOLCANIC_WATER_BEAKER = registerItem("beaker/volcanic_water", new BeakerItem(ModFluids.VOLCANIC_WATER, new Item.Settings().maxCount(4)));
    public static final Item NETHERRACK_SOLUTION_BEAKER = registerItem("beaker/netherrack_solution", new BeakerItem(ModFluids.NETHERRACK_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item RED_PHOSPHORUS_SOLUTION_BEAKER = registerItem("beaker/red_phosphorus_solution", new BeakerItem(ModFluids.RED_PHOSPHORUS_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item RED_PHOSPHORUS_BEAKER = registerItem("beaker/red_phosphorus", new BeakerItem(ModFluids.RED_PHOSPHORUS, new Item.Settings().maxCount(4)));
    public static final Item DIRTY_ASH_SOLUTION_BEAKER = registerItem("beaker/dirty_ash_solution", new BeakerItem(ModFluids.DIRTY_ASH_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CLEAN_ASH_SOLUTION_BEAKER = registerItem("beaker/clean_ash_solution", new BeakerItem(ModFluids.CLEAN_ASH_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item DIRTY_IODINE_SOLUTION_BEAKER = registerItem("beaker/dirty_iodine_solution", new BeakerItem(ModFluids.DIRTY_IODINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CLEAN_IODINE_SOLUTION_BEAKER = registerItem("beaker/clean_iodine_solution", new BeakerItem(ModFluids.CLEAN_IODINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item IODINE_BEAKER = registerItem("beaker/iodine", new BeakerItem(ModFluids.IODINE, new Item.Settings().maxCount(4)));
    public static final Item SALT_SOLUTION_BEAKER = registerItem("beaker/salt_solution", new BeakerItem(ModFluids.SALT_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CRYSTALISED_SALT_SOLUTION_BEAKER = registerItem("beaker/crystalised_salt_solution", new BeakerItem(ModFluids.CRYSTALISED_SALT_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item SODIUM_CARBONATE_SOLUTION_BEAKER = registerItem("beaker/sodium_carbonate_solution", new BeakerItem(ModFluids.SODIUM_CARBONATE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item SODIUM_CARBONATE_BEAKER = registerItem("beaker/sodium_carbonate", new BeakerItem(ModFluids.SODIUM_CARBONATE, new Item.Settings().maxCount(4)));
    public static final Item AMMONIA_SLUDGE_BEAKER = registerItem("beaker/ammonia_sludge", new BeakerItem(ModFluids.AMMONIA_SLUDGE, new Item.Settings().maxCount(4)));
    public static final Item AMMONIA_SOLUTION_BEAKER = registerItem("beaker/ammonia_solution", new BeakerItem(ModFluids.AMMONIA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item AMMONIA_BEAKER = registerItem("beaker/ammonia", new BeakerItem(ModFluids.AMMONIA, new Item.Settings().maxCount(4)));
    public static final Item HYDROGEN_PEROXIDE_BEAKER = registerItem("beaker/hydrogen_peroxide", new BeakerItem(ModFluids.HYDROGEN_PEROXIDE, new Item.Settings().maxCount(4)));
    public static final Item BRINE_BEAKER = registerItem("beaker/brine", new BeakerItem(ModFluids.BRINE, new Item.Settings().maxCount(4)));
    public static final Item CONCENTRATED_VOLCANIC_WATER_BEAKER = registerItem("beaker/concentrated_volcanic_water", new BeakerItem(ModFluids.CONCENTRATED_VOLCANIC_WATER, new Item.Settings().maxCount(4)));
    public static final Item SULPHURIC_ACID_SOLUTION_BEAKER = registerItem("beaker/sulphuric_acid_solution", new BeakerItem(ModFluids.SULPHURIC_ACID_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item SULPHURIC_ACID_BEAKER = registerItem("beaker/sulphuric_acid", new BeakerItem(ModFluids.SULPHURIC_ACID, new Item.Settings().maxCount(4)));
    public static final Item HYDRAZINE_SOLUTION_BEAKER = registerItem("beaker/hydrazine_solution", new BeakerItem(ModFluids.HYDRAZINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item HYDRAZINE_BEAKER = registerItem("beaker/hydrazine", new BeakerItem(ModFluids.HYDRAZINE, new Item.Settings().maxCount(4)));
    public static final Item HYDROIODIC_ACID_BEAKER = registerItem("beaker/hydroiodic_acid", new BeakerItem(ModFluids.HYDROIODIC_ACID, new Item.Settings().maxCount(4)));
    public static final Item EPHEDRA_SLUDGE_BEAKER = registerItem("beaker/ephedra_sludge", new BeakerItem(ModFluids.EPHEDRA_SLUDGE, new Item.Settings().maxCount(4)));
    public static final Item DIRTY_EPHEDRA_SOLUTION_BEAKER = registerItem("beaker/dirty_ephedra_solution", new BeakerItem(ModFluids.DIRTY_EPHEDRA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CLEAN_EPHEDRA_SOLUTION_BEAKER = registerItem("beaker/clean_ephedra_solution", new BeakerItem(ModFluids.CLEAN_EPHEDRA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item CONCENTRATED_EPHEDRA_SOLUTION_BEAKER = registerItem("beaker/concentrated_ephedra_solution", new BeakerItem(ModFluids.CONCENTRATED_EPHEDRA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item ALKALINE_EPHEDRA_SOLUTION_BEAKER = registerItem("beaker/alkaline_ephedra_solution", new BeakerItem(ModFluids.ALKALINE_EPHEDRA_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item EPHEDRINE_SOLUTION_BEAKER = registerItem("beaker/ephedrine_solution", new BeakerItem(ModFluids.EPHEDRINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item PSEUDOEPHEDRINE_BEAKER = registerItem("beaker/pseudoephedrine", new BeakerItem(ModFluids.PSEUDOEPHEDRINE, new Item.Settings().maxCount(4)));
    public static final Item METHAMPHETAMINE_ACID_SOLUTION_BEAKER = registerItem("beaker/methamphetamine_acid", new BeakerItem(ModFluids.METHAMPHETAMINE_ACID_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item METHAMPHETAMINE_SOLUTION_BEAKER = registerItem("beaker/methamphetamine_solution", new BeakerItem(ModFluids.METHAMPHETAMINE_SOLUTION, new Item.Settings().maxCount(4)));
    public static final Item METHAMPHETAMINE_BEAKER = registerItem("beaker/methamphetamine", new BeakerItem(ModFluids.METHAMPHETAMINE, new Item.Settings().maxCount(4)));


    public static final Item DIRTY_SOLUTION_TEST_TUBE = registerItem("test_tube/dirty_solution", new TestTubeItem(ModFluids.DIRTY_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item WATER_TEST_TUBE = registerItem("test_tube/water", new TestTubeItem(Fluids.WATER, new Item.Settings().maxCount(20)));
    public static final Item ETHANOL_TEST_TUBE = registerItem("test_tube/ethanol", new TestTubeItem(ModFluids.ETHANOL, new Item.Settings().maxCount(20)));
    public static final Item SALT_WATER_TEST_TUBE = registerItem("test_tube/salt_water", new TestTubeItem(ModFluids.SALT_WATER, new Item.Settings().maxCount(20)));
    public static final Item VOLCANIC_WATER_TEST_TUBE = registerItem("test_tube/volcanic_water", new TestTubeItem(ModFluids.VOLCANIC_WATER, new Item.Settings().maxCount(20)));
    public static final Item NETHERRACK_SOLUTION_TEST_TUBE = registerItem("test_tube/netherrack_solution", new TestTubeItem(ModFluids.NETHERRACK_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item RED_PHOSPHORUS_SOLUTION_TEST_TUBE = registerItem("test_tube/red_phosphorus_solution", new TestTubeItem(ModFluids.RED_PHOSPHORUS_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item RED_PHOSPHORUS_TEST_TUBE = registerItem("test_tube/red_phosphorus", new TestTubeItem(ModFluids.RED_PHOSPHORUS, new Item.Settings().maxCount(20)));
    public static final Item DIRTY_ASH_SOLUTION_TEST_TUBE = registerItem("test_tube/dirty_ash_solution", new TestTubeItem(ModFluids.DIRTY_ASH_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item CLEAN_ASH_SOLUTION_TEST_TUBE = registerItem("test_tube/clean_ash_solution", new TestTubeItem(ModFluids.CLEAN_ASH_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item DIRTY_IODINE_SOLUTION_TEST_TUBE = registerItem("test_tube/dirty_iodine_solution", new TestTubeItem(ModFluids.DIRTY_IODINE_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item CLEAN_IODINE_SOLUTION_TEST_TUBE = registerItem("test_tube/clean_iodine_solution", new TestTubeItem(ModFluids.CLEAN_IODINE_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item IODINE_TEST_TUBE = registerItem("test_tube/iodine", new TestTubeItem(ModFluids.IODINE, new Item.Settings().maxCount(20)));
    public static final Item SALT_SOLUTION_TEST_TUBE = registerItem("test_tube/salt_solution", new TestTubeItem(ModFluids.SALT_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item CRYSTALISED_SALT_SOLUTION_TEST_TUBE = registerItem("test_tube/crystalised_salt_solution", new TestTubeItem(ModFluids.CRYSTALISED_SALT_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item SODIUM_CARBONATE_SOLUTION_TEST_TUBE = registerItem("test_tube/sodium_carbonate_solution", new TestTubeItem(ModFluids.SODIUM_CARBONATE_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item SODIUM_CARBONATE_TEST_TUBE = registerItem("test_tube/sodium_carbonate", new TestTubeItem(ModFluids.SODIUM_CARBONATE, new Item.Settings().maxCount(20)));
    public static final Item AMMONIA_SLUDGE_TEST_TUBE = registerItem("test_tube/ammonia_sludge", new TestTubeItem(ModFluids.AMMONIA_SLUDGE, new Item.Settings().maxCount(20)));
    public static final Item AMMONIA_SOLUTION_TEST_TUBE = registerItem("test_tube/ammonia_solution", new TestTubeItem(ModFluids.AMMONIA_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item AMMONIA_TEST_TUBE = registerItem("test_tube/ammonia", new TestTubeItem(ModFluids.AMMONIA, new Item.Settings().maxCount(20)));
    public static final Item HYDROGEN_PEROXIDE_TEST_TUBE = registerItem("test_tube/hydrogen_peroxide", new TestTubeItem(ModFluids.HYDROGEN_PEROXIDE, new Item.Settings().maxCount(20)));
    public static final Item BRINE_TEST_TUBE = registerItem("test_tube/brine", new TestTubeItem(ModFluids.BRINE, new Item.Settings().maxCount(20)));
    public static final Item CONCENTRATED_VOLCANIC_WATER_TEST_TUBE = registerItem("test_tube/concentrated_volcanic_water", new TestTubeItem(ModFluids.CONCENTRATED_VOLCANIC_WATER, new Item.Settings().maxCount(20)));
    public static final Item SULPHURIC_ACID_SOLUTION_TEST_TUBE = registerItem("test_tube/sulphuric_acid_solution", new TestTubeItem(ModFluids.SULPHURIC_ACID_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item SULPHURIC_ACID_TEST_TUBE = registerItem("test_tube/sulphuric_acid", new TestTubeItem(ModFluids.SULPHURIC_ACID, new Item.Settings().maxCount(20)));
    public static final Item HYDRAZINE_SOLUTION_TEST_TUBE = registerItem("test_tube/hydrazine_solution", new TestTubeItem(ModFluids.HYDRAZINE_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item HYDRAZINE_TEST_TUBE = registerItem("test_tube/hydrazine", new TestTubeItem(ModFluids.HYDRAZINE, new Item.Settings().maxCount(20)));
    public static final Item HYDROIODIC_ACID_TEST_TUBE = registerItem("test_tube/hydroiodic_acid", new TestTubeItem(ModFluids.HYDROIODIC_ACID, new Item.Settings().maxCount(20)));
    public static final Item EPHEDRA_SLUDGE_TEST_TUBE = registerItem("test_tube/ephedra_sludge", new TestTubeItem(ModFluids.EPHEDRA_SLUDGE, new Item.Settings().maxCount(20)));
    public static final Item DIRTY_EPHEDRA_SOLUTION_TEST_TUBE = registerItem("test_tube/dirty_ephedra_solution", new TestTubeItem(ModFluids.DIRTY_EPHEDRA_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item CLEAN_EPHEDRA_SOLUTION_TEST_TUBE = registerItem("test_tube/clean_ephedra_solution", new TestTubeItem(ModFluids.CLEAN_EPHEDRA_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item CONCENTRATED_EPHEDRA_SOLUTION_TEST_TUBE = registerItem("test_tube/concentrated_ephedra_solution", new TestTubeItem(ModFluids.CONCENTRATED_EPHEDRA_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item ALKALINE_EPHEDRA_SOLUTION_TEST_TUBE = registerItem("test_tube/alkaline_ephedra_solution", new TestTubeItem(ModFluids.ALKALINE_EPHEDRA_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item EPHEDRINE_SOLUTION_TEST_TUBE = registerItem("test_tube/ephedrine_solution", new TestTubeItem(ModFluids.EPHEDRINE_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item PSEUDOEPHEDRINE_TEST_TUBE = registerItem("test_tube/pseudoephedrine", new TestTubeItem(ModFluids.PSEUDOEPHEDRINE, new Item.Settings().maxCount(20)));
    public static final Item METHAMPHETAMINE_ACID_SOLUTION_TEST_TUBE = registerItem("test_tube/methamphetamine_acid", new TestTubeItem(ModFluids.METHAMPHETAMINE_ACID_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item METHAMPHETAMINE_SOLUTION_TEST_TUBE = registerItem("test_tube/methamphetamine_solution", new TestTubeItem(ModFluids.METHAMPHETAMINE_SOLUTION, new Item.Settings().maxCount(20)));
    public static final Item METHAMPHETAMINE_TEST_TUBE = registerItem("test_tube/methamphetamine", new TestTubeItem(ModFluids.METHAMPHETAMINE, new Item.Settings().maxCount(20)));



    private static Item registerItem(String name, Item item) {
        Identifier id = NarchaoticsMain.id(name);
        Item registeredItem = Registry.register(Registries.ITEM, id, item);
        if (registeredItem instanceof BucketItem) {
            BUCKETS.put(registeredItem, id);
        } else if (registeredItem instanceof AbstractConicalFlaskItem) {
            CONICAL_FLASKS.put(registeredItem, id);
        } else if (registeredItem instanceof AbstractRoundFlaskItem) {
            ROUND_FLASKS.put(registeredItem, id);
        } else if (registeredItem instanceof AbstractBeakerItem) {
            BEAKERS.put(registeredItem, id);
        } else if (registeredItem instanceof AbstractTestTubeItem) {
            TEST_TUBES.put(registeredItem, id);
        }
        return registeredItem;
    }

    public static void registerModItems() {
        NarchaoticsMain.LOGGER.info("Registering Mod Items");
    }
}
