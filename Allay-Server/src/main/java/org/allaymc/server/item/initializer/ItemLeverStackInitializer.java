package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLeverStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeverStackInitializer {
  static void init() {
    ItemTypes.LEVER_TYPE = ItemTypeBuilder
            .builder(ItemLeverStack.class)
            .vanillaItem(VanillaItemId.LEVER)
            .build();
  }
}
