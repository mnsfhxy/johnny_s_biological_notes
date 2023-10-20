package com.mnsfhxy.johnny_s_biological_notes.block;

import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BellAttachType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BlockEcoBottle extends HalfTransparentBlock {
    private final VoxelShape SHAPES= Stream.of(
            Block.box(3, 0, 13, 13, 10, 13),
            Block.box(13, 0, 3, 13, 10, 13),
            Block.box(3, 0, 3, 3, 10, 13),
            Block.box(3, 0, 3, 13, 10, 3),
            Block.box(3, 0, 3, 13, 0, 13),
            Shapes.join(Block.box(4, 10, 4, 12, 12, 12), Block.box(3, 10, 3, 13, 10, 13), BooleanOp.OR),
            Block.box(3, 0, 3, 13, 2, 13),
            Shapes.join(Block.box(2, 2, 8, 14, 8, 8), Block.box(2, 2, 8, 14, 8, 8), BooleanOp.OR)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPES;
//        return super.getShape(pState, pLevel, pPos, pContext);
    }
    public enum EcoBottleContentType implements StringRepresentable {
        EMPTY("empty"),
        BRAIN_CORAL("brain_coral"),
        FIRE_CORAL("fire_coral"),
        TUBE_CORAL("tube_coral"),
        HORN_CORAL("horn_coral"),
        BUBBLE_CORAL("bubble_coral");
        private final String name;

        private EcoBottleContentType(String pName) {
            this.name = pName;
        }

        public String getSerializedName() {
            return this.name;
        }
        public static EcoBottleContentType getType(String name){
            if (name.equals("brain_coral")) {
                return BRAIN_CORAL;
            } else if (name.equals("fire_coral")) {
                return FIRE_CORAL;
            } else if (name.equals("tube_coral")) {
                return TUBE_CORAL;
            }else if (name.equals("horn_coral")) {
                return HORN_CORAL;
            }else if (name.equals("bubble_coral")) {
                return BUBBLE_CORAL;
            }else {
                return EMPTY;
            }
        }
        public Item getItem() {
            if (name.equals("brain_coral")) {
                return Items.BRAIN_CORAL;
            } else if (name.equals("fire_coral")) {
                return Items.FIRE_CORAL;
            } else if (name.equals("tube_coral")) {
                return Items.TUBE_CORAL;
            }else if (name.equals("horn_coral")) {
                return Items.HORN_CORAL;
            }else if (name.equals("bubble_coral")) {
                return Items.BUBBLE_CORAL;
            }else {
                return Items.AIR;
            }
        }


    }
    public enum EcoBottleAttachmentType implements StringRepresentable {
        FLOOR("floor"),
        CEILING("ceiling");

        private final String name;

        private EcoBottleAttachmentType(String pName) {
            this.name = pName;
        }

        public String getSerializedName() {
            return this.name;
        }


    }
//    public static RegistryObject<BlockEcoBottle> getEcoBottleBlock(String name){
//        if (name.equals("brain_coral")) {
//            return RegistrationInit.BLOCK_ECO_BOTTLE_BRAIN_CORAL;
//        } else if (name.equals("fire_coral")) {
//            return RegistrationInit.BLOCK_ECO_BOTTLE_FIRE_CORAL;
//        } else if (name.equals("tube_coral")) {
//            return RegistrationInit.BLOCK_ECO_BOTTLE_TUBE_CORAL;
//        }else if (name.equals("horn_coral")) {
//            return RegistrationInit.BLOCK_ECO_BOTTLE_HORN_CORAL;
//        }else if (name.equals("bubble_coral")) {
//            return RegistrationInit.BLOCK_ECO_BOTTLE_BUBBLE_CORAL;
//        }else {
//            return null;
//        }
//    }
    public static final EnumProperty<EcoBottleAttachmentType> ATTACHMENT = EnumProperty.create("attachment", EcoBottleAttachmentType.class);
    public static final EnumProperty<EcoBottleContentType> CONTENT = EnumProperty.create("content", EcoBottleContentType.class);
    public static Predicate<BlockState> canPlaceIn = (p) -> {
        if (p.is(Blocks.FIRE_CORAL)) {
            return true;
        }
        if (p.is(Blocks.BRAIN_CORAL)) {
            return true;
        }
        if (p.is(Blocks.BUBBLE_CORAL)) {
            return true;
        }
        if (p.is(Blocks.HORN_CORAL)) {
            return true;
        }
        if (p.is(Blocks.TUBE_CORAL)) {
            return true;
        }
        return false;
    };

    public BlockEcoBottle() {
        super(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).noOcclusion().strength(0, 0));
        this.registerDefaultState(this.stateDefinition.any().setValue(ATTACHMENT, EcoBottleAttachmentType.FLOOR).setValue(CONTENT, EcoBottleContentType.EMPTY));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getClickedFace();
        BlockPos blockpos = pContext.getClickedPos();
        Direction.Axis direction$axis = direction.getAxis();

        if (direction$axis == Direction.Axis.Y) {
            BlockState blockstate = this.defaultBlockState().setValue(ATTACHMENT, direction == Direction.DOWN ? EcoBottleAttachmentType.CEILING : EcoBottleAttachmentType.FLOOR);
            if (blockstate.canSurvive(pContext.getLevel(), blockpos)) {
                return blockstate;
            }
        }
        return this.defaultBlockState();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ATTACHMENT, CONTENT);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        EcoBottleContentType content = pState.getValue(CONTENT);
        BlockState blockstate = (item instanceof BlockItem ? ((BlockItem) item).getBlock() : Blocks.AIR).defaultBlockState();
        if (blockstate.is(Blocks.AIR)) {
            if (content == EcoBottleContentType.EMPTY) {
                return InteractionResult.PASS;
            } else {
                pPlayer.setItemInHand(pHand, new ItemStack(content.getItem()));
                pLevel.setBlock(pPos, RegistrationInit.BLOCK_ECO_BOTTLE.get().defaultBlockState(),  3);
                return InteractionResult.SUCCESS;
            }
        } else {
            if (canPlaceIn.test(blockstate)&&content == EcoBottleContentType.EMPTY) {
                String name = blockstate.getBlock().getDescriptionId().replace("block.minecraft.", "").toLowerCase();
                pLevel.setBlock(pPos, RegistrationInit.BLOCK_ECO_BOTTLE.get().defaultBlockState().setValue(CONTENT,EcoBottleContentType.getType(name)),  3);
                itemstack.shrink(1);
                pPlayer.setItemInHand(pHand, itemstack);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
    }
}
