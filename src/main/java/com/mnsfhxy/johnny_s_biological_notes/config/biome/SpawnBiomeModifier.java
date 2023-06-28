package com.mnsfhxy.johnny_s_biological_notes.config.biome;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.WorldInit;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpawnBiomeModifier implements BiomeModifier {
    private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(JohnnySBiologicalNotes.MODID, "johnny_mob_spawns"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, JohnnySBiologicalNotes.MODID);

    public SpawnBiomeModifier() {
    }

    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD) {
           WorldInit.addBiomeSpawns(biome, builder);
        }
    }

    public Codec<? extends BiomeModifier> codec() {
        return (Codec)SERIALIZER.get();
    }

    public static Codec<SpawnBiomeModifier> makeCodec() {
        return Codec.unit(SpawnBiomeModifier::new);
    }
}
