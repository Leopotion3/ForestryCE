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
package forestry.mail;

import java.util.List;

import net.minecraft.world.item.ItemStack;

import com.mojang.authlib.GameProfile;

import forestry.api.mail.EnumAddressee;
import forestry.api.mail.EnumTradeStationState;
import forestry.api.mail.IMailAddress;
import forestry.api.mail.ITradeStationInfo;

public record TradeStationInfo(IMailAddress address, GameProfile owner, ItemStack tradegood, List<ItemStack> required,
							   EnumTradeStationState state) implements ITradeStationInfo {
	public TradeStationInfo {
		if (address.getType() != EnumAddressee.TRADER) {
			throw new IllegalArgumentException("TradeStation address must be a trader");
		}
	}
}
