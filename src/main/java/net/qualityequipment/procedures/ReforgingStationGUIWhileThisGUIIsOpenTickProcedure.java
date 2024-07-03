package net.qualityequipment.procedures;

import net.qualityequipment.network.QualityEquipmentModVariables;
import net.qualityequipment.init.QualityEquipmentModItems;
import net.qualityequipment.configuration.ReforgesConfiguration;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;
import java.util.Map;

import java.io.File;

public class ReforgingStationGUIWhileThisGUIIsOpenTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File qualityequipment = new File("");
		com.google.gson.JsonObject mainjsonobject = new com.google.gson.JsonObject();
		String material = "";
		boolean exists = false;
		boolean hasOverride = false;
		ItemStack refitem = ItemStack.EMPTY;
		ItemStack refitem1 = ItemStack.EMPTY;
		if ((entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new QualityEquipmentModVariables.PlayerVariables())).update2 == 1) {
			if (new Object() {
				public int getAmount(int sltid) {
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
						if (stack != null)
							return stack.getCount();
					}
					return 0;
				}
			}.getAmount(0) > 0 || new Object() {
				public int getAmount(int sltid) {
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
						if (stack != null)
							return stack.getCount();
					}
					return 0;
				}
			}.getAmount(1) > 0) {
				{
					double _setval = 1;
					entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.update1 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (new Object() {
				public int getAmount(int sltid) {
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
						if (stack != null)
							return stack.getCount();
					}
					return 0;
				}
			}.getAmount(0) == 0 && new Object() {
				public int getAmount(int sltid) {
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
						if (stack != null)
							return stack.getCount();
					}
					return 0;
				}
			}.getAmount(1) == 0) {
				{
					double _setval = 0;
					entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.update1 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (new Object() {
				public int getAmount(int sltid) {
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
						if (stack != null)
							return stack.getCount();
					}
					return 0;
				}
			}.getAmount(0) > 0) {
				{
					double _setval = 1;
					entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.sword = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (new Object() {
				public int getAmount(int sltid) {
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
						if (stack != null)
							return stack.getCount();
					}
					return 0;
				}
			}.getAmount(0) == 0) {
				{
					double _setval = 0;
					entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.sword = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (new Object() {
				public int getAmount(int sltid) {
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
						if (stack != null)
							return stack.getCount();
					}
					return 0;
				}
			}.getAmount(1) > 0) {
				{
					double _setval = 1;
					entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.ingot = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if (new Object() {
				public int getAmount(int sltid) {
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
						if (stack != null)
							return stack.getCount();
					}
					return 0;
				}
			}.getAmount(1) == 0) {
				{
					double _setval = 0;
					entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.ingot = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			refitem = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY);
			refitem1 = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY);
			String classname = refitem.getItem().getClass().getName();
			if (!(refitem.getItem() == ItemStack.EMPTY.getItem()) && !ReforgesConfiguration.REFORGE_BLACKLIST.get().contains(ForgeRegistries.ITEMS.getKey(refitem.getItem()).toString())
					? refitem.getItem() instanceof ArmorItem || refitem.getItem() instanceof BowItem || refitem.getItem() instanceof CrossbowItem || refitem.getItem() instanceof ShieldItem || refitem.getItem() instanceof TieredItem
							|| classname.equals("se.mickelus.tetra.items.modular.impl.shield.ModularShieldItem") || QualityEquipmentModVariables.tetraItems.contains(classname) || QualityEquipmentModVariables.tetraBows.contains(classname)
					: false) {
				hasOverride = new Object() {
					public boolean hasOverride() {
						boolean has = false;
						for (String override : ReforgesConfiguration.MATERIAL_OVERRIDES.get()) {
							String[] keypair = override.split(",");
							if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == ForgeRegistries.ITEMS
									.getValue(new ResourceLocation(keypair[0]))) {
								has = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY)
										.getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation(keypair[1]));
								if (has)
									break;
							}
						}
						return has;
					}
				}.hasOverride();
				exists = refitem.getItem().isValidRepairItem(refitem, refitem1) ? !(new Object() {
					public boolean hasOverride() {
						boolean has = false;
						for (String override : ReforgesConfiguration.MATERIAL_OVERRIDES.get()) {
							String[] keypair = override.split(",");
							if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == ForgeRegistries.ITEMS
									.getValue(new ResourceLocation(keypair[0]))) {
								has = true;
								break;
							}
						}
						return has;
					}
				}.hasOverride()) : refitem1.getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation(((ReforgesConfiguration.FALLBACK_MATERIAL.get())).toLowerCase(java.util.Locale.ENGLISH)));
				if (hasOverride ? true : exists) {
					{
						boolean _setval = true;
						entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.successhammer = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack _setstack = new ItemStack(QualityEquipmentModItems.REFORGE_GUI_BUTTON.get()).copy();
						_setstack.setCount(1);
						((Slot) _slots.get(2)).set(_setstack);
						_player.containerMenu.broadcastChanges();
					}
				} else {
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						((Slot) _slots.get(2)).set(ItemStack.EMPTY);
						_player.containerMenu.broadcastChanges();
					}
					{
						boolean _setval = false;
						entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.successhammer = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			} else {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(2)).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
				{
					boolean _setval = false;
					entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.successhammer = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			{
				double _setval = 0;
				entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.update2 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
