package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class HydrazineSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<HydrazineSolutionCauldronBlock> CODEC = createCodec(HydrazineSolutionCauldronBlock::new);

    public HydrazineSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.HYDRAZINE_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
