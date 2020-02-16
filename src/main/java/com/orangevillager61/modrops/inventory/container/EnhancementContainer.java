package com.orangevillager61.modrops.inventory.container;

import com.orangevillager61.modrops.MoDrops;
import com.orangevillager61.modrops.blocks.EnhancementTable;
import com.orangevillager61.modrops.lists.BlockList;
import com.orangevillager61.modrops.lists.ItemList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class EnhancementContainer extends Container{
	private final IInventory outputSlot = new CraftResultInventory();
	public EnhancementTable enhancement_block;
	private LazyOptional<IItemHandler> handler; 

	   private final IInventory inputSlots = new Inventory(2) {
	      /**
	       * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
	       * it hasn't changed and skip it.
	       */
	      public void markDirty() {
	         super.markDirty();
	         EnhancementContainer.this.onCraftMatrixChanged(this);
	      }
	   };
	   private final IWorldPosCallable field_216980_g;
	   public final EnhancementTable block;
	   public int materialCost;
		public EnhancementContainer(int id, PlayerInventory playerInv, PacketBuffer extraData)
		{
			this(id, playerInv, extraData.readBlockPos());
		}

	   public EnhancementContainer(int p_i50102_1_, PlayerInventory p_i50102_2_, final BlockPos pos) {
	      super(ContainerList.EnhancementTable, p_i50102_1_);
	      this.field_216980_g = IWorldPosCallable.of(p_i50102_2_.player.world, pos);
	      World world = p_i50102_2_.player.world;
	      this.block = (EnhancementTable) world.getBlockState(pos).getBlock(); 
		  //this.handler = this.block.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		  //IItemHandler itemhandler = this.handler.orElse(null);
	      this.addSlot(new Slot(this.inputSlots, 0, 27, 47));
	      this.addSlot(new Slot(this.inputSlots, 1, 76, 47));
	      this.addSlot(new Slot(this.outputSlot, 2, 134, 47) {
	         /**
	          * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
	          */
	         public boolean isItemValid(ItemStack stack) {
	            return false;
	         }

	         /**
	          * Return whether this slot's stack can be taken from this slot.
	          */
	         public boolean canTakeStack(PlayerEntity playerIn) {
	            return (playerIn.abilities.isCreativeMode || this.getHasStack());
	         }

	         public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
		         ItemStack itemstack1 = EnhancementContainer.this.inputSlots.getStackInSlot(0);
		         ItemStack itemstack2 = EnhancementContainer.this.inputSlots.getStackInSlot(1);
		         CompoundNBT nbtvenom = itemstack1.getOrCreateChildTag(MoDrops.modid + "has_bee_venom");
		         boolean has_venom = nbtvenom.getBoolean(MoDrops.modid + "has_bee_venom");
		         EnhancementContainer.this.inputSlots.setInventorySlotContents(0, ItemStack.EMPTY);
	             if (!itemstack2.isEmpty() && itemstack2.getCount() > 0 && !has_venom == true && itemstack2.getItem() == ItemList.bee_venom) {
	          	   if(itemstack2.getCount() < 4)
	          	   {
	          		     itemstack2.shrink(itemstack2.getCount());
	                     EnhancementContainer.this.inputSlots.setInventorySlotContents(1, itemstack2);		                 
	          	   }
	          	   else
	          	   {
	          		     itemstack2.shrink(3);
	                     EnhancementContainer.this.inputSlots.setInventorySlotContents(1, itemstack2);
	          	   }
	             } 
	            EnhancementContainer.this.field_216980_g.consume((p_216931_1_, p_216931_2_) -> {
                   p_216931_1_.playEvent(1029, p_216931_2_, 0);
                   });
	            return stack;
	         }
	      });

	      for(int i = 0; i < 3; ++i) {
	         for(int j = 0; j < 9; ++j) {
	            this.addSlot(new Slot(p_i50102_2_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
	         }
	      }

	      for(int k = 0; k < 9; ++k) {
	         this.addSlot(new Slot(p_i50102_2_, k, 8 + k * 18, 142));
	      }

	   }

	   /**
	    * Callback for when the crafting matrix is changed.
	    */
	   public void onCraftMatrixChanged(IInventory inventoryIn) {
	      super.onCraftMatrixChanged(inventoryIn);
	      if (inventoryIn == this.inputSlots) {
	         this.updateRepairOutput();
	      }

	   }
	   /**
	    * called when the Anvil Input Slot changes, calculates the new result and puts it in the output slot
	    */
	   public void updateRepairOutput() {
	      ItemStack itemstack = this.inputSlots.getStackInSlot(0);
	      if (itemstack.isEmpty()) {
	         this.outputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
	      } else {
	    	  	 ItemStack itemstack1 = EnhancementContainer.this.inputSlots.getStackInSlot(0).copy();
		         ItemStack itemstack2 = EnhancementContainer.this.inputSlots.getStackInSlot(1);
		         CompoundNBT nbtvenom = itemstack1.getOrCreateChildTag(MoDrops.modid + "has_bee_venom");
		         boolean has_venom = nbtvenom.getBoolean(MoDrops.modid + "has_bee_venom");
	             if (!itemstack2.isEmpty() && itemstack2.getCount() > 0 && !has_venom == true && itemstack2.getItem() == ItemList.bee_venom) {
	          	   if(itemstack2.getCount() > 0 && itemstack2.getCount() < 4)
	          	   {	                 
	          		   CompoundNBT nbt = itemstack1.getOrCreateTag();
		                 nbt.putBoolean(MoDrops.modid + "has_bee_venom", true);
		                 nbt.putInt(MoDrops.modid + "bee_venom_strength", itemstack2.getCount());
		                 itemstack1.setTag(nbt);
		                 EnhancementContainer.this.outputSlot.setInventorySlotContents(1, itemstack1);
	          	   }
	          	   else {
	          		   CompoundNBT nbt = itemstack1.getOrCreateTag();
		                 nbt.putBoolean(MoDrops.modid + "has_bee_venom", true);
		                 nbt.putInt(MoDrops.modid + "bee_venom_strength", 1);
		                 itemstack1.write(nbt);
		                 itemstack1.setTag(nbt);
		                 EnhancementContainer.this.outputSlot.setInventorySlotContents(1, itemstack1);
	          	   }
	             }
	      }
	   }
	   /**
	    * Called when the container is closed.
	    */
	   public void onContainerClosed(PlayerEntity playerIn) {
	      super.onContainerClosed(playerIn);
	      this.field_216980_g.consume((p_216973_2_, p_216973_3_) -> {
	         this.clearContainer(playerIn, p_216973_2_, this.inputSlots);
	      });
	   }

	   /**
	    * Determines whether supplied player can use this container
	    */
	   public boolean canInteractWith(PlayerEntity playerIn) {
		      return (boolean) this.field_216980_g.applyOrElse((p_216979_1_, p_216979_2_) -> {
		         return !(p_216979_1_.getBlockState(p_216979_2_) == BlockList.enhancement_table.getDefaultState()) ? false : playerIn.getDistanceSq((double)p_216979_2_.getX() + 0.5D, (double)p_216979_2_.getY() + 0.5D, (double)p_216979_2_.getZ() + 0.5D) <= 64.0D;
		      }, true);
		   }

	   /**
	    * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
	    * inventory and the other inventory(s).
	    */
	   public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
	      ItemStack itemstack = ItemStack.EMPTY;
	      Slot slot = this.inventorySlots.get(index);
	      if (slot != null && slot.getHasStack()) {
	         ItemStack itemstack1 = slot.getStack();
	         itemstack = itemstack1.copy();
	         if (index == 2) {
	            if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
	               return ItemStack.EMPTY;
	            }

	            slot.onSlotChange(itemstack1, itemstack);
	         } else if (index != 0 && index != 1) {
	            if (index >= 3 && index < 39 && !this.mergeItemStack(itemstack1, 0, 2, false)) {
	               return ItemStack.EMPTY;
	            }
	         } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
	            return ItemStack.EMPTY;
	         }

	         if (itemstack1.isEmpty()) {
	            slot.putStack(ItemStack.EMPTY);
	         } else {
	            slot.onSlotChanged();
	         }

	         if (itemstack1.getCount() == itemstack.getCount()) {
	            return ItemStack.EMPTY;
	         }

	         slot.onTake(playerIn, itemstack1);
	      }

	      return itemstack;
	   }
	}