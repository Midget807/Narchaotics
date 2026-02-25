package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class SaltWaterCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<SaltWaterCauldronBlock> CODEC = createCodec(SaltWaterCauldronBlock::new);

    public SaltWaterCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.SALT_WATER_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
