package net.qualityequipment.client.gui;

import net.qualityequipment.world.inventory.ReforgingStationGUIMenu;
import net.qualityequipment.procedures.ShowonhammerProcedure;
import net.qualityequipment.procedures.ShowoffhammerProcedure;
import net.qualityequipment.procedures.CoverProcedure;
import net.qualityequipment.procedures.Cover1Procedure;
import net.qualityequipment.procedures.ClickedchangeProcedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class ReforgingStationGUIScreen extends AbstractContainerScreen<ReforgingStationGUIMenu> {
	private final static HashMap<String, Object> guistate = ReforgingStationGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public ReforgingStationGUIScreen(ReforgingStationGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("quality_equipment:textures/screens/reforging_station_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("quality_equipment:textures/screens/reforging_station.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 256, 256, 256, 256);

		guiGraphics.blit(new ResourceLocation("quality_equipment:textures/screens/ingot_img.png"), this.leftPos + 62, this.topPos + 43, 0, 0, 50, 50, 50, 50);

		guiGraphics.blit(new ResourceLocation("quality_equipment:textures/screens/swordgui.png"), this.leftPos + 61, this.topPos + 4, 0, 0, 50, 50, 50, 50);

		if (ShowoffhammerProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("quality_equipment:textures/screens/hammeroff.png"), this.leftPos + -96, this.topPos + 23, 0, 0, 256, 256, 256, 256);
		}
		if (Cover1Procedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("quality_equipment:textures/screens/cover1.png"), this.leftPos + 62, this.topPos + 3, 0, 0, 50, 50, 50, 50);
		}
		if (CoverProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("quality_equipment:textures/screens/cover1.png"), this.leftPos + 62, this.topPos + 43, 0, 0, 50, 50, 50, 50);
		}
		if (ShowonhammerProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("quality_equipment:textures/screens/successhammer.png"), this.leftPos + -96, this.topPos + 39, 0, 0, 256, 256, 256, 256);
		}
		if (ClickedchangeProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("quality_equipment:textures/screens/hammeroff.png"), this.leftPos + -96, this.topPos + 23, 0, 0, 256, 256, 256, 256);
		}
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.quality_equipment.reforging_station_gui.label_reforging_station"), 44, 6, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.quality_equipment.reforging_station_gui.label_inventory"), 8, 71, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
