package com.orangevillager61.modrops.items;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class MoDropsFoods {
	
	public static final Food  raw_calamari = (new Food.Builder()).hunger(2).saturation(0.15F).build();
	public static final Food  raw_villager = (new Food.Builder()).hunger(5).saturation(0.5F).effect(new EffectInstance(Effects.HUNGER, 300), 0.8F).build();
	public static final Food  cooked_villager = (new Food.Builder()).hunger(9).saturation(1.1F).build();
	public static final Food  salted_cooked_villager = (new Food.Builder()).hunger(10).saturation(1.45F).build();
	public static final Food  cooked_calamari  = (new Food.Builder()).hunger(5).saturation(0.45F).build();
	public static final Food  salted_cooked_calamari = (new Food.Builder()).hunger(6).saturation(0.8F).build();
	public static final Food  salted_cooked_porkchop = (new Food.Builder()).hunger(9).saturation(1.15F).build();
	public static final Food  salted_cooked_mutton = (new Food.Builder()).hunger(7).saturation(1.15F).build();
	public static final Food  salted_cooked_chicken  = (new Food.Builder()).hunger(7).saturation(0.95F).build();
	public static final Food  salted_cooked_rabbit =  (new Food.Builder()).hunger(6).saturation(0.95F).build();
	public static final Food salt = (new Food.Builder()).hunger(0).saturation(0F).effect(new EffectInstance(Effects.POISON, 10), 0.6F).build();

}
