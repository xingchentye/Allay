package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCarpetStackInitializer {
  static void init() {
    ItemTypes.CARPET_TYPE = ItemTypeBuilder
            .builder(ItemCarpetStack.class)
            .vanillaItem(VanillaItemId.CARPET)
            .build();
  }
}
