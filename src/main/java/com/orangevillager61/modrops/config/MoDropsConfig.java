package com.orangevillager61.modrops.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MoDropsConfig {
	
	public static ForgeConfigSpec.BooleanValue enable_drops;
	public static ForgeConfigSpec.BooleanValue enable_salt_gen;
	//public static ForgeConfigSpec.BooleanValue enable_creeper_egg;

	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
	{
		server.comment("Enable Mob Drops");
		
		enable_drops = server
							.comment("Enabling this will allow mobs to drop MoDrops items."
									+ "Recommended to keep enabled.")
							.define("Optional.Enable_Mob_Drops", true);
		
		server.comment("Enable Salt Ore Generation");
		
		enable_salt_gen = server
							.comment("Enabling this will allow salt ore to generation in the world."
									+ "Recommended to keep enabled.")
							.define("Optional.Enable_Salt_Ore_Gen", true);
		
		//server.comment("Enable Creeper Spawn Egg Crafting");
		
		//enable_creeper_egg = server
							//.comment("Enabling this will players to craft creeper spawn eggs. Disable if you don't want player to be able to do this.")
							//.define("Optional.Enable_Creeper_Egg_Crafting", true);
	}
}
