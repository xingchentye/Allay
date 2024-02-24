package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardLightBlueStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardLightBlueStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_LIGHT_BLUE_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardLightBlueStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_LIGHT_BLUE_STAINED_GLASS)
            .build();
  }
}
