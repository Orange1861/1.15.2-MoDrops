package com.orangevillager61.modrops.inventory.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

public class OpenEnhancementContainer implements INamedContainerProvider {

	private ITextComponent name;
	private PlayerInventory playerinv;
	private BlockPos pos;
	
	   public OpenEnhancementContainer(PlayerInventory tory, ITextComponent name, BlockPos ppos) {
		      this.playerinv = tory;
		      this.name = name;
		      this.pos = ppos;
		   }
	   
	   
	@Override
	public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
		return new EnhancementContainer(p_createMenu_1_, this.playerinv, this.pos);
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.name;
	}
	   

}