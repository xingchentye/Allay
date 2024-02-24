package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement112Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement112BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_112_TYPE = BlockTypeBuilder
            .builder(BlockElement112Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_112)
            .build();
  }
}
