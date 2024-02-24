package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement102Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement102StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_102_TYPE = ItemTypeBuilder
            .builder(ItemElement102Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_102)
            .build();
  }
}
