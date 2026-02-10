package net.midget807.narchaotics.mixin;

import net.midget807.narchaotics.util.inject.FlaskStorable;
import net.midget807.narchaotics.registry.ModItems;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterFluid.class)
public abstract class WaterFluidMixin implements FlaskStorable {
    @Override
    public Item narchaotics$getConicalFlaskItem() {
        return ModItems.WATER_CONICAL_FLASK;
    }

    @Override
    public Item narchaotics$getRoundFlaskItem() {
        return ModItems.WATER_ROUND_FLASK;
    }

    @Override
    public Item narchaotics$getBeakerItem() {
        return ModItems.WATER_BEAKER;
    }

    @Override
    public Item narchaotics$getTestTubeItem() {
        return ModItems.WATER_TEST_TUBE;
    }
}
