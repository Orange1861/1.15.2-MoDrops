package com.orangevillager61.modrops.events.mobdrops;

import java.util.Random;

import com.orangevillager61.modrops.lists.ItemList;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VillagerDrops{

	
	public static double rand;
    public static final Random r = new Random();

    @SubscribeEvent
	public void onEvent(LivingDropsEvent event)
	{
	    if (event.getEntity() instanceof VillagerEntity || event.getEntity() instanceof WanderingTraderEntity)
	    {
	        // DEBUG
	        ItemStack itemStackToDrop1 = new ItemStack(Items.EMERALD,  r.nextInt(3) + 1);
	        ItemStack itemStackToDrop2 = new ItemStack(Items.BONE,  r.nextInt(1));
	        if (event.getEntity().isBurning()){
	        ItemStack itemStackToDrop4 = new ItemStack(ItemList.cooked_villager,  r.nextInt(2) + 1);
	        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		        	  event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop4));
	        }
	        else if (!event.getEntity().isBurning()){
		        ItemStack itemStackToDrop4 = new ItemStack(ItemList.raw_villager,  r.nextInt(2) + 1);
		        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
			        	  event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop4));
	        }
	        ItemStack itemStackToDrop3 = new ItemStack(ItemList.villager_nose, 1);
	        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		        	  event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop1));
	        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		        	  event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop2));
	        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		        	  event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop3));
	        
	    }
	    
	} 
}
