package com.orangevillager61.modrops;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orangevillager61.modrops.blocks.AdvancedBrineFilter;
import com.orangevillager61.modrops.blocks.EnhancementTable;
import com.orangevillager61.modrops.blocks.StoneBrineFilter;
import com.orangevillager61.modrops.blocks.Stone_Biomaker;
import com.orangevillager61.modrops.blocks.WoodenBrineFilter;
import com.orangevillager61.modrops.client.gui.EnhancementTableGUI;
import com.orangevillager61.modrops.config.Config;
import com.orangevillager61.modrops.config.MoDropsConfig;
import com.orangevillager61.modrops.events.enhancement_effects.EnhancedAttack;
import com.orangevillager61.modrops.events.mobdrops.CowDrops;
import com.orangevillager61.modrops.events.mobdrops.EndermanDrops;
import com.orangevillager61.modrops.events.mobdrops.HorseDrops;
import com.orangevillager61.modrops.events.mobdrops.PigDrops;
import com.orangevillager61.modrops.events.mobdrops.SheepDrops;
import com.orangevillager61.modrops.events.mobdrops.SkeletonDrops;
import com.orangevillager61.modrops.events.mobdrops.SquidDrops;
import com.orangevillager61.modrops.events.mobdrops.VillagerDrops;
import com.orangevillager61.modrops.events.mobdrops.ZombieDrops;
import com.orangevillager61.modrops.events.mobdrops.ZombiePigmanDrops;
import com.orangevillager61.modrops.inventory.container.ContainerList;
import com.orangevillager61.modrops.inventory.container.EnhancementContainer;
import com.orangevillager61.modrops.items.MoDropsFoods;
import com.orangevillager61.modrops.items.capabilities.IItemEffectData;
import com.orangevillager61.modrops.lists.BlockList;
import com.orangevillager61.modrops.lists.ItemList;
import com.orangevillager61.modrops.world.OreGeneration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ChorusFruitItem;
import net.minecraft.item.Foods;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("modrops")
public class MoDrops {
	
	public static MoDrops instance;
	public static final String modid = "modrops";
	public static final Logger logger = LogManager.getLogger(modid);
	
	public static final ItemGroup MoDropsItemGroup = new MoDropsItemGroup();
	
	public MoDrops()
	{
		instance = this;
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.config);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addGenericListener(ContainerType.class, this::containerEvent);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		MinecraftForge.EVENT_BUS.register(this);
		
		Config.loadConfig(Config.config, FMLPaths.CONFIGDIR.get().resolve("modrops.toml").toString());
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		if (MoDropsConfig.enable_salt_gen.get() == true)
		{
			OreGeneration.setupOreGen();
		}
		if (MoDropsConfig.enable_drops.get() == true)
		{
	      	MinecraftForge.EVENT_BUS.register(new HorseDrops());
	      	MinecraftForge.EVENT_BUS.register(new SquidDrops());
	     	MinecraftForge.EVENT_BUS.register(new EndermanDrops());
	     	MinecraftForge.EVENT_BUS.register(new VillagerDrops());
	     	MinecraftForge.EVENT_BUS.register(new CowDrops());     	
	     	MinecraftForge.EVENT_BUS.register(new PigDrops());
	     	MinecraftForge.EVENT_BUS.register(new ZombiePigmanDrops());
	     	MinecraftForge.EVENT_BUS.register(new ZombieDrops());
	     	MinecraftForge.EVENT_BUS.register(new SkeletonDrops());
	     	MinecraftForge.EVENT_BUS.register(new SheepDrops());
		}
     	MinecraftForge.EVENT_BUS.register(new EnhancedAttack());
    	//CapabilityManager.INSTANCE.register(IItemEffectData.class, new ItemEffectDataStorage<>(), () -> new ItemEffectUser());
		MoDropsPacketHandler.packetRegister();
	}
	@SubscribeEvent
	public void containerEvent(final RegistryEvent.Register<ContainerType<?>> event)
	{
		event.getRegistry().registerAll(
				IForgeContainerType.create(EnhancementContainer::new).setRegistryName(modid + ":enhancement_container")
        );
	}
	
	private void clientRegistries(final FMLClientSetupEvent event)
	{
        ScreenManager.registerFactory(ContainerList.EnhancementTable, EnhancementTableGUI::new);
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{		
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			event.getRegistry().registerAll
			(
					//BlockItems
					ItemList.salt_block = new BlockItem(BlockList.salt_block, new Item.Properties().group(MoDropsItemGroup)).setRegistryName(BlockList.salt_block.getRegistryName()),
					ItemList.salt_ore = new BlockItem(BlockList.salt_ore, new Item.Properties().group(MoDropsItemGroup)).setRegistryName(BlockList.salt_ore.getRegistryName()),
					ItemList.advanced_brine_filter = new BlockItem(BlockList.advanced_brine_filter, new Item.Properties().group(MoDropsItemGroup)).setRegistryName("advanced_brine_filter"),
					ItemList.stone_brine_filter = new BlockItem(BlockList.stone_brine_filter, new Item.Properties().group(MoDropsItemGroup)).setRegistryName("stone_brine_filter"),
					ItemList.stone_biomaker = new BlockItem(BlockList.stone_biomaker, new Item.Properties().group(MoDropsItemGroup)).setRegistryName("stone_biomaker"),
					ItemList.enhancement_table = new BlockItem(BlockList.enhancement_table, new Item.Properties().group(MoDropsItemGroup)).setRegistryName("enhancement_table"),
					ItemList.acacia_brine_filter = new BlockItem(BlockList.acacia_brine_filter, new Item.Properties().group(MoDropsItemGroup)).setRegistryName("acacia_brine_filter"),
					ItemList.birch_brine_filter = new BlockItem(BlockList.birch_brine_filter, new Item.Properties().group(MoDropsItemGroup)).setRegistryName("birch_brine_filter"),
					ItemList.dark_oak_brine_filter = new BlockItem(BlockList.dark_oak_brine_filter, new Item.Properties().group(MoDropsItemGroup)).setRegistryName("dark_oak_brine_filter"),
					ItemList.jungle_brine_filter = new BlockItem(BlockList.jungle_brine_filter, new Item.Properties().group(MoDropsItemGroup)).setRegistryName("jungle_brine_filter"),
					ItemList.oak_brine_filter = new BlockItem(BlockList.oak_brine_filter, new Item.Properties().group(MoDropsItemGroup)).setRegistryName("oak_brine_filter"),
					ItemList.spruce_brine_filter = new BlockItem(BlockList.spruce_brine_filter, new Item.Properties().group(MoDropsItemGroup)).setRegistryName("spruce_brine_filter"),
					
					//Foods
					ItemList.raw_ender_meat = new ChorusFruitItem(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.salted_cooked_calamari)).setRegistryName(location("raw_ender_meat")),
					ItemList.raw_calamari = new Item(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.raw_calamari)).setRegistryName(location("raw_calamari")),
					ItemList.raw_villager = new Item(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.raw_villager)).setRegistryName(location("raw_villager")),
					ItemList.raw_horse_meat = new Item(new Item.Properties().group(MoDropsItemGroup).food(Foods.MUTTON)).setRegistryName(location("raw_horse_meat")),
					ItemList.cooked_calamari = new Item(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.cooked_calamari)).setRegistryName(location("cooked_calamari")),
					ItemList.cooked_villager = new Item(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.cooked_villager)).setRegistryName(location("cooked_villager")),
					ItemList.cooked_horse_meat = new Item(new Item.Properties().group(MoDropsItemGroup).food(Foods.COOKED_MUTTON)).setRegistryName(location("cooked_horse_meat")),
					ItemList.salted_cooked_calamari = new Item(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.salted_cooked_calamari)).setRegistryName(location("salted_cooked_calamari")),
					ItemList.salted_cooked_villager = new Item(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.salted_cooked_villager)).setRegistryName(location("salted_cooked_villager")),
					ItemList.salted_cooked_horse_meat = new Item(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.salted_cooked_mutton)).setRegistryName(location("salted_cooked_horse_meat")),
					ItemList.salted_cooked_chicken = new Item(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.salted_cooked_chicken)).setRegistryName(location("salted_cooked_chicken")),
					ItemList.salted_cooked_mutton = new Item(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.salted_cooked_mutton)).setRegistryName(location("salted_cooked_mutton")),
					ItemList.salted_cooked_porkchop = new Item(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.salted_cooked_porkchop)).setRegistryName(location("salted_cooked_porkchop")),
					ItemList.salted_cooked_steak = new Item(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.salted_cooked_porkchop)).setRegistryName(location("salted_cooked_steak")),
					ItemList.salted_cooked_rabbit = new Item(new Item.Properties().group(MoDropsItemGroup).food(MoDropsFoods.salted_cooked_rabbit)).setRegistryName(location("salted_cooked_rabbit")),
					ItemList.salted_rotten_flesh = new Item(new Item.Properties().group(MoDropsItemGroup).food(Foods.COOKED_RABBIT)).setRegistryName(location("salted_rotten_flesh")),
					ItemList.salted_cooked_salmon = new Item(new Item.Properties().group(MoDropsItemGroup).food(Foods.COOKED_RABBIT)).setRegistryName(location("salted_cooked_salmon")),
					
					//Tools
					ItemList.bone_axe = new AxeItem(ItemTier.GOLD, 6.0F, -3.0F, new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("bone_axe")),
					ItemList.bone_hoe = new HoeItem(ItemTier.GOLD, -3.0F, new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("bone_hoe")),
					ItemList.bone_sword = new SwordItem(ItemTier.GOLD, 3, -2.4F, new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("bone_sword")),
					ItemList.bone_shovel = new ShovelItem(ItemTier.GOLD, 1.5F, -3.0F, new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("bone_shovel")),
					ItemList.bone_pickaxe = new PickaxeItem(ItemTier.GOLD, 1, -2.8F, new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("bone_pickaxe")),
					
					//Items
					ItemList.adaptive_egg = new Item(new Item.Properties().group(MoDropsItemGroup).maxStackSize(1)).setRegistryName(location("adaptive_egg")),
					ItemList.adaptive_biomatter = new Item(new Item.Properties().group(MoDropsItemGroup).maxStackSize(1)).setRegistryName(location("adaptive_biomatter")),
					ItemList.compressed_biomatter = new Item(new Item.Properties().group(MoDropsItemGroup).maxStackSize(64)).setRegistryName(location("compressed_biomatter")),
					ItemList.biomatter = new Item(new Item.Properties().group(MoDropsItemGroup).maxStackSize(99)).setRegistryName(location("biomatter")),
					ItemList.horn = new Item(new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("horn")),
					ItemList.bucket_of_brine = new Item(new Item.Properties().group(MoDropsItemGroup).maxStackSize(16)).setRegistryName(location("bucket_of_brine")),
					ItemList.bucket_of_salt = new Item(new Item.Properties().group(MoDropsItemGroup).maxStackSize(16)).setRegistryName(location("bucket_of_salt")),
					ItemList.skelly_arm = new Item(new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("skelly_arm")),
					ItemList.skelly_leg = new Item(new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("skelly_leg")),
					ItemList.skelly_torso = new Item(new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("skelly_torso")),
					ItemList.zombie_arm = new Item(new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("zombie_arm")),
					ItemList.zombie_leg = new Item(new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("zombie_leg")),
					ItemList.zombie_torso = new Item(new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("zombie_torso")),
					ItemList.villager_nose = new Item(new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("villager_nose")),
					ItemList.bee_venom = new Item(new Item.Properties().group(MoDropsItemGroup)).setRegistryName(location("bee_venom")),
					ItemList.salt = new Item(new Item.Properties().group(MoDropsItemGroup).maxStackSize(99).food(MoDropsFoods.salt)).setRegistryName(location("salt"))
				);	
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
		{
			event.getRegistry().registerAll
			(
				BlockList.advanced_brine_filter = new AdvancedBrineFilter(Block.Properties.create(Material.IRON).sound(SoundType.STONE).hardnessAndResistance(20.0F, 60.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(location("advanced_brine_filter")),
				BlockList.stone_brine_filter = new StoneBrineFilter(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(6.0F, 15.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)).setRegistryName(location("stone_brine_filter")),
				BlockList.acacia_brine_filter = new WoodenBrineFilter(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(6.0F, 15.0F).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(location("acacia_brine_filter")),
				BlockList.birch_brine_filter = new WoodenBrineFilter(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(6.0F, 15.0F).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(location("birch_brine_filter")),
				BlockList.dark_oak_brine_filter = new WoodenBrineFilter(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(6.0F, 15.0F).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(location("dark_oak_brine_filter")),
				BlockList.jungle_brine_filter = new WoodenBrineFilter(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(6.0F, 15.0F).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(location("jungle_brine_filter")),
				BlockList.oak_brine_filter = new WoodenBrineFilter(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(6.0F, 15.0F).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(location("oak_brine_filter")),
				BlockList.spruce_brine_filter = new WoodenBrineFilter(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(6.0F, 15.0F).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(location("spruce_brine_filter")),
				BlockList.salt_block = new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(10.0F, 11.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(location("salt_block")),
				BlockList.stone_biomaker = new Stone_Biomaker(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(6.0F, 15.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)).setRegistryName(location("stone_biomaker")),
				BlockList.enhancement_table = new EnhancementTable(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(6.0F, 15.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)).setRegistryName(location("enhancement_table")),
				BlockList.salt_ore = new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 8.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(location("salt_ore"))
			);
			
		}
		private static ResourceLocation location(String name) 
		{
			return new ResourceLocation(modid, name);
		}
		
	}
};
