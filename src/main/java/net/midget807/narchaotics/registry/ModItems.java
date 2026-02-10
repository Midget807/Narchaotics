package net.midget807.narchaotics.registry;

import net.midget807.narchaotics.NarchaoticsMain;
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

public class ModItems {
    public static final Identifier ENTITY_INTERACTION_RANGE_MODIFIER_ID = NarchaoticsMain.id("entity_interaction_range");
    public static final Identifier BLOCK_INTERACTION_RANGE_MODIFIER_ID = NarchaoticsMain.id("block_interaction_range");

    public static final Item ICON = registerItem("itemgroup_icon", new Item(new Item.Settings()));

    public static final Item BURNER = registerItem("burner", new Item(new Item.Settings()));
    public static final Item CONICAL_FLASK = registerItem("conical_flask", new ConicalFlaskItem(Fluids.EMPTY, null, new Item.Settings()));
    public static final Item BEAKER = registerItem("beaker", new Item(new Item.Settings()));
    public static final Item FILTER_PAPER = registerItem("filter_paper", new Item(new Item.Settings()));
    public static final Item FUNNEL = registerItem("funnel", new Item(new Item.Settings()));
    public static final Item FILTER_FUNNEL = registerItem("filter_funnel", new Item(new Item.Settings()));
    public static final Item STAND = registerItem("stand", new Item(new Item.Settings()));
    public static final Item CLAMP = registerItem("clamp", new Item(new Item.Settings()));
    public static final Item STAND_AND_CLAMP = registerItem("stand_and_clamp", new Item(new Item.Settings()));
    public static final Item CONDENSER = registerItem("condenser_column", new Item(new Item.Settings()));
    public static final Item SEPARATORY_FUNNEL = registerItem("separatory_funnel", new Item(new Item.Settings()));
    public static final Item ROUND_FLASK = registerItem("round_flask", new Item(new Item.Settings()));
    public static final Item TEST_TUBE = registerItem("test_tube", new Item(new Item.Settings()));

    public static final Item EPHEDRA_SEEDS = registerItem("ephedra_seeds", new AliasedBlockItem(ModBlocks.EPHEDRA_CROP, new Item.Settings()));
    public static final Item EPHEDRA = registerItem("ephedra", new Item(new Item.Settings()));
    public static final Item DRIED_EPHEDRA = registerItem("dried_ephedra", new Item(new Item.Settings()));
    public static final Item EPHEDRA_DUST = registerItem("ephedra_dust", new Item(new Item.Settings()));

    public static final Item METHAMPHETAMINE = registerItem("bad_broken", new Item(new Item.Settings()));


    public static final Item ETHANOL_BUCKET = registerItem("bucket/ethanol", new BucketItem(ModFluids.ETHANOL, new Item.Settings().maxCount(1)));

    public static final Item WATER_CONICAL_FLASK = registerItem("conical_flask/ethanol", new ConicalFlaskItem(ModFluids.ETHANOL, ModItems.CONICAL_FLASK, new Item.Settings().maxCount(4)));
    public static final Item ETHANOL_CONICAL_FLASK = registerItem("conical_flask/ethanol", new ConicalFlaskItem(ModFluids.ETHANOL, ModItems.CONICAL_FLASK, new Item.Settings().maxCount(4)));

    public static final Item WATER_ROUND_FLASK = registerItem("round_flask/ethanol", new RoundFlaskItem(ModFluids.ETHANOL, ModItems.ROUND_FLASK, new Item.Settings().maxCount(4)));
    public static final Item ETHANOL_ROUND_FLASK = registerItem("round_flask/ethanol", new RoundFlaskItem(ModFluids.ETHANOL, ModItems.ROUND_FLASK, new Item.Settings().maxCount(4)));

    public static final Item WATER_BEAKER = registerItem("beaker/ethanol", new BeakerItem(ModFluids.ETHANOL, ModItems.BEAKER, new Item.Settings().maxCount(4)));
    public static final Item ETHANOL_BEAKER = registerItem("beaker/ethanol", new BeakerItem(ModFluids.ETHANOL, ModItems.BEAKER, new Item.Settings().maxCount(4)));

    public static final Item WATER_TEST_TUBE = registerItem("test_tube/ethanol", new TestTubeItem(ModFluids.ETHANOL, ModItems.TEST_TUBE, new Item.Settings().maxCount(20)));
    public static final Item ETHANOL_TEST_TUBE = registerItem("test_tube/ethanol", new TestTubeItem(ModFluids.ETHANOL, ModItems.TEST_TUBE, new Item.Settings().maxCount(20)));




    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, NarchaoticsMain.id(name), item);
    }

    public static void registerModItems() {
        NarchaoticsMain.LOGGER.info("Registering Mod Items");
    }
}
