package com.mnsfhxy.johnny_s_biological_notes.entity.drifter;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
public class AnimationDrifter {


    public static final AnimationDefinition DRIFTERS_WALKING = AnimationDefinition.Builder.withLength(4.125f).looping().build();
    public static final AnimationDefinition DRIFTERS_ALERT_0 = AnimationDefinition.Builder.withLength(2.2083435f).looping()
            .addAnimation("arms",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("rightarm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(-5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("rightarm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-40f, 0f, 10f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(-45f, 0f, 7.5f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.degreeVec(-40f, 0f, 10f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("leftarm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("leftarm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -7.5f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(10f, 0f, -7.5f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 0f, -7.5f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition DRIFTERS_ALERT_1 = AnimationDefinition.Builder.withLength(4.208343f).looping()
            .addAnimation("arms",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("rightarm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(-4.2f, -1f, -1.6f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("rightarm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-40f, -35f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.degreeVec(-55f, -35f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.degreeVec(-40f, -35f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("leftarm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(4.5f, -1f, -0.6f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("leftarm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-57.5f, 35f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.degreeVec(-66.5f, 35f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.degreeVec(-57.5f, 35f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition DRIFTERS_FIGHT = AnimationDefinition.Builder.withLength(0.5f)
            .addAnimation("arms",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("rightarm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(-4.2f, -1f, -1.6f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(-5f, 0f, -2f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.4167667f, KeyframeAnimations.posVec(-4.2f, -1f, -1.6f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("rightarm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-40f, -35f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(-115f, -10f, -25f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.3433333f, KeyframeAnimations.degreeVec(-27.5f, -10f, -25f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.4167667f, KeyframeAnimations.degreeVec(-40f, -35f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("leftarm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(4.5f, -1f, -0.6f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(2.5f, 0f, -3.6f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.3433333f, KeyframeAnimations.posVec(4.3f, -0.4f, -0.4f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.4167667f, KeyframeAnimations.posVec(4.5f, -1f, -0.6f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("leftarm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-57.5f, 35f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(-127.5f, 45f, -20f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.3433333f, KeyframeAnimations.degreeVec(-60.83f, 60f, -29.17f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.4167667f, KeyframeAnimations.degreeVec(-57.5f, 35f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("body_1",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 22.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.3433333f, KeyframeAnimations.degreeVec(0f, -5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
}
