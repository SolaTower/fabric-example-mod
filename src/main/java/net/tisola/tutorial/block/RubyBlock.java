package net.tisola.tutorial.block;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.util.math.Vec3d;
import net.tisola.tutorial.block.entity.RubyBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RubyBlock extends Block implements BlockEntityProvider {
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");
    private RubyBlockEntity entity;

    public RubyBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(CHARGED, false));
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        entity = new RubyBlockEntity(pos, state);
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
        RubyBlockEntity entity = (RubyBlockEntity) world.getBlockEntity(pos);
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
        RubyBlockEntity entity = (RubyBlockEntity) world.getBlockEntity(pos);
        if (state.get(CHARGED)) {
            FrogEntity frogEntity = (FrogEntity) EntityType.FROG.create(world);
            assert frogEntity != null;
            BlockPos frogPos = new BlockPos(pos.getX(),pos.getY(),pos.getZ() + 1);
            frogEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(frogPos));
            world.spawnEntity(frogEntity);
            world.setBlockState(pos, state.with(CHARGED, false));
        }

        entity.clear();
        super.onSteppedOn(world, pos, state, _entity);
    }
}