package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class CleanAshSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<CleanAshSolutionCauldronBlock> CODEC = createCodec(CleanAshSolutionCauldronBlock::new);

    public CleanAshSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.CLEAN_ASH_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
