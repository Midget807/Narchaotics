package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class IodineCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<IodineCauldronBlock> CODEC = createCodec(IodineCauldronBlock::new);

    public IodineCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.IODINE_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
