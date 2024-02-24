package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement30Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement30StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_30_TYPE = ItemTypeBuilder
            .builder(ItemElement30Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_30)
            .build();
  }
}
