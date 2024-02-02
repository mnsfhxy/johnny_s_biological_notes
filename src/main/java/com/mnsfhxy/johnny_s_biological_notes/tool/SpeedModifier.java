package com.mnsfhxy.johnny_s_biological_notes.tool;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

/**
 * 移速修改器，因为修改之后还需要还原
 * 因此使用独立的类封装状态及其操作
 */
public class SpeedModifier {
    private AttributeInstance attribute;
    private AttributeModifier modifier;
    private double ratio;
    private String name;

    public SpeedModifier(AttributeInstance attribute, double ratio, String name) {
        this.attribute = attribute;
        this.ratio = ratio;
        this.name = name;
    }

    public void enable() {
        if (attribute != null) {
            this.modifier =
                    new AttributeModifier(this.name,
                            this.ratio,
                            AttributeModifier.Operation.MULTIPLY_TOTAL);
            attribute.addTransientModifier(modifier);
        }
    }

    public void disable() {
        if (attribute != null &&
                modifier != null) {
            attribute.removeModifier(modifier);
        }
    }
}