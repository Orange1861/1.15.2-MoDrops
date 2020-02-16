package com.orangevillager61.modrops.events.mobdrops;

import java.util.Random;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PigDrops{

	
	public static double rand;
    public static final Random r = new Random();

    @SubscribeEvent
	public void onEvent(LivingDropsEvent event)
	{
	    if (event.getEntity() instanceof PigEntity)
	    {
	        // DEBUG
	        ItemStack itemStackToDrop1 = new ItemStack(Items.BONE,  r.nextInt(1) + 0);
	        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		        	  event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop1));
	    }
	} 
}
