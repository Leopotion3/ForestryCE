/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.mail.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import forestry.core.config.Constants;
import forestry.core.gui.GuiForestry;
import forestry.core.render.ColourProperties;
import forestry.mail.tiles.TradeStationBlockEntity;

public class TradeStationScreen extends GuiForestry<TradeStationMenu> {
	private final TradeStationBlockEntity station;

	public TradeStationScreen(TradeStationMenu menu, Inventory inv, Component title) {
		super(Constants.TEXTURE_PATH_GUI + "/mailtrader2.png", menu, inv, title);
		this.station = menu.getTile();
		this.imageWidth = 226;
		this.imageHeight = 220;
	}

	@Override
	protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
		Component name = station.getTitle();
		graphics.drawString(this.font, name, textLayout.getCenteredOffset(name), 6, ColourProperties.INSTANCE.get("gui.mail.text"));

		Component receive = Component.translatable("for.gui.mail.receive");
		graphics.drawString(this.font, receive, textLayout.getCenteredOffset(receive, 70) + 51, 45, ColourProperties.INSTANCE.get("gui.mail.text"));

		Component send = Component.translatable("for.gui.mail.send");
		graphics.drawString(this.font, send, textLayout.getCenteredOffset(send, 70) + 51, 99, ColourProperties.INSTANCE.get("gui.mail.text"));

		super.renderLabels(graphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(graphics, partialTicks, mouseX, mouseY);

		graphics.drawString(this.font, menu.getAddress().getName(), leftPos + 19, topPos + 22, ColourProperties.INSTANCE.get("gui.mail.text"));
	}

	@Override
	protected void addLedgers() {
		addErrorLedger(station);
		addHintLedger("trade.station");
		addOwnerLedger(station);
	}
}
