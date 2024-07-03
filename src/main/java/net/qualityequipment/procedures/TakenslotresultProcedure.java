package net.qualityequipment.procedures;

import net.qualityequipment.network.QualityEquipmentModVariables;
import net.qualityequipment.configuration.ReforgesConfiguration;
import net.qualityequipment.QualityEquipmentMod;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class TakenslotresultProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean repeat = false;
		double randomreforge = 0;
		List<Object> toolreforges = new ArrayList<>();
		List<Object> bowreforges = new ArrayList<>();
		List<Object> armorreforges = new ArrayList<>();
		List<Object> shieldreforges = new ArrayList<>();
		List<Object> helmetreforges = new ArrayList<>();
		List<Object> chestplatereforges = new ArrayList<>();
		List<Object> leggingsreforges = new ArrayList<>();
		List<Object> bootsreforges = new ArrayList<>();
		com.google.gson.JsonObject quality = new com.google.gson.JsonObject();
		String type = "";
		ItemStack refitem = ItemStack.EMPTY;
		if (world.isClientSide()) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.use")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.use")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
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
			} else if ((type).equals("armor")) {
				armorreforges.add(keypair[0]);
			} else if ((type).equals("shield")) {
				shieldreforges.add(keypair[0]);
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
		refitem = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY);
		String classname = refitem.getItem().getClass().getName();
		if (refitem.getItem() instanceof TieredItem || QualityEquipmentModVariables.tetraItems.contains(classname)) {
			randomreforge = Mth.nextInt(RandomSource.create(), 1, (int) toolreforges.size());
			refitem.getOrCreateTag().putString("Reforge", (toolreforges.get((int) (randomreforge - 1)) instanceof String _s ? _s : ""));
		}
		if (refitem.getItem() instanceof BowItem || refitem.getItem() instanceof CrossbowItem || QualityEquipmentModVariables.tetraBows.contains(classname)) {
			randomreforge = Mth.nextInt(RandomSource.create(), 1, (int) bowreforges.size());
			refitem.getOrCreateTag().putString("Reforge", (bowreforges.get((int) (randomreforge - 1)) instanceof String _s ? _s : ""));
		}
		if (refitem.getItem() instanceof ArmorItem armor) {
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
			randomreforge = Mth.nextInt(RandomSource.create(), 1, (int) armorreforges.size());
			refitem.getOrCreateTag().putString("Reforge", (armorreforges.get((int) (randomreforge - 1)) instanceof String _s ? _s : ""));
		}
		if (refitem.getItem() instanceof ShieldItem || classname.equals("se.mickelus.tetra.items.modular.impl.shield.ModularShieldItem")) {
			randomreforge = Mth.nextInt(RandomSource.create(), 1, (int) shieldreforges.size());
			refitem.getOrCreateTag().putString("Reforge", (shieldreforges.get((int) (randomreforge - 1)) instanceof String _s ? _s : ""));
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			((Slot) _slots.get(2)).remove(1);
			_player.containerMenu.broadcastChanges();
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			((Slot) _slots.get(1)).remove(1);
			_player.containerMenu.broadcastChanges();
		}
		{
			boolean _setval = true;
			entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.successhammer2 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		QualityEquipmentMod.queueServerWork((int) 3.5, () -> {
			{
				boolean _setval = false;
				entity.getCapability(QualityEquipmentModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.successhammer2 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		});
	}
}
