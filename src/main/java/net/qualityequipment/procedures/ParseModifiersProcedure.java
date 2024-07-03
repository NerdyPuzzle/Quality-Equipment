package net.qualityequipment.procedures;

import net.qualityequipment.network.QualityEquipmentModVariables;
import net.qualityequipment.configuration.ReforgesConfiguration;
import net.qualityequipment.QualityEquipmentMod;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class ParseModifiersProcedure {
	@SubscribeEvent
	public static void addAttributeModifier(ItemAttributeModifierEvent event) {
		execute(event, event.getItemStack());
	}

	public static void execute(ItemStack itemstack) {
		execute(null, itemstack);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack) {
		com.google.gson.JsonObject quality = new com.google.gson.JsonObject();
		com.google.gson.JsonObject modifier = new com.google.gson.JsonObject();
		AttributeModifier iterator = null;
		String modtype = "";
		if (itemstack.hasTag()) {
			if (itemstack.getTag().contains("Reforge")) {
				for (String stringiterator : ReforgesConfiguration.QUALITIES.get()) {
					String[] keypair = stringiterator.split("=");
					if ((itemstack.getOrCreateTag().getString("Reforge")).equals(keypair[0])) {
						quality = new Object() {
							public com.google.gson.JsonObject parse(String rawJson) {
								try {
									return new com.google.gson.Gson().fromJson(rawJson, com.google.gson.JsonObject.class);
								} catch (Exception e) {
									QualityEquipmentMod.LOGGER.error(e);
									return new com.google.gson.Gson().fromJson("{}", com.google.gson.JsonObject.class);
								}
							}
						}.parse(keypair[1]);
						com.google.gson.JsonArray modifiers = quality.get("modifiers").getAsJsonArray();
						modtype = quality.get("type").getAsString();
						if (itemstack.getItem() instanceof TieredItem || QualityEquipmentModVariables.tetraItems.contains(itemstack.getItem().getClass().getName())) {
							if ((modtype).equals("tool")) {
								if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.MAINHAND) {
									for (int index0 = 0; index0 < (int) modifiers.size(); index0++) {
										modifier = modifiers.get(index0).getAsJsonObject();;
										if ((modifier.get("operation").getAsString()).equals("addition")) {
											iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index0), modifier.get("value").getAsDouble(), AttributeModifier.Operation.ADDITION);
										} else if ((modifier.get("operation").getAsString()).equals("multiply_base")) {
											iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index0), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_BASE);
										} else {
											iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index0), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_TOTAL);
										}
										_event.addModifier(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(modifier.get("attribute").getAsString())), iterator);
									}
								}
								break;
							}
						} else if (itemstack.getItem() instanceof BowItem || itemstack.getItem() instanceof CrossbowItem || QualityEquipmentModVariables.tetraBows.contains(itemstack.getItem().getClass().getName())) {
							if ((modtype).equals("bow")) {
								if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.MAINHAND) {
									for (int index1 = 0; index1 < (int) modifiers.size(); index1++) {
										modifier = modifiers.get(index1).getAsJsonObject();;
										if ((modifier.get("operation").getAsString()).equals("addition")) {
											iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index1), modifier.get("value").getAsDouble(), AttributeModifier.Operation.ADDITION);
										} else if ((modifier.get("operation").getAsString()).equals("multiply_base")) {
											iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index1), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_BASE);
										} else {
											iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index1), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_TOTAL);
										}
										_event.addModifier(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(modifier.get("attribute").getAsString())), iterator);
									}
								}
								if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.OFFHAND) {
									for (int index2 = 0; index2 < (int) modifiers.size(); index2++) {
										modifier = modifiers.get(index2).getAsJsonObject();;
										if ((modifier.get("operation").getAsString()).equals("addition")) {
											iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index2), modifier.get("value").getAsDouble(), AttributeModifier.Operation.ADDITION);
										} else if ((modifier.get("operation").getAsString()).equals("multiply_base")) {
											iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index2), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_BASE);
										} else {
											iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index2), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_TOTAL);
										}
										_event.addModifier(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(modifier.get("attribute").getAsString())), iterator);
									}
								}
								break;
							}
						} else if (itemstack.getItem() instanceof ArmorItem) {
							if ((modtype).equals("armor") || (modtype).equals("helmet") || (modtype).equals("chestplate") || (modtype).equals("leggings") || (modtype).equals("boots")) {
								if (itemstack.getItem() instanceof ArmorItem item && item.getType() == ArmorItem.Type.HELMET) {
									if ((modtype).equals("armor") || (modtype).equals("helmet")) {
										if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.HEAD) {
											for (int index3 = 0; index3 < (int) modifiers.size(); index3++) {
												modifier = modifiers.get(index3).getAsJsonObject();;
												if ((modifier.get("operation").getAsString()).equals("addition")) {
													iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index3), modifier.get("value").getAsDouble(), AttributeModifier.Operation.ADDITION);
												} else if ((modifier.get("operation").getAsString()).equals("multiply_base")) {
													iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index3), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_BASE);
												} else {
													iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index3), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_TOTAL);
												}
												_event.addModifier(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(modifier.get("attribute").getAsString())), iterator);
											}
										}
										break;
									}
								} else if (itemstack.getItem() instanceof ArmorItem item && item.getType() == ArmorItem.Type.CHESTPLATE) {
									if ((modtype).equals("armor") || (modtype).equals("chestplate")) {
										if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.CHEST) {
											for (int index4 = 0; index4 < (int) modifiers.size(); index4++) {
												modifier = modifiers.get(index4).getAsJsonObject();;
												if ((modifier.get("operation").getAsString()).equals("addition")) {
													iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index4), modifier.get("value").getAsDouble(), AttributeModifier.Operation.ADDITION);
												} else if ((modifier.get("operation").getAsString()).equals("multiply_base")) {
													iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index4), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_BASE);
												} else {
													iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index4), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_TOTAL);
												}
												_event.addModifier(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(modifier.get("attribute").getAsString())), iterator);
											}
										}
										break;
									}
								} else if (itemstack.getItem() instanceof ArmorItem item && item.getType() == ArmorItem.Type.LEGGINGS) {
									if ((modtype).equals("armor") || (modtype).equals("leggings")) {
										if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.LEGS) {
											for (int index5 = 0; index5 < (int) modifiers.size(); index5++) {
												modifier = modifiers.get(index5).getAsJsonObject();;
												if ((modifier.get("operation").getAsString()).equals("addition")) {
													iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index5), modifier.get("value").getAsDouble(), AttributeModifier.Operation.ADDITION);
												} else if ((modifier.get("operation").getAsString()).equals("multiply_base")) {
													iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index5), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_BASE);
												} else {
													iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index5), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_TOTAL);
												}
												_event.addModifier(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(modifier.get("attribute").getAsString())), iterator);
											}
										}
										break;
									}
								} else {
									if ((modtype).equals("armor") || (modtype).equals("boots")) {
										if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.FEET) {
											for (int index6 = 0; index6 < (int) modifiers.size(); index6++) {
												modifier = modifiers.get(index6).getAsJsonObject();;
												if ((modifier.get("operation").getAsString()).equals("addition")) {
													iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index6), modifier.get("value").getAsDouble(), AttributeModifier.Operation.ADDITION);
												} else if ((modifier.get("operation").getAsString()).equals("multiply_base")) {
													iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index6), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_BASE);
												} else {
													iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index6), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_TOTAL);
												}
												_event.addModifier(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(modifier.get("attribute").getAsString())), iterator);
											}
										}
										break;
									}
								}
							}
						} else if (itemstack.getItem() instanceof ShieldItem || itemstack.getItem().getClass().getName().equals("se.mickelus.tetra.items.modular.impl.shield.ModularShieldItem")) {
							if ((modtype).equals("shield")) {
								if (event instanceof ItemAttributeModifierEvent _event && _event.getSlotType() == EquipmentSlot.OFFHAND) {
									for (int index7 = 0; index7 < (int) modifiers.size(); index7++) {
										modifier = modifiers.get(index7).getAsJsonObject();;
										if ((modifier.get("operation").getAsString()).equals("addition")) {
											iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index7), modifier.get("value").getAsDouble(), AttributeModifier.Operation.ADDITION);
										} else if ((modifier.get("operation").getAsString()).equals("multiply_base")) {
											iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index7), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_BASE);
										} else {
											iterator = new AttributeModifier(UUID.fromString(modifier.get("uuid").getAsString()), ("modifier_" + index7), modifier.get("value").getAsDouble(), AttributeModifier.Operation.MULTIPLY_TOTAL);
										}
										_event.addModifier(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(modifier.get("attribute").getAsString())), iterator);
									}
								}
								break;
							}
						}
					}
				}
			}
		}
	}
}
