package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class AlkalineEphedraSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<AlkalineEphedraSolutionCauldronBlock> CODEC = createCodec(AlkalineEphedraSolutionCauldronBlock::new);

    public AlkalineEphedraSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.ALKALINE_EPHEDRA_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
