package cyano.basemetals.items;

import cyano.basemetals.material.IMetalObject;
import cyano.basemetals.material.MetalMaterial;
import cyano.basemetals.registry.IOreDictionaryEntry;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Rods
 * 
 * @author Jasmine Iwanek
 *
 */
public class ItemMetalRod extends net.minecraft.item.Item implements IOreDictionaryEntry, IMetalObject {

	private final MetalMaterial metal;
	private final String oreDict;

	/**
	 *
	 * @param metal The material to make the rod from
	 */
	public ItemMetalRod(MetalMaterial metal) {
		this.metal = metal;
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.oreDict = "rod" + metal.getCapitalizedName();
	}

	@Override
	public String getOreDictionaryName() {
		return this.oreDict;
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
}
