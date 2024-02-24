package org.allaymc.server.item.initializer.boots;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boots.ItemChainmailBootsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainmailBootsStackInitializer {
  static void init() {
    ItemTypes.CHAINMAIL_BOOTS_TYPE = ItemTypeBuilder
            .builder(ItemChainmailBootsStack.class)
            .vanillaItem(VanillaItemId.CHAINMAIL_BOOTS)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
