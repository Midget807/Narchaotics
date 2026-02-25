package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class PseudoephedrineCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<PseudoephedrineCauldronBlock> CODEC = createCodec(PseudoephedrineCauldronBlock::new);

    public PseudoephedrineCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.PSEUDOEPHEDRINE_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
