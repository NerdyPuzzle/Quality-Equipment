package net.qualityequipment.procedures;

import net.qualityequipment.network.QualityEquipmentModVariables;

import net.minecraft.world.entity.Entity;

public class Cover1Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new QualityEquipmentModVariables.PlayerVariables())).sword == 1;
	}
}
