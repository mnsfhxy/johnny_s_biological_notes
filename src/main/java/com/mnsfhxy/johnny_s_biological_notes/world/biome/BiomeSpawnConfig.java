package com.mnsfhxy.johnny_s_biological_notes.world.biome;

import com.google.common.collect.ImmutableList;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class BiomeSpawnConfig {

    SpawnData TridacnaShell=new SpawnData(
            RegistrationInit.TRIDACNA.get(),
            ImmutableList.of(
                    Biomes.LUKEWARM_OCEAN,
                    Biomes.WARM_OCEAN,
                    Biomes.DEEP_LUKEWARM_OCEAN
            ),
            1,
            1,
            10
    );


    class SpawnData{
        ImmutableList<ResourceKey<Biome>> SpawnBiomes;
        EntityType<?> Entity;
        int minCount;
        int maxCount;
        int weight;

        public SpawnData( EntityType<?> entity,ImmutableList<ResourceKey<Biome>> spawnBiomes, int minCount, int maxCount, int weight) {
            SpawnBiomes = spawnBiomes;
            Entity = entity;
            this.minCount = minCount;
            this.maxCount = maxCount;
            this.weight = weight;
        }
    }
}
