package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement104Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement104StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_104_TYPE = ItemTypeBuilder
            .builder(ItemElement104Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_104)
            .build();
  }
}
