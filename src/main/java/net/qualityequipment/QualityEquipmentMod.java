package net.qualityequipment;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.qualityequipment.init.QualityEquipmentModTabs;
import net.qualityequipment.init.QualityEquipmentModMenus;
import net.qualityequipment.init.QualityEquipmentModItems;
import net.qualityequipment.init.QualityEquipmentModBlocks;
import net.qualityequipment.init.QualityEquipmentModBlockEntities;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.AbstractMap;

@Mod("quality_equipment")
public class QualityEquipmentMod {
	public static final Logger LOGGER = LogManager.getLogger(QualityEquipmentMod.class);
	public static final String MODID = "quality_equipment";

	public QualityEquipmentMod() {
		// Start of user code block mod constructor
		// End of user code block mod constructor
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		QualityEquipmentModBlocks.REGISTRY.register(bus);
		QualityEquipmentModBlockEntities.REGISTRY.register(bus);
		QualityEquipmentModItems.REGISTRY.register(bus);
		QualityEquipmentModTabs.REGISTRY.register(bus);
		QualityEquipmentModMenus.REGISTRY.register(bus);
		// Start of user code block mod init
		if (ModList.get().isLoaded("bettercombat")) {
			try {
				// Locate the classes from the Better Combat API
				Class<?> attackRangeExtensionsClass = Class.forName("net.bettercombat.api.client.AttackRangeExtensions");
				Class<?> modifierClass = Class.forName("net.bettercombat.api.client.AttackRangeExtensions$Modifier");
				Class<?> operationClass = Class.forName("net.bettercombat.api.client.AttackRangeExtensions$Operation");
				// Locate the register method
				java.lang.reflect.Method registerMethod = attackRangeExtensionsClass.getDeclaredMethod("register", Function.class);
				// Create the function to pass to the register method
				Function<Object, Object> function = (context) -> {
					try {
						// Use standard method calls to get player and attribute value
						Object player = context.getClass().getDeclaredMethod("player").invoke(context);
						double attributeValue = ((Player) player).getAttribute(ForgeMod.ENTITY_REACH.get()).getValue();
						// Create the Modifier instance using reflection
						java.lang.reflect.Constructor<?> modifierConstructor = modifierClass.getDeclaredConstructor(double.class, operationClass);
						Object operationAdd = Enum.valueOf((Class<Enum>) operationClass, "ADD");
						return modifierConstructor.newInstance(attributeValue - 3, operationAdd);
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				};
				// Register the function using reflection
				registerMethod.invoke(null, function);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// End of user code block mod init
	}

	// Start of user code block mod methods
	// End of user code block mod methods
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	public static void queueServerWork(int tick, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
			workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
	}

	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
			workQueue.forEach(work -> {
				work.setValue(work.getValue() - 1);
				if (work.getValue() == 0)
					actions.add(work);
			});
			actions.forEach(e -> e.getKey().run());
			workQueue.removeAll(actions);
		}
	}
}
