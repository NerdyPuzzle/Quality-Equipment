
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.qualityequipment.init;

import net.qualityequipment.item.ReforgeGuiButtonItem;
import net.qualityequipment.QualityEquipmentMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

public class QualityEquipmentModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, QualityEquipmentMod.MODID);
	public static final RegistryObject<Item> REFORGING_STATION = block(QualityEquipmentModBlocks.REFORGING_STATION);
	public static final RegistryObject<Item> REFORGE_GUI_BUTTON = REGISTRY.register("reforge_gui_button", () -> new ReforgeGuiButtonItem());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
