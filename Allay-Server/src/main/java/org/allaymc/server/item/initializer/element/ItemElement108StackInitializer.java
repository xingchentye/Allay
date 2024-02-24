package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement108Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement108StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_108_TYPE = ItemTypeBuilder
            .builder(ItemElement108Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_108)
            .build();
  }
}
