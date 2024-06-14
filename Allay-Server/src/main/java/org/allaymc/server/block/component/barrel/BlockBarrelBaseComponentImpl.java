package org.allaymc.server.block.component.barrel;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.annotation.RequireBlockProperty;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import static java.lang.Math.abs;
import static org.allaymc.api.data.VanillaBlockPropertyTypes.FACING_DIRECTION;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "facing_direction")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "open_bit")
public class BlockBarrelBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockBarrelBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(EntityPlayer player, Dimension dimension, BlockState blockState, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
        checkPlaceMethodParam(player, dimension, blockState, targetBlockPos, placeBlockPos, clickPos, blockFace);
        if (player != null) {
            if (abs(player.getLocation().x() - placeBlockPos.x()) < 2 && abs(player.getLocation().z() - placeBlockPos.z()) < 2) {
                var y = player.getLocation().y() + player.getEyeHeight();
                if (y - placeBlockPos.y() > 2) {
                    blockState = blockState.setProperty(FACING_DIRECTION, BlockFace.UP.ordinal());
                } else if (placeBlockPos.y() - y > 0) {
                    blockState = blockState.setProperty(FACING_DIRECTION, BlockFace.DOWN.ordinal());
                } else {
                    blockState = blockState.setProperty(FACING_DIRECTION, player.getHorizontalFace().opposite().ordinal());
                }
            } else {
                blockState = blockState.setProperty(FACING_DIRECTION, player.getHorizontalFace().opposite().ordinal());
            }
        }
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        return true;
    }
}
