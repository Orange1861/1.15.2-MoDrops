package com.orangevillager61.modrops.packet;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageSendBlock {

	public BlockPos blockpos;

    public MessageSendBlock(BlockPos id)
    {
    	this.blockpos = id;
    }

    public MessageSendBlock(PacketBuffer buf)
    {
    	this.blockpos = buf.readBlockPos();
    }

    public void encode(PacketBuffer buf)
    {
    	buf.writeBlockPos(this.blockpos);
    }
    
    public void handle(Supplier<NetworkEvent.Context> context)
    {

    }
}
