package org.allaymc.server.block.component.common;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.common.BlockBaseComponent;
import org.allaymc.api.block.component.event.BlockOnInteractEvent;
import org.allaymc.api.block.component.event.BlockOnNeighborUpdateEvent;
import org.allaymc.api.block.component.event.BlockOnPlaceEvent;
import org.allaymc.api.block.component.event.BlockOnReplaceEvent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public class BlockBaseComponentImpl implements BlockBaseComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_base_component");

    @Manager
    protected ComponentManager<?> manager;

    protected BlockType<? extends BlockBehavior> blockType;

    public BlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        this.blockType = blockType;
    }

    @Override
    public BlockType<? extends BlockBehavior> getBlockType() {
        return blockType;
    }

    @Override
    public void onNeighborUpdate(Vector3ic updated, Vector3ic neighbor, BlockFace face, Dimension dimension) {
        manager.callEvent(new BlockOnNeighborUpdateEvent(updated, neighbor, face, dimension));
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos blockState) {
    }

    @Override
    public void onScheduledUpdate(BlockStateWithPos blockState) {
    }

    @Override
    public boolean place(EntityPlayer player, Dimension dimension, BlockState blockState, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
        checkPlaceMethodParam(player, dimension, blockState, targetBlockPos, placeBlockPos, clickPos, blockFace);
        // TODO: check whether the old block can be replaced
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        return true;
    }

    @Override
    public void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState) {
        manager.callEvent(new BlockOnPlaceEvent(currentBlockState, newBlockState));
    }

    @Override
    public void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState) {
        manager.callEvent(new BlockOnReplaceEvent(currentBlockState, newBlockState));
    }

    @Override
    public void onBreak(BlockStateWithPos blockState, ItemStack usedItem, EntityPlayer player) {
        if (!blockState.blockState().getBlockType().getMaterial().isAlwaysDestroyable() && !usedItem.isCorrectToolFor(blockState.blockState())) return;
        var drops = getDrops(usedItem);
        if (drops.length == 0) return;
        for (var drop : drops) {
            player.getDimension().dropItem(drop, new Vector3f(blockState.pos()).add(0.5f, 0.5f, 0.5f));
        }
    }

    @Override
    public boolean onInteract(EntityPlayer player, ItemStack itemStack, Dimension dimension, Vector3ic blockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
        var event = new BlockOnInteractEvent(player, itemStack, dimension, blockPos, placeBlockPos, clickPos, blockFace, false);
        manager.callEvent(event);
        return event.isSuccess();
    }
}
