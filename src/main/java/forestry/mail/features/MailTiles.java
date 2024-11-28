package forestry.mail.features;

import forestry.api.modules.ForestryModuleIds;
import forestry.mail.blocks.BlockTypeMail;
import forestry.mail.tiles.TileMailbox;
import forestry.mail.tiles.TileStampCollector;
import forestry.mail.tiles.TradeStationBlockEntity;
import forestry.modules.features.FeatureProvider;
import forestry.modules.features.FeatureTileType;
import forestry.modules.features.IFeatureRegistry;
import forestry.modules.features.ModFeatureRegistry;

@FeatureProvider
public class MailTiles {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ForestryModuleIds.MAIL);

	public static final FeatureTileType<TileMailbox> MAILBOX = REGISTRY.tile(TileMailbox::new, "mailbox", () -> MailBlocks.BASE.get(BlockTypeMail.MAILBOX).collect());
	public static final FeatureTileType<TileStampCollector> STAMP_COLLECTOR = REGISTRY.tile(TileStampCollector::new, "stamp_collector", () -> MailBlocks.BASE.get(BlockTypeMail.STAMP_COLLETOR).collect());
	public static final FeatureTileType<TradeStationBlockEntity> TRADER = REGISTRY.tile(TradeStationBlockEntity::new, "trader", () -> MailBlocks.BASE.get(BlockTypeMail.TRADE_STATION).collect());
}
