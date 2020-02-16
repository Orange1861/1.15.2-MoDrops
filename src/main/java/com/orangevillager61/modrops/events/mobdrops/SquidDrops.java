package com.orangevillager61.modrops.events.mobdrops;

import java.util.Random;

import com.orangevillager61.modrops.lists.ItemList;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SquidDrops{

	
	public static double rand;
    public static final Random r = new Random();

    @SubscribeEvent
	public void onEvent(LivingDropsEvent event)
	{
	    if (event.getEntity() instanceof SquidEntity)
	    {
	    	if (event.getEntity().isBurning()){
	    		ItemStack itemStackToDrop = new ItemStack(ItemList.cooked_calamari, r.nextInt(4) + 1);
	    		 event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
			        	  event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop));
	    		
	    	}
	    	else if (!event.getEntity().isBurning()){
	    	ItemStack itemStackToDrop = new ItemStack(ItemList.raw_calamari, r.nextInt(4) + 2);
	    	 event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		        	  event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop));
	    }
	} 
}
}


