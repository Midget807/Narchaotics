package net.midget807.narchaotics.registry;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public class ModColorProviderRegistry {
    public static final int FUCKASS_COLOR_CONSTANT = 16777216;

    public static void registerBlockColors() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x332319, ModBlocks.DIRTY_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xF7F0CA, ModBlocks.ETHANOL_CAULDRON);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x090E47, ModBlocks.SALT_WATER_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x4b93f4, ModBlocks.VOLCANIC_WATER_CAULDRON);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x812626, ModBlocks.NETHERRACK_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xc72929, ModBlocks.RED_PHOSPHORUS_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xec1e1e, ModBlocks.RED_PHOSPHORUS_CAULDRON);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x292d29, ModBlocks.DIRTY_ASH_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x647062, ModBlocks.CLEAN_ASH_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x271f33, ModBlocks.DIRTY_IODINE_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x8660bd, ModBlocks.CLEAN_IODINE_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x8644e3, ModBlocks.IODINE_CAULDRON);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xffe1ba, ModBlocks.SALT_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xf7cd93, ModBlocks.CRYSTALISED_SALT_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xd9ab6d, ModBlocks.SODIUM_CARBONATE_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xe89627, ModBlocks.SODIUM_CARBONATE_CAULDRON);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x202f1e, ModBlocks.AMMONIA_SLUDGE_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x48a737, ModBlocks.AMMONIA_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x2fcc14, ModBlocks.AMMONIA_CAULDRON);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x4429df, ModBlocks.HYDROGEN_PEROXIDE_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x778028, ModBlocks.BRINE_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x1776f6, ModBlocks.CONCENTRATED_VOLCANIC_WATER_CAULDRON);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xf2a247, ModBlocks.SULPHURIC_ACID_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xff8700, ModBlocks.SULPHURIC_ACID_CAULDRON);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x9126c5, ModBlocks.HYDRAZINE_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x9e52c3, ModBlocks.HYDRAZINE_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xac00ff, ModBlocks.HYDROIODIC_ACID_CAULDRON);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x473e1a, ModBlocks.EPHEDRA_SLUDGE_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x655d3c, ModBlocks.DIRTY_EPHEDRA_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x655d3c, ModBlocks.CLEAN_EPHEDRA_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xbb8f24, ModBlocks.CONCENTRATED_EPHEDRA_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xe7be5d, ModBlocks.ALKALINE_EPHEDRA_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xffc335, ModBlocks.EPHEDRINE_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xffbc1c, ModBlocks.PSEUDOEPHEDRINE_CAULDRON);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x539aa9, ModBlocks.METHAMPHETAMINE_ACID_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x39bad6, ModBlocks.METHAMPHETAMINE_SOLUTION_CAULDRON);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x03bde5, ModBlocks.METHAMPHETAMINE_CAULDRON);

    }

    public static void registerItemColors() {

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xd0b00c - FUCKASS_COLOR_CONSTANT,
                ModItems.POTASSIUM_CHLORIDE_DUST);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xd0d0d0 - FUCKASS_COLOR_CONSTANT,
                ModItems.CALCITE_DUST);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xa5a5a5 - FUCKASS_COLOR_CONSTANT,
                ModItems.TUNGSTEN_OXIDE_DUST);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x332319 - FUCKASS_COLOR_CONSTANT,
                ModItems.DIRTY_SOLUTION_BUCKET, ModItems.DIRTY_SOLUTION_CONICAL_FLASK, ModItems.DIRTY_SOLUTION_ROUND_FLASK, ModItems.DIRTY_SOLUTION_BEAKER, ModItems.DIRTY_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xF7F0CA - FUCKASS_COLOR_CONSTANT,
                ModItems.ETHANOL_BUCKET, ModItems.ETHANOL_CONICAL_FLASK, ModItems.ETHANOL_ROUND_FLASK, ModItems.ETHANOL_BEAKER, ModItems.ETHANOL_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x090E47 - FUCKASS_COLOR_CONSTANT,
                ModItems.SALT_WATER_BUCKET, ModItems.SALT_WATER_CONICAL_FLASK, ModItems.SALT_WATER_ROUND_FLASK, ModItems.SALT_WATER_BEAKER, ModItems.SALT_WATER_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x4b93f4 - FUCKASS_COLOR_CONSTANT,
                ModItems.VOLCANIC_WATER_BUCKET, ModItems.VOLCANIC_WATER_CONICAL_FLASK, ModItems.VOLCANIC_WATER_ROUND_FLASK, ModItems.VOLCANIC_WATER_BEAKER, ModItems.VOLCANIC_WATER_TEST_TUBE, ModItems.NETHERRACK_DUST);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x812626 - FUCKASS_COLOR_CONSTANT,
                ModItems.NETHERRACK_SOLUTION_BUCKET, ModItems.NETHERRACK_SOLUTION_CONICAL_FLASK, ModItems.NETHERRACK_SOLUTION_ROUND_FLASK, ModItems.NETHERRACK_SOLUTION_BEAKER, ModItems.NETHERRACK_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xc72929 - FUCKASS_COLOR_CONSTANT,
                ModItems.RED_PHOSPHORUS_SOLUTION_BUCKET, ModItems.RED_PHOSPHORUS_SOLUTION_CONICAL_FLASK, ModItems.RED_PHOSPHORUS_SOLUTION_ROUND_FLASK, ModItems.RED_PHOSPHORUS_SOLUTION_BEAKER, ModItems.RED_PHOSPHORUS_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xec1e1e - FUCKASS_COLOR_CONSTANT,
                ModItems.RED_PHOSPHORUS_BUCKET, ModItems.RED_PHOSPHORUS_CONICAL_FLASK, ModItems.RED_PHOSPHORUS_ROUND_FLASK, ModItems.RED_PHOSPHORUS_BEAKER, ModItems.RED_PHOSPHORUS_TEST_TUBE, ModItems.RED_PHOSPHORUS_DUST);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x292d29 - FUCKASS_COLOR_CONSTANT,
                ModItems.DIRTY_ASH_SOLUTION_BUCKET, ModItems.DIRTY_ASH_SOLUTION_CONICAL_FLASK, ModItems.DIRTY_ASH_SOLUTION_ROUND_FLASK, ModItems.DIRTY_ASH_SOLUTION_BEAKER, ModItems.DIRTY_ASH_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x647062 - FUCKASS_COLOR_CONSTANT,
                ModItems.CLEAN_ASH_SOLUTION_BUCKET, ModItems.CLEAN_ASH_SOLUTION_CONICAL_FLASK, ModItems.CLEAN_ASH_SOLUTION_ROUND_FLASK, ModItems.CLEAN_ASH_SOLUTION_BEAKER, ModItems.CLEAN_ASH_SOLUTION_TEST_TUBE, ModItems.KELP_ASH);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x271f33 - FUCKASS_COLOR_CONSTANT,
                ModItems.DIRTY_IODINE_SOLUTION_BUCKET, ModItems.DIRTY_IODINE_SOLUTION_CONICAL_FLASK, ModItems.DIRTY_IODINE_SOLUTION_ROUND_FLASK, ModItems.DIRTY_IODINE_SOLUTION_BEAKER, ModItems.DIRTY_IODINE_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x8660bd - FUCKASS_COLOR_CONSTANT,
                ModItems.CLEAN_IODINE_SOLUTION_BUCKET, ModItems.CLEAN_IODINE_SOLUTION_CONICAL_FLASK, ModItems.CLEAN_IODINE_SOLUTION_ROUND_FLASK, ModItems.CLEAN_IODINE_SOLUTION_BEAKER, ModItems.CLEAN_IODINE_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x8644e3 - FUCKASS_COLOR_CONSTANT,
                ModItems.IODINE_BUCKET, ModItems.IODINE_CONICAL_FLASK, ModItems.IODINE_ROUND_FLASK, ModItems.IODINE_BEAKER, ModItems.IODINE_TEST_TUBE, ModItems.IODINE_DUST);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xffe1ba - FUCKASS_COLOR_CONSTANT,
                ModItems.SALT_SOLUTION_BUCKET, ModItems.SALT_SOLUTION_CONICAL_FLASK, ModItems.SALT_SOLUTION_ROUND_FLASK, ModItems.SALT_SOLUTION_BEAKER, ModItems.SALT_SOLUTION_TEST_TUBE, ModItems.MIXED_SALTS);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xf7cd93 - FUCKASS_COLOR_CONSTANT,
                ModItems.CRYSTALISED_SALT_SOLUTION_BUCKET, ModItems.CRYSTALISED_SALT_SOLUTION_CONICAL_FLASK, ModItems.CRYSTALISED_SALT_SOLUTION_ROUND_FLASK, ModItems.CRYSTALISED_SALT_SOLUTION_BEAKER, ModItems.CRYSTALISED_SALT_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xd9ab6d - FUCKASS_COLOR_CONSTANT,
                ModItems.SODIUM_CARBONATE_SOLUTION_BUCKET, ModItems.SODIUM_CARBONATE_SOLUTION_CONICAL_FLASK, ModItems.SODIUM_CARBONATE_SOLUTION_ROUND_FLASK, ModItems.SODIUM_CARBONATE_SOLUTION_BEAKER, ModItems.SODIUM_CARBONATE_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xe89627 - FUCKASS_COLOR_CONSTANT,
                ModItems.SODIUM_CARBONATE_BUCKET, ModItems.SODIUM_CARBONATE_CONICAL_FLASK, ModItems.SODIUM_CARBONATE_ROUND_FLASK, ModItems.SODIUM_CARBONATE_BEAKER, ModItems.SODIUM_CARBONATE_TEST_TUBE, ModItems.SODIUM_CARBONATE_DUST);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x202f1e - FUCKASS_COLOR_CONSTANT,
                ModItems.AMMONIA_SLUDGE_BUCKET, ModItems.AMMONIA_SLUDGE_CONICAL_FLASK, ModItems.AMMONIA_SLUDGE_ROUND_FLASK, ModItems.AMMONIA_SLUDGE_BEAKER, ModItems.AMMONIA_SLUDGE_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x48a737 - FUCKASS_COLOR_CONSTANT,
                ModItems.AMMONIA_SOLUTION_BUCKET, ModItems.AMMONIA_SOLUTION_CONICAL_FLASK, ModItems.AMMONIA_SOLUTION_ROUND_FLASK, ModItems.AMMONIA_SOLUTION_BEAKER, ModItems.AMMONIA_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x2fcc14 - FUCKASS_COLOR_CONSTANT,
                ModItems.AMMONIA_BUCKET, ModItems.AMMONIA_CONICAL_FLASK, ModItems.AMMONIA_ROUND_FLASK, ModItems.AMMONIA_BEAKER, ModItems.AMMONIA_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x4429df - FUCKASS_COLOR_CONSTANT,
                ModItems.HYDROGEN_PEROXIDE_BUCKET, ModItems.HYDROGEN_PEROXIDE_CONICAL_FLASK, ModItems.HYDROGEN_PEROXIDE_ROUND_FLASK, ModItems.HYDROGEN_PEROXIDE_BEAKER, ModItems.HYDROGEN_PEROXIDE_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x778028 - FUCKASS_COLOR_CONSTANT,
                ModItems.BRINE_BUCKET, ModItems.BRINE_CONICAL_FLASK, ModItems.BRINE_ROUND_FLASK, ModItems.BRINE_BEAKER, ModItems.BRINE_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x1776f6 - FUCKASS_COLOR_CONSTANT,
                ModItems.CONCENTRATED_VOLCANIC_WATER_BUCKET, ModItems.CONCENTRATED_VOLCANIC_WATER_CONICAL_FLASK, ModItems.CONCENTRATED_VOLCANIC_WATER_ROUND_FLASK, ModItems.CONCENTRATED_VOLCANIC_WATER_BEAKER, ModItems.CONCENTRATED_VOLCANIC_WATER_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xf2a247 - FUCKASS_COLOR_CONSTANT,
                ModItems.SULPHURIC_ACID_SOLUTION_BUCKET, ModItems.SULPHURIC_ACID_SOLUTION_CONICAL_FLASK, ModItems.SULPHURIC_ACID_SOLUTION_ROUND_FLASK, ModItems.SULPHURIC_ACID_SOLUTION_BEAKER, ModItems.SULPHURIC_ACID_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xff8700 - FUCKASS_COLOR_CONSTANT,
                ModItems.SULPHURIC_ACID_BUCKET, ModItems.SULPHURIC_ACID_CONICAL_FLASK, ModItems.SULPHURIC_ACID_ROUND_FLASK, ModItems.SULPHURIC_ACID_BEAKER, ModItems.SULPHURIC_ACID_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x9126c5 - FUCKASS_COLOR_CONSTANT,
                ModItems.HYDRAZINE_SOLUTION_BUCKET, ModItems.HYDRAZINE_SOLUTION_CONICAL_FLASK, ModItems.HYDRAZINE_SOLUTION_ROUND_FLASK, ModItems.HYDRAZINE_SOLUTION_BEAKER, ModItems.HYDRAZINE_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x9e52c3 - FUCKASS_COLOR_CONSTANT,
                ModItems.HYDRAZINE_BUCKET, ModItems.HYDRAZINE_CONICAL_FLASK, ModItems.HYDRAZINE_ROUND_FLASK, ModItems.HYDRAZINE_BEAKER, ModItems.HYDRAZINE_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xac00ff - FUCKASS_COLOR_CONSTANT,
                ModItems.HYDROIODIC_ACID_BUCKET, ModItems.HYDROIODIC_ACID_CONICAL_FLASK, ModItems.HYDROIODIC_ACID_ROUND_FLASK, ModItems.HYDROIODIC_ACID_BEAKER, ModItems.HYDROIODIC_ACID_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x473e1a - FUCKASS_COLOR_CONSTANT,
                ModItems.EPHEDRA_SLUDGE_BUCKET, ModItems.EPHEDRA_SLUDGE_CONICAL_FLASK, ModItems.EPHEDRA_SLUDGE_ROUND_FLASK, ModItems.EPHEDRA_SLUDGE_BEAKER, ModItems.EPHEDRA_SLUDGE_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x655d3c - FUCKASS_COLOR_CONSTANT,
                ModItems.DIRTY_EPHEDRA_SOLUTION_BUCKET, ModItems.DIRTY_EPHEDRA_SOLUTION_CONICAL_FLASK, ModItems.DIRTY_EPHEDRA_SOLUTION_ROUND_FLASK, ModItems.DIRTY_EPHEDRA_SOLUTION_BEAKER, ModItems.DIRTY_EPHEDRA_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x655d3c - FUCKASS_COLOR_CONSTANT,
                ModItems.CLEAN_EPHEDRA_SOLUTION_BUCKET, ModItems.CLEAN_EPHEDRA_SOLUTION_CONICAL_FLASK, ModItems.CLEAN_EPHEDRA_SOLUTION_ROUND_FLASK, ModItems.CLEAN_EPHEDRA_SOLUTION_BEAKER, ModItems.CLEAN_EPHEDRA_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xbb8f24 - FUCKASS_COLOR_CONSTANT,
                ModItems.CONCENTRATED_EPHEDRA_SOLUTION_BUCKET, ModItems.CONCENTRATED_EPHEDRA_SOLUTION_CONICAL_FLASK, ModItems.CONCENTRATED_EPHEDRA_SOLUTION_ROUND_FLASK, ModItems.CONCENTRATED_EPHEDRA_SOLUTION_BEAKER, ModItems.CONCENTRATED_EPHEDRA_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xe7be5d - FUCKASS_COLOR_CONSTANT,
                ModItems.ALKALINE_EPHEDRA_SOLUTION_BUCKET, ModItems.ALKALINE_EPHEDRA_SOLUTION_CONICAL_FLASK, ModItems.ALKALINE_EPHEDRA_SOLUTION_ROUND_FLASK, ModItems.ALKALINE_EPHEDRA_SOLUTION_BEAKER, ModItems.ALKALINE_EPHEDRA_SOLUTION_TEST_TUBE, ModItems.EPHEDRA_DUST);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xffc335 - FUCKASS_COLOR_CONSTANT,
                ModItems.EPHEDRINE_SOLUTION_BUCKET, ModItems.EPHEDRINE_SOLUTION_CONICAL_FLASK, ModItems.EPHEDRINE_SOLUTION_ROUND_FLASK, ModItems.EPHEDRINE_SOLUTION_BEAKER, ModItems.EPHEDRINE_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0xffbc1c - FUCKASS_COLOR_CONSTANT,
                ModItems.PSEUDOEPHEDRINE_BUCKET, ModItems.PSEUDOEPHEDRINE_CONICAL_FLASK, ModItems.PSEUDOEPHEDRINE_ROUND_FLASK, ModItems.PSEUDOEPHEDRINE_BEAKER, ModItems.PSEUDOEPHEDRINE_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x539aa9 - FUCKASS_COLOR_CONSTANT,
                ModItems.METHAMPHETAMINE_ACID_SOLUTION_BUCKET, ModItems.METHAMPHETAMINE_ACID_SOLUTION_CONICAL_FLASK, ModItems.METHAMPHETAMINE_ACID_SOLUTION_ROUND_FLASK, ModItems.METHAMPHETAMINE_ACID_SOLUTION_BEAKER, ModItems.METHAMPHETAMINE_ACID_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x39bad6 - FUCKASS_COLOR_CONSTANT,
                ModItems.METHAMPHETAMINE_SOLUTION_BUCKET, ModItems.METHAMPHETAMINE_SOLUTION_CONICAL_FLASK, ModItems.METHAMPHETAMINE_SOLUTION_ROUND_FLASK, ModItems.METHAMPHETAMINE_SOLUTION_BEAKER, ModItems.METHAMPHETAMINE_SOLUTION_TEST_TUBE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : 0x03bde5 - FUCKASS_COLOR_CONSTANT,
                ModItems.METHAMPHETAMINE_BUCKET, ModItems.METHAMPHETAMINE_CONICAL_FLASK, ModItems.METHAMPHETAMINE_ROUND_FLASK, ModItems.METHAMPHETAMINE_BEAKER, ModItems.METHAMPHETAMINE_TEST_TUBE);

    }
}
