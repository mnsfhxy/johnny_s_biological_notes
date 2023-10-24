package com.mnsfhxy.johnny_s_biological_notes.world.biome;

import com.google.common.collect.ImmutableList;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

//反射调用
public class BiomeSpawnConfig {

    final ModSpawnData TRIDACNA=new ModSpawnData(
            RegistrationInit.TRIDACNA.get(),
            ImmutableList.of(
                    Biomes.LUKEWARM_OCEAN,
                    Biomes.WARM_OCEAN,
                    Biomes.DEEP_LUKEWARM_OCEAN
            ),
            50,
            1,
            1
    );
    final ModSpawnData LOITER=new ModSpawnData(
            RegistrationInit.LOITER.get(),
            ImmutableList.of(
                    Biomes.SOUL_SAND_VALLEY
            ),
            45,
            1,
            3
    );
//    final ModSpawnData Crab=new ModSpawnData(
//      RegistrationInit.CRAB.get(),
//            MobCategory.WATER_CREATURE,
//            ImmutableList.of(
//
//            ),
//            20,
//            2,5
//
//    );


}
