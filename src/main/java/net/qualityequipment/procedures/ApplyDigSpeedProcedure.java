package net.qualityequipment.procedures;

import net.qualityequipment.init.QualityEquipmentModAttributes;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Mod.EventBusSubscriber
public class ApplyDigSpeedProcedure {
	@SubscribeEvent
	public static void execute(PlayerEvent.BreakSpeed event) {
		float modifier = (float) event.getEntity().getAttribute(QualityEquipmentModAttributes.DIGSPEED.get()).getValue();
		if (modifier != 1) {
			float original = event.getOriginalSpeed();
			event.setNewSpeed(original * modifier);
		}
	}
}
