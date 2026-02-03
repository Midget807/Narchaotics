package net.midget807.narchaotics.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.midget807.narchaotics.NarchaoticsMain;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ModItemGroups {
    public static final ItemGroup MAIN = Registry.register(Registries.ITEM_GROUP, NarchaoticsMain.id("narchaotics_main"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.narchaotics.main"))
                    .icon(() -> new ItemStack(ModItems.ICON))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.DISTILLATION_WORKBENCH.asItem());
                        entries.add(ModItems.EPHEDRA_SEEDS);
                        entries.add(ModItems.CONICAL_FLASK);
                        entries.add(ModItems.ROUND_FLASK);
                        entries.add(ModItems.BEAKER);
                        entries.add(ModItems.TEST_TUBE);
                        entries.add(ModItems.BURNER);
                        entries.add(ModItems.FILTER_PAPER);
                        entries.add(ModItems.FUNNEL);
                        entries.add(ModItems.FILTER_FUNNEL);
                        entries.add(ModItems.CONDENSER);
                        entries.add(ModItems.STAND);
                        entries.add(ModItems.CLAMP);
                        entries.add(ModItems.STAND_AND_CLAMP);
                    })
                    .build()
    );

    public static void registerModItemGroups() {
        NarchaoticsMain.LOGGER.info("Registering Mod Item Groups");
    }
}
