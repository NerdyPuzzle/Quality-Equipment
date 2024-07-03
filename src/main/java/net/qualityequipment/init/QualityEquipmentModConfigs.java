package net.qualityequipment.init;

import net.qualityequipment.configuration.ReforgesConfiguration;
import net.qualityequipment.QualityEquipmentMod;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = QualityEquipmentMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class QualityEquipmentModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ReforgesConfiguration.SPEC, "quality_equipment.toml");
		});
	}
}
