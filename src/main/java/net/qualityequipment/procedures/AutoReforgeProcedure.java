package net.qualityequipment.procedures;

import net.qualityequipment.network.QualityEquipmentModVariables;
import net.qualityequipment.configuration.ReforgesConfiguration;
import net.qualityequipment.QualityEquipmentMod;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;
import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber
public class AutoReforgeProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		boolean reforge = false;
		double randomr = 0;
		List<Object> toolreforges = new ArrayList<>();
		List<Object> bowreforges = new ArrayList<>();
		List<Object> shieldreforges = new ArrayList<>();
		List<Object> armorreforges = new ArrayList<>();
		List<Object> helmetreforges = new ArrayList<>();
		List<Object> chestplatereforges = new ArrayList<>();
		List<Object> leggingsreforges = new ArrayList<>();
		List<Object> bootsreforges = new ArrayList<>();
		com.google.gson.JsonObject quality = new com.google.gson.JsonObject();
		String type = "";
		if (ReforgesConfiguration.AUTO_REFORGE.get()) {
			if (!world.isClientSide()) {
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx);
							String classname = itemstackiterator.getItem().getClass().getName();
							if (itemstackiterator.getItem() instanceof TieredItem || QualityEquipmentModVariables.tetraItems.contains(classname) || QualityEquipmentModVariables.tetraBows.contains(classname)
									|| classname.equals("se.mickelus.tetra.items.modular.impl.shield.ModularShieldItem") || itemstackiterator.getItem() instanceof BowItem || itemstackiterator.getItem() instanceof CrossbowItem
									|| itemstackiterator.getItem() instanceof ShieldItem || itemstackiterator.getItem() instanceof ArmorItem) {
								if (!itemstackiterator.hasTag()) {
									reforge = true;
								}
								if (itemstackiterator.hasTag()) {
									if (!itemstackiterator.getTag().contains("Autoreforge") && !itemstackiterator.getTag().contains("Reforge")) {
										reforge = true;
									}
								}
								if (reforge) {
									if (!itemstackiterator.getOrCreateTag().getBoolean("Autoreforge")) {
										itemstackiterator.getOrCreateTag().putBoolean("Autoreforge", true);
										if ((double) ReforgesConfiguration.AUTO_REFORGE_CHANCE.get() >= Math.random()) {
											for (String stringiterator : ReforgesConfiguration.QUALITIES.get()) {
												String[] keypair = stringiterator.split("=");
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
												type = quality.get("type").getAsString();
												if ((type).equals("tool")) {
													toolreforges.add(keypair[0]);
												} else if ((type).equals("bow")) {
													bowreforges.add(keypair[0]);
												} else if ((type).equals("shield")) {
													shieldreforges.add(keypair[0]);
												} else if ((type).equals("armor")) {
													armorreforges.add(keypair[0]);
												} else if ((type).equals("helmet")) {
													helmetreforges.add(keypair[0]);
												} else if ((type).equals("chestplate")) {
													chestplatereforges.add(keypair[0]);
												} else if ((type).equals("leggings")) {
													leggingsreforges.add(keypair[0]);
												} else if ((type).equals("boots")) {
													bootsreforges.add(keypair[0]);
												}
											}
											if (itemstackiterator.getItem() instanceof TieredItem || QualityEquipmentModVariables.tetraItems.contains(classname)) {
												randomr = Mth.nextInt(RandomSource.create(), 1, (int) toolreforges.size());
												itemstackiterator.getOrCreateTag().putString("Reforge", (toolreforges.get((int) (randomr - 1)) instanceof String _s ? _s : ""));
											} else if (itemstackiterator.getItem() instanceof BowItem || itemstackiterator.getItem() instanceof CrossbowItem || QualityEquipmentModVariables.tetraBows.contains(classname)) {
												randomr = Mth.nextInt(RandomSource.create(), 1, (int) bowreforges.size());
												itemstackiterator.getOrCreateTag().putString("Reforge", (bowreforges.get((int) (randomr - 1)) instanceof String _s ? _s : ""));
											} else if (itemstackiterator.getItem() instanceof ShieldItem || classname.equals("se.mickelus.tetra.items.modular.impl.shield.ModularShieldItem")) {
												randomr = Mth.nextInt(RandomSource.create(), 1, (int) shieldreforges.size());
												itemstackiterator.getOrCreateTag().putString("Reforge", (shieldreforges.get((int) (randomr - 1)) instanceof String _s ? _s : ""));
											} else if (itemstackiterator.getItem() instanceof ArmorItem armor) {
												if (armor.getType() == ArmorItem.Type.HELMET) {
													for (int index0 = 0; index0 < (int) helmetreforges.size(); index0++) {
														armorreforges.add(helmetreforges.get((int) index0));
													}
												} else if (armor.getType() == ArmorItem.Type.CHESTPLATE) {
													for (int index1 = 0; index1 < (int) chestplatereforges.size(); index1++) {
														armorreforges.add(chestplatereforges.get((int) index1));
													}
												} else if (armor.getType() == ArmorItem.Type.LEGGINGS) {
													for (int index2 = 0; index2 < (int) leggingsreforges.size(); index2++) {
														armorreforges.add(leggingsreforges.get((int) index2));
													}
												} else {
													for (int index3 = 0; index3 < (int) bootsreforges.size(); index3++) {
														armorreforges.add(bootsreforges.get((int) index3));
													}
												}
												randomr = Mth.nextInt(RandomSource.create(), 1, (int) armorreforges.size());
												itemstackiterator.getOrCreateTag().putString("Reforge", (armorreforges.get((int) (randomr - 1)) instanceof String _s ? _s : ""));
											}
										}
									}
									reforge = false;
								}
							}
						}
					}
				}
			}
		}
	}
}
