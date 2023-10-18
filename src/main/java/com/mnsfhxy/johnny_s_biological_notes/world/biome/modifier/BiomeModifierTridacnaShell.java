package com.mnsfhxy.johnny_s_biological_notes.world.biome.modifier;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.block.BlockTridacnaShell;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.world.features.FeatureTridacnaShell;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class BiomeModifierTridacnaShell implements BiomeModifier {

    public static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(JohnnySBiologicalNotes.MODID, "tridacna_shell_biome_modifier"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, JohnnySBiologicalNotes.MODID);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == BiomeModifier.Phase.ADD && BlockTridacnaShell.canBorn(biome)) {
            var feature = new FeatureTridacnaShell();
            var placedFeature = new PlacedFeature(
                    Holder.direct(new ConfiguredFeature(feature, new NoneFeatureConfiguration())),
                    new ArrayList<PlacementModifier>()
            );
            builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.direct(placedFeature));
        }
    }
    public Codec<? extends BiomeModifier> codec() {
        return (Codec)SERIALIZER.get();
    }

    public static Codec<BiomeModifierTridacnaShell> makeCodec() {
        return Codec.unit(BiomeModifierTridacnaShell::new);
    }

}
