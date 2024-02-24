package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWarpedFungusStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedFungusStackInitializer {
  static void init() {
    ItemTypes.WARPED_FUNGUS_TYPE = ItemTypeBuilder
            .builder(ItemWarpedFungusStack.class)
            .vanillaItem(VanillaItemId.WARPED_FUNGUS)
            .build();
  }
}
