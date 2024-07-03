/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.qualityequipment.init;

import net.qualityequipment.QualityEquipmentMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.EntityType;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class QualityEquipmentModAttributes {
	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, QualityEquipmentMod.MODID);
	public static final RegistryObject<Attribute> DIGSPEED = ATTRIBUTES.register("dig_speed", () -> (new RangedAttribute("attribute." + QualityEquipmentMod.MODID + ".dig_speed", 1, -100, 100)).setSyncable(true));
	public static final RegistryObject<Attribute> PROJECTILEDAMAGE = ATTRIBUTES.register("projectile_damage", () -> (new RangedAttribute("attribute." + QualityEquipmentMod.MODID + ".projectile_damage", 1, -100, 100)).setSyncable(true));
	public static final RegistryObject<Attribute> JUMPHEIGHT = ATTRIBUTES.register("jump_height", () -> (new RangedAttribute("attribute." + QualityEquipmentMod.MODID + ".jump_height", 1, -100, 100)).setSyncable(true));

	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ATTRIBUTES.register(FMLJavaModLoadingContext.get().getModEventBus());
		});
	}

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
		event.add(EntityType.PLAYER, DIGSPEED.get());
		event.add(EntityType.PLAYER, PROJECTILEDAMAGE.get());
		event.add(EntityType.PLAYER, JUMPHEIGHT.get());
	}
}
