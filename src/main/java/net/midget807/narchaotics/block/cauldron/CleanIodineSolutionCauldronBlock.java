package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class CleanIodineSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<CleanIodineSolutionCauldronBlock> CODEC = createCodec(CleanIodineSolutionCauldronBlock::new);

    public CleanIodineSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.CLEAN_IODINE_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
