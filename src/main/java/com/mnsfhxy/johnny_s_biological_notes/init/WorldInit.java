package com.mnsfhxy.johnny_s_biological_notes.init;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.config.ConfigBiome;
import com.mnsfhxy.johnny_s_biological_notes.config.ConfigSpawn;
import com.mnsfhxy.johnny_s_biological_notes.config.biome.SpawnBiomeData;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.common.world.ModifiableStructureInfo;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = JohnnySBiologicalNotes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldInit {

//    public static void modifyStructure(Holder<Structure> structure, ModifiableStructureInfo.StructureInfo.Builder builder) {
//        if (structure.is(BuiltinStructures.END_CITY) && AMConfig.mimicubeSpawnInEndCity && AMConfig.mimicubeSpawnWeight > 0) {
//            builder.getStructureSettings().getOrAddSpawnOverrides(MobCategory.MONSTER).addSpawn(new MobSpawnSettings.SpawnerData(AMEntityRegistry.MIMICUBE.get(), AMConfig.mimicubeSpawnWeight, 1, 3));
//        }
//        if (structure.is(BuiltinStructures.NETHER_FOSSIL) && AMConfig.soulVultureSpawnOnFossil && AMConfig.soulVultureSpawnWeight > 0) {
//            builder.getStructureSettings().getOrAddSpawnOverrides(MobCategory.MONSTER).addSpawn(new MobSpawnSettings.SpawnerData(AMEntityRegistry.SOUL_VULTURE.get(), AMConfig.soulVultureSpawnWeight, 1, 1));
//        }
//        if (structure.is(BuiltinStructures.SHIPWRECK) && AMConfig.restrictSkelewagSpawns && AMConfig.skelewagSpawnWeight > 0) {
//            builder.getStructureSettings().getOrAddSpawnOverrides(MobCategory.MONSTER).addSpawn(new MobSpawnSettings.SpawnerData(AMEntityRegistry.SKELEWAG.get(), AMConfig.skelewagSpawnWeight, 1, 2));
//        }
//        if (structure.is(AMTagRegistry.SPAWNS_UNDERMINERS) && AMConfig.restrictUnderminerSpawns && AMConfig.underminerSpawnWeight > 0) {
//            builder.getStructureSettings().getOrAddSpawnOverrides(MobCategory.AMBIENT).addSpawn(new MobSpawnSettings.SpawnerData(AMEntityRegistry.UNDERMINER.get(), AMConfig.underminerSpawnWeight, 1, 1));
//        }
//    }

    private static ResourceLocation getBiomeName(Holder<Biome> biome) {
        return biome.unwrap().map((resourceKey) -> resourceKey.location(), (noKey) -> null);
    }

    public static boolean testBiome(Pair<String, SpawnBiomeData> entry, Holder<Biome> biome) {
        boolean result = false;
        try {
            result = ConfigBiome.test(entry, biome, getBiomeName(biome));
        } catch (Exception e) {
            JohnnySBiologicalNotes.LOGGER.warn("could not test biome config for " + entry.getLeft() + ", defaulting to no spawns for mob");
            result = false;
        }
        return result;
    }

    public static void addBiomeSpawns(Holder<Biome> biome, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (testBiome(ConfigBiome.peeper, biome) && ConfigSpawn.SpawnWeightPeeper > 0) {
            builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(RegistrationInit.PEEPER.get(), ConfigSpawn.SpawnWeightPeeper, 1, 1));
        }
        if (testBiome(ConfigBiome.crab, biome) && ConfigSpawn.SpawnWeightCrab > 0) {
            builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(RegistrationInit.CRAB.get(), ConfigSpawn.SpawnWeightCrab, 2, 5));
        }

    }

}
