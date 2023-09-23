package com.mnsfhxy.johnny_s_biological_notes.block;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Wearable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockTridacnaShell extends Block implements Wearable {
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
        super(BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_ORANGE).strength(1.0F).sound(SoundType.WOOD));
        this.registerDefaultState(this.getStateDefinition().any().setValue(STATE,TridacnaShellState.CLOSED));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(STATE);
    }
}
