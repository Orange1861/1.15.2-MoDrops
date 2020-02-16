package com.orangevillager61.modrops.blocks;

import com.orangevillager61.modrops.MoDropsPacketHandler;
import com.orangevillager61.modrops.inventory.container.OpenEnhancementContainer;
import com.orangevillager61.modrops.packet.MessageSendBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EnhancementTable extends Block {

	   public EnhancementTable(Block.Properties properties) {
	      super(properties);
	   }
	   //@Override
	   //public ICapabilityProvider initCapabilities()
	   //{
		   
	   //}

	   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) { 
		   if (worldIn.isRemote) {
		         return ActionResultType.SUCCESS;
		      } else {
				 ServerPlayerEntity server_player = (ServerPlayerEntity) player;  
		    	 MoDropsPacketHandler.INSTANCE.sendToServer(new MessageSendBlock(pos));
		    	 NetworkHooks.openGui(server_player, new OpenEnhancementContainer(player.inventory, player.getDisplayName(), pos), b -> {  b.writeBlockPos(pos);} );
		         return ActionResultType.SUCCESS;
		      }
		   }
		}
