package net.midget807.narchaotics.block.cauldron;

import com.mojang.serialization.MapCodec;
import net.midget807.narchaotics.block.CustomCauldronBlock;
import net.midget807.narchaotics.registry.ModCustomCauldronBehaviours;
import net.minecraft.block.AbstractCauldronBlock;

public class EphedraSludgeCauldronBlock extends CustomCauldronBlock {
    public static final MapCodec<EphedraSludgeCauldronBlock> CODEC = createCodec(EphedraSludgeCauldronBlock::new);

    public EphedraSludgeCauldronBlock(Settings settings) {
        super(settings, ModCustomCauldronBehaviours.EPHEDRA_SLUDGE_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }
}
