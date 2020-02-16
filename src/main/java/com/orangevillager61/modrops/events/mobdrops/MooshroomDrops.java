package com.orangevillager61.modrops.events.mobdrops;

import java.util.Random;

import com.orangevillager61.modrops.lists.ItemList;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MooshroomDrops{

	public static double rand;
    public static final Random r = new Random();

    @SubscribeEvent
	public void onEvent(LivingDropsEvent event)
	{
	    if (event.getEntity() instanceof MooshroomEntity)
	    {
	        // DEBUG
	        ItemStack itemStackToDrop1 = new ItemStack(Items.BONE,  r.nextInt(1) + 0);
	        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		              event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop1));
	        if (r.nextInt(10)<5){
	        ItemStack itemStackToDrop2 = new ItemStack(ItemList.horn,  r.nextInt(2) + 0);
	        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		              event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop2));
	        }
	    }
	} 
}
