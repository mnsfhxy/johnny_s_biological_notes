package com.mnsfhxy.johnny_s_biological_notes.entity.peeper;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class AnimationPeeper {

    public static final AnimationDefinition WALKING = AnimationDefinition.Builder.withLength(1f).

            looping()
                    .

            addAnimation("body",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0.15f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0.15f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR)))
                    .

            addAnimation("body",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 15f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, -15f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM)))
                    .

            addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.25f, KeyframeAnimations.posVec(-1f, -0.2f, 0.2f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.75f, KeyframeAnimations.posVec(1f, 0.2f, 0.2f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR)))
                    .

            addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 5f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, -5f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR)))
                    .

            addAnimation("legr",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 5f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, -5f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR)))
                    .

            addAnimation("legr",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(0.25f, KeyframeAnimations.degreeVec(20f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(0.75f, KeyframeAnimations.degreeVec(-20f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM)))
                    .

            addAnimation("legl",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, -5f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 5f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR)))
                    .

            addAnimation("legl",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(0.25f, KeyframeAnimations.degreeVec(-20f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(0.75f, KeyframeAnimations.degreeVec(20f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM))).

            build();

    public static final AnimationDefinition WAITING = AnimationDefinition.Builder.withLength(2.5f).

            looping()
                    .

            addAnimation("body",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(1.25f, KeyframeAnimations.posVec(0f, -0.4f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(2.5f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR)))
                    .

            addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(1.375f, KeyframeAnimations.posVec(0f, 0.3f, 0f),

                                    AnimationChannel.Interpolations.CATMULLROM),
                            new

                                    Keyframe(2.5f, KeyframeAnimations.posVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR)))
                    .

            addAnimation("legr",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(2.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR)))
                    .

            addAnimation("legl",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),

                                    AnimationChannel.Interpolations.LINEAR),
                            new

                                    Keyframe(2.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),

                                    AnimationChannel.Interpolations.LINEAR))).

            build();
}
