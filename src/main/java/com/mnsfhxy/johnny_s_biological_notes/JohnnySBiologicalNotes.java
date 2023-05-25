package com.mnsfhxy.johnny_s_biological_notes;

import com.mnsfhxy.johnny_s_biological_notes.init.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JohnnySBiologicalNotes.MODID)
public class JohnnySBiologicalNotes {

    public static final String MODID = "johnny_s_biological_notes";
    public static final Logger LOGGER = LogUtils.getLogger();

    public JohnnySBiologicalNotes() {
        IEventBus modBusEvent = FMLJavaModLoadingContext.get().getModEventBus();
        ModInit.init();
        ForgeInit.init();
        RegistrationInit.init();//包括物品 方块 效果 实体
        SoundInit.init();
        PotionsInit.init();//效果 药水
        modBusEvent.addListener(this::setup);
    }
    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(RegistrationInit::initDispenser);

    }

}
