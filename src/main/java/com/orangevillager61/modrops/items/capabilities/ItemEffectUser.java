package com.orangevillager61.modrops.items.capabilities;

import com.orangevillager61.modrops.MoDrops;

import net.minecraft.nbt.CompoundNBT;

public class ItemEffectUser implements IItemEffectData{
	
	protected boolean has_poison = false;
	protected int poison_strength = 0; //Should go from 0-5 higher is more powerful
	
	protected boolean has_bee_venom = false;
	protected int bee_venom_strength = 0; //Should go from 0-3 higher is more powerful
	
	protected boolean has_bee_stinger_thorns = false;
	protected int bee_stinger_thorns_strength = 0; //Should go from 0-5 high is more powerful
	
	public ItemEffectUser()
	{
	}
	
	public ItemEffectUser readFromNBT(CompoundNBT nbt)
    {
		this.set_poison_boolean(nbt.getBoolean("has_poison"));
		this.set_bee_venom_boolean(nbt.getBoolean("has_bee_venom"));
		this.set_bee_stinger_thorns_boolean(nbt.getBoolean("has_bee_stinger_thorns"));
		this.set_poison_strength(nbt.getInt("poison_strength"));
		this.set_bee_venom_strength(nbt.getInt("bee_venom_strength"));
		this.set_bee_stinger_thorns_strength(nbt.getInt("bee_stinger_thorns_strength"));
        return this;
    }

    public CompoundNBT writeToNBT(CompoundNBT nbt)
    {
    	nbt.putBoolean("has_poison", this.get_poison_boolean());
    	nbt.putBoolean("has_bee_venom", this.get_bee_venom_boolean());
    	nbt.putBoolean("has_bee_stinger_thorns", this.get_bee_stinger_thorns_boolean());
    	nbt.putInt("poison_strength", this.get_poison_strength());
    	nbt.putInt("bee_venom_strength", this.get_poison_strength());
    	nbt.putInt("bee_stinger_thorns_strength", this.get_bee_stinger_thorns_strength());
		MoDrops.logger.info("Saved Values");
        return nbt;
    }
    
	@Override
	public void set_poison_boolean(Boolean bool) {
		this.has_bee_venom = bool;
	}

	@Override
	public boolean get_poison_boolean() {
		return this.has_poison;
	}
	
	@Override
	public void set_poison_strength(int in) {
		this.poison_strength = in;
	}

	@Override
	public int get_poison_strength() {
		return this.poison_strength;
	}

	@Override
	public void poison_effect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean get_bee_venom_boolean() {
		return this.has_bee_venom;
	}

	@Override
	public void set_bee_venom_boolean(Boolean bool) {
		this.has_bee_venom = bool;
	}

	@Override
	public void set_bee_venom_strength(int in) {
		this.bee_venom_strength = in;
	}

	@Override
	public int get_bee_venom_strength() {
		return this.bee_venom_strength;
	}

	@Override
	public void bee_venom_effect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean get_bee_stinger_thorns_boolean() {
		return this.has_bee_stinger_thorns;
	}

	@Override
	public void set_bee_stinger_thorns_boolean(Boolean bool) {
		this.has_bee_stinger_thorns = bool;
	}

	@Override
	public void set_bee_stinger_thorns_strength(int in) {
		this.bee_stinger_thorns_strength = in;
	}

	@Override
	public int get_bee_stinger_thorns_strength() {
		return this.bee_stinger_thorns_strength;
	}

	@Override
	public void bee_stinger_thorns_effect() {
		// TODO Auto-generated method stub
		
	}
    
}
