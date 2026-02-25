package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class ConcentratedEphedraSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<ConcentratedEphedraSolutionCauldronBlock> CODEC = createCodec(ConcentratedEphedraSolutionCauldronBlock::new);

    public ConcentratedEphedraSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.CONCENTRATED_EPHEDRA_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
