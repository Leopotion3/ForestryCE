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
package forestry.core.circuits;

import java.util.Locale;

import net.minecraft.client.gui.components.Button;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.vertex.PoseStack;

import forestry.api.IForestryApi;
import forestry.api.circuits.ForestryCircuitSocketTypes;
import forestry.api.circuits.ICircuit;
import forestry.api.circuits.ICircuitLayout;
import forestry.api.farming.HorizontalDirection;
import forestry.core.config.Constants;
import forestry.core.gui.GuiForestry;
import forestry.core.inventory.ItemInventorySolderingIron;
import forestry.core.render.ColourProperties;

public class GuiSolderingIron extends GuiForestry<ContainerSolderingIron> {
	private final ItemInventorySolderingIron itemInventory;

	public GuiSolderingIron(ContainerSolderingIron container, Inventory inv, Component title) {
		super(Constants.TEXTURE_PATH_GUI + "/solder.png", container, inv, title);

		this.itemInventory = container.getItemInventory();
		this.imageWidth = 176;
		this.imageHeight = 205;
	}

	@Override
	protected void renderBg(PoseStack transform, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(transform, partialTicks, mouseX, mouseY);

		ICircuitLayout layout = menu.getLayout();
		Component title = layout.getName();
		this.font.draw(transform, title, leftPos + 8 + textLayout.getCenteredOffset(title, 138), topPos + 16, ColourProperties.INSTANCE.get("gui.screen"));

		for (int i = 0; i < 4; i++) {
			Component description;
			ItemStack tube = itemInventory.getItem(i + 2);
			ICircuit circuit = IForestryApi.INSTANCE.getCircuitManager().getCircuit(layout, tube);
			if (circuit == null) {
				description = Component.literal("(").append(Component.translatable("for.gui.noeffect")).append(")");
			} else {
				description = circuit.getDisplayName();
			}

			int row = i * 20;
			this.font.draw(transform, description, leftPos + 32, topPos + 36 + row, ColourProperties.INSTANCE.get("gui.screen"));

			if (tube.isEmpty()) {
				if (ForestryCircuitSocketTypes.FARM == layout.getSocketType()) {
					Direction farmDirection = HorizontalDirection.VALUES.get(i);
					String farmDirectionString = farmDirection.toString().toLowerCase(Locale.ENGLISH);
					Component localizedDirection = Component.translatable("for.gui.solder." + farmDirectionString);
					this.font.draw(transform, localizedDirection, leftPos + 17, topPos + 36 + row, ColourProperties.INSTANCE.get("gui.screen"));
				}
			}
		}
	}

	@Override
	public void init() {
		super.init();

		addRenderableWidget(new Button(leftPos + 12, topPos + 10, 12, 18, Component.literal("<"), b -> ContainerSolderingIron.regressSelection(0)));
		addRenderableWidget(new Button(leftPos + 130, topPos + 10, 12, 18, Component.literal(">"), b -> ContainerSolderingIron.advanceSelection(0)));
	}

	@Override
	protected void addLedgers() {
		addErrorLedger(itemInventory);
		addHintLedger("soldering.iron");
	}
}
