package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPiglinBannerPatternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPiglinBannerPatternStackInitializer {
  static void init() {
    ItemTypes.PIGLIN_BANNER_PATTERN_TYPE = ItemTypeBuilder
            .builder(ItemPiglinBannerPatternStack.class)
            .vanillaItem(VanillaItemId.PIGLIN_BANNER_PATTERN)
            .build();
  }
}
