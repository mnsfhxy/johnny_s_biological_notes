package com.mnsfhxy.johnny_s_biological_notes.capability.spirit;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;

public class PlayerSpirit {
    private int spirit;
    private final int MAX_SPIRIT=5;
    private final int MIN_SPIRIT=0;
    private final RandomSource random = RandomSource.create();
    public void addSpirit(){
        int add= random.nextInt(2)+1;
        this.spirit=Math.min(spirit+add,MAX_SPIRIT);
    }
    public void cleanSpirit(){
        this.spirit=MIN_SPIRIT;
    }
    public int getSpirit() {
        return spirit;
    }
    public boolean isMax(){
        return this.spirit==MAX_SPIRIT;
    }

    public void saveNBTData(CompoundTag compound) {
        compound.putInt("spirit",spirit );
    }
    public void copyFrom(PlayerSpirit source){
        spirit=source.spirit;
    }
    public void loadNBTData(CompoundTag compound) {
        spirit = compound.getInt("spirit");
    }
}
