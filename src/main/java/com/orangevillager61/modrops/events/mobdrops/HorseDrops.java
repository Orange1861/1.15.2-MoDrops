package com.orangevillager61.modrops.events.mobdrops;

import java.util.Random;

import com.orangevillager61.modrops.lists.ItemList;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HorseDrops{

	
	public static double rand;
    public static final Random r = new Random();

    @SubscribeEvent
	public void onEvent(LivingDropsEvent event)
	{
	    if (event.getEntity() instanceof HorseEntity)
	    {
	    	if (event.getEntity().isBurning()){
	    		ItemStack itemStackToDrop1 = new ItemStack(ItemList.cooked_horse_meat,  r.nextInt(2) + 1);
		        ItemStack itemStackToDrop2 = new ItemStack(Items.BONE,  r.nextInt(1) + 0);
		        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		        	  event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop1));
		        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		        		event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop2));

	    	}else
	    	if (!event.getEntity().isBurning()){
	        // DEBUG
	        ItemStack itemStackToDrop1 = new ItemStack(ItemList.raw_horse_meat,  r.nextInt(2) + 1);
	        ItemStack itemStackToDrop2 = new ItemStack(Items.BONE,  r.nextInt(1) + 0);
	        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		              event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop1));
	        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		              event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop2));
	    }
	    }
	} 
}


	