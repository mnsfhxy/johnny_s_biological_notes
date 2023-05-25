package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.Item.ItemModFishBucket;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.EntityCrab;
import com.mnsfhxy.johnny_s_biological_notes.effect.EffectFearWater;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Registry;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.Level;
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

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ModInit.ITEM_GROUP);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JohnnySBiologicalNotes.MODID);
    public static final DeferredRegister<MobEffect> MOBEFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, JohnnySBiologicalNotes.MODID);

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, JohnnySBiologicalNotes.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JohnnySBiologicalNotes.MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        //        BLOCKS.register(bus);
        ITEMS.register(bus);
        //        BLOCK_ENTITIES.register(bus);
        //        CONTAINERS.register(bus);
        ENTITIES.register(bus);
        //        STRUCTURES.register(bus);
        //        BIOME_MODIFIERS.register(bus);
        //        PLACED_FEATURES.register(bus);
        MOBEFFECTS.register(bus);
        PARTICLE_TYPES.register(bus);
    }

//Entity注册
    public static final RegistryObject<EntityType<EntityCrab>> CRAB =
            ENTITIES.register(
                    "crab",
                    () ->registerEntity(EntityType.Builder.of(EntityCrab::new, MobCategory.AMBIENT)
                            .sized(0.75F, 0.75F),"crab"));








//Item注册
    public static final RegistryObject<Item> CRAB_EGG =
            ITEMS.register(
                    "crab", () -> new ForgeSpawnEggItem(CRAB, 0xe25243, 0xc1bbb9, ITEM_PROPERTIES));

    public static final RegistryObject<Item> CRAB_SHELL=ITEMS.register("crab_shell",()->new Item(new Item.Properties().tab(ModInit.ITEM_GROUP)));
    public static final RegistryObject<Item> CRAB_MEAT=ITEMS.register("crab_meat",()->new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).meat().build())));
    public static final RegistryObject<Item> COOKED_CRAB_MEAT=ITEMS.register("cooked_crab_meat",()->new Item(new Item.Properties().tab(ModInit.ITEM_GROUP).food(new FoodProperties.Builder().nutrition(5).saturationMod(3f).meat().build())));
    public static final RegistryObject<Item> CRAB_BUCKET = ITEMS.register("crab_bucket", () -> new ItemModFishBucket(CRAB, Fluids.WATER, new Item.Properties().tab(ModInit.ITEM_GROUP)));

    //tages
//    public static final TagKey<EntityType<?>> HYDROBIOS = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(JohnnySBiologicalNotes.MODID, "hydrobios"));

    //effect注册
    public static RegistryObject<MobEffect> FEAR_WATER =
            MOBEFFECTS.register(
                    "fear_water_effect", () -> new EffectFearWater(MobEffectCategory.HARMFUL, 65793));

    //投掷器
    public static void initDispenser(){
        DispenseItemBehavior bucketDispenseBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(BlockSource blockSource, ItemStack stack) {
                DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem)stack.getItem();
                BlockPos blockpos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
                Level level = blockSource.getLevel();
                if (dispensiblecontaineritem.emptyContents((Player)null, level, blockpos, (BlockHitResult)null)) {
                    dispensiblecontaineritem.checkExtraContent((Player)null, level, stack, blockpos);
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
