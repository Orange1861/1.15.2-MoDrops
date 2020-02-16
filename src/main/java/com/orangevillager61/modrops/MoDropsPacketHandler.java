package com.orangevillager61.modrops;

import com.orangevillager61.modrops.packet.MessageSendBlock;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class MoDropsPacketHandler {

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
	    new ResourceLocation(MoDrops.modid, "main"),
	    () -> PROTOCOL_VERSION,
	    PROTOCOL_VERSION::equals,
	    PROTOCOL_VERSION::equals
	);
	@SubscribeEvent
	public static void packetRegister()
	{
		 int messageNumber = 0;
		 INSTANCE.registerMessage(messageNumber++, MessageSendBlock.class, MessageSendBlock::encode, MessageSendBlock::new, MessageSendBlock::handle);
	}
}
