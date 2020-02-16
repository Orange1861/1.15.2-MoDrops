package com.orangevillager61.modrops.world;

import com.orangevillager61.modrops.lists.BlockList;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration 
{
	public static void setupOreGen()
	{
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			CountRangeConfig salt_ore_placement = new CountRangeConfig(5, 4, 0, 128);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.salt_ore.getDefaultState(), 10)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(salt_ore_placement)));
		}
	}
}
