package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class VolcanicWaterCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<VolcanicWaterCauldronBlock> CODEC = createCodec(VolcanicWaterCauldronBlock::new);

    public VolcanicWaterCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.VOLCANIC_WATER_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
