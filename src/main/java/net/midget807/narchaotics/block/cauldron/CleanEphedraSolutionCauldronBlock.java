package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class CleanEphedraSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<CleanEphedraSolutionCauldronBlock> CODEC = createCodec(CleanEphedraSolutionCauldronBlock::new);

    public CleanEphedraSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.CLEAN_EPHEDRA_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
