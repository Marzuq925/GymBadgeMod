package net.marz.badgemod.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.marz.badgemod.MarzsGymBadgeMod;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;


public class Badge extends Block implements Waterloggable {

   public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
   public static final EnumProperty<Direction> FACE= EnumProperty.of("face", Direction.class);
   public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty UP_SCALE = BooleanProperty.of("upscale");

   private static final VoxelShape UP_SHAPE =
           Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D);

   private static final VoxelShape DOWN_SHAPE =
           Block.createCuboidShape(2.0D, 14.0D, 2.0D, 14.0D, 16.0D, 14.0D);

   private static final VoxelShape NORTH_SHAPE =
            Block.createCuboidShape(2.0D, 2.0D, 14.0D, 14.0D, 14.0D, 16.0D);

   private static final VoxelShape SOUTH_SHAPE =
            Block.createCuboidShape(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 2.0D);

   private static final VoxelShape EAST_SHAPE =
           Block.createCuboidShape(0.0D, 2.0D, 2.0D, 2.0D, 14.0D, 14.0D);

   private static final VoxelShape WEST_SHAPE =
            Block.createCuboidShape(14.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D);


   public Badge(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACE, Direction.UP).with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(UP_SCALE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACE, FACING, WATERLOGGED, UP_SCALE);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return state.getOutlineShape(view, pos, context);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
        Direction playerFacing = ctx.getHorizontalPlayerFacing().getOpposite();
        boolean inWater = ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER);
        return super.getDefaultState().with(FACE, direction).with(FACING, playerFacing).with(WATERLOGGED, inWater);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
       if (!world.isClient) {
           if (player.isSneaking()) {
               // Shift + right click
               boolean upscale = !state.get(UP_SCALE);
               world.setBlockState(pos, state.with(UP_SCALE, upscale));
           } else {
               // Normal right click

           }
       }
        return ActionResult.success(world.isClient);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
       return switch (state.get(FACE)){
           case UP -> UP_SHAPE;
           case DOWN -> DOWN_SHAPE;
           case NORTH -> NORTH_SHAPE;
           case SOUTH -> SOUTH_SHAPE;
           case EAST -> EAST_SHAPE;
           case WEST -> WEST_SHAPE;
       };
    }
}

