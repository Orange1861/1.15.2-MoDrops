package com.orangevillager61.modrops.items.capabilities;

public interface IItemEffectData {
	
	void set_poison_boolean(Boolean bool);
	boolean get_poison_boolean();
	
	void set_poison_strength(int in);
	int get_poison_strength();
	
	void poison_effect();
	
	boolean get_bee_venom_boolean();
	void set_bee_venom_boolean(Boolean bool);
	
	void set_bee_venom_strength(int in);
	int get_bee_venom_strength();
	
	void bee_venom_effect();
	
	boolean get_bee_stinger_thorns_boolean();
	void set_bee_stinger_thorns_boolean(Boolean bool);
	
	void set_bee_stinger_thorns_strength(int in);
	int get_bee_stinger_thorns_strength();
	
	void bee_stinger_thorns_effect();
}
