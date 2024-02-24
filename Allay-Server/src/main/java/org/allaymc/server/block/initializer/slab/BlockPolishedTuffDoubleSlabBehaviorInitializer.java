package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockPolishedTuffDoubleSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedTuffDoubleSlabBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_TUFF_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockPolishedTuffDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_TUFF_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
