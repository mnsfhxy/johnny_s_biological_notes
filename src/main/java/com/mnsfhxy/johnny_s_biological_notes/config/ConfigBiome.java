package com.mnsfhxy.johnny_s_biological_notes.config;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.config.biome.BiomeEntryType;
import com.mnsfhxy.johnny_s_biological_notes.config.biome.SpawnBiomeData;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ConfigBiome {
    //    public static Pair<String, SpawnBiomeData>
    public static final SpawnBiomeData EMPTY = new SpawnBiomeData();

    public static final SpawnBiomeData PeeperBiome = new SpawnBiomeData()
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:dark_forest", 0);
    public static final SpawnBiomeData CrabBiome = new SpawnBiomeData()
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:deep_cold_ocean", 0)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:frozen_ocean", 1)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:warm_ocean", 2)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:ocean", 3)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:lukewarm_ocean", 4)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:deep_lukewarm_ocean", 5)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:cold_ocean", 6)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:deep_ocean", 7)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:deep_frozen_ocean", 8)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:beach", 9)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:mangrove_swamp", 10);


    public static Pair<String, SpawnBiomeData> peeper = Pair.of(JohnnySBiologicalNotes.MODID + ":peeper_spawns", PeeperBiome);
    public static Pair<String, SpawnBiomeData> crab = Pair.of(JohnnySBiologicalNotes.MODID + ":crab_spawns", CrabBiome);

    private static Map<String, SpawnBiomeData> biomeConfigValues = new HashMap<>();
    private static boolean init = false;

    public static void init() {

        try {
            for (Field f : ConfigBiome.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Pair) {
                    String id = (String) ((Pair) obj).getLeft();
                    SpawnBiomeData data = (SpawnBiomeData) ((Pair) obj).getRight();
//                    biomeConfigValues.put(id, SpawnBiomeConfig.create(new ResourceLocation(id), data));
                    biomeConfigValues.put(id, data);
                }
            }
        } catch (Exception e) {
            JohnnySBiologicalNotes.LOGGER.warn("Encountered error building johnnymobs biome config .json files");
            e.printStackTrace();
        }
        init = true;
    }

    public static boolean test(Pair<String, SpawnBiomeData> entry, Holder<Biome> biome, ResourceLocation name) {
        if (!init) {
            return false;
        }
        return biomeConfigValues.get(entry.getKey()).matches(biome, name);
    }

    public static boolean test(Pair<String, SpawnBiomeData> spawns, Holder<Biome> biome) {
        return test(spawns, biome, ForgeRegistries.BIOMES.getKey(biome.value()));
    }
}
