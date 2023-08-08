package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.Item.ItemKatana;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.capability.spirit.PlayerSpirit;
import com.mnsfhxy.johnny_s_biological_notes.capability.spirit.PlayerSpiritProvider;
import com.mnsfhxy.johnny_s_biological_notes.capability.spirit.SpiritEvents;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.EntityCrab;
//import com.mnsfhxy.johnny_s_biological_notes.entity.crab.BRendererCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.EntityDrifter;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.EntityPeeper;
import com.mnsfhxy.johnny_s_biological_notes.networking.Messages;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
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
            return new ItemStack(Items.DIAMOND);
        }

        @Override
        public String getRecipeFolderName() {
            return JohnnySBiologicalNotes.MODID;
        }
    };
    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addGenericListener(Entity.class, SpiritEvents::onAttachCapabilitiesPlayer);
        bus.addListener(SpiritEvents::onPlayerCloned);
        bus.addListener(SpiritEvents::onRegisterCapabilities);
//        bus.addListener(ManaEvents::onWorldTick);
    }

    public static void init() {
//                IEventBus bus = MinecraftForge.EVENT_BUS;

    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(RegistrationInit.CRAB.get(), EntityCrab.prepareAttributes().build());
        event.put(RegistrationInit.DRIFTER.get(), EntityDrifter.prepareAttributes().build());
        event.put(RegistrationInit.PEEPER.get(), EntityPeeper.prepareAttributes().build());
        event.put(RegistrationInit.JELLY.get(), EntityJelly.prepareAttributes().build());


    }


    @SubscribeEvent
    public static void onSpawnPlacementRegisterEvent(SpawnPlacementRegisterEvent event) {
//        event.register(RegistrationInit.CRAB.get(),
//                SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
//                EntityCrab::checkCrabSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
//        event.register(RegistrationInit.PEEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE_WG,
//                EntityPeeper::checkPeeperSpawnRule,SpawnPlacementRegisterEvent.Operation.REPLACE);

    }


    @SubscribeEvent
    public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(PotionsInit::initBrewing);
        event.enqueueWork(EntityPeeper::init);
        event.enqueueWork(EntityCrab::init);
        Messages.register();

    }


}
