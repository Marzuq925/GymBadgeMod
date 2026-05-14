package net.marz.badgemod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.marz.badgemod.MarzsGymBadgeMod;


public class Badge extends Block {

   public static final BooleanProperty FLAT = BooleanProperty.of("flat");
   public static final EnumProperty<Direction> FACE= EnumProperty.of("face", Direction.class);

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
        this.setDefaultState(getDefaultState().with(FACE, Direction.UP));

    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACE);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return state.getOutlineShape(view, pos, context);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();

        return super.getDefaultState().with(FACE, direction);
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

