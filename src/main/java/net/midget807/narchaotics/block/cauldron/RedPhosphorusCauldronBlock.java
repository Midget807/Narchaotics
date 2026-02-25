package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class RedPhosphorusCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<RedPhosphorusCauldronBlock> CODEC = createCodec(RedPhosphorusCauldronBlock::new);

    public RedPhosphorusCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.RED_PHOSPHORUS_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
