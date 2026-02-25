package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class SaltSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<SaltSolutionCauldronBlock> CODEC = createCodec(SaltSolutionCauldronBlock::new);

    public SaltSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.SALT_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
