package com.mnsfhxy.johnny_s_biological_notes.world.biome;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModSpawnData<T extends Mob> {
    EntityType<T> entityType;
    ImmutableList<ResourceKey<Biome>> SpawnBiomes;
    MobSpawnSettings.SpawnerData spawnerData;
    MobCategory mobCategory;
    SpawnPlacements.Type spawnPlacementType;
    Heightmap.Types heightMapType;
    SpawnPlacements.SpawnPredicate<T> predicate;
    public  ModSpawnData(EntityType<T> entity, MobCategory mobCategory, ImmutableList<ResourceKey<Biome>> spawnBiomes, int weight, int minCount, int maxCount, SpawnPlacements.Type pDecoratorType, Heightmap.Types pHeightMapType, SpawnPlacements.SpawnPredicate<T> pDecoratorPredicate) {
        SpawnBiomes = spawnBiomes;
        this.mobCategory=mobCategory;
        this.entityType=entity;
        spawnerData=new MobSpawnSettings.SpawnerData(entity,weight,minCount,maxCount);
        spawnPlacementType=pDecoratorType;
        heightMapType=pHeightMapType;
        predicate=pDecoratorPredicate;
    }
    public  ModSpawnData( EntityType<T> entity,ImmutableList<ResourceKey<Biome>> spawnBiomes, int weight,int minCount, int maxCount, SpawnPlacements.Type pDecoratorType, Heightmap.Types pHeightMapType, SpawnPlacements.SpawnPredicate<T> pDecoratorPredicate) {
        SpawnBiomes = spawnBiomes;
        this.entityType=entity;
        this.mobCategory=entity.getCategory();
        spawnerData=new MobSpawnSettings.SpawnerData(entity,weight,minCount,maxCount);
        spawnPlacementType=pDecoratorType;
        heightMapType=pHeightMapType;
        predicate=pDecoratorPredicate;
    }

    public EntityType<T> getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType<T> entityType) {
        this.entityType = entityType;
    }

    public SpawnPlacements.Type getSpawnPlacementType() {
        return spawnPlacementType;
    }

    public void setSpawnPlacementType(SpawnPlacements.Type spawnPlacementType) {
        this.spawnPlacementType = spawnPlacementType;
    }

    public Heightmap.Types getHeightMapType() {
        return heightMapType;
    }

    public void setHeightMapType(Heightmap.Types heightMapType) {
        this.heightMapType = heightMapType;
    }

    public SpawnPlacements.SpawnPredicate<T> getPredicate() {
        return predicate;
    }

    public void setPredicate(SpawnPlacements.SpawnPredicate<T> predicate) {
        this.predicate = predicate;
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
