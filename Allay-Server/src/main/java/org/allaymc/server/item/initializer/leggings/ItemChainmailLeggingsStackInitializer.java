package org.allaymc.server.item.initializer.leggings;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.leggings.ItemChainmailLeggingsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainmailLeggingsStackInitializer {
  static void init() {
    ItemTypes.CHAINMAIL_LEGGINGS_TYPE = ItemTypeBuilder
            .builder(ItemChainmailLeggingsStack.class)
            .vanillaItem(VanillaItemId.CHAINMAIL_LEGGINGS)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
