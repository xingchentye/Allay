package org.allaymc.server.item.initializer.leggings;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.leggings.ItemGoldenLeggingsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenLeggingsStackInitializer {
  static void init() {
    ItemTypes.GOLDEN_LEGGINGS_TYPE = ItemTypeBuilder
            .builder(ItemGoldenLeggingsStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_LEGGINGS)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
