package com.mnsfhxy.johnny_s_biological_notes.mixin;

import com.google.common.collect.ImmutableList;
import com.mnsfhxy.johnny_s_biological_notes.spawn.DrifterSpawner;
import com.mnsfhxy.johnny_s_biological_notes.spawn.ModSpawners;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.storage.ServerLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public abstract class MixinServerLevel {
    @Shadow
    private  ServerLevelData serverLevelData;
    @Inject(method = "tickCustomSpawners",at = @At("TAIL"))
    public void tickCustomSpawners(boolean pSpawnEnemies, boolean pSpawnFriendlies, CallbackInfo ci) {
        if(ModSpawners.customSpawners==null) ModSpawners.customSpawners=ImmutableList.of(new DrifterSpawner(serverLevelData));
        for(CustomSpawner customspawner : ModSpawners.customSpawners) {
            customspawner.tick((ServerLevel) (Object)this, pSpawnEnemies, pSpawnFriendlies);
        }
    }
}
