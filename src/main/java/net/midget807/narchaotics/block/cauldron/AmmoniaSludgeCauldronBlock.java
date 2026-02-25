package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class AmmoniaSludgeCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<AmmoniaSludgeCauldronBlock> CODEC = createCodec(AmmoniaSludgeCauldronBlock::new);

    public AmmoniaSludgeCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.AMMONIA_SLUDGE_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
