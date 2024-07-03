
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.qualityequipment.init;

import net.qualityequipment.block.ReforgingStationBlock;
import net.qualityequipment.QualityEquipmentMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

public class QualityEquipmentModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, QualityEquipmentMod.MODID);
	public static final RegistryObject<Block> REFORGING_STATION = REGISTRY.register("reforging_station", () -> new ReforgingStationBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
