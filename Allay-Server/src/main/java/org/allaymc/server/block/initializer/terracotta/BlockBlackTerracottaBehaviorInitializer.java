package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockBlackTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.BLACK_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockBlackTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_TERRACOTTA)
            .build();
  }
}
