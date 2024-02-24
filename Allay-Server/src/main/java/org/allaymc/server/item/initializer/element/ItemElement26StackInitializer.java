package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement26Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement26StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_26_TYPE = ItemTypeBuilder
            .builder(ItemElement26Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_26)
            .build();
  }
}
