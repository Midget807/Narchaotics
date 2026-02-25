package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class SodiumCarbonateSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<SodiumCarbonateSolutionCauldronBlock> CODEC = createCodec(SodiumCarbonateSolutionCauldronBlock::new);

    public SodiumCarbonateSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.SODIUM_CARBONATE_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
