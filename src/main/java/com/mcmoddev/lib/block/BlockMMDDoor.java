package com.mcmoddev.lib.block;

import java.util.Random;

import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.material.IMMDObject;
import com.mcmoddev.lib.material.MMDMaterial;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Door Block
 *
 * @author DrCyano
 *
 */
public class BlockMMDDoor extends net.minecraft.block.BlockDoor implements IMMDObject {

	private final MMDMaterial material;

	/**
	 *
	 * @param material
	 *            The material the door is made from
	 */
	public BlockMMDDoor(MMDMaterial material) {
		super((material.getToolHarvestLevel() > 0) ? material.getVanillaMaterial() : Material.ROCK);
		this.material = material;
		this.setSoundType(this.material.getSoundType());
		this.blockHardness = this.material.getBlockHardness();
		this.blockResistance = this.material.getBlastResistance();
		this.setHarvestLevel("pickaxe", this.material.getRequiredHarvestLevel());
		this.disableStats();
	}

	/**
	 * @deprecated
	 */
	@SideOnly(Side.CLIENT)
	@Override
	@Deprecated
	public ItemStack getItem(final World w, final BlockPos c, final IBlockState bs) {
		return new ItemStack(this.material.getItem(Names.DOOR));
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return (state.getValue(BlockDoor.HALF) == EnumDoorHalf.UPPER) ? null : this.material.getItem(Names.DOOR);
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos coord, IBlockState blockstate,
									final EntityPlayer player,
									final EnumHand hand, ItemStack heldItem,
									final EnumFacing face,
									final float partialX, final float partialY, final float partialZ) {
		if (this.material.getToolHarvestLevel() > 1)
			return false;
		final BlockPos pos = (blockstate.getValue(BlockDoor.HALF) == EnumDoorHalf.LOWER) ? coord : coord.down();
		final IBlockState bs = coord.equals(pos) ? blockstate : world.getBlockState(pos);
		if (bs.getBlock() != this)
			return false;
		blockstate = bs.cycleProperty(BlockDoor.OPEN);
		world.setBlockState(pos, blockstate, 2);
		world.markBlockRangeForRenderUpdate(pos, coord);
		world.playEvent(player, ((Boolean) blockstate.getValue(BlockDoor.OPEN)) ? 1003 : 1006, coord, 0);
		return true;
	}

	@Override
	public MMDMaterial getMMDMaterial() {
		return this.material;
	}
}
