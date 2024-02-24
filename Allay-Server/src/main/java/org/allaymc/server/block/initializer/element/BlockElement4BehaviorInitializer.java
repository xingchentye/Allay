package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement4Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement4BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_4_TYPE = BlockTypeBuilder
            .builder(BlockElement4Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_4)
            .build();
  }
}
