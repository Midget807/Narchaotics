package net.midget807.narchaotics.util;

import net.midget807.narchaotics.block.ChemistryWorkbenchType;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;

public class ModProperties {
    public static final IntProperty FLUID_4 = IntProperty.of("has_fluid_4", 0, 4);

    public static final BooleanProperty HAS_BURNER = BooleanProperty.of("has_burner");
    public static final BooleanProperty HAS_FLASK = BooleanProperty.of("has_flask");
    public static final BooleanProperty HAS_BEAKER = BooleanProperty.of("has_beaker");
    public static final BooleanProperty HAS_STAND = BooleanProperty.of("has_stand");
    public static final BooleanProperty HAS_CONDENSER_OUTPUT = BooleanProperty.of("has_condenser_output");
    public static final BooleanProperty HAS_CONDENSER = BooleanProperty.of("has_condenser");
    public static final BooleanProperty HAS_FILTER = BooleanProperty.of("has_filter");

    public static final EnumProperty<ChemistryWorkbenchType> CHEMISTRY_WORKBENCH_TYPE = EnumProperty.of("workbench_type", ChemistryWorkbenchType.class);
}
