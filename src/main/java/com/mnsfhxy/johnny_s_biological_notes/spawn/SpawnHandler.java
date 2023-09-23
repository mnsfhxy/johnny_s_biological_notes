package com.mnsfhxy.johnny_s_biological_notes.spawn;

import com.google.gson.JsonElement;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpawnHandler {
    public static BiomeModifier createSpawnModifier(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData... spawnerData) {
        return (spawnerData.length == 1)
                ? ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(biomes, spawnerData[0])
                : new ForgeBiomeModifiers.AddSpawnsBiomeModifier(biomes, Arrays.stream(spawnerData).toList());
    }

    @SafeVarargs
    public static HolderSet<Biome> getBiomeHolderSet(Registry<Biome> registry, ResourceKey<Biome>... biomes) {
        return HolderSet.direct(Arrays.stream(biomes).map(registry::getHolderOrThrow).toList());
    }

    public static void datagenBiomeModifiers(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        RegistryAccess registryAccess = RegistryAccess.builtinCopy();
        Registry<Biome> registry = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);

        Map<ResourceLocation, BiomeModifier> modifierMap = new HashMap<>();
//        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_warm_ocean_spawns"),
//                createSpawnModifier(getBiomeHolderSet(registry, Biomes.WARM_OCEAN),
//                        new MobSpawnSettings.SpawnerData(RegistrationInit.TRIDACNA.get(), 32, 1, 1)
//                        , new MobSpawnSettings.SpawnerData(RegistrationInit.CRAB.get(), 32, 1, 1)
//
//                )
//        );
//        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_lukewarm_ocean_spawns"),
//                createSpawnModifier(getBiomeHolderSet(registry, Biomes.LUKEWARM_OCEAN),
//                        new MobSpawnSettings.SpawnerData(RegistrationInit.TRIDACNA.get(), 32, 1, 1)
//                        , new MobSpawnSettings.SpawnerData(RegistrationInit.CRAB.get(), 32, 1, 1)
//
//                )
//        );
//        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_deep_lukewarm_ocean_spawns"),
//                createSpawnModifier(getBiomeHolderSet(registry, Biomes.DEEP_LUKEWARM_OCEAN),
//                        new MobSpawnSettings.SpawnerData(RegistrationInit.TRIDACNA.get(), 32, 1, 1)
//                        , new MobSpawnSettings.SpawnerData(RegistrationInit.CRAB.get(), 32, 1, 1)
//
//                )
//        );
//
//        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_deep_cold_ocean_spawns"),
//                createSpawnModifier(getBiomeHolderSet(registry, Biomes.DEEP_COLD_OCEAN),
//                        new MobSpawnSettings.SpawnerData(RegistrationInit.CRAB.get(), 32, 1, 1)
//
//                )
//        );
//        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_frozen_ocean_spawns"),
//                createSpawnModifier(getBiomeHolderSet(registry, Biomes.FROZEN_OCEAN),
//                        new MobSpawnSettings.SpawnerData(RegistrationInit.CRAB.get(), 32, 1, 1)
//
//                )
//        );
//
//        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_ocean_spawns"),
//                createSpawnModifier(getBiomeHolderSet(registry, Biomes.OCEAN),
//                        new MobSpawnSettings.SpawnerData(RegistrationInit.CRAB.get(), 32, 1, 1)
//
//                )
//        );
//        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_cold_ocean_spawns"),
//                createSpawnModifier(getBiomeHolderSet(registry, Biomes.COLD_OCEAN),
//                        new MobSpawnSettings.SpawnerData(RegistrationInit.CRAB.get(), 32, 1, 1)
//
//                )
//        );
//        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_deep_ocean_spawns"),
//                createSpawnModifier(getBiomeHolderSet(registry, Biomes.DEEP_OCEAN),
//                        new MobSpawnSettings.SpawnerData(RegistrationInit.CRAB.get(), 32, 1, 1)
//
//                )
//        );
//        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_deep_frozen_ocean_spawns"),
//                createSpawnModifier(getBiomeHolderSet(registry, Biomes.DEEP_FROZEN_OCEAN),
//                        new MobSpawnSettings.SpawnerData(RegistrationInit.CRAB.get(), 32, 1, 1)
//
//                )
//        );
//        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_beach_spawns"),
//                createSpawnModifier(getBiomeHolderSet(registry, Biomes.BEACH),
//                        new MobSpawnSettings.SpawnerData(RegistrationInit.CRAB.get(), 32, 1, 1)
//
//                )
//        );
//        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_mangrove_swamp_spawns"),
//                createSpawnModifier(getBiomeHolderSet(registry, Biomes.MANGROVE_SWAMP),
//                        new MobSpawnSettings.SpawnerData(RegistrationInit.CRAB.get(), 32, 1, 1)
//                )
//        );

        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_oceans_spawns"),
                createSpawnModifier(getBiomeHolderSet(registry, Biomes.OCEAN, Biomes.DEEP_OCEAN),
                        new MobSpawnSettings.SpawnerData(RegistrationInit.TRIDACNA.get(), 32, 1, 2)
                )
        );

        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_lukewarm_oceans_spawns"),
                createSpawnModifier(getBiomeHolderSet(registry, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN),
                        new MobSpawnSettings.SpawnerData(RegistrationInit.TRIDACNA.get(), 32, 1, 3)
                )
        );

        modifierMap.put(new ResourceLocation(JohnnySBiologicalNotes.MODID, "add_warm_ocean_spawns"),
                createSpawnModifier(getBiomeHolderSet(registry, Biomes.WARM_OCEAN),
                        new MobSpawnSettings.SpawnerData(RegistrationInit.TRIDACNA.get(), 64, 1, 4)
                )
        );


        final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, registryAccess);

        generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(
                generator, existingFileHelper, JohnnySBiologicalNotes.MODID, ops, ForgeRegistries.Keys.BIOME_MODIFIERS, modifierMap));
    }
    public void gatherData(GatherDataEvent event){
        SpawnHandler.datagenBiomeModifiers(event);
    }
}
