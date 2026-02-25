package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class ConcentratedVolcanicWaterCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<ConcentratedVolcanicWaterCauldronBlock> CODEC = createCodec(ConcentratedVolcanicWaterCauldronBlock::new);

    public ConcentratedVolcanicWaterCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.CONCENTRATED_VOLCANIC_WATER_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
