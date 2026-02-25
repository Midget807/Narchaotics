package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class RedPhosphorusSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<RedPhosphorusSolutionCauldronBlock> CODEC = createCodec(RedPhosphorusSolutionCauldronBlock::new);

    public RedPhosphorusSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.RED_PHOSPHORUS_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
