package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class HydrogenPeroxideCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<HydrogenPeroxideCauldronBlock> CODEC = createCodec(HydrogenPeroxideCauldronBlock::new);

    public HydrogenPeroxideCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.HYDROGEN_PEROXIDE_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
