package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class SulphuricAcidSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<SulphuricAcidSolutionCauldronBlock> CODEC = createCodec(SulphuricAcidSolutionCauldronBlock::new);

    public SulphuricAcidSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.SULPHURIC_ACID_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
