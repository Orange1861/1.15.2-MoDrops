package com.orangevillager61.modrops;

import com.orangevillager61.modrops.items.capabilities.IItemEffectData;
import com.orangevillager61.modrops.items.capabilities.ItemEffectUser;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class MoDropsCapabilitiesHandler {

	public static class ItemEffectDataStorage<T extends IItemEffectData> implements IStorage<T>
	{
		@Override
		public INBT writeNBT(Capability<T> capability, T instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			ItemEffectUser ItemEffect = (ItemEffectUser) instance;
			if (ItemEffect != null)
			{
				ItemEffect.writeToNBT(nbt);
			}
			else
			{
				nbt.putString("No_Data", "");
			}
			return nbt;
		}

		@Override
		public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt) {
			CompoundNBT tags = (CompoundNBT) nbt;
			ItemEffectUser ItemEffect = (ItemEffectUser) instance;
			ItemEffect.readFromNBT(tags);
		}
	}
}
