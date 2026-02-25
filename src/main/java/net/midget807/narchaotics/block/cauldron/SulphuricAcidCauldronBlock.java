package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class SulphuricAcidCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<SulphuricAcidCauldronBlock> CODEC = createCodec(SulphuricAcidCauldronBlock::new);

    public SulphuricAcidCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.SULPHURIC_ACID_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
