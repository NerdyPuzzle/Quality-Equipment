
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.qualityequipment.init;

import net.qualityequipment.world.inventory.ReforgingStationGUIMenu;
import net.qualityequipment.QualityEquipmentMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

public class QualityEquipmentModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, QualityEquipmentMod.MODID);
	public static final RegistryObject<MenuType<ReforgingStationGUIMenu>> REFORGING_STATION_GUI = REGISTRY.register("reforging_station_gui", () -> IForgeMenuType.create(ReforgingStationGUIMenu::new));
}
