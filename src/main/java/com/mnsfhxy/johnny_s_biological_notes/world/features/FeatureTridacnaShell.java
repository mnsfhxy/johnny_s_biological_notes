package com.mnsfhxy.johnny_s_biological_notes.world.features;

import com.mnsfhxy.johnny_s_biological_notes.block.BlockTridacnaShell;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class FeatureTridacnaShell extends Feature<NoneFeatureConfiguration> {

    public FeatureTridacnaShell() {
        super(NoneFeatureConfiguration.CODEC);
    }
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        boolean flag =false;
        RandomSource randomsource=pContext.random();
        WorldGenLevel worldgenlevel=pContext.level();
        BlockPos blockpos=pContext.origin();
        if(randomsource.nextInt(3)!=0)return false;
        int i = randomsource.nextInt(8) - randomsource.nextInt(8);
        int j = randomsource.nextInt(8) - randomsource.nextInt(8);
        int k = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR, blockpos.getX() + i, blockpos.getZ() + j);
        BlockPos blockpos1 = new BlockPos(blockpos.getX() + i, k, blockpos.getZ() + j);
        if (worldgenlevel.getBlockState(blockpos1).is(Blocks.WATER)) {
            boolean flag1 = randomsource.nextInt(25) ==0;
            BlockState blockstate = flag1 ? RegistrationInit.BLOCK_OLDER_TRIDACNA_SHELL.get().defaultBlockState() : RegistrationInit.BLOCK_TRIDACNA_SHELL.get().defaultBlockState();
            if(randomsource.nextInt(4)==0){
                blockstate=blockstate.getBlock()==RegistrationInit.BLOCK_OLDER_TRIDACNA_SHELL.get()?RegistrationInit.BLOCK_OLDER_TRIDACNA_SHELL_BROKEN.get().defaultBlockState():RegistrationInit.BLOCK_TRIDACNA_SHELL_BROKEN.get().defaultBlockState();
            }
            if (BlockTridacnaShell.canSurvive(worldgenlevel, blockpos1)) {
                    worldgenlevel.setBlock(blockpos1, blockstate, 2);
                flag = true;
            }
        }
        return flag;
    }
}
