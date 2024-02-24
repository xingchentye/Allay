package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemPiglinBruteSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPiglinBruteSpawnEggStackInitializer {
  static void init() {
    ItemTypes.PIGLIN_BRUTE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemPiglinBruteSpawnEggStack.class)
            .vanillaItem(VanillaItemId.PIGLIN_BRUTE_SPAWN_EGG)
            .build();
  }
}
