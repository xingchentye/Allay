package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemBambooSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooSlabStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemBambooSlabStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_SLAB)
            .build();
  }
}
