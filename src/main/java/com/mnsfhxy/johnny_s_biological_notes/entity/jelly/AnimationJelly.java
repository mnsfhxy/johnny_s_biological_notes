package com.mnsfhxy.johnny_s_biological_notes.entity.jelly;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class AnimationJelly {


    public static final AnimationDefinition JELLY_MOVING = AnimationDefinition.Builder.withLength(2f).looping()
.addAnimation("tentacle",
                      new AnimationChannel(AnimationChannel.Targets.SCALE,
		new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1f, KeyframeAnimations.scaleVec(1.15f, 1f, 1.15f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1.375f, KeyframeAnimations.scaleVec(0.7f, 1f, 0.7f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(2f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("oral_arms",
                                  new AnimationChannel(AnimationChannel.Targets.POSITION,
		new Keyframe(0.8343334f, KeyframeAnimations.posVec(0f, 0f, 0f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1.3433333f, KeyframeAnimations.posVec(0f, -1.4f, 0f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0f, 0f),
    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("oral_arms",
                                  new AnimationChannel(AnimationChannel.Targets.SCALE,
		new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.8343334f, KeyframeAnimations.scaleVec(1.08f, 1f, 1.08f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1.3433333f, KeyframeAnimations.scaleVec(0.5f, 1.2f, 0.5f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(2f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("core",
                                  new AnimationChannel(AnimationChannel.Targets.POSITION,
		new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1f, KeyframeAnimations.posVec(0f, -0.8f, 0f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1.5416767f, KeyframeAnimations.posVec(0f, 0.4f, 0f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0f, 0f),
    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bell_0",
                                  new AnimationChannel(AnimationChannel.Targets.POSITION,
		new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1f, KeyframeAnimations.posVec(0f, -1.1f, 0f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1.375f, KeyframeAnimations.posVec(0f, 2.1f, 0f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0f, 0f),
    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bell_0",
                                  new AnimationChannel(AnimationChannel.Targets.SCALE,
		new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1f, KeyframeAnimations.scaleVec(1.15f, 1.15f, 1.15f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1.375f, KeyframeAnimations.scaleVec(0.7f, 0.7f, 0.7f),
    AnimationChannel.Interpolations.LINEAR),
            new Keyframe(2f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
    AnimationChannel.Interpolations.LINEAR))).build();
}
