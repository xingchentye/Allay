package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement10Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement10StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_10_TYPE = ItemTypeBuilder
            .builder(ItemElement10Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_10)
            .build();
  }
}
