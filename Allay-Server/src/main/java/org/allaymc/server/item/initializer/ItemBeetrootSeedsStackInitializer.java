package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBeetrootSeedsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeetrootSeedsStackInitializer {
  static void init() {
    ItemTypes.BEETROOT_SEEDS_TYPE = ItemTypeBuilder
            .builder(ItemBeetrootSeedsStack.class)
            .vanillaItem(VanillaItemId.BEETROOT_SEEDS)
            .build();
  }
}
