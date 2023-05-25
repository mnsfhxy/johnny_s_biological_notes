package com.mnsfhxy.johnny_s_biological_notes.util;

import com.google.gson.Gson;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;


public class UtilSoundJsonGen {
    private final File file = new File("I:\\CODE\\MINECRAFT\\johnny-s-biological-notes\\src\\main\\resources\\assets\\johnny_s_biological_notes\\sounds.json");
    //    private final String fileExtension
    public static final String namespace = JohnnySBiologicalNotes.MODID;

    public static void main(String[] args) {
        new UtilSoundJsonGen().gen();
    }
    public static String getNamespace() {
        return namespace;
    }

    public void gen() {
        SoundsJson soundsJson = new SoundsJson()
                .simpleAdd("crab_bubble", 1)
                .simpleAdd("crab_drop_shell", 1)
                .simpleAdd("crab_hurt",2)
                .simpleAdd("crab_talon",2)
                .simpleAdd("crab_walking",1)
                .simpleAdd("crab_dig",1);

        Gson gson = new Gson();
        String json = gson.toJson(soundsJson.soundsJson);
        try  {
            if(!file.exists())file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class SoundsJson {
        Map<String, SoundEvent> soundsJson;

        public SoundsJson() {
            this.soundsJson = new HashMap<>();
        }

        public  SoundsJson add(String soundName, SoundEvent soundEvent) {
            soundsJson.put(soundName, soundEvent);
            return this;
        }

        ;

        public SoundsJson simpleAdd(String soundName, int soundNum) {
            SoundEvent soundEvent = new SoundEvent(soundName).num(soundNum);
            soundsJson.put(soundName, soundEvent);
            return this;

        }
    }

    private class Sound {
        private String name;

        public Sound(String name) {
            this.name = getNamespace() + ":" + name;
        }
    }

    private class SoundEvent {
        private String category;
        private List<Sound> sounds;
        private String subtitle;

        public SoundEvent(String name) {
            this.category = Category.NEUTRAL.getName();
            this.subtitle = UtilSoundJsonGen.getNamespace() + ".sound.subtitle." + name;
            this.sounds = new ArrayList<>();
        }

        public SoundEvent(String name, Category category) {
            this.category = category.getName();
            this.subtitle = UtilSoundJsonGen.getNamespace() + ".sound.subtitle." + name;
            this.sounds = new ArrayList<>();
        }

        public SoundEvent add(String fileNameWithoutSuffix) {
            sounds.add(new Sound(fileNameWithoutSuffix));
            return this;
        }

        public SoundEvent num(int n) {
            String name = this.subtitle.replace(UtilSoundJsonGen.getNamespace() + ".sound.subtitle.", "");
            for (int i = 0; i < n; i++) {
                sounds.add(new Sound(name + "_" + String.valueOf(i)));
            }
            return this;
        }

    }

    private enum Category {
        //        MASTER("master"),
//        MUSIC("music"),
//        RECORDS("record"),
//        WEATHER("weather"),
//        BLOCKS("block"),
//        HOSTILE("hostile"),
//        VOICE("voice"),
//        PLAYERS("player"),
//        AMBIENT("ambient"),
        NEUTRAL("neutral");

        private final String name;

        Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
