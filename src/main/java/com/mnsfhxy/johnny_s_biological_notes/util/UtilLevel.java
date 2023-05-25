package com.mnsfhxy.johnny_s_biological_notes.util;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;

public class UtilLevel {


    //    public static int TIME_NIGHT
    public enum TIME{
        GAMEDAY(24000),
        SECOND(20),
        MINUTE(1200);
        private int tick;
        private TIME(int i) {
            this.tick = i;
        }

        public int getTick() {
            return tick;
        }
    }
    public enum DAY_TIME {
        DAY(1000),
        MIDNIGHT(18000),
        NIGHT(13000),
        NOON(6000);
        private int tick;

        private DAY_TIME(int i) {
            this.tick = i;
        }

        public int getTick() {
            return tick;
        }
    }
    public static  boolean isClientSide(){
        return Minecraft.getInstance().level!=null&&Minecraft.getInstance().level.isClientSide;
    }
    public static boolean isMidday(Level level) {

//        if (Minecraft.getInstance().level != null) JohnnySBiologicalNotes.LOGGER.info(String.valueOf(Minecraft.getInstance().level.getDayTime()));
//        else {
//            JohnnySBiologicalNotes.LOGGER.info("null");
//        } ;
//        JohnnySBiologicalNotes.LOGGER.info((Minecraft.getInstance().level==null)+"");
        return level != null && !level.dimensionType().hasFixedTime() && level.getDayTime() > DAY_TIME.NOON.getTick()&&level.getDayTime()< DAY_TIME.NIGHT.getTick();
    }
    public static boolean isNight(Level level) {

//        if (Minecraft.getInstance().level != null) JohnnySBiologicalNotes.LOGGER.info(String.valueOf(Minecraft.getInstance().level.getDayTime()));
//        else {
//            JohnnySBiologicalNotes.LOGGER.info("null");
//        } ;
//        JohnnySBiologicalNotes.LOGGER.info((Minecraft.getInstance().level==null)+"");
        return level != null && !level.dimensionType().hasFixedTime() && level.getDayTime() > DAY_TIME.NIGHT.getTick();
    }
    public static void info(String m){
        JohnnySBiologicalNotes.LOGGER.info(m);
    }
//    public <T extends IGetLevel> T clientExcute(T t){
//        if(t.hasLevel().isClientSide)return t;
//        else return null;
//    }
}
