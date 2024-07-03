package net.qualityequipment.procedures;

import net.qualityequipment.init.QualityEquipmentModAttributes;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ApplyJumpHeightProcedure {
	@SubscribeEvent
	public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		double modifier = 0;
		if (entity instanceof Player) {
			modifier = ((LivingEntity) entity).getAttribute(QualityEquipmentModAttributes.JUMPHEIGHT.get()).getValue();
			if (!(modifier == 1)) {
				entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), (entity.getDeltaMovement().y() + 0.1 * (modifier - 1)), (entity.getDeltaMovement().z())));
			}
		}
	}
}
