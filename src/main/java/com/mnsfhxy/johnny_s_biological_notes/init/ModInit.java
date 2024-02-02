package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.capability.spirit.SpiritEvents;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.EntityBeluga;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.young.EntityYoungBeluga;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.EntityCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.EntityDrifter;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.bubble.EntityJellyBubble;
import com.mnsfhxy.johnny_s_biological_notes.entity.loiter.EntityLoiter;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.EntityPeeper;
import com.mnsfhxy.johnny_s_biological_notes.entity.tridacna.EntityTridacna;
import com.mnsfhxy.johnny_s_biological_notes.networking.Messages;
import com.mnsfhxy.johnny_s_biological_notes.world.biome.BiomeSpawnConfig;
import com.mnsfhxy.johnny_s_biological_notes.world.biome.ModSpawnData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.lang.reflect.Field;

//注意！在使用了 @Mod.EventBusSubscriber 的类里，所有方法都必须有 public 和 static 修饰符。
@Mod.EventBusSubscriber(modid = JohnnySBiologicalNotes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModInit {
    public static final String TAB_NAME = JohnnySBiologicalNotes.MODID;
    public static final CreativeModeTab ITEM_GROUP_BLOCK = new CreativeModeTab(TAB_NAME+"_block") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RegistrationInit.BLOCK_TRIDACNA_SHELL.get());
        }

        @Override
        public String getRecipeFolderName() {
            return JohnnySBiologicalNotes.MODID+"_block";
        }
    };
    public static final CreativeModeTab ITEM_GROUP_EGG = new CreativeModeTab(TAB_NAME+"_egg") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RegistrationInit.CRAB_EGG.get());
        }

        @Override
        public String getRecipeFolderName() {
            return JohnnySBiologicalNotes.MODID+"_egg";
        }
    };
    public static final CreativeModeTab ITEM_GROUP_MATERIAL = new CreativeModeTab(TAB_NAME+"_material") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RegistrationInit.ITEM_FORGED_PLATE.get());
        }

        @Override
        public String getRecipeFolderName() {
            return JohnnySBiologicalNotes.MODID+"_material";
        }
    };
    public static final CreativeModeTab ITEM_GROUP_FOOD = new CreativeModeTab(TAB_NAME+"_food") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RegistrationInit.ITEM_JELLY_PLATTER.get());
        }

        @Override
        public String getRecipeFolderName() {
            return JohnnySBiologicalNotes.MODID+"_food";
        }
    };
    public static final CreativeModeTab ITEM_GROUP_TOOL = new CreativeModeTab(TAB_NAME+"_tool") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RegistrationInit.ITEM_DIAMOND_KATANA.get());
        }

        @Override
        public String getRecipeFolderName() {
            return JohnnySBiologicalNotes.MODID+"_tool";
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

        Class<?> BCNClass = BiomeSpawnConfig.class;
        BiomeSpawnConfig biomeSpawnConfig = new BiomeSpawnConfig();
        Field[] fields = BCNClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == ModSpawnData.class) {
                field.setAccessible(true);
                ModSpawnData spawnData = null;
                try {
                    spawnData = (ModSpawnData) field.get(biomeSpawnConfig);
                    SpawnPlacements.register(spawnData.getEntityType(),spawnData.getSpawnPlacementType(),spawnData.getHeightMapType(),spawnData.getPredicate());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }
        }


        event.put(RegistrationInit.CRAB.get(), EntityCrab.prepareAttributes().build());
        event.put(RegistrationInit.DRIFTER.get(), EntityDrifter.prepareAttributes().build());
        event.put(RegistrationInit.PEEPER.get(), EntityPeeper.prepareAttributes().build());
        event.put(RegistrationInit.JELLY.get(), EntityJelly.prepareAttributes().build());
        event.put(RegistrationInit.JELLY_BUBBLE.get(), EntityJellyBubble.prepareAttributes().build());
        event.put(RegistrationInit.TRIDACNA.get(), EntityTridacna.prepareAttributes().build());
        event.put(RegistrationInit.LOITER.get(), EntityLoiter.prepareAttributes().build());
        event.put(RegistrationInit.BELUGA.get(), EntityBeluga.prepareAttributes().build());
        event.put(RegistrationInit.YOUNG_BELUGA.get(), EntityYoungBeluga.prepareAttributes().build());

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
