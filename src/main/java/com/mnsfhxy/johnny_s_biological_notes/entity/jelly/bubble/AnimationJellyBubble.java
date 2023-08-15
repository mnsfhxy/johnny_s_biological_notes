package com.mnsfhxy.johnny_s_biological_notes.entity.jelly.bubble;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class AnimationJellyBubble {


    public static final AnimationDefinition JELLY_BUBBLE = AnimationDefinition.Builder.withLength(28f).looping()
            .addAnimation("jelly_bubble_core",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(14f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(21f, KeyframeAnimations.posVec(0f, -0.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(28f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("jelly_bubble_core",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(14f, KeyframeAnimations.degreeVec(0f, -180f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(28f, KeyframeAnimations.degreeVec(0f, -360f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
}
