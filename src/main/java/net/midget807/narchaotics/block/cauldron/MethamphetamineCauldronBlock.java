package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class MethamphetamineCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<MethamphetamineCauldronBlock> CODEC = createCodec(MethamphetamineCauldronBlock::new);

    public MethamphetamineCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.METHAMPHETAMINE_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
