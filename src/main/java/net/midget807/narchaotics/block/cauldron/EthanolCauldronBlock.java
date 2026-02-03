package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class EthanolCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<EthanolCauldronBlock> CODEC = createCodec(EthanolCauldronBlock::new);

    public EthanolCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.ETHANOL_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
