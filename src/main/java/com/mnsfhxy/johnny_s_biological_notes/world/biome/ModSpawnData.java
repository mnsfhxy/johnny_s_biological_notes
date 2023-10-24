package com.mnsfhxy.johnny_s_biological_notes.world.biome;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class ModSpawnData{
    ImmutableList<ResourceKey<Biome>> SpawnBiomes;
    MobSpawnSettings.SpawnerData spawnerData;
    MobCategory mobCategory;
    public ModSpawnData( EntityType<?> entity,MobCategory mobCategory,ImmutableList<ResourceKey<Biome>> spawnBiomes, int weight,int minCount, int maxCount) {
        SpawnBiomes = spawnBiomes;
        this.mobCategory=mobCategory;
        spawnerData=new MobSpawnSettings.SpawnerData(entity,weight,minCount,maxCount);
    }
    public ModSpawnData( EntityType<?> entity,ImmutableList<ResourceKey<Biome>> spawnBiomes, int weight,int minCount, int maxCount) {
        SpawnBiomes = spawnBiomes;
        this.mobCategory=entity.getCategory();
        spawnerData=new MobSpawnSettings.SpawnerData(entity,weight,minCount,maxCount);
    }
    public MobCategory getMobCategory() {
        return mobCategory;
    }

    public void setMobCategory(MobCategory mobCategory) {
        this.mobCategory = mobCategory;
    }

    public ImmutableList<ResourceKey<Biome>> getSpawnBiomes() {
        return SpawnBiomes;
    }

    public void setSpawnBiomes(ImmutableList<ResourceKey<Biome>> spawnBiomes) {
        SpawnBiomes = spawnBiomes;
    }

    public MobSpawnSettings.SpawnerData getSpawnerData() {
        return spawnerData;
    }

    public void setSpawnerData(MobSpawnSettings.SpawnerData spawnerData) {
        this.spawnerData = spawnerData;
    }
}
