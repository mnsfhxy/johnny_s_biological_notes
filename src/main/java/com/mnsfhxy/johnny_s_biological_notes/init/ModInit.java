package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;

import com.mnsfhxy.johnny_s_biological_notes.capability.spirit.SpiritEvents;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.EntityCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.EntityDrifter;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.bubble.EntityJellyBubble;
import com.mnsfhxy.johnny_s_biological_notes.entity.loiter.EntityLoiter;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.EntityPeeper;
import com.mnsfhxy.johnny_s_biological_notes.entity.tridacna.EntityTridacna;
import com.mnsfhxy.johnny_s_biological_notes.networking.Messages;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

//注意！在使用了 @Mod.EventBusSubscriber 的类里，所有方法都必须有 public 和 static 修饰符。
@Mod.EventBusSubscriber(modid = JohnnySBiologicalNotes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModInit {
    public static final String TAB_NAME = "JohnnySBiologicalNotes";
    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RegistrationInit.ITEM_CRAB_MEAT.get());
        }

        @Override
        public String getRecipeFolderName() {
            return JohnnySBiologicalNotes.MODID;
        }
    };


    public static void init() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addGenericListener(Entity.class, SpiritEvents::onAttachCapabilitiesPlayer);
        bus.addListener(SpiritEvents::onPlayerCloned);
        bus.addListener(SpiritEvents::onRegisterCapabilities);

    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        SpawnPlacements.Type SPAWN_ON_WATER_GROUND = SpawnPlacements.Type.create("ON_WATER_GROUND",
                ((levelReader, blockPos, entityType) -> levelReader.getFluidState(blockPos).is(FluidTags.WATER)
                        &&levelReader.getBlockState(blockPos.below()).isFaceSturdy(levelReader, blockPos.below(), Direction.UP)));

        SpawnPlacements.register(RegistrationInit.CRAB.get(), SPAWN_ON_WATER_GROUND, Heightmap.Types.OCEAN_FLOOR, EntityCrab::checkCrabSpawnRules);
        SpawnPlacements.register(RegistrationInit.PEEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> ( Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
        SpawnPlacements.register(RegistrationInit.JELLY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntityJelly::checkJellySpawnRules);
        SpawnPlacements.register(RegistrationInit.TRIDACNA.get(), SPAWN_ON_WATER_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityTridacna::checkSpawnRules);

        event.put(RegistrationInit.CRAB.get(), EntityCrab.prepareAttributes().build());
        event.put(RegistrationInit.DRIFTER.get(), EntityDrifter.prepareAttributes().build());
        event.put(RegistrationInit.PEEPER.get(), EntityPeeper.prepareAttributes().build());
        event.put(RegistrationInit.JELLY.get(), EntityJelly.prepareAttributes().build());
        event.put(RegistrationInit.JELLY_BUBBLE.get(), EntityJellyBubble.prepareAttributes().build());
        event.put(RegistrationInit.TRIDACNA.get(), EntityTridacna.prepareAttributes().build());
        event.put(RegistrationInit.LOITER.get(), EntityLoiter.prepareAttributes().build());
    }


    @SubscribeEvent
    public static void onSpawnPlacementRegisterEvent(SpawnPlacementRegisterEvent event) {
//        event.register(RegistrationInit.CRAB.get(),
//                SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
//                EntityCrab::checkCrabSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
//        event.register(RegistrationInit.PEEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE_WG,
//                EntityPeeper::checkPeeperSpawnRule,SpawnPlacementRegisterEvent.Operation.REPLACE);
//        event.register(
//                RegistrationInit.TRIDACNA.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
//                EntityTridacna::checkTridacnaSpawnRules,SpawnPlacementRegisterEvent.Operation.OR
//        );

    }

    //生物生成在此注册
    @SubscribeEvent
    public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(PotionsInit::initBrewing);
        Messages.register();

    }


}
