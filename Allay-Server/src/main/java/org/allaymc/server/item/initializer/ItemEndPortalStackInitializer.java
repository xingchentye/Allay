package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEndPortalStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndPortalStackInitializer {
  static void init() {
    ItemTypes.END_PORTAL_TYPE = ItemTypeBuilder
            .builder(ItemEndPortalStack.class)
            .vanillaItem(VanillaItemId.END_PORTAL)
            .build();
  }
}
