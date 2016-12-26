package cyano.basemetals.items;

import cyano.basemetals.material.IMetalObject;
import cyano.basemetals.material.MetalMaterial;
import cyano.basemetals.registry.IOreDictionaryEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;

/**
 *
 * @author Jasmine Iwanek
 *
 */
public class ItemMetalSlab extends ItemSlab implements IOreDictionaryEntry, IMetalObject {

	final MetalMaterial metal;

	/**
	 *
	 * @param metal The material to make the slab from
	 */
	public ItemMetalSlab(MetalMaterial metal) {
		super(metal.half_slab, metal.half_slab, metal.double_slab);
		this.metal = metal;
	}

	/**
	 *
	 * @param metal The material to make the slab from
	 * @param block The block to use to make the slab
	 * @param slab The half slab block to use to make the slab
	 * @param doubleslab The double slab block to use to make the slab
	 */
	public ItemMetalSlab(MetalMaterial metal, Block block, BlockSlab slab, BlockSlab doubleslab) {
		super(block, slab, doubleslab);
		this.metal = metal;
	}

	@Override
	public MetalMaterial getMaterial() {
		return this.metal;
	}

	@Override
	@Deprecated
	public MetalMaterial getMetalMaterial() {
		return this.metal;
	}

	@Override
	public String getOreDictionaryName() {
		return "slab" + this.metal.getCapitalizedName();
	}
}
