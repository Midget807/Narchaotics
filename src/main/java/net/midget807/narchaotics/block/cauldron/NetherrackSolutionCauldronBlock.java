package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class NetherrackSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<NetherrackSolutionCauldronBlock> CODEC = createCodec(NetherrackSolutionCauldronBlock::new);

    public NetherrackSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.NETHERRACK_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
