package net.midget807.narchaotics.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

public class ModEmiUtils {

    public static MutableText translatable(String s, Object... objects) {
        return Text.translatable(s, objects);
    }

    public static MutableText append(MutableText text, Text appended) {
        return text.append(appended);
    }

    public static OrderedText ordered(Text text) {
        return text.asOrderedText();
    }
}
