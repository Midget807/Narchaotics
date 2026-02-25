package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class CrystalisedSaltSolutionCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<CrystalisedSaltSolutionCauldronBlock> CODEC = createCodec(CrystalisedSaltSolutionCauldronBlock::new);

    public CrystalisedSaltSolutionCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.CRYSTALISED_SALT_SOLUTION_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
