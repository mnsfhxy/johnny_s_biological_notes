package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.effect.EffectVulnusRecover;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.EntityCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.EntityDrifter;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.Container;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.PlayLevelSoundEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = JohnnySBiologicalNotes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeInit {
    public static void init() {

    }

    @SubscribeEvent
    public static void onPlayerCrafted(PlayerEvent.ItemCraftedEvent event) {
        Container inventory = event.getInventory();
        ItemStack crafting = event.getCrafting();
        if (crafting.is(RegistrationInit.ITEM_JELLY.get())) {
            event.getEntity().getInventory().add(new ItemStack(Items.GLASS_BOTTLE, crafting.getCount()));
        }

    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        List<EntityDrifter> drifters = event.getEntity().level.getEntitiesOfClass(EntityDrifter.class, event.getEntity().getBoundingBox().inflate(64F, 10, 64F));
        for (var drifter : drifters) {
            EntityDrifter.Favorability favorability = drifter.favorability.get(event.getOriginal().getUUID());
            if (favorability != null) {
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
        } else if (entitySource instanceof EntityDrifter) {
            ((EntityDrifter) entitySource).addEffect(new MobEffectInstance(MobEffects.REGENERATION, UtilLevel.TIME.SECOND.getTick() * 15, 1));
        }
    }


    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        Entity pTarget = event.getEntity();
        if (pTarget instanceof LivingEntity) {
            LivingEntity pLivingEntity = ((LivingEntity) pTarget);
            if (pLivingEntity.hasEffect(PotionsInit.VULNUS_RECOVER.get())) {
                if (EffectVulnusRecover.canEffect.test(pLivingEntity)) {
                    if (!pLivingEntity.isDeadOrDying()) {
                        pLivingEntity.setHealth(Math.min(pLivingEntity.getHealth() + 3 + Objects.requireNonNull(pLivingEntity.getEffect(PotionsInit.VULNUS_RECOVER.get())).getAmplifier(), pLivingEntity.getMaxHealth()));
                    }
                }
            }
        }


    }
    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        Entity pTarget = event.getTarget();
        Player pPlayer = event.getEntity();

        //浪客好感度更新
        List<EntityDrifter> drifters = pPlayer.level.getEntitiesOfClass(EntityDrifter.class, pPlayer.getBoundingBox().inflate(64F, 10, 64F));
        for (var drifter : drifters) {
            if (pTarget instanceof Villager || pTarget instanceof EntityDrifter) {
                drifter.updateFavorability(pPlayer, (LivingEntity) pTarget);
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
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_WEATHER) {
            return;
        }

            // this is a world pos of the player
            try {
                UtilRender.drawParticle(event);
            } catch (Throwable ignore) {

        }
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
//        if(entity instanceof EntityTridacna){
//            System.out.println("11111111111111111");
//        }
        if (entity instanceof EntityCrab) {
            ((EntityCrab) entity).spawnColorInit();
        }
    }
    @SubscribeEvent
    public static void onPlaySoundAtEntity(PlayLevelSoundEvent.AtEntity event){
        if(event.getLevel().isClientSide){
            LocalPlayer player = Minecraft.getInstance().player;
            if(player!=null&&player.hasEffect(PotionsInit.CONCENTRATE.get())){
                if(player.distanceTo(event.getEntity())<15){
                    UtilRender.addParticle(RegistrationInit.CONCENTRATE_PARTICLE.get(),10,event.getEntity().getRandomX(0.6D),event.getEntity().getY(),event.getEntity().getRandomZ(0.6d),0.0D, 0.0D, 0.0D);
//                    event.getLevel().addParticle(ParticleTypes.BUBBLE,event.getEntity().getRandomX(0.6D),event.getEntity().getY(),event.getEntity().getRandomZ(0.6d),0.0D, 0.0D, 0.0D);
                }
            }

        }
    }

}


