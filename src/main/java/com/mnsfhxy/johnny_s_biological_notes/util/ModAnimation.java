package com.mnsfhxy.johnny_s_biological_notes.util;

import net.minecraft.world.entity.AnimationState;

import java.util.Timer;
import java.util.TimerTask;

public class ModAnimation extends AnimationState{

    private AnimationState animationState;
    private boolean isStart;
    public ModAnimation() {
        this.animationState = new AnimationState();
    }

    public ModAnimation(AnimationState animationState) {
        isStart = false;
        this.animationState = animationState;
    }

    public AnimationState getAnimationState() {
        isStart = false;
        return this.animationState;
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
    public void playOnce(int tickStart,int tickTime){
        if(animationState.isStarted())return;
        animationState.start(tickStart);
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(tickTime);
                    animationState.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
