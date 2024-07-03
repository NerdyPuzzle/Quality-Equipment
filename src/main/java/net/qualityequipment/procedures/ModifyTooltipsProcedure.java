package net.qualityequipment.procedures;

import org.checkerframework.checker.units.qual.s;

import net.qualityequipment.network.QualityEquipmentModVariables;
import net.qualityequipment.init.QualityEquipmentModItems;
import net.qualityequipment.configuration.ReforgesConfiguration;
import net.qualityequipment.QualityEquipmentMod;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;

@Mod.EventBusSubscriber
public class ModifyTooltipsProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getItemStack(), event.getToolTip());
	}

	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		execute(null, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double num = 0;
		double modifierindex = 0;
		List<Object> whitelist = new ArrayList<>();
		com.google.gson.JsonObject quality = new com.google.gson.JsonObject();
		com.google.gson.JsonObject modf = new com.google.gson.JsonObject();
		String modifier = "";
		String key = "";
		String modtype = "";
		String additive = "";
		String fuck = "";
		if (itemstack.getItem() == QualityEquipmentModItems.REFORGE_GUI_BUTTON.get()) {
			tooltip.clear();
		}
		if (itemstack.hasTag()) {
			if (itemstack.getTag().contains("Reforge")) {
				Component nbtc = null;
				Component idc = null;
				if (((ItemTooltipEvent) event).getFlags().isAdvanced()) {
					nbtc = tooltip.get(tooltip.size() - 1);
					idc = tooltip.get(tooltip.size() - 2);
					tooltip.remove(tooltip.size() - 1);
					tooltip.remove(tooltip.size() - 1);
				}
				tooltip.add(Component.literal(""));
				tooltip.add(Component.literal((Component.translatable("quality_equipment.quality").getString() + "" + Component.translatable((itemstack.getOrCreateTag().getString("Reforge"))).getString())));
				List<Component> modifiers = new ArrayList<>();
				List<Integer> erasures = new ArrayList<>();
				String classname = itemstack.getItem().getClass().getName();
				if (itemstack.getItem() instanceof TieredItem || QualityEquipmentModVariables.tetraItems.contains(classname)) {
					modtype = "tool";
				} else if (itemstack.getItem() instanceof BowItem || itemstack.getItem() instanceof CrossbowItem || QualityEquipmentModVariables.tetraBows.contains(classname)) {
					modtype = "bow";
				} else if (itemstack.getItem() instanceof ShieldItem || classname.equals("se.mickelus.tetra.items.modular.impl.shield.ModularShieldItem")) {
					modtype = "shield";
				} else if (itemstack.getItem() instanceof ArmorItem) {
					modtype = "armor";
				}
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
						fuck = quality.get("type").getAsString();
						if ((modtype).equals("armor")
								? (fuck).equals(modtype) || ((ArmorItem) itemstack.getItem()).getType() == ArmorItem.Type.HELMET && fuck.equals("helmet")
										|| ((ArmorItem) itemstack.getItem()).getType() == ArmorItem.Type.CHESTPLATE && fuck.equals("chestplate") || ((ArmorItem) itemstack.getItem()).getType() == ArmorItem.Type.LEGGINGS && fuck.equals("leggings")
										|| ((ArmorItem) itemstack.getItem()).getType() == ArmorItem.Type.BOOTS && fuck.equals("boots")
								: (fuck).equals(modtype)) {
							com.google.gson.JsonArray modifiersarr = quality.get("modifiers").getAsJsonArray();
							for (int index0 = 0; index0 < (int) modifiersarr.size(); index0++) {
								modf = modifiersarr.get(index0).getAsJsonObject();;
								additive = "" + Math.abs((modf.get("attribute").getAsString().split(":")[1]).equals("generic.knockback_resistance") ? modf.get("value").getAsDouble() * 10 : modf.get("value").getAsDouble());
								if (additive.endsWith(".0")) {
									additive = new java.text.DecimalFormat("###").format(new Object() {
										double convert(String s) {
											try {
												return Double.parseDouble(s.trim());
											} catch (Exception e) {
											}
											return 0;
										}
									}.convert(additive));
								}
								whitelist.add((ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(modf.get("attribute").getAsString())).getDescriptionId() + ",args=["
										+ ((modf.get("operation").getAsString()).equals("addition")
												? additive
												: new java.text.DecimalFormat("###").format(Math.abs(modf.get("value").getAsDouble() * ((modf.get("attribute").getAsString().split(":")[1]).equals("generic.knockback_resistance") ? 1000 : 100))))));
							}
						}
					}
				}
				boolean remove = true;
				if (itemstack.getItem() instanceof BowItem || itemstack.getItem() instanceof CrossbowItem) {
					remove = false;
				}
				for (int index1 = 0; index1 < (int) tooltip.size(); index1++) {
					if (tooltip.get((int) num).getString().equals(Component.translatable("item.modifiers.offhand").getString())) {
						if (itemstack.getItem() instanceof BowItem || itemstack.getItem() instanceof CrossbowItem) {
							remove = true;
						}
					}
					if (((tooltip.get((int) num).toString()).contains("[style={color=blue}]") || (tooltip.get((int) num).toString()).contains("[style={color=red}]"))
							&& (tooltip.get((int) num).toString()).contains("translation{key='attribute.modifier.")) {
						int index = 0;
						for (Object objitem : whitelist) {
							String[] listitems = ((String) objitem).split(",");
							String listitem1 = listitems[0];
							String listitem2 = listitems[1];
							String tooltipstr = tooltip.get((int) num).toString();
							if (tooltipstr.contains(listitem1) && tooltipstr.contains(listitem2)) {
								if (remove)
									whitelist.remove(index);
								modifiers.add(tooltip.get((int) num));
								erasures.add((int) num);
								break;
							}
							index++;
						}
					}
					num = num + 1;
				}
				for (int i = 0; i < erasures.size(); i++)
					tooltip.remove(erasures.get(i) - i);
				if (itemstack.getItem() instanceof TieredItem || QualityEquipmentModVariables.tetraItems.contains(itemstack.getItem().getClass().getName())) {
					tooltip.add(Component.literal(("\u00A77" + Component.translatable("item.modifiers.mainhand").getString())));
				} else if (itemstack.getItem() instanceof BowItem || itemstack.getItem() instanceof CrossbowItem || QualityEquipmentModVariables.tetraBows.contains(classname)) {
					tooltip.add(Component.literal(("\u00A77" + Component.translatable("item.modifiers.mainhand").getString())));
					tooltip.add(Component.literal(("\u00A77" + Component.translatable("item.modifiers.offhand").getString())));
				} else if (itemstack.getItem() instanceof ShieldItem || classname.equals("se.mickelus.tetra.items.modular.impl.shield.ModularShieldItem")) {
					tooltip.add(Component.literal(("\u00A77" + Component.translatable("item.modifiers.offhand").getString())));
				} else if (itemstack.getItem() instanceof ArmorItem item) {
					if (item.getType() == ArmorItem.Type.HELMET) {
						tooltip.add(Component.literal(("\u00A77" + Component.translatable("item.modifiers.head").getString())));
					} else if (item.getType() == ArmorItem.Type.CHESTPLATE) {
						tooltip.add(Component.literal(("\u00A77" + Component.translatable("item.modifiers.chest").getString())));
					} else if (item.getType() == ArmorItem.Type.LEGGINGS) {
						tooltip.add(Component.literal(("\u00A77" + Component.translatable("item.modifiers.legs").getString())));
					} else {
						tooltip.add(Component.literal(("\u00A77" + Component.translatable("item.modifiers.feet").getString())));
					}
				}
				List<String> tips = new ArrayList<>();
				for (int i = 0; i < modifiers.size(); i++) {
					String mod = modifiers.get(modifiers.size() - 1 - i).getString();
					String color = mod.contains("+") ? " §9" : " §c";
					tips.add(color + mod);
				}
				if (itemstack.getItem() instanceof BowItem || itemstack.getItem() instanceof CrossbowItem || itemstack.getItem() instanceof ShieldItem || classname.equals("se.mickelus.tetra.items.modular.impl.shield.ModularShieldItem")
						|| QualityEquipmentModVariables.tetraBows.contains(classname)) {
					erasures.clear();
					for (int index2 = 0; index2 < (int) tooltip.size(); index2++) {
						if (!(itemstack.getItem() instanceof ShieldItem) && !classname.equals("se.mickelus.tetra.items.modular.impl.shield.ModularShieldItem")) {
							if ((tooltip.get(index2).getString().equals(Component.translatable("item.modifiers.mainhand").getString()) || tooltip.get(index2).getString().equals(Component.translatable("item.modifiers.offhand").getString()))
									&& tooltip.get(index2 + 1).getString().isEmpty()) {
								erasures.add(index2);
								erasures.add(index2 + 1);
							}
						} else {
							if (tooltip.get(index2).getString().equals(Component.translatable("item.modifiers.offhand").getString()) && tooltip.get(index2 + 1).getString().isEmpty()) {
								erasures.add(index2);
								erasures.add(index2 + 1);
							}
						}
					}
					if (itemstack.getItem() instanceof BowItem || itemstack.getItem() instanceof CrossbowItem || QualityEquipmentModVariables.tetraBows.contains(classname)) {
						for (int i = 0; i < tips.size() / 2; i++)
							tips.remove(tips.size() - 1);
					}
					for (int i = 0; i < erasures.size(); i++)
						tooltip.remove(erasures.get(i) - i);
				}
				Collections.sort(tips, new Comparator<String>() {
					@Override
					public int compare(String s1, String s2) {
						if (s1.contains("-") && s2.contains("+")) {
							return 1; // move s1 below s2
						} else if (s1.contains("+") && s2.contains("-")) {
							return -1; // move s2 below s1
						} else {
							return s1.compareTo(s2); // regular alphabetical order
						}
					}
				});
				for (String tip : tips)
					tooltip.add(Component.literal(tip));
				if (((ItemTooltipEvent) event).getFlags().isAdvanced()) {
					tooltip.add(idc);
					tooltip.add(nbtc);
				}
			}
		}
	}
}
