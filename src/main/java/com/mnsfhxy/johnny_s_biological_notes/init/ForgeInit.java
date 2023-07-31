package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.Item.ItemKatana;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.capability.spirit.PlayerSpirit;
import com.mnsfhxy.johnny_s_biological_notes.capability.spirit.PlayerSpiritProvider;
import com.mnsfhxy.johnny_s_biological_notes.capability.spirit.SpiritOverlay;
import com.mnsfhxy.johnny_s_biological_notes.config.ConfigBiome;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.EntityCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.EntityDrifter;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = JohnnySBiologicalNotes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeInit {
    public static void init() {

    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        List<EntityDrifter> drifters = event.getEntity().level.getEntitiesOfClass(EntityDrifter.class, event.getEntity().getBoundingBox().inflate(64F, 10, 64F));
        for (var drifter :  drifters) {
            EntityDrifter.Favorability favorability = drifter.favorability.get(event.getOriginal().getUUID());
            if(favorability!=null){
                drifter.favorability.remove(event.getOriginal().getUUID());
            }
        }

    }
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {

        Entity entitySource = event.getSource().getEntity();
        //Drifter好感度
        if (entitySource instanceof Player) {

            List<EntityDrifter> entitiesOfClass = entitySource.level.getEntitiesOfClass(EntityDrifter.class, entitySource.getBoundingBox().inflate(64F, 10, 64F));
            for (var drifter : entitiesOfClass) {
                drifter.updateFavorability((Player) entitySource, event.getEntity());
            }
        }else if(entitySource instanceof EntityDrifter){
            ((EntityDrifter)entitySource).addEffect(new MobEffectInstance(MobEffects.REGENERATION,UtilLevel.TIME.SECOND.getTick()*15,1));
        }
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        Entity pTarget = event.getTarget();
        Player pPlayer = event.getEntity();

        //浪客好感度更新
        List<EntityDrifter> drifters = pPlayer.level.getEntitiesOfClass(EntityDrifter.class, pPlayer.getBoundingBox().inflate(64F, 10, 64F));
        for (var drifter :  drifters) {
            if (pTarget instanceof Villager||pTarget instanceof EntityDrifter) {
                 drifter.updateFavorability(pPlayer,(LivingEntity) pTarget);
            }
        }

//        if (event.getEntity().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ItemKatana) {
//            event.getEntity().getCapability(PlayerSpiritProvider.PLAYER_SPIRIT).ifPresent(s -> {
//                if (s.isMax()) {
//                    SpiritOverlay.HUD = SpiritOverlay.SPIRIT_FIGHT;
//                    amount.updateAndGet(v -> (float) (v + (5 - pTarget.getArrowCount())));
//                    s.cleanSpirit();
//                } else {
//                    s.addSpirit();
//                }
//            });
//        }


    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onWorldRenderLast(
            RenderLevelStageEvent event) // Called when drawing the world.
    {

    }

    //无法触发?
    @SubscribeEvent
    public void onModConfigEvent(final ModConfigEvent event) {
        final ModConfig config = event.getConfig();
        // Rebake the configs when they change
//        if (config.getSpec() == ConfigHolder.COMMON_SPEC) {
//            AMConfig.bake(config);
//        }
//        ConfigBiome.init();
    }

    @SubscribeEvent
    public static void onEntitySpawned(EntityJoinLevelEvent event) {
        //
        Entity entity = event.getEntity();
        if (entity instanceof EntityCrab) {
            ((EntityCrab) entity).spawnColorInit();
        }
    }


}


