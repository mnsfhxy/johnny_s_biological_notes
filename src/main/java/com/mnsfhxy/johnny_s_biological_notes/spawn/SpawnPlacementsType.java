package com.mnsfhxy.johnny_s_biological_notes.spawn;

import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biomes;

public class SpawnPlacementsType {
    public static final  SpawnPlacements.Type SPAWN_ON_WATER_GROUND = SpawnPlacements.Type.create("ON_WATER_GROUND",
            ((levelReader, blockPos, entityType) -> levelReader.getFluidState(blockPos).is(FluidTags.WATER)
                    &&levelReader.getBlockState(blockPos.below()).isFaceSturdy(levelReader, blockPos.below(), Direction.UP)));
    public static final  SpawnPlacements.Type SPAWN_ON_WATER_GROUND_AND_BEACH = SpawnPlacements.Type.create("ON_WATER_GROUND_AND_BEACH",
            ((levelReader, blockPos, entityType) -> (levelReader.getFluidState(blockPos).is(FluidTags.WATER)||levelReader.getBiome(blockPos).is(Biomes.BEACH))
                    &&levelReader.getBlockState(blockPos.below()).isFaceSturdy(levelReader, blockPos.below(), Direction.UP)));

}
