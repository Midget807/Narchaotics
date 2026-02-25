package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class HydroiodicAcidCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<HydroiodicAcidCauldronBlock> CODEC = createCodec(HydroiodicAcidCauldronBlock::new);

    public HydroiodicAcidCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.HYDROIODIC_ACID_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
