package com.mnsfhxy.johnny_s_biological_notes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Wearable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;

public class BlockTridacnaShell extends HorizontalDirectionalBlock implements Wearable {
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
        super(BlockBehaviour.Properties.of(Material.STONE).strength(2.5F).sound(SoundType.WOOD).explosionResistance(2.5F));
        this.registerDefaultState(this.getStateDefinition().any().setValue(STATE,TridacnaShellState.CLOSED));
    }
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            if ( pLevel.hasNeighborSignal(pPos)) {
                pState.setValue(STATE,TridacnaShellState.CLOSED);
            }else {
                pState.setValue(STATE,TridacnaShellState.OPEN);
            }
        }
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(STATE);
    }
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.BLOCK;
    }
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            if ( pLevel.hasNeighborSignal(pPos)) {
                pState.setValue(STATE,TridacnaShellState.CLOSED);
            }else {
                pState.setValue(STATE,TridacnaShellState.OPEN);
            }

        }
    }
}
