package com.orangevillager61.modrops.blocks;

import com.orangevillager61.modrops.lists.ItemList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class WoodenBrineFilter extends Block{

	protected static final EnumProperty<EnumType> TYPE = EnumProperty.create("type", WoodenBrineFilter.EnumType.class);
	protected static final VoxelShape Shape = VoxelShapes.create(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
	
	public WoodenBrineFilter(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(TYPE, EnumType.EMPTY));
		
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		      return Shape;
		   }

	//@Override
	//public boolean isSolid(BlockState state) {
		      //return false;
		   //}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		      builder.add(TYPE);
	   }

	public enum EnumType implements IStringSerializable {
		EMPTY(0, "empty"), FULL1(1, "1full"), FULL2(3, "2full"), FULL(2, "full");

		private int ID;
		private String name;

		private EnumType(int ID, String name) {
			this.ID = ID;
			this.name = name;
		}
		
		@Override
		public String getName() {
			return this.name;
		}

		public int getID() {
			return this.ID;
		}

		@Override
		public String toString() {
			return this.getName();
		}

	}
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit){		 
		 
		if (state.get(TYPE) == EnumType.EMPTY){
			ItemStack itemstack = player.getHeldItemMainhand();
			if (itemstack.getItem() == Items.WATER_BUCKET){
				worldIn.setBlockState(pos, (this.getDefaultState().with(TYPE, EnumType.FULL1)));
				if (!player.isCreative()) {
					player.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.BUCKET, 1));
					return ActionResultType.SUCCESS;
					}
				return ActionResultType.PASS;
			}
		} else 
			if (state.get(TYPE) == EnumType.FULL1){
				ItemStack itemstack = player.getHeldItemMainhand();
				if (itemstack.getItem() == Items.WATER_BUCKET){
					worldIn.setBlockState(pos, (this.getDefaultState().with(TYPE, EnumType.FULL2)));
					if (!player.isCreative()) {
						player.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.BUCKET, 1));
						return ActionResultType.SUCCESS;
						}
					return ActionResultType.PASS;
				}
			} else
			if (state.get(TYPE) == EnumType.FULL2){
				ItemStack itemstack = player.getHeldItemMainhand();
				if (itemstack.getItem() == Items.WATER_BUCKET){
					worldIn.setBlockState(pos, (this.getDefaultState().with(TYPE, EnumType.FULL)));
					if (!player.isCreative()) {
						player.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.BUCKET, 1));
						return ActionResultType.SUCCESS;
							}
					return ActionResultType.PASS;
					}
				} else 
		if (state.get(TYPE) == EnumType.FULL){
			if (!worldIn.isRemote){
				worldIn.setBlockState(pos, (this.getDefaultState().with(TYPE, EnumType.EMPTY)));
				ItemEntity entityitem2 = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemList.bucket_of_brine, 1));
				worldIn.addEntity(entityitem2);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.SUCCESS;
			}
}