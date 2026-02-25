package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class AmmoniaCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<AmmoniaCauldronBlock> CODEC = createCodec(AmmoniaCauldronBlock::new);

    public AmmoniaCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.AMMONIA_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
