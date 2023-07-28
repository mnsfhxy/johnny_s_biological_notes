package com.mnsfhxy.johnny_s_biological_notes.util;

import net.minecraft.world.entity.AnimationState;

public class UtilAnimation {

    public static void startIfNotStart(AnimationState animationState,int tick){

        animationState.start(tick);


    }

}
