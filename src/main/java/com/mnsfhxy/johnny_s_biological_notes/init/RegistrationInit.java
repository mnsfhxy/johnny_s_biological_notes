package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.Item.ItemKatana;
import com.mnsfhxy.johnny_s_biological_notes.Item.ItemModFishBucket;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.block.BlockJelly;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.EntityCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.EntityDrifter;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.EntityPeeper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistrationInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JohnnySBiologicalNotes.MODID);
    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ModInit.ITEM_GROUP);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JohnnySBiologicalNotes.MODID);

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, JohnnySBiologicalNotes.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JohnnySBiologicalNotes.MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        //        BLOCK_ENTITIES.register(bus);
        //        CONTAINERS.register(bus);
        ENTITIES.register(bus);
        //        STRUCTURES.register(bus);
        //        BIOME_MODIFIERS.register(bus);
        //        PLACED_FEATURES.register(bus);
        PARTICLE_TYPES.register(bus);
    }
    //Block注册
    public static final RegistryObject<BlockJelly> BLOCK_JELLY = BLOCKS.register("jelly_block", BlockJelly::new);
    public static final RegistryObject<Item> BLOCK_ITEM_JELLY = fromBlock(BLOCK_JELLY);
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
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
                    "jelly", () -> new ForgeSpawnEggItem(JELLY, 0x534620, 0x6c7911, ITEM_PROPERTIES));

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


    //tages
//    public static final TagKey<EntityType<?>> HYDROBIOS = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(JohnnySBiologicalNotes.MODID, "hydrobios"));

//粒子注册

    public static final RegistryObject<SimpleParticleType> CHOP_PARTICLE =
            PARTICLE_TYPES.register("chop", () -> new SimpleParticleType(true));

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
}
