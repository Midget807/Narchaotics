package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class SodiumCarbonateCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<SodiumCarbonateCauldronBlock> CODEC = createCodec(SodiumCarbonateCauldronBlock::new);

    public SodiumCarbonateCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.SODIUM_CARBONATE_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
