package com.orangevillager61.modrops.events.mobdrops;

import java.util.Random;

import com.orangevillager61.modrops.lists.ItemList;

import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ZombieDrops{

	
	public static double rand;
    public static final Random r = new Random();

    @SubscribeEvent
	public void onEvent(LivingDropsEvent event)
	{
	    if (event.getEntity() instanceof ZombieEntity)
	    {
	        ItemStack itemStackToDrop1 = new ItemStack(Items.BONE,  r.nextInt(2) + 0);
	        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		              event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop1));
	        if(r.nextInt(100)<40){
	        ItemStack itemStackToDrop2 = new ItemStack(ItemList.zombie_arm, r.nextInt(2) + 1);
	        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
		              event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop2));
	        }
	        if(r.nextInt(100)<40){
		        ItemStack itemStackToDrop3 = new ItemStack(ItemList.zombie_leg, r.nextInt(2) + 1);
		        event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
			              event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop3));
	        }
		    if(r.nextInt(100)<40){
			     ItemStack itemStackToDrop4 = new ItemStack(ItemList.zombie_torso);
			     event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
				          event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop4));
		    }
		    if(r.nextInt(100)<5){
			     ItemStack itemStackToDrop5 = new ItemStack(Blocks.ZOMBIE_HEAD);
			     event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), 
				          event.getEntity().getPosY(), event.getEntity().getPosZ(), itemStackToDrop5));
		    }
	    }
	    }}
	
