package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class EphedrineSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<EphedrineSolutionCauldronBlock> CODEC = createCodec(EphedrineSolutionCauldronBlock::new);

    public EphedrineSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.EPHEDRINE_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
