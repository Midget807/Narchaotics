package net.midget807.narchaotics.registry;

import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.block.*;
import net.midget807.narchaotics.block.cauldron.AlkalineEphedraSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.AmmoniaCauldronBlock;
import net.midget807.narchaotics.block.cauldron.AmmoniaSludgeCauldronBlock;
import net.midget807.narchaotics.block.cauldron.AmmoniaSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.BrineCauldronBlock;
import net.midget807.narchaotics.block.cauldron.CleanAshSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.CleanEphedraSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.CleanIodineSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.ConcentratedEphedraSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.ConcentratedVolcanicWaterCauldronBlock;
import net.midget807.narchaotics.block.cauldron.CrystalisedSaltSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.DirtyAshSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.DirtyEphedraSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.DirtyIodineSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.DirtySolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.EphedraSludgeCauldronBlock;
import net.midget807.narchaotics.block.cauldron.EphedrineSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.EthanolCauldronBlock;
import net.midget807.narchaotics.block.cauldron.HydrazineCauldronBlock;
import net.midget807.narchaotics.block.cauldron.HydrazineSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.HydrogenPeroxideCauldronBlock;
import net.midget807.narchaotics.block.cauldron.HydroiodicAcidCauldronBlock;
import net.midget807.narchaotics.block.cauldron.IodineCauldronBlock;
import net.midget807.narchaotics.block.cauldron.MethamphetamineAcidSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.MethamphetamineCauldronBlock;
import net.midget807.narchaotics.block.cauldron.MethamphetamineSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.NetherrackSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.PseudoephedrineCauldronBlock;
import net.midget807.narchaotics.block.cauldron.RedPhosphorusCauldronBlock;
import net.midget807.narchaotics.block.cauldron.RedPhosphorusSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.SaltSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.SaltWaterCauldronBlock;
import net.midget807.narchaotics.block.cauldron.SodiumCarbonateCauldronBlock;
import net.midget807.narchaotics.block.cauldron.SodiumCarbonateSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.SulphuricAcidCauldronBlock;
import net.midget807.narchaotics.block.cauldron.SulphuricAcidSolutionCauldronBlock;
import net.midget807.narchaotics.block.cauldron.VolcanicWaterCauldronBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class ModBlocks {
    public static final Map<Block, Identifier> FLUIDS = new LinkedHashMap<>();
    public static final Map<Block, Identifier> CAULDRONS = new LinkedHashMap<>();

    public static final Block EMPTY = registerBlock("empty", new Block(AbstractBlock.Settings.copy(Blocks.AIR)));


    public static final Block DIRTY_SOLUTION_CAULDRON = registerBlockWithoutItem("dirty_solution_cauldron", new DirtySolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block ETHANOL_CAULDRON = registerBlockWithoutItem("ethanol_cauldron", new EthanolCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block SALT_WATER_CAULDRON = registerBlockWithoutItem("salt_water_cauldron", new SaltWaterCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block VOLCANIC_WATER_CAULDRON = registerBlockWithoutItem("volcanic_water_cauldron", new VolcanicWaterCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block NETHERRACK_SOLUTION_CAULDRON = registerBlockWithoutItem("netherrack_solution_cauldron", new NetherrackSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block RED_PHOSPHORUS_SOLUTION_CAULDRON = registerBlockWithoutItem("red_phosphorus_solution_cauldron", new RedPhosphorusSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block RED_PHOSPHORUS_CAULDRON = registerBlockWithoutItem("red_phosphorus_cauldron", new RedPhosphorusCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block DIRTY_ASH_SOLUTION_CAULDRON = registerBlockWithoutItem("dirty_ash_solution_cauldron", new DirtyAshSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block CLEAN_ASH_SOLUTION_CAULDRON = registerBlockWithoutItem("clean_ash_solution_cauldron", new CleanAshSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block DIRTY_IODINE_SOLUTION_CAULDRON = registerBlockWithoutItem("dirty_iodine_solution_cauldron", new DirtyIodineSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block CLEAN_IODINE_SOLUTION_CAULDRON = registerBlockWithoutItem("clean_iodine_solution_cauldron", new CleanIodineSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block IODINE_CAULDRON = registerBlockWithoutItem("iodine_cauldron", new IodineCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block SALT_SOLUTION_CAULDRON = registerBlockWithoutItem("salt_solution_cauldron", new SaltSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block CRYSTALISED_SALT_SOLUTION_CAULDRON = registerBlockWithoutItem("crystalised_salt_solution_cauldron", new CrystalisedSaltSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block SODIUM_CARBONATE_SOLUTION_CAULDRON = registerBlockWithoutItem("sodium_carbonate_solution_cauldron", new SodiumCarbonateSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block SODIUM_CARBONATE_CAULDRON = registerBlockWithoutItem("sodium_carbonate_cauldron", new SodiumCarbonateCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block AMMONIA_SLUDGE_CAULDRON = registerBlockWithoutItem("ammonia_sludge_cauldron", new AmmoniaSludgeCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block AMMONIA_SOLUTION_CAULDRON = registerBlockWithoutItem("ammonia_solution_cauldron", new AmmoniaSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block AMMONIA_CAULDRON = registerBlockWithoutItem("ammonia_cauldron", new AmmoniaCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block HYDROGEN_PEROXIDE_CAULDRON = registerBlockWithoutItem("hydrogen_peroxide_cauldron", new HydrogenPeroxideCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block BRINE_CAULDRON = registerBlockWithoutItem("brine_cauldron", new BrineCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block CONCENTRATED_VOLCANIC_WATER_CAULDRON = registerBlockWithoutItem("concentrated_volcanic_water_cauldron", new ConcentratedVolcanicWaterCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block SULPHURIC_ACID_SOLUTION_CAULDRON = registerBlockWithoutItem("sulphuric_acid_solution_cauldron", new SulphuricAcidSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block SULPHURIC_ACID_CAULDRON = registerBlockWithoutItem("sulphuric_acid_cauldron", new SulphuricAcidCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block HYDRAZINE_SOLUTION_CAULDRON = registerBlockWithoutItem("hydrazine_solution_cauldron", new HydrazineSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block HYDRAZINE_CAULDRON = registerBlockWithoutItem("hydrazine_cauldron", new HydrazineCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block HYDROIODIC_ACID_CAULDRON = registerBlockWithoutItem("hydroiodic_acid_cauldron", new HydroiodicAcidCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block EPHEDRA_SLUDGE_CAULDRON = registerBlockWithoutItem("ephedra_sludge_cauldron", new EphedraSludgeCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block DIRTY_EPHEDRA_SOLUTION_CAULDRON = registerBlockWithoutItem("dirty_ephedra_solution_cauldron", new DirtyEphedraSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block CLEAN_EPHEDRA_SOLUTION_CAULDRON = registerBlockWithoutItem("clean_ephedra_solution_cauldron", new CleanEphedraSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block CONCENTRATED_EPHEDRA_SOLUTION_CAULDRON = registerBlockWithoutItem("concentrated_ephedra_solution_cauldron", new ConcentratedEphedraSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block ALKALINE_EPHEDRA_SOLUTION_CAULDRON = registerBlockWithoutItem("alkaline_ephedra_solution_cauldron", new AlkalineEphedraSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block EPHEDRINE_SOLUTION_CAULDRON = registerBlockWithoutItem("ephedrine_solution_cauldron", new EphedrineSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block PSEUDOEPHEDRINE_CAULDRON = registerBlockWithoutItem("pseudoephedrine_cauldron", new PseudoephedrineCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block METHAMPHETAMINE_ACID_SOLUTION_CAULDRON = registerBlockWithoutItem("methamphetamine_acid_solution_cauldron", new MethamphetamineAcidSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block METHAMPHETAMINE_SOLUTION_CAULDRON = registerBlockWithoutItem("methamphetamine_solution_cauldron", new MethamphetamineSolutionCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));
    public static final Block METHAMPHETAMINE_CAULDRON = registerBlockWithoutItem("methamphetamine_cauldron", new MethamphetamineCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));


    public static final Block DIRTY_SOLUTION = registerBlockWithoutItem("dirty_solution", new FluidBlock(ModFluids.DIRTY_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block ETHANOL = registerBlockWithoutItem("ethanol", new FluidBlock(ModFluids.ETHANOL, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block SALT_WATER = registerBlockWithoutItem("salt_water", new FluidBlock(ModFluids.SALT_WATER, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block VOLCANIC_WATER = registerBlockWithoutItem("volcanic_water", new FluidBlock(ModFluids.VOLCANIC_WATER, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block NETHERRACK_SOLUTION = registerBlockWithoutItem("netherrack_solution", new FluidBlock(ModFluids.NETHERRACK_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block RED_PHOSPHORUS_SOLUTION = registerBlockWithoutItem("red_phosphorus_solution", new FluidBlock(ModFluids.RED_PHOSPHORUS_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block RED_PHOSPHORUS = registerBlockWithoutItem("red_phosphorus", new FluidBlock(ModFluids.RED_PHOSPHORUS, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block DIRTY_ASH_SOLUTION = registerBlockWithoutItem("dirty_ash_solution", new FluidBlock(ModFluids.DIRTY_ASH_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block CLEAN_ASH_SOLUTION = registerBlockWithoutItem("clean_ash_solution", new FluidBlock(ModFluids.CLEAN_ASH_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block DIRTY_IODINE_SOLUTION = registerBlockWithoutItem("dirty_iodine_solution", new FluidBlock(ModFluids.DIRTY_IODINE_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block CLEAN_IODINE_SOLUTION = registerBlockWithoutItem("clean_iodine_solution", new FluidBlock(ModFluids.CLEAN_IODINE_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block IODINE = registerBlockWithoutItem("iodine", new FluidBlock(ModFluids.IODINE, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block SALT_SOLUTION = registerBlockWithoutItem("salt_solution", new FluidBlock(ModFluids.SALT_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block CRYSTALISED_SALT_SOLUTION = registerBlockWithoutItem("crystalised_salt_solution", new FluidBlock(ModFluids.CRYSTALISED_SALT_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block SODIUM_CARBONATE_SOLUTION = registerBlockWithoutItem("sodium_carbonate_solution", new FluidBlock(ModFluids.SODIUM_CARBONATE_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block SODIUM_CARBONATE = registerBlockWithoutItem("sodium_carbonate", new FluidBlock(ModFluids.SODIUM_CARBONATE, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block AMMONIA_SLUDGE = registerBlockWithoutItem("ammonia_sludge", new FluidBlock(ModFluids.AMMONIA_SLUDGE, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block AMMONIA_SOLUTION = registerBlockWithoutItem("ammonia_solution", new FluidBlock(ModFluids.AMMONIA_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block AMMONIA = registerBlockWithoutItem("ammonia", new FluidBlock(ModFluids.AMMONIA, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block HYDROGEN_PEROXIDE = registerBlockWithoutItem("hydrogen_peroxide", new FluidBlock(ModFluids.HYDROGEN_PEROXIDE, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block BRINE = registerBlockWithoutItem("brine", new FluidBlock(ModFluids.BRINE, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block CONCENTRATED_VOLCANIC_WATER = registerBlockWithoutItem("concentrated_volcanic_water", new FluidBlock(ModFluids.CONCENTRATED_VOLCANIC_WATER, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block SULPHURIC_ACID_SOLUTION = registerBlockWithoutItem("sulphuric_acid_solution", new FluidBlock(ModFluids.SULPHURIC_ACID_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block SULPHURIC_ACID = registerBlockWithoutItem("sulphuric_acid", new FluidBlock(ModFluids.SULPHURIC_ACID, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block HYDRAZINE_SOLUTION = registerBlockWithoutItem("hydrazine_solution", new FluidBlock(ModFluids.HYDRAZINE_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block HYDRAZINE = registerBlockWithoutItem("hydrazine", new FluidBlock(ModFluids.HYDRAZINE, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block HYDROIODIC_ACID = registerBlockWithoutItem("hydroiodic_acid", new FluidBlock(ModFluids.HYDROIODIC_ACID, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block EPHEDRA_SLUDGE = registerBlockWithoutItem("ephedra_sludge", new FluidBlock(ModFluids.EPHEDRA_SLUDGE, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block DIRTY_EPHEDRA_SOLUTION = registerBlockWithoutItem("dirty_ephedra_solution", new FluidBlock(ModFluids.DIRTY_EPHEDRA_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block CLEAN_EPHEDRA_SOLUTION = registerBlockWithoutItem("clean_ephedra_solution", new FluidBlock(ModFluids.CLEAN_EPHEDRA_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block CONCENTRATED_EPHEDRA_SOLUTION = registerBlockWithoutItem("concentrated_ephedra_solution", new FluidBlock(ModFluids.CONCENTRATED_EPHEDRA_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block ALKALINE_EPHEDRA_SOLUTION = registerBlockWithoutItem("alkaline_ephedra_solution", new FluidBlock(ModFluids.ALKALINE_EPHEDRA_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block EPHEDRINE_SOLUTION = registerBlockWithoutItem("ephedrine_solution", new FluidBlock(ModFluids.EPHEDRINE_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block PSEUDOEPHEDRINE = registerBlockWithoutItem("pseudoephedrine", new FluidBlock(ModFluids.PSEUDOEPHEDRINE, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block METHAMPHETAMINE_ACID_SOLUTION = registerBlockWithoutItem("methamphetamine_acid_solution", new FluidBlock(ModFluids.METHAMPHETAMINE_ACID_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block METHAMPHETAMINE_SOLUTION = registerBlockWithoutItem("methamphetamine_solution", new FluidBlock(ModFluids.METHAMPHETAMINE_SOLUTION, AbstractBlock.Settings.copy(Blocks.WATER)));
    public static final Block METHAMPHETAMINE = registerBlockWithoutItem("methamphetamine", new FluidBlock(ModFluids.METHAMPHETAMINE, AbstractBlock.Settings.copy(Blocks.WATER)));

    public static final Block EPHEDRA_CROP = registerBlockWithoutItem("ephedra", new EphedraCropBlock(AbstractBlock.Settings.create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));

    public static final Block CHEMISTRY_WORKBENCH = registerBlock("chemistry_workbench", new ChemistryWorkbenchBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_AQUA).strength(0.6f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block DISTILLATION_WORKBENCH = registerBlock("distillation_workbench", new DistillationWorkbenchBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_AQUA).strength(0.6f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block FILTER_WORKBENCH = registerBlock("filter_workbench", new FilterWorkbenchBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_AQUA).strength(0.6f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block EVAPORATE_WORKBENCH = registerBlock("evaporate_workbench", new EvaporateWorkbenchBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_AQUA).strength(0.6f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block DISSOLVE_WORKBENCH = registerBlock("dissolve_workbench", new DissolveWorkbenchBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_AQUA).strength(0.6f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block SEPARATE_WORKBENCH = registerBlock("separate_workbench", new SeparateWorkbenchBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_AQUA).strength(0.6f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block PHOTOELECTRIC_EXTRACTOR = registerBlock("photoelectric_extractor", new PhotoelectricExtractorBlock(AbstractBlock.Settings.create().mapColor(MapColor.GOLD).strength(0.6f).sounds(BlockSoundGroup.METAL).nonOpaque()));
    public static final Block TANK = registerBlock("tank", new FluidTankBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLUE).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL).nonOpaque()));
    public static final Block DISPOSAL_TANK = registerBlock("disposal_tank", new DisposalTankBlock(AbstractBlock.Settings.create().mapColor(MapColor.YELLOW).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL).nonOpaque()));

    private static Block registerBlockWithoutItem(String blockName, Block block) {
        Identifier id = NarchaoticsMain.id(blockName);
        Block registered = Registry.register(Registries.BLOCK, id, block);
        FLUIDS.put(Blocks.WATER, Registries.BLOCK.getId(Blocks.WATER));
        if (block instanceof FluidBlock) {
            FLUIDS.put(registered, id);
        }
        if (block instanceof AbstractCauldronBlock) {
            CAULDRONS.put(registered, id);
        }
        return registered;
    }

    private static Block registerBlock(String name, Block block, Item.Settings settings) {
        registerBlockItem(name, block, settings);
        return Registry.register(Registries.BLOCK, NarchaoticsMain.id(name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, NarchaoticsMain.id(name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return registerBlockItem(name, block, new Item.Settings());
    }

    private static Item registerBlockItem(String name, Block block, Item.Settings settings) {
        return Registry.register(Registries.ITEM, NarchaoticsMain.id(name), new BlockItem(block, settings));
    }

    public static void registerModBlocks() {
        NarchaoticsMain.LOGGER.info("Registering Mod Blocks");
    }
}
