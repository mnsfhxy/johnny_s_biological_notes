package com.mnsfhxy.johnny_s_biological_notes.init;

import com.google.common.collect.Sets;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Stores IDs for built in loot tables, i.e. loot tables which are not based directly on a block or entity ID.
 */
public class LootTablesInit {
   private static final Set<ResourceLocation> LOCATIONS = Sets.newHashSet();
   private static final Set<ResourceLocation> IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);

   public static final ResourceLocation PEEPER_GIFT = register("entities/peeper_gift");

   private static ResourceLocation register(String path) {
      return register(new ResourceLocation(JohnnySBiologicalNotes.MODID,path));
   }

   private static ResourceLocation register(ResourceLocation pId) {
      if (LOCATIONS.add(pId)) {
         return pId;
      } else {
         throw new IllegalArgumentException(pId + " is already a registered built-in loot table");
      }
   }
   public static final LootContextParamSet PEEPER_GIFT_PARAM = paramRegister((p_81440_) -> {
      p_81440_.required(LootContextParams.THIS_ENTITY);
   });
   private static LootContextParamSet paramRegister(Consumer<LootContextParamSet.Builder> pBuilderConsumer) {
      LootContextParamSet.Builder lootcontextparamset$builder = new LootContextParamSet.Builder();
      pBuilderConsumer.accept(lootcontextparamset$builder);
      LootContextParamSet lootcontextparamset = lootcontextparamset$builder.build();
      return lootcontextparamset;

   }

   public static Set<ResourceLocation> all() {
      return IMMUTABLE_LOCATIONS;
   }
}
