package net.tisola.tutorial.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.tisola.tutorial.block.ModBlocks;

import javax.annotation.Nullable;


public class MushiEntity extends BlockEntity {

    protected int currentCharge = 0;

    public MushiEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.MUSHI_BLOCK_ENTITY, pos, state);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }


    public float getCharge() {
        return (float) currentCharge;
    }

    public void charge() {
        currentCharge += 1;
    }

    public void clear() {
        currentCharge = 0;
    }

    public boolean isCharged() {
        final int maxCharge = 4;
        return currentCharge == maxCharge;
    }

}
