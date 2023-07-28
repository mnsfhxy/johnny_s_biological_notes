package com.mnsfhxy.johnny_s_biological_notes.util;

import net.minecraft.world.entity.AnimationState;

public class ModAnimation {
    private boolean isStart;
    private AnimationState animationState;

    public ModAnimation() {
        isStart = false;
        this.animationState = new AnimationState();
    }

    public ModAnimation(AnimationState animationState) {
        isStart = false;
        this.animationState = animationState;
    }

    public void start(int tick) {
        if (!isStart) {
            isStart = true;
            animationState.start(tick);
        }
    }

    public void stop() {
        if (isStart) {
            isStart=false;
            animationState.stop();
        }
    }

    public AnimationState getAnimationState() {
        return this.animationState;

    }

}
