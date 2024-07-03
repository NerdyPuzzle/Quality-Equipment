
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.qualityequipment.init;

import net.qualityequipment.client.gui.ReforgingStationGUIScreen;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class QualityEquipmentModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(QualityEquipmentModMenus.REFORGING_STATION_GUI.get(), ReforgingStationGUIScreen::new);
		});
	}
}
