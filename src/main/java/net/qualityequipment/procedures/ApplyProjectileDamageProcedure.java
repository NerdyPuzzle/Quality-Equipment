package net.qualityequipment.procedures;

import net.qualityequipment.init.QualityEquipmentModAttributes;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ApplyProjectileDamageProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getSource());
		}
	}

	public static void execute(DamageSource damagesource) {
		execute(null, damagesource);
	}

	private static void execute(@Nullable Event event, DamageSource damagesource) {
		if (damagesource == null)
			return;
		Entity cause = null;
		double modifier = 0;
		if (damagesource.is(DamageTypes.ARROW)) {
			cause = damagesource.getDirectEntity();
			if (!(cause == null)) {
				if (cause instanceof Player) {
					modifier = ((LivingEntity) cause).getAttribute(QualityEquipmentModAttributes.PROJECTILEDAMAGE.get()).getBaseValue();
					if (modifier != 1) {
						((LivingHurtEvent) event).setAmount(((LivingHurtEvent) event).getAmount() * (float) modifier);
					}
				}
			}
		}
	}
}
