package com.orangevillager61.modrops.inventory.container;

import com.orangevillager61.modrops.MoDrops;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

public class ContainerList {

	@ObjectHolder(MoDrops.modid + ":enhancement_container")
	public static ContainerType<EnhancementContainer> EnhancementTable;
	
}
