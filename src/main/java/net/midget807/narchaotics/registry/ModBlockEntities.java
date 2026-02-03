package net.midget807.narchaotics.registry;

import net.midget807.narchaotics.NarchaoticsMain;
import net.midget807.narchaotics.block.entity.*;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {
    public static final BlockEntityType<ChemistryWorkbenchBlockEntity> CHEMISTRY_WORKBENCH = register("chemistry_workbench", ChemistryWorkbenchBlockEntity::new, ModBlocks.CHEMISTRY_WORKBENCH);
    public static final BlockEntityType<DistillationWorkbenchBlockEntity> DISTILLATION_WORKBENCH = register("distillation_workbench", DistillationWorkbenchBlockEntity::new, ModBlocks.DISTILLATION_WORKBENCH);
    public static final BlockEntityType<FilterWorkbenchBlockEntity> FILTER_WORKBENCH = register("filter_workbench", FilterWorkbenchBlockEntity::new, ModBlocks.FILTER_WORKBENCH);
    public static final BlockEntityType<EvaporateWorkbenchBlockEntity> EVAPORATE_WORKBENCH = register("evaporate_workbench", EvaporateWorkbenchBlockEntity::new, ModBlocks.EVAPORATE_WORKBENCH);
    public static final BlockEntityType<DissolveWorkbenchBlockEntity> DISSOLVE_WORKBENCH = register("dissolve_workbench", DissolveWorkbenchBlockEntity::new, ModBlocks.DISSOLVE_WORKBENCH);

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.BlockEntityFactory<? extends T> entityFactory, Block... blocks) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, NarchaoticsMain.id(name), BlockEntityType.Builder.<T>create(entityFactory, blocks).build());
    }

    public static void registerModBlockEntities() {
        NarchaoticsMain.LOGGER.info("Registering Mod Block Entities");
    }
}
