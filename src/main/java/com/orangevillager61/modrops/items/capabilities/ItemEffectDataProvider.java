package com.orangevillager61.modrops.items.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ItemEffectDataProvider implements ICapabilitySerializable<INBT>{
	
	@CapabilityInject(IItemEffectData.class)
	public static final Capability<IItemEffectData> CAPABILITY_ITEM_EFFECT_DATA = null;
	
    private final LazyOptional<IItemEffectData> holder = LazyOptional.of(() -> CAPABILITY_ITEM_EFFECT_DATA.getDefaultInstance());
	
	private IItemEffectData instance = CAPABILITY_ITEM_EFFECT_DATA.getDefaultInstance();
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		return CAPABILITY_ITEM_EFFECT_DATA.orEmpty(cap, this.holder).cast();
	}
	 
	@Override
	public INBT serializeNBT()
	{
		return CAPABILITY_ITEM_EFFECT_DATA.getStorage().writeNBT(CAPABILITY_ITEM_EFFECT_DATA, this.instance, null);
	}
	
	@Override
	public void deserializeNBT(INBT nbt)
	{
		CAPABILITY_ITEM_EFFECT_DATA.getStorage().readNBT(CAPABILITY_ITEM_EFFECT_DATA, this.instance, null, nbt);
	}
}
