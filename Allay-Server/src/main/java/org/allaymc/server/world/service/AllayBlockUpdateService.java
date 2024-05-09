package org.allaymc.server.world.service;

import lombok.RequiredArgsConstructor;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.service.BlockUpdateService;
import org.joml.Vector3ic;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class AllayBlockUpdateService implements BlockUpdateService {
    public static final int MAX_NU_PER_TICK = 65535;

    protected final Dimension dimension;
    protected final Map<Vector3ic, Long> scheduledUpdates = new ConcurrentHashMap<>();
    protected final Queue<NeighborUpdate> neighborUpdates = new LinkedList<>();

    @Override
    public void tick(long tick) {
        List<Vector3ic> positions = new ArrayList<>(scheduledUpdates.size() / 4);
        for (var entry : scheduledUpdates.entrySet()) {
            if (entry.getValue() <= tick) {
                positions.add(entry.getKey());
                scheduledUpdates.remove(entry.getKey());
            }
        }
        for (var pos : positions) {
            var layer0 = dimension.getBlockState(pos);
            var b0 = new BlockStateWithPos(layer0, new Position3i(pos, dimension), 0);
            layer0.getBehavior().onScheduledUpdate(b0);

            var layer1 = dimension.getBlockState(pos, 1);
            if (layer1.getBlockAttributes().isLiquid()) {
                var b1 = new BlockStateWithPos(layer1, new Position3i(pos, dimension), 1);
                layer1.getBehavior().onScheduledUpdate(b1);
            }
        }
        int count = 0;
        while (!neighborUpdates.isEmpty() && count < MAX_NU_PER_TICK) {
            var neighborUpdate = neighborUpdates.poll();
            var pos = neighborUpdate.pos;
            var neighborPos = neighborUpdate.neighborPos;
            var blockFace = neighborUpdate.blockFace;

            var layer0 = dimension.getBlockState(pos);
            layer0.getBehavior().onNeighborUpdate(pos, neighborPos, blockFace, dimension);

            var layer1 = dimension.getBlockState(pos, 1);
            if (layer1.getBlockAttributes().isLiquid()) {
                layer1.getBehavior().onNeighborUpdate(pos, neighborPos, blockFace, dimension);
            }
            count++;
        }
    }

    @Override
    public void scheduleBlockUpdate(Vector3ic pos, Duration delay) {
        scheduledUpdates.putIfAbsent(pos, dimension.getWorld().getTick() + delay.toNanos() / 50000000);
    }

    @Override
    public void neighborBlockUpdate(Vector3ic pos, Vector3ic neighborPos, BlockFace blockFace) {
        neighborUpdates.add(new NeighborUpdate(pos, neighborPos, blockFace));
    }

    protected record NeighborUpdate(Vector3ic pos, Vector3ic neighborPos, BlockFace blockFace) {}
}
