package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class BrineCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<BrineCauldronBlock> CODEC = createCodec(BrineCauldronBlock::new);

    public BrineCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.BRINE_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
