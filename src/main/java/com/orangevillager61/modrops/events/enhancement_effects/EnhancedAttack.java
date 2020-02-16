package com.orangevillager61.modrops.events.enhancement_effects;

import com.orangevillager61.modrops.MoDrops;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnhancedAttack {
	
	public PlayerEntity player;
	public LivingEntity target;
	private ItemStack item;
	private CompoundNBT nbt_has_bee_venom;
	private CompoundNBT nbt;
	
	@SubscribeEvent
	public void onEvent(AttackEntityEvent event)
	{
		if (event.getTarget() instanceof LivingEntity)
	    {
			this.player = event.getPlayer();
			this.target = (LivingEntity) event.getTarget();
			this.item = this.player.getActiveItemStack();
			this.nbt = this.item.getOrCreateTag();
			//Bee venom check
			this.nbt_has_bee_venom = this.item.getOrCreateChildTag(MoDrops.modid + "has_bee_venom");
			if(this.nbt_has_bee_venom.getBoolean(MoDrops.modid + "has_bee_venom") == true)
			{
				int bee_venom_strength = this.item.getChildTag(MoDrops.modid + "bee_venom_strength").getInt(MoDrops.modid + "bee_venom_strength");
				if (bee_venom_strength == 1)
				{
	                this.nbt.putBoolean(MoDrops.modid + "has_bee_venom", false);
	                this.nbt.putInt(MoDrops.modid + "bee_venom_strength", 0);
	                this.target.addPotionEffect(new EffectInstance(Effects.POISON, 24, 2));
				} else
				if (bee_venom_strength == 2)
				{
	                this.nbt.putInt(MoDrops.modid + "bee_venom_strength", 1);
	                this.target.addPotionEffect(new EffectInstance(Effects.POISON, 36, 2));
				} else
				if (bee_venom_strength == 3)
				{
	                this.nbt.putInt(MoDrops.modid + "bee_venom_strength", 2);
	                this.target.addPotionEffect(new EffectInstance(Effects.POISON, 48, 2));
				} else
				{
	                this.nbt.putBoolean(MoDrops.modid + "has_bee_venom", false);
	                this.nbt.putInt(MoDrops.modid + "bee_venom_strength", 0);
				}
			}
			this.item.setTag(this.nbt);
	    }
	}
}
