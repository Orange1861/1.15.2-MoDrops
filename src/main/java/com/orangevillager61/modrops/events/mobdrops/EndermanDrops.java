package com.orangevillager61.modrops.events.mobdrops;

import java.util.Random;

import com.orangevillager61.modrops.lists.ItemList;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EndermanDrops{

	public static double rand;
    public static final Random r = new Random();

    @SubscribeEvent
	public void onEvent(LivingDropsEvent event)
	{
	    if (event.getEntity() instanceof EndermanEntity)
	    {
	        // DEBUG
	    	if(r.nextInt(100)<25){
	        ItemStack itemStackToDrop = new ItemStack(ItemList.raw_ender_meat, r.nextInt(2) + 1);
	        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
	              event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop));
	    }
	} 
}
}

