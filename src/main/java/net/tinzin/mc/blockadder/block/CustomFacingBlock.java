package net.tinzin.mc.blockadder.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import javax.annotation.Nullable;

public class CustomFacingBlock extends FacingBlock {
	private boolean player;

	public CustomFacingBlock(Settings settings, boolean player) {
		super(settings);
		setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.with(FACING, mirror.apply(state.get(FACING)));
	}

	@Override
	//TODO: custom outline shape
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos) {
		return super.getOutlineShape(state, view, pos, ePos);
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return player? getDefaultState().with(FACING, ctx.getPlayerFacing()) : getDefaultState().with(FACING, ctx.getSide());
	}
}
