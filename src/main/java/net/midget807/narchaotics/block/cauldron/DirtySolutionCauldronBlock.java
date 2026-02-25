package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class DirtySolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<DirtySolutionCauldronBlock> CODEC = createCodec(DirtySolutionCauldronBlock::new);

    public DirtySolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.DIRTY_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
