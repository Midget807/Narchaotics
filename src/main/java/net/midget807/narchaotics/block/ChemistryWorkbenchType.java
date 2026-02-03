package net.midget807.narchaotics.block;

import net.minecraft.util.StringIdentifiable;

public enum ChemistryWorkbenchType implements StringIdentifiable {
    SINGLE("single"),
    LEFT("left"),
    RIGHT("right");

    private final String name;

    private ChemistryWorkbenchType(final String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public ChemistryWorkbenchType getOpposite() {
        return switch (this) {
            case SINGLE -> SINGLE;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
