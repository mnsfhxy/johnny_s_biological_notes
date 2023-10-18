package com.mnsfhxy.johnny_s_biological_notes.block;

import com.mnsfhxy.johnny_s_biological_notes.block.blockentity.BETridacnaShell;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Wearable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


public class BlockTridacnaShell extends  HorizontalDirectionalBlock implements Wearable, SimpleWaterloggedBlock,EntityBlock{
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private final VoxelShape openShape=Shapes.join(Stream.of(
            Block.box(14.299999999999999, 0.30000000000000004, 1, 14.299999999999999, 10.299999999999999, 14.999999999999996),
            Block.box(9.299999999999999, 0.30000000000000004, 1, 14.299999999999999, 10.299999999999999, 1),
            Block.box(9.299999999999999, 0.30000000000000004, 15, 14.299999999999999, 10.299999999999999, 15),
            Block.box(9.299999999999999, 10.299999999999999, 1, 14.300000000000002, 10.299999999999999, 15),
            Block.box(9.299999999999999, 0.30000000000000004, 1, 14.299999999999963, 0.30000000000000004, 15),
            Block.box(7.299999999999999, 0.30000000000000004, 1, 9.299999999999999, 10.299999999999997, 1),
            Block.box(7.299999999999999, 0.30000000000000004, 1, 9.299999999999999, 0.30000000000000004, 15),
            Block.box(7.299999999999995, 10.299999999999999, 1, 9.299999999999995, 10.299999999999999, 15),
            Block.box(7.299999999999999, 0.30000000000000004, 15, 9.299999999999999, 10.299999999999997, 15),
            Block.box(9.3, 0.30000000000000004, 1, 14.299999999999999, 10.299999999999999, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(1.6000000000000032, 0.30000000000000004, 1.0000000000000036, 1.6000000000000032, 10.299999999999999, 15),
            Block.box(1.6000000000000068, 0.30000000000000004, 15, 6.600000000000005, 10.299999999999999, 15),
            Block.box(1.6000000000000068, 0.30000000000000004, 1, 6.600000000000005, 10.299999999999999, 1),
            Block.box(1.6000000000000032, 10.299999999999999, 1, 6.600000000000005, 10.299999999999999, 15),
            Block.box(1.6000000000000423, 0.30000000000000004, 1, 6.600000000000005, 0.30000000000000004, 15),
            Block.box(6.600000000000005, 0.30000000000000004, 15, 8.600000000000005, 10.299999999999997, 15),
            Block.box(6.600000000000005, 0.30000000000000004, 1, 8.600000000000005, 0.30000000000000004, 15),
            Block.box(6.6000000000000085, 10.299999999999999, 1, 8.600000000000009, 10.299999999999999, 15),
            Block.box(6.600000000000005, 0.30000000000000004, 1, 8.600000000000005, 10.299999999999997, 1),
            Block.box(1.6000000000000068, 0.30000000000000004, 1, 6.600000000000003, 10.299999999999999, 15)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);

    private final VoxelShape closedShape=Shapes.join(Stream.of(
            Block.box(15.299999999999999, 0.30000000000000004, 1, 15.299999999999999, 10.299999999999999, 14.999999999999996),
            Block.box(10.299999999999999, 0.30000000000000004, 1, 15.299999999999999, 10.299999999999999, 1),
            Block.box(10.299999999999999, 0.30000000000000004, 15, 15.299999999999999, 10.299999999999999, 15),
            Block.box(10.299999999999999, 10.299999999999999, 1, 15.300000000000002, 10.299999999999999, 15),
            Block.box(10.299999999999999, 0.30000000000000004, 1, 15.299999999999963, 0.30000000000000004, 15),
            Block.box(8.299999999999999, 0.30000000000000004, 1, 10.299999999999999, 10.299999999999997, 1),
            Block.box(8.299999999999999, 0.30000000000000004, 1, 10.299999999999999, 0.30000000000000004, 15),
            Block.box(8.299999999999995, 10.299999999999999, 1, 10.299999999999995, 10.299999999999999, 15),
            Block.box(8.299999999999999, 0.30000000000000004, 15, 10.299999999999999, 10.299999999999997, 15),
            Block.box(10.3, 0.30000000000000004, 1, 15.299999999999999, 10.299999999999999, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(1.3000000000000025, 0.2999999999999997, 1.0000000000000036, 1.3000000000000025, 10.3, 15),
            Block.box(1.300000000000006, 0.2999999999999997, 15, 6.300000000000004, 10.3, 15),
            Block.box(1.300000000000006, 0.2999999999999997, 1, 6.300000000000004, 10.3, 1),
            Block.box(1.3000000000000025, 10.3, 1, 6.300000000000004, 10.3, 15),
            Block.box(1.3000000000000416, 0.2999999999999997, 1, 6.300000000000004, 0.2999999999999997, 15),
            Block.box(6.300000000000004, 0.2999999999999997, 15, 8.300000000000004, 10.299999999999997, 15),
            Block.box(6.300000000000004, 0.2999999999999997, 1, 8.300000000000004, 0.2999999999999997, 15),
            Block.box(6.300000000000008, 10.3, 1, 8.300000000000008, 10.3, 15),
            Block.box(6.300000000000004, 0.2999999999999997, 1, 8.300000000000004, 10.299999999999997, 1),
            Block.box(1.300000000000006, 0.2999999999999997, 1, 6.3000000000000025, 10.3, 15)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BETridacnaShell(pPos,pState);
    }


    public static boolean canSurvive(LevelReader pLevel, BlockPos pPos) {
        Holder<Biome> biome = pLevel.getBiome(pPos);
        return canBorn(biome);
    }
    public static boolean canBorn(Holder<Biome> biome){
        return biome.is(Biomes.WARM_OCEAN)||biome.is(Biomes.BEACH)||biome.is(Biomes.STONY_SHORE);

    }
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {

        return pState.getValue(STATE)==TridacnaShellState.CLOSED?closedShape:openShape;
//        return super.getShape(pState, pLevel, pPos, pContext);
    }

    public enum TridacnaShellState implements StringRepresentable{
        CLOSED("closed"),
        OPEN("open");
        private final String name;
        private TridacnaShellState(String pName){
            this.name=pName;
        }
        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
    public static final EnumProperty<TridacnaShellState> STATE=EnumProperty.create("state",TridacnaShellState.class);

    public BlockTridacnaShell() {
        super(BlockBehaviour.Properties.of(Material.STONE).strength(2.5F).sound(SoundType.WOOD).explosionResistance(2.5F).noOcclusion());
        this.registerDefaultState(this.getStateDefinition().any().setValue(STATE,TridacnaShellState.OPEN).setValue(WATERLOGGED, Boolean.FALSE));
    }
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            if (pLevel.hasNeighborSignal(pPos)) {
                pLevel.setBlock(pPos,pState.setValue(STATE,TridacnaShellState.CLOSED), 2);
            }

        }
    }
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(STATE,WATERLOGGED);
    }
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.BLOCK;
    }

    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            boolean flag = pState.getValue(STATE)==TridacnaShellState.CLOSED;
            if (flag != pLevel.hasNeighborSignal(pPos)) {
                if (flag) {
                    pLevel.scheduleTick(pPos, this, 4);
                } else {
                    pLevel.setBlock(pPos, pState.cycle(STATE), 2);
                }
            }
//            if ( pLevel.hasNeighborSignal(pPos)) {
//                pState.setValue(STATE,TridacnaShellState.CLOSED);
//
//            }else {
//                pState.setValue(STATE,TridacnaShellState.OPEN);
//            }
//            pLevel.setBlock(pPos,pState.cycle(STATE), 2);
        }
    }
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(STATE)==TridacnaShellState.CLOSED && !pLevel.hasNeighborSignal(pPos)) {
            pLevel.setBlock(pPos, pState.cycle(STATE), 2);
        }

    }
    public @NotNull FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }
}
