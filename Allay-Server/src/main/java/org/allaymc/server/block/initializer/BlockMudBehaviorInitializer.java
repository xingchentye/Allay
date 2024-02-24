package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMudBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMudBehaviorInitializer {
  static void init() {
    BlockTypes.MUD_TYPE = BlockTypeBuilder
            .builder(BlockMudBehavior.class)
            .vanillaBlock(VanillaBlockId.MUD)
            .build();
  }
}
