package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement34Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement34BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_34_TYPE = BlockTypeBuilder
            .builder(BlockElement34Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_34)
            .build();
  }
}
