package com.orangevillager61.modrops.events.mobdrops;

import java.util.Random;

import net.minecraft.entity.passive.BeeEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BeeDrops{

	
	public static double rand;
    public static final Random r = new Random();

    @SubscribeEvent
	public void onEvent(LivingDropsEvent event)
	{
	    if (event.getEntity() instanceof BeeEntity)
	    {
	        // DEBUG
	        //ItemStack itemStackToDrop1 = new ItemStack(Items.BONE,  r.nextInt(1) + 0);
	        //event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		        	 // event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop1));
	    }
	} 
}
