package net.tisola.tutorial.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.tisola.tutorial.block.ModBlocks;
import net.tisola.tutorial.block.entity.MushiEntity;
import net.tisola.tutorial.particle.ModParticle;


public class Mushi extends MushroomPlantBlock implements BlockEntityProvider {

    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");
    private MushiEntity entity;

    public Mushi(Settings settings) {
        super(settings, null);

        setDefaultState(getStateManager().getDefaultState().with(CHARGED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.25f, 0.0f, 0.25f, 0.75f, 0.6f, 0.75f);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        entity = new MushiEntity(pos, state);
        return entity;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
//            player.sendMessage(Text.of("Hello, world!"), false);
        }
        MushiEntity entity = (MushiEntity) world.getBlockEntity(pos);
        if (entity == null)
            return ActionResult.FAIL;
        player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, .2f, .5f + entity.getCharge() / 8);
        if (entity.isCharged())
            world.setBlockState(pos, state.with(CHARGED, true));
        else
            entity.charge();
        return ActionResult.SUCCESS;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity _entity) {
        MushiEntity entity = (MushiEntity) world.getBlockEntity(pos);
        if (state.get(CHARGED)) {
//            spwanFrog(world, pos);
            spwanParticle(world, pos);
            world.setBlockState(pos, state.with(CHARGED, false));
        }

        entity.clear();
        super.onSteppedOn(world, pos, state, _entity);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
    }

    private static void spwanParticle(World world, BlockPos pos) {
        for(int i = 0; i < 360; i++) {
            if(i % 20 == 0) {
                world.addParticle(ModParticle.GREEN_FLAME,
                        pos.getX() + 0.5d, pos.getY() + 1, pos.getZ() + 0.5d,
                        Math.cos(i) * 0.25d, 0.15d, Math.sin(i) * 0.25d);
            }
        }
    }

    private static void spwanFrog(World world, BlockPos pos) {
        FrogEntity frogEntity = EntityType.FROG.create(world);
        assert frogEntity != null;
        BlockPos frogPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
        frogEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(frogPos));
        world.spawnEntity(frogEntity);
    }
}