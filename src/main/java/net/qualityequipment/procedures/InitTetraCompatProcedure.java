package net.qualityequipment.procedures;

import net.qualityequipment.network.QualityEquipmentModVariables;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class InitTetraCompatProcedure {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		execute();
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		QualityEquipmentModVariables.tetraItems.add("se.mickelus.tetra.items.modular.ModularItem");
		QualityEquipmentModVariables.tetraItems.add("se.mickelus.tetra.items.modular.impl.ModularSingleHeadedItem");
		QualityEquipmentModVariables.tetraItems.add("se.mickelus.tetra.items.modular.impl.ModularDoubleHeadedItem");
		QualityEquipmentModVariables.tetraItems.add("se.mickelus.tetra.items.modular.impl.ModularBladedItem");
		QualityEquipmentModVariables.tetraItems.add("se.mickelus.tetra.items.modular.impl.dynamic.DynamicModularItem");
		QualityEquipmentModVariables.tetraBows.add("se.mickelus.tetra.items.modular.impl.crossbow.ModularCrossbowItem");
		QualityEquipmentModVariables.tetraBows.add("se.mickelus.tetra.items.modular.impl.bow.ModularBowItem");
	}
}
