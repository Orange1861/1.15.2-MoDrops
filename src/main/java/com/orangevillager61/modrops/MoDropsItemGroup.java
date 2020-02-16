package com.orangevillager61.modrops;

import com.orangevillager61.modrops.lists.ItemList;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MoDropsItemGroup extends ItemGroup {

	public MoDropsItemGroup() {
		super("MoDropsItemGroup");
	}

	@Override
	public ItemStack createIcon() {

		return new ItemStack(ItemList.raw_horse_meat);
	}

}
