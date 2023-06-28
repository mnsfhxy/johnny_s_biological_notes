package com.mnsfhxy.johnny_s_biological_notes.config.biome;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.biome.Biome;

public class SpawnBiomeData {
    private List<List<SpawnBiomeData.SpawnBiomeEntry>> biomes = new ArrayList();

    public SpawnBiomeData() {
    }

    private SpawnBiomeData(SpawnBiomeData.SpawnBiomeEntry[][] biomesRead) {
        this.biomes = new ArrayList();
        SpawnBiomeData.SpawnBiomeEntry[][] var2 = biomesRead;
        int var3 = biomesRead.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            SpawnBiomeData.SpawnBiomeEntry[] innerArray = var2[var4];
            this.biomes.add(Arrays.asList(innerArray));
        }

    }

    public SpawnBiomeData addBiomeEntry(BiomeEntryType type, boolean negate, String value, int pool) {
        if (this.biomes.isEmpty() || this.biomes.size() < pool + 1) {
            this.biomes.add(new ArrayList());
        }

        ((List)this.biomes.get(pool)).add(new SpawnBiomeData.SpawnBiomeEntry(type, negate, value));
        return this;
    }

    public boolean matches(@Nullable Holder<Biome> biomeHolder, ResourceLocation registryName) {
        Iterator var3 = this.biomes.iterator();

        boolean overall;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            List<SpawnBiomeData.SpawnBiomeEntry> all = (List)var3.next();
            overall = true;
            Iterator var6 = all.iterator();

            while(var6.hasNext()) {
                SpawnBiomeData.SpawnBiomeEntry cond = (SpawnBiomeData.SpawnBiomeEntry)var6.next();
                if (!cond.matches(biomeHolder, registryName)) {
                    overall = false;
                }
            }
        } while(!overall);

        return true;
    }

    private class SpawnBiomeEntry {
        BiomeEntryType type;
        boolean negate;
        String value;

        public SpawnBiomeEntry(BiomeEntryType type, boolean remove, String value) {
            this.type = type;
            this.negate = remove;
            this.value = value;
        }

        public boolean matches(@Nullable Holder<Biome> biomeHolder, ResourceLocation registryName) {
            if (this.type.isDepreciated()) {
                JohnnySBiologicalNotes.LOGGER.warn("biome config: BIOME_DICT and BIOME_CATEGORY are no longer valid in 1.19+. Please use BIOME_TAG instead.");
                return false;
            } else if (this.type == BiomeEntryType.BIOME_TAG) {
                if (biomeHolder.getTagKeys().anyMatch((biomeTagKey) -> {
                    return biomeTagKey.location() != null && biomeTagKey.location().toString().equals(this.value);
                })) {
                    return !this.negate;
                } else {
                    return this.negate;
                }
            } else if (registryName.toString().equals(this.value)) {
                return !this.negate;
            } else {
                return this.negate;
            }
        }
    }

    public static class Deserializer implements JsonDeserializer<SpawnBiomeData>, JsonSerializer<SpawnBiomeData> {
        public Deserializer() {
        }

        public SpawnBiomeData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonobject = json.getAsJsonObject();
            SpawnBiomeData.SpawnBiomeEntry[][] biomesRead = (SpawnBiomeData.SpawnBiomeEntry[][])GsonHelper.getAsObject(jsonobject, "biomes", new SpawnBiomeData.SpawnBiomeEntry[0][0], context, SpawnBiomeData.SpawnBiomeEntry[][].class);
            return new SpawnBiomeData(biomesRead);
        }

        public JsonElement serialize(SpawnBiomeData src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonobject = new JsonObject();
            jsonobject.add("biomes", context.serialize(src.biomes));
            return jsonobject;
        }
    }
}

