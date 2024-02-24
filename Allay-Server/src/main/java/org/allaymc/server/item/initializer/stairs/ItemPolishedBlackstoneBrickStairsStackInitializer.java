package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemPolishedBlackstoneBrickStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneBrickStairsStackInitializer {
  static void init() {
    ItemTypes.POLISHED_BLACKSTONE_BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBrickStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_STAIRS)
            .build();
  }
}
