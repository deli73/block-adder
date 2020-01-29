package net.tinzin.mc.blockadder.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

import javax.annotation.Nullable;

public class NonRoofBlock extends Block {
	public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST, Direction.DOWN);

	public NonRoofBlock(Settings settings) {
		super(settings);
		setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.DOWN));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		Direction direction = state.get(FACING);
		if (direction == Direction.DOWN) direction = Direction.UP;
		BlockPos blockPos = pos.offset(direction.getOpposite());
		BlockState blockState = world.getBlockState(blockPos);
		return blockState.isSideSolidFullSquare(world, blockPos, direction);
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction side = ctx.getSide();
		if (side == Direction.UP) side = Direction.DOWN;
		return getDefaultState().with(FACING, side);
	}
}
