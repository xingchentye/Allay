package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement56Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement56BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_56_TYPE = BlockTypeBuilder
            .builder(BlockElement56Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_56)
            .build();
  }
}
