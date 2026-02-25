package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class DirtyAshSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<DirtyAshSolutionCauldronBlock> CODEC = createCodec(DirtyAshSolutionCauldronBlock::new);

    public DirtyAshSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.DIRTY_ASH_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
