package com.mnsfhxy.johnny_s_biological_notes.world.biome.modifier;

import com.google.common.collect.ImmutableList;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.world.biome.BiomeSpawnConfig;
import com.mnsfhxy.johnny_s_biological_notes.world.biome.ModSpawnData;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;

public class BiomeModifierSpawn implements BiomeModifier {
    public static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(JohnnySBiologicalNotes.MODID, "johnny_add_spawns"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, JohnnySBiologicalNotes.MODID);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {

        if (phase == Phase.ADD) {
            addBiomeSpawns(biome, builder);
        }
    }

    public static void addBiomeSpawns(Holder<Biome> biome, ModifiableBiomeInfo.BiomeInfo.Builder builder)  {
        Class<?> BCNClass = BiomeSpawnConfig.class;
        BiomeSpawnConfig biomeSpawnConfig = new BiomeSpawnConfig();
        Field[] fields = BCNClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == ModSpawnData.class) {
                field.setAccessible(true);
                ModSpawnData spawnData = null;
                try {
                    spawnData = (ModSpawnData) field.get(biomeSpawnConfig);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                if (testBiome(spawnData.getSpawnBiomes(), biome)) {
                    builder.getMobSpawnSettings().getSpawner(spawnData.getMobCategory()).add(spawnData.getSpawnerData());
                }
            }
        }
    }




    public static boolean testBiome(ImmutableList<ResourceKey<Biome>> list, Holder<Biome> biome) {
        for (var i : list) {
            if (biome.is(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return (Codec)SERIALIZER.get();
    }
    public static Codec<BiomeModifierSpawn> makeCodec() {
        return Codec.unit(BiomeModifierSpawn::new);
    }

}
