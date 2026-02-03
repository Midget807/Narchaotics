package net.midget807.narchaotics.registry;

import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.block.*;
import net.midget807.narchaotics.block.cauldron.EthanolCauldronBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

@SuppressWarnings("deprecation")
public class ModBlocks {
    public static final Block EMPTY = registerBlock("empty", new Block(AbstractBlock.Settings.copy(Blocks.AIR)));

    public static final Block ETHANOL_CAULDRON = registerBlockWithoutItem("ethanol_cauldron", new EthanolCauldronBlock(AbstractBlock.Settings.copyShallow(Blocks.CAULDRON)));

    public static final Block ETHANOL = registerBlockWithoutItem("ethanol", new FluidBlock(ModFluids.ETHANOL_STILL, AbstractBlock.Settings.copy(Blocks.WATER)));

    public static final Block EPHEDRA_CROP = registerBlockWithoutItem("ephedra", new EphedraCropBlock(AbstractBlock.Settings.create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));

    public static final Block CHEMISTRY_WORKBENCH = registerBlock("chemistry_workbench", new ChemistryWorkbenchBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_AQUA).strength(0.6f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block DISTILLATION_WORKBENCH = registerBlock("distillation_workbench", new DistillationWorkbenchBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_AQUA).strength(0.6f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block FILTER_WORKBENCH = registerBlock("filter_workbench", new FilterWorkbenchBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_AQUA).strength(0.6f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block EVAPORATE_WORKBENCH = registerBlock("evaporate_workbench", new EvaporateWorkbenchBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_AQUA).strength(0.6f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block DISSOLVE_WORKBENCH = registerBlock("dissolve_workbench", new DissolveWorkbenchBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_AQUA).strength(0.6f).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    private static Block registerBlockWithoutItem(String blockName, Block block) {
        return Registry.register(Registries.BLOCK, NarchaoticsMain.id(blockName), block);
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
