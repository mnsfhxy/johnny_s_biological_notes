package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.Item.ItemGlueBottle;
import com.mnsfhxy.johnny_s_biological_notes.Item.ItemKatana;
import com.mnsfhxy.johnny_s_biological_notes.Item.ItemModFishBucket;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.block.BlockEcoBottle;
import com.mnsfhxy.johnny_s_biological_notes.block.BlockTridacnaShell;
import com.mnsfhxy.johnny_s_biological_notes.block.blockentity.BETridacnaShell;
import com.mnsfhxy.johnny_s_biological_notes.block.gluedblock.BlockGluedConcretePowder;
import com.mnsfhxy.johnny_s_biological_notes.block.gluedblock.BlockGluedSand;
import com.mnsfhxy.johnny_s_biological_notes.block.BlockJelly;
import com.mnsfhxy.johnny_s_biological_notes.block.BlockJellyEmbryo;
import com.mnsfhxy.johnny_s_biological_notes.block.blockentity.BEJellyEmbryo;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.EntityCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.EntityDrifter;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.bubble.EntityJellyBubble;
import com.mnsfhxy.johnny_s_biological_notes.entity.loiter.EntityLoiter;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.EntityPeeper;
import com.mnsfhxy.johnny_s_biological_notes.entity.tridacna.EntityTridacna;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import com.mnsfhxy.johnny_s_biological_notes.world.biome.modifier.BiomeModifierTridacnaShell;
import com.mnsfhxy.johnny_s_biological_notes.world.features.FeatureTridacnaShell;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.HolderSet;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;

public class RegistrationInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JohnnySBiologicalNotes.MODID);
    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ModInit.ITEM_GROUP);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JohnnySBiologicalNotes.MODID);
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZER = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, JohnnySBiologicalNotes.MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, JohnnySBiologicalNotes.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JohnnySBiologicalNotes.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, JohnnySBiologicalNotes.MODID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, JohnnySBiologicalNotes.MODID);
//    public static final DeferredRegister<Codec<? extends BiomeModifier>>  BIOME_MODIFIERS=   DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, JohnnySBiologicalNotes.MODID);
    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITY_TYPE.register(bus);
//                BLOCK_ENTITIES.register(bus);
        //        CONTAINERS.register(bus);
        ENTITIES.register(bus);
        //        STRUCTURES.register(bus);
        BIOME_MODIFIER_SERIALIZER.register(bus);

//        BIOME_MODIFIERS.register(bus);
        //        PLACED_FEATURES.register(bus);
        PARTICLE_TYPES.register(bus);
    }


    //Block注册
    public static final RegistryObject<BlockJelly> BLOCK_JELLY = BLOCKS.register("jelly_block", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_BLACK = BLOCKS.register("jelly_block_black", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_BLUE = BLOCKS.register("jelly_block_blue", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_BROWN = BLOCKS.register("jelly_block_brown", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_CYAN = BLOCKS.register("jelly_block_cyan", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_GRAY = BLOCKS.register("jelly_block_gray", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_GREEN = BLOCKS.register("jelly_block_green", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_LIGHTBLUE = BLOCKS.register("jelly_block_lightblue", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_LIGHTGRAY = BLOCKS.register("jelly_block_lightgray", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_LIGHTGREEN = BLOCKS.register("jelly_block_lightgreen", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_MAGENTA = BLOCKS.register("jelly_block_magenta", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_ORANGE = BLOCKS.register("jelly_block_orange", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_PINK = BLOCKS.register("jelly_block_pink", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_PURPLE = BLOCKS.register("jelly_block_purple", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_RED = BLOCKS.register("jelly_block_red", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_WHITE = BLOCKS.register("jelly_block_white", BlockJelly::new);
    public static final RegistryObject<BlockJelly> BLOCK_JELLY_YELLOW = BLOCKS.register("jelly_block_yellow", BlockJelly::new);
    public static final RegistryObject<BlockJellyEmbryo> BLOCK_JELLY_EMBRYO = BLOCKS.register("jelly_embryo", BlockJellyEmbryo::new);
    public static final RegistryObject<BlockGluedSand> BLOCK_GLUED_SAND = BLOCKS.register("glued_sand", () -> new BlockGluedSand(14406560, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedSand> BLOCK_GLUED_RED_SAND = BLOCKS.register("glued_red_sand", () -> new BlockGluedSand(11098145, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_WHITE_CONCRETE_POWDER = BLOCKS.register("glued_white_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.WHITE_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.WHITE).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_ORANGE_CONCRETE_POWDER = BLOCKS.register("glued_orange_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.ORANGE_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.ORANGE).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_MAGENTA_CONCRETE_POWDER = BLOCKS.register("glued_magenta_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.MAGENTA_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.MAGENTA).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_LIGHT_BLUE_CONCRETE_POWDER = BLOCKS.register("glued_light_blue_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.LIGHT_BLUE_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.LIGHT_BLUE).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_YELLOW_CONCRETE_POWDER = BLOCKS.register("glued_yellow_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.YELLOW_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.YELLOW).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_LIME_CONCRETE_POWDER = BLOCKS.register("glued_lime_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.LIME_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.LIME).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_PINK_CONCRETE_POWDER = BLOCKS.register("glued_pink_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.PINK_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.PINK).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_GRAY_CONCRETE_POWDER = BLOCKS.register("glued_gray_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.GRAY_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.GRAY).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_LIGHT_GRAY_CONCRETE_POWDER = BLOCKS.register("glued_light_gray_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.LIGHT_GRAY_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.LIGHT_GRAY).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_CYAN_CONCRETE_POWDER = BLOCKS.register("glued_cyan_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.CYAN_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.CYAN).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_PURPLE_CONCRETE_POWDER = BLOCKS.register("glued_purple_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.PURPLE_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.PURPLE).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_BLUE_CONCRETE_POWDER = BLOCKS.register("glued_blue_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.BLUE_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.BLUE).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_BROWN_CONCRETE_POWDER = BLOCKS.register("glued_brown_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.BROWN_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.BROWN).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_GREEN_CONCRETE_POWDER = BLOCKS.register("glued_green_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.GREEN_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.GREEN).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_RED_CONCRETE_POWDER = BLOCKS.register("glued_red_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.RED_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.RED).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockGluedConcretePowder> BLOCK_GLUED_BLACK_CONCRETE_POWDER = BLOCKS.register("glued_black_concrete_powder", () -> new BlockGluedConcretePowder(Blocks.BLACK_CONCRETE, BlockBehaviour.Properties.of(Material.SAND, DyeColor.BLACK).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<BlockEcoBottle> BLOCK_ECO_BOTTLE = BLOCKS.register("eco_bottle", BlockEcoBottle::new);
    public static final RegistryObject<BlockEcoBottle> BLOCK_ECO_BOTTLE_BUBBLE_CORAL = BLOCKS.register("eco_bottle_bubble_coral", BlockEcoBottle::new);
    public static final RegistryObject<BlockEcoBottle> BLOCK_ECO_BOTTLE_FIRE_CORAL = BLOCKS.register("eco_bottle_fire_coral", BlockEcoBottle::new);
    public static final RegistryObject<BlockEcoBottle> BLOCK_ECO_BOTTLE_HORN_CORAL = BLOCKS.register("eco_bottle_horn_coral", BlockEcoBottle::new);
    public static final RegistryObject<BlockEcoBottle> BLOCK_ECO_BOTTLE_TUBE_CORAL = BLOCKS.register("eco_bottle_tube_coral", BlockEcoBottle::new);
    public static final RegistryObject<BlockEcoBottle> BLOCK_ECO_BOTTLE_BRAIN_CORAL = BLOCKS.register("eco_bottle_brain_coral", BlockEcoBottle::new);

    public static final RegistryObject<BlockTridacnaShell> BLOCK_TRIDACNA_SHELL = BLOCKS.register("tridacna_shell", BlockTridacnaShell::new);
    public static final RegistryObject<BlockTridacnaShell> BLOCK_TRIDACNA_SHELL_BROKEN = BLOCKS.register("tridacna_shell_broken", BlockTridacnaShell::new);
    public static final RegistryObject<BlockTridacnaShell> BLOCK_OLDER_TRIDACNA_SHELL = BLOCKS.register("older_tridacna_shell", BlockTridacnaShell::new);
    public static final RegistryObject<BlockTridacnaShell> BLOCK_OLDER_TRIDACNA_SHELL_BROKEN = BLOCKS.register("older_tridacna_shell_broken", BlockTridacnaShell::new);

    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_EMBRYO = fromBlock(BLOCK_JELLY_EMBRYO);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY = fromBlock(BLOCK_JELLY);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_BLACK = fromBlock(BLOCK_JELLY_BLACK);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_BLUE = fromBlock(BLOCK_JELLY_BLUE);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_BROWN = fromBlock(BLOCK_JELLY_BROWN);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_CYAN = fromBlock(BLOCK_JELLY_CYAN);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_GRAY = fromBlock(BLOCK_JELLY_GRAY);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_GREEN = fromBlock(BLOCK_JELLY_GREEN);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_LIGHTBLUE = fromBlock(BLOCK_JELLY_LIGHTBLUE);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_LIGHTGRAY = fromBlock(BLOCK_JELLY_LIGHTGRAY);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_LIGHTGREEN = fromBlock(BLOCK_JELLY_LIGHTGREEN);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_MAGENTA = fromBlock(BLOCK_JELLY_MAGENTA);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_ORANGE = fromBlock(BLOCK_JELLY_ORANGE);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_PINK = fromBlock(BLOCK_JELLY_PINK);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_PURPLE = fromBlock(BLOCK_JELLY_PURPLE);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_RED = fromBlock(BLOCK_JELLY_RED);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_WHITE = fromBlock(BLOCK_JELLY_WHITE);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY_YELLOW = fromBlock(BLOCK_JELLY_YELLOW);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_SAND = fromBlock(BLOCK_GLUED_SAND);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_RED_SAND = fromBlock(BLOCK_GLUED_RED_SAND);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_WHITE_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_WHITE_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_ORANGE_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_ORANGE_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_MAGENTA_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_MAGENTA_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_LIGHT_BLUE_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_LIGHT_BLUE_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_YELLOW_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_YELLOW_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_LIME_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_LIME_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_PINK_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_PINK_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_GRAY_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_GRAY_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_LIGHT_GRAY_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_LIGHT_GRAY_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_CYAN_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_CYAN_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_PURPLE_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_PURPLE_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_BLUE_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_BLUE_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_BROWN_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_BROWN_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_GREEN_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_GREEN_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_RED_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_RED_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_GLUED_BLACK_CONCRETE_POWDER = fromBlock(BLOCK_GLUED_BLACK_CONCRETE_POWDER);
    public static final RegistryObject<Item> BLOCK_ITEM_ECO_BOTTLE = fromBlock(BLOCK_ECO_BOTTLE);

    public static final RegistryObject<Item> BLOCK_ITEM_TRIDACNA_SHELL=fromBlock(BLOCK_TRIDACNA_SHELL,16);
    public static final RegistryObject<Item> BLOCK_ITEM_OLDER_TRIDACNA_SHELL=fromBlock(BLOCK_OLDER_TRIDACNA_SHELL,16);
    public static final RegistryObject<Item> BLOCK_ITEM_OLDER_TRIDACNA_SHELL_BROKEN=fromBlock(BLOCK_OLDER_TRIDACNA_SHELL_BROKEN,16);
    public static final RegistryObject<Item> BLOCK_ITEM_TRIDACNA_SHELL_BROKEN=fromBlock(BLOCK_TRIDACNA_SHELL_BROKEN,16);





    //BlockEntityType
    public static final RegistryObject<BlockEntityType<BEJellyEmbryo>> JELLY_EMBRYO_BE = BLOCK_ENTITY_TYPE.register("jelly_embryo", () -> BlockEntityType.Builder.of(BEJellyEmbryo::new, (BLOCK_JELLY_EMBRYO.get())).build(null));
    public static final RegistryObject<BlockEntityType<BETridacnaShell>> TRIDACNA_SHELL_BE = BLOCK_ENTITY_TYPE.register("tridacna_shell", () -> BlockEntityType.Builder.of(BETridacnaShell::new, (BLOCK_TRIDACNA_SHELL.get())).build(null));
//    public static final RegistryObject<BlockEntityType<BETridacnaShell>> OLDER_TRIDACNA_SHELL_BE = BLOCK_ENTITY_TYPE.register("older_tridacna_shell", () -> BlockEntityType.Builder.of(BETridacnaShell::new, (BLOCK_TRIDACNA_SHELL.get())).build(null));
//    public static final RegistryObject<BlockEntityType<BETridacnaShell>> TRIDACNA_SHELL_BROKEN_BE = BLOCK_ENTITY_TYPE.register("tridacna_shell_broken", () -> BlockEntityType.Builder.of(BETridacnaShell::new, (BLOCK_TRIDACNA_SHELL.get())).build(null));
//    public static final RegistryObject<BlockEntityType<BETridacnaShell>> OLDER_TRIDACNA_SHELL_BROKEN_BE = BLOCK_ENTITY_TYPE.register("older_tridacna_shell_broken", () -> BlockEntityType.Builder.of(BETridacnaShell::new, (BLOCK_TRIDACNA_SHELL.get())).build(null));
//


    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block,int pStackTo) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES.stacksTo(pStackTo)));
    }

    //Entity注册
    public static final RegistryObject<EntityType<EntityCrab>> CRAB =
            ENTITIES.register(
                    "crab",
                    () -> registerEntity(EntityType.Builder.of(EntityCrab::new, MobCategory.AMBIENT)
                            .sized(0.75F, 0.75F), "crab"));

    public static final RegistryObject<EntityType<EntityDrifter>> DRIFTER =
            ENTITIES.register(
                    "drifter",
                    () -> registerEntity(EntityType.Builder.of(EntityDrifter::new, MobCategory.AMBIENT)
                            .sized(0.75F, 1.8F), "drifter"));


    public static final RegistryObject<EntityType<EntityPeeper>> PEEPER =
            ENTITIES.register(
                    "peeper",
                    () -> registerEntity(EntityType.Builder.of(EntityPeeper::new, MobCategory.MONSTER)
                            .sized(1F, 1.2F), "peeper"));


    public static final RegistryObject<EntityType<EntityJelly>> JELLY =
            ENTITIES.register("jelly",
                    () -> registerEntity(EntityType.Builder.of(EntityJelly::new, MobCategory.AMBIENT)
                            .sized(1F, 1F), "jelly"));

    public static final RegistryObject<EntityType<EntityJellyBubble>> JELLY_BUBBLE =
            ENTITIES.register("jelly_bubble",
                    () -> registerEntity(EntityType.Builder.of(EntityJellyBubble::new, MobCategory.MISC)
                            .sized((float) (1.0 / 16), (float) (1.0 / 16)), "jelly_bubble"));
    public static final RegistryObject<EntityType<EntityTridacna>> TRIDACNA =
            ENTITIES.register("tridacna",
                    () -> registerFarSpawnEntity(EntityType.Builder.of(EntityTridacna::new,MobCategory.WATER_CREATURE)
                            .sized(1F, 1F), "tridacna"));
    public static final RegistryObject<EntityType<EntityLoiter>> LOITER =
            ENTITIES.register("loiter",
                    () -> registerEntity(EntityType.Builder.of(EntityLoiter::new,MobCategory.MONSTER)
                            .sized(1F, 1F), "loiter"));

    //Item注册
    //egg
    public static final RegistryObject<Item> CRAB_EGG =
            ITEMS.register(
                    "crab", () -> new ForgeSpawnEggItem(CRAB, 0xe25243, 0xc1bbb9, ITEM_PROPERTIES));
    public static final RegistryObject<Item> DRIFTER_EGG =
            ITEMS.register(
                    "drifter", () -> new ForgeSpawnEggItem(DRIFTER, 0x794343, 0xbba84c, ITEM_PROPERTIES));

    public static final RegistryObject<Item> PEEPER_EGG =
            ITEMS.register(
                    "peeper", () -> new ForgeSpawnEggItem(PEEPER, 0x534620, 0x6c7911, ITEM_PROPERTIES));
    public static final RegistryObject<Item> JELLY_EGG =
            ITEMS.register(
                    "jelly", () -> new ForgeSpawnEggItem(JELLY, 0x7e6f9f, 0xf6f2fb, ITEM_PROPERTIES));
    public static final RegistryObject<Item> TRIDACNA_EGG =
            ITEMS.register(
                    "tridacna", () -> new ForgeSpawnEggItem(TRIDACNA, 0x7e61cf, 0xf2f2fb, ITEM_PROPERTIES));
    public static final RegistryObject<Item> LOITER_EGG =
            ITEMS.register(
                    "loiter", () -> new ForgeSpawnEggItem(LOITER, 0x1e61cf, 0xc2f2fb, ITEM_PROPERTIES));

    public static final RegistryObject<Item> FORGED_PLATE = ITEMS.register("forged_plate", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP)));

    public static final RegistryObject<Item> WOOD_BLADE = ITEMS.register("wood_blade", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP)));
    public static final RegistryObject<Item> STONE_BLADE = ITEMS.register("stone_blade", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP)));
    public static final RegistryObject<Item> IRON_BLADE = ITEMS.register("iron_blade", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP)));
    public static final RegistryObject<Item> GOLD_BLADE = ITEMS.register("gold_blade", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP)));
    public static final RegistryObject<Item> DIAMOND_BLADE = ITEMS.register("diamond_blade", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP)));
    public static final RegistryObject<Item> NETHERITE_BLADE = ITEMS.register("netherite_blade", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP)));


    public static final RegistryObject<ItemKatana> WOOD_KATANA = ITEMS.register("wood_katana", () -> new ItemKatana(Tiers.WOOD, 4, -3f));
    public static final RegistryObject<ItemKatana> STONE_KATANA = ITEMS.register("stone_katana", () -> new ItemKatana(Tiers.STONE, 4, -3f));
    public static final RegistryObject<ItemKatana> IRON_KATANA = ITEMS.register("iron_katana", () -> new ItemKatana(Tiers.IRON, 5, -3f));
    public static final RegistryObject<ItemKatana> GOLD_KATANA = ITEMS.register("gold_katana", () -> new ItemKatana(Tiers.GOLD, 7, -3f));
    public static final RegistryObject<ItemKatana> DIAMOND_KATANA = ITEMS.register("diamond_katana", () -> new ItemKatana(Tiers.DIAMOND, 6, -3f));
    public static final RegistryObject<ItemKatana> NETHERITE_KATANA = ITEMS.register("netherite_katana", () -> new ItemKatana(Tiers.NETHERITE, 7, -3f));


    public static final RegistryObject<Item> CRAB_SHELL = ITEMS.register("crab_shell", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP)));
    public static final RegistryObject<Item> CRAB_MEAT = ITEMS.register("crab_meat", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).meat().build())));
    public static final RegistryObject<Item> COOKED_CRAB_MEAT = ITEMS.register("cooked_crab_meat", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(5).saturationMod(3f).meat().build())));
    public static final RegistryObject<Item> CRAB_BUCKET = ITEMS.register("crab_bucket", () -> new ItemModFishBucket(CRAB, Fluids.WATER, new Item.Properties().tab(ModInit.ITEM_GROUP)));

    public static final RegistryObject<Item> ITEM_JELLY = ITEMS.register("jelly_jelly", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_BLACK = ITEMS.register("jelly_black", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_BLUE = ITEMS.register("jelly_blue", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_BROWN = ITEMS.register("jelly_brown", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_CYAN = ITEMS.register("jelly_cyan", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_GRAY = ITEMS.register("jelly_gray", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_GREEN = ITEMS.register("jelly_green", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_LIGHTBLUE = ITEMS.register("jelly_lightblue", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_LIGHTGRAY = ITEMS.register("jelly_lightgray", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_LIGHTGREEN = ITEMS.register("jelly_lightgreen", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_MAGENTA = ITEMS.register("jelly_magenta", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_ORANGE = ITEMS.register("jelly_orange", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_PINK = ITEMS.register("jelly_pink", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_PURPLE = ITEMS.register("jelly_purple", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_RED = ITEMS.register("jelly_red", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_WHITE = ITEMS.register("jelly_white", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> ITEM_JELLY_YELLOW = ITEMS.register("jelly_yellow", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> SEMI_SOLIDFIED_PROTEIN = ITEMS.register("semi_solidfied_protein", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP)));
    public static final RegistryObject<Item> SOLIDFIED_PROTEIN = ITEMS.register("solidfied_protein", () -> new Item(new Item.Properties().tab(ModInit.ITEM_GROUP)));
    public static final RegistryObject<Item> JELLY_PLATTER = ITEMS.register("jelly_platter", () -> new BowlFoodItem(new Item.Properties().tab(ModInit.ITEM_GROUP).stacksTo(1).food(new FoodProperties.Builder().nutrition(5).effect(() -> new MobEffectInstance(MobEffects.HEAL, 10), 1.0F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, UtilLevel.TIME.SECOND.getTick() * 22, 1), 1.0F).effect(() -> new MobEffectInstance(PotionsInit.VULNUS_RECOVER.get(), UtilLevel.TIME.MINUTE.getTick() * 2), 1.0F).alwaysEat().build())));

    //ModItemModels,zh,en,
//    public static final RegistryObject<Item> GLUE_BOTTLE = ITEMS.register("glue_bottle", () -> new ItemGlueBottle(new Item.Properties().tab(ModInit.ITEM_GROUP)));


    //tages
//    public static final TagKey<EntityType<?>> HYDROBIOS = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(JohnnySBiologicalNotes.MODID, "hydrobios"));

    //Feature注册
    public static final RegistryObject<FeatureTridacnaShell> FEATURE_TRIDACNA_SHELL = FEATURES.register("tridacna_shell", FeatureTridacnaShell::new);

    //biomeModifierSerializer注册

//    RegistryObject<Codec<BiomeModifierTridacnaShell>>TRIDACNA_SHELL_MODIFIER   = BIOME_MODIFIERS.register("tridacna_shell_biome_modifier", BiomeModifierTridacnaShell::makeCodec);


//粒子注册

    public static final RegistryObject<SimpleParticleType> CHOP_PARTICLE =
            PARTICLE_TYPES.register("chop", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> JELLY_GLOW_PARTICLE =
            PARTICLE_TYPES.register("jelly_glow", () -> new SimpleParticleType(true));

    //投掷器
    public static void initDispenser() {
        DispenseItemBehavior bucketDispenseBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(BlockSource blockSource, ItemStack stack) {
                DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem) stack.getItem();
                BlockPos blockpos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
                Level level = blockSource.getLevel();
                if (dispensiblecontaineritem.emptyContents((Player) null, level, blockpos, (BlockHitResult) null)) {
                    dispensiblecontaineritem.checkExtraContent((Player) null, level, stack, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultDispenseItemBehavior.dispense(blockSource, stack);
                }
            }
        };
        DispenserBlock.registerBehavior(CRAB_BUCKET.get(), bucketDispenseBehavior);

    }

    private static final EntityType registerEntity(EntityType.Builder builder, String entityName) {
        return (EntityType) builder.build(entityName);
    }
    private static final EntityType registerFarSpawnEntity(EntityType.Builder builder, String entityName) {
        return (EntityType) builder.canSpawnFarFromPlayer().build(entityName);
    }
    public static Object getFieldValue(String fieldName) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        // 这里假设要获取的类名为"ClassName"


        Class<?> clazz = RegistrationInit.class;

        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);  // 设置为可访问，即使是私有成员变量也可以获取到

        Object value = field.get(clazz.newInstance());  // 创建类的实例并获取成员变量的值
//        RecordCodecBuilder<BiomeModifierTridacnaShell, HolderSet<Biome>> biomes = Biome.LIST_CODEC.fieldOf("biomes").forGetter(BiomeModifierTridacnaShell::biomes)

        return value;
    }

}
