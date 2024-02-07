package com.mnsfhxy.johnny_s_biological_notes.world.biome;

import com.google.common.collect.ImmutableList;
import com.mnsfhxy.johnny_s_biological_notes.config.Config;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.EntityBeluga;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.EntityCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mnsfhxy.johnny_s_biological_notes.entity.loiter.EntityLoiter;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.EntityPeeper;
import com.mnsfhxy.johnny_s_biological_notes.entity.tridacna.EntityTridacna;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.spawn.SpawnPlacementsType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

//反射调用
public class BiomeSpawnConfig {

    final ModSpawnData<EntityTridacna> TRIDACNA=new ModSpawnData<>(
            RegistrationInit.TRIDACNA.get(),
            ImmutableList.of(
                    Biomes.LUKEWARM_OCEAN,
                    Biomes.WARM_OCEAN,
                    Biomes.DEEP_LUKEWARM_OCEAN
            ),
            50,
            1,
            1,
            SpawnPlacementsType.SPAWN_ON_WATER_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            EntityTridacna::checkSpawnRules
    );
    final ModSpawnData<EntityLoiter> LOITER=new ModSpawnData<>(
            RegistrationInit.LOITER.get(),
            ImmutableList.of(
                    Biomes.SOUL_SAND_VALLEY
            ),
            56,
            1,
            1,
            SpawnPlacements.Type.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            EntityLoiter::checkLoiterSpawnRules
    );
    final ModSpawnData<EntityCrab> CRAB=new ModSpawnData<>(
            RegistrationInit.CRAB.get(),
            ImmutableList.of(
//                    Biomes.DEEP_COLD_OCEAN,
//                    Biomes.FROZEN_OCEAN,
//                    Biomes.WARM_OCEAN,
//                    Biomes.OCEAN,
//                    Biomes.LUKEWARM_OCEAN,
//                    Biomes.DEEP_LUKEWARM_OCEAN,
//                    Biomes.COLD_OCEAN,
//                    Biomes.DEEP_OCEAN,
//                    Biomes.DEEP_FROZEN_OCEAN,
                    Biomes.BEACH,
                    Biomes.MANGROVE_SWAMP
            ),
            50,
            1,
            3,
            SpawnPlacements.Type.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            EntityCrab::checkCrabSpawnRules
    );
    final ModSpawnData<EntityPeeper> PEEPER=new ModSpawnData<>(
            RegistrationInit.PEEPER.get(),
            ImmutableList.of(
                    Biomes.DARK_FOREST
            ),
            100,
            1,
            1,
            SpawnPlacements.Type.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            (entityType, world, reason, pos, random) -> ( Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random))
    );
    final ModSpawnData<EntityJelly> JELLY=new ModSpawnData<>(
            RegistrationInit.JELLY.get(),
            ImmutableList.of(
                    Biomes.THE_VOID,
                    Biomes.PLAINS,
                    Biomes.SUNFLOWER_PLAINS,
                    Biomes.SNOWY_PLAINS,
                    Biomes.ICE_SPIKES,
                    Biomes.DESERT,
                    Biomes.SWAMP,
                    Biomes.MANGROVE_SWAMP,
                    Biomes.FOREST,
                    Biomes.FLOWER_FOREST,
                    Biomes.BIRCH_FOREST,
                    Biomes.DARK_FOREST,
                    Biomes.OLD_GROWTH_BIRCH_FOREST,
                    Biomes.OLD_GROWTH_PINE_TAIGA,
                    Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                    Biomes.TAIGA,
                    Biomes.SNOWY_TAIGA,
                    Biomes.SAVANNA,
                    Biomes.SAVANNA_PLATEAU,
                    Biomes.WINDSWEPT_HILLS,
                    Biomes.WINDSWEPT_GRAVELLY_HILLS,
                    Biomes.WINDSWEPT_FOREST,
                    Biomes.WINDSWEPT_SAVANNA,
                    Biomes.JUNGLE,
                    Biomes.SPARSE_JUNGLE,
                    Biomes.BAMBOO_JUNGLE,
                    Biomes.BADLANDS,
                    Biomes.ERODED_BADLANDS,
                    Biomes.WOODED_BADLANDS,
                    Biomes.MEADOW,
                    Biomes.GROVE,
                    Biomes.SNOWY_BEACH,
                    Biomes.FROZEN_PEAKS,
                    Biomes.JAGGED_PEAKS,
                    Biomes.STONY_PEAKS,
                    Biomes.RIVER,
                    Biomes.FROZEN_RIVER,
                    Biomes.BEACH,
                    Biomes.SNOWY_BEACH,
                    Biomes.SNOWY_PLAINS,
                    Biomes.STONY_SHORE,
                    Biomes.WARM_OCEAN,
                    Biomes.LUKEWARM_OCEAN,
                    Biomes.DEEP_LUKEWARM_OCEAN,
                    Biomes.OCEAN,
                    Biomes.DEEP_OCEAN,
                    Biomes.COLD_OCEAN,
                    Biomes.DEEP_COLD_OCEAN,
                    Biomes.FROZEN_OCEAN,
                    Biomes.DEEP_FROZEN_OCEAN,
                    Biomes.DRIPSTONE_CAVES,
                    Biomes.LUSH_CAVES,
                    Biomes.SMALL_END_ISLANDS
            ),
            50,
            1,
            6,
            SpawnPlacements.Type.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            EntityJelly::checkJellySpawnRules
    );
    final ModSpawnData<EntityBeluga> BELUGA=new ModSpawnData<>(
            RegistrationInit.BELUGA.get(),
            ImmutableList.of(
                    Biomes.COLD_OCEAN,
                    Biomes.DEEP_COLD_OCEAN,
                    Biomes.FROZEN_OCEAN,
                    Biomes.DEEP_FROZEN_OCEAN
            ),
            Config.getInstance().intValueOf("entity.beluga.spawn.weight"),
            Config.getInstance().intValueOf("entity.beluga.spawn.min"),
            Config.getInstance().intValueOf("entity.beluga.spawn.max"),
            SpawnPlacements.Type.IN_WATER,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            EntityBeluga::checkSurfaceWaterAnimalSpawnRules
    );
}
