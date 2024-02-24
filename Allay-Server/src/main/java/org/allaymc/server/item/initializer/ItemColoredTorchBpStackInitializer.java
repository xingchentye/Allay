package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemColoredTorchBpStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemColoredTorchBpStackInitializer {
  static void init() {
    ItemTypes.COLORED_TORCH_BP_TYPE = ItemTypeBuilder
            .builder(ItemColoredTorchBpStack.class)
            .vanillaItem(VanillaItemId.COLORED_TORCH_BP)
            .build();
  }
}
