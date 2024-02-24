package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGlassBottleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlassBottleStackInitializer {
  static void init() {
    ItemTypes.GLASS_BOTTLE_TYPE = ItemTypeBuilder
            .builder(ItemGlassBottleStack.class)
            .vanillaItem(VanillaItemId.GLASS_BOTTLE)
            .build();
  }
}
