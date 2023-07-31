package com.mnsfhxy.johnny_s_biological_notes.spawn;

import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.EntityDrifter;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.animal.horse.TraderLlama;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.*;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.ServerLevelData;

import javax.annotation.Nullable;
import java.util.Optional;

public class DrifterSpawner implements CustomSpawner {

    private final RandomSource random = RandomSource.create();
    private int tickDelay;
    private int spawnDelay;
    private final int SPAWN_DELAY = UtilLevel.TIME.MINUTE.getTick() * 5;

    public DrifterSpawner() {
        this.tickDelay = UtilLevel.TIME.MINUTE.getTick();
        this.spawnDelay = SPAWN_DELAY;
    }

    @Override
    public int tick(ServerLevel pLevel, boolean pSpawnHostiles, boolean pSpawnPassives) {
        if (--this.tickDelay > 0) {
            return 0;
        } else {
            this.tickDelay = UtilLevel.TIME.MINUTE.getTick();
            this.spawnDelay -= UtilLevel.TIME.MINUTE.getTick();
//            this.serverLevelData.setWanderingTraderSpawnDelay(this.spawnDelay);
            if (this.spawnDelay > 0) {
                return 0;
            } else {
                this.spawnDelay = SPAWN_DELAY;
                if (!pLevel.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
                    return 0;
                } else {
                    this.spawn(pLevel);
                    return 1;
                }
            }
        }
    }



    private boolean spawn(ServerLevel pServerLevel) {
        Player player = pServerLevel.getRandomPlayer();
        if (player == null) {
            return true;
        } else if (this.random.nextInt(10) != 0) {
            return false;
        } else {
            BlockPos blockpos = player.blockPosition();
            int i = 48;
            PoiManager poimanager = pServerLevel.getPoiManager();
            Optional<BlockPos> optional = poimanager.find((p_219713_) -> {
                return p_219713_.is(PoiTypes.MEETING);
            }, (p_219711_) -> {
                return true;
            }, blockpos, 48, PoiManager.Occupancy.ANY);
            BlockPos blockpos1 = optional.orElse(blockpos);
            BlockPos blockpos2 = this.findSpawnPositionNear(pServerLevel, blockpos1, 48);
            if (blockpos2 != null && this.hasEnoughSpace(pServerLevel, blockpos2)) {
                if (pServerLevel.getBiome(blockpos2).is(BiomeTags.WITHOUT_WANDERING_TRADER_SPAWNS)) {
                    return false;
                }

                EntityDrifter entityDrifter = RegistrationInit.DRIFTER.get().spawn(pServerLevel, (CompoundTag) null, (Component) null, (Player) null, blockpos2, MobSpawnType.EVENT, false, false);
                if (entityDrifter != null) {
                    entityDrifter.restrictTo(blockpos1, 16);
                    return true;
                }
            }

            return false;
        }
    }


    @Nullable
    private BlockPos findSpawnPositionNear(LevelReader pLevel, BlockPos pPos, int pMaxDistance) {
        BlockPos blockpos = null;

        for (int i = 0; i < 10; ++i) {
            int j = pPos.getX() + this.random.nextInt(pMaxDistance * 2) - pMaxDistance;
            int k = pPos.getZ() + this.random.nextInt(pMaxDistance * 2) - pMaxDistance;
            int l = pLevel.getHeight(Heightmap.Types.WORLD_SURFACE, j, k);
            BlockPos blockpos1 = new BlockPos(j, l, k);
            if (NaturalSpawner.isSpawnPositionOk(SpawnPlacements.Type.ON_GROUND, pLevel, blockpos1, EntityType.WANDERING_TRADER)) {
                blockpos = blockpos1;
                break;
            }
        }

        return blockpos;
    }

    private boolean hasEnoughSpace(BlockGetter pLevel, BlockPos pPos) {
        for (BlockPos blockpos : BlockPos.betweenClosed(pPos, pPos.offset(1, 2, 1))) {
            if (!pLevel.getBlockState(blockpos).getCollisionShape(pLevel, blockpos).isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
