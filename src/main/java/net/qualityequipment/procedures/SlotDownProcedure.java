package net.qualityequipment.procedures;

import net.qualityequipment.network.QualityEquipmentModVariables;

import net.minecraft.world.entity.Entity;

public class SlotDownProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = 1;
			entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.update2 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
