package net.tisola.tutorial.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.tisola.tutorial.block.ModBlock;

import javax.annotation.Nullable;


public class RubyBlockEntity extends BlockEntity {
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(4, ItemStack.EMPTY);

    protected int currentCharge = 0;
    private final int maxCharge = 8;

    public RubyBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlock.RUBY_BLOCK_ENTITY, pos, state);
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
        return currentCharge == maxCharge;
    }

}
