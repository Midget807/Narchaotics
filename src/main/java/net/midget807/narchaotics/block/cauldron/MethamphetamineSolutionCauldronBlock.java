package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class MethamphetamineSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<MethamphetamineSolutionCauldronBlock> CODEC = createCodec(MethamphetamineSolutionCauldronBlock::new);

    public MethamphetamineSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.METHAMPHETAMINE_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
