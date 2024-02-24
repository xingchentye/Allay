package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement89Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement89BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_89_TYPE = BlockTypeBuilder
            .builder(BlockElement89Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_89)
            .build();
  }
}
