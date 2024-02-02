package org.allaymc.server.world;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.generator.Generator;
import org.allaymc.api.world.service.BlockUpdateService;
import org.allaymc.api.world.service.ChunkService;
import org.allaymc.api.world.service.EntityPhysicsService;
import org.allaymc.api.world.service.EntityService;
import org.allaymc.server.world.service.AllayBlockUpdateService;
import org.allaymc.server.world.service.AllayChunkService;
import org.allaymc.server.world.service.AllayEntityPhysicsService;
import org.allaymc.server.world.service.AllayEntityService;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 11/12/2023
 *
 * @author Cool_Loong
 */
@Slf4j
@Getter
public class AllayDimension implements Dimension {
    protected final Generator generator;
    protected final ChunkService chunkService;
    protected final EntityPhysicsService entityPhysicsService;
    protected final BlockUpdateService blockUpdateService;
    protected final EntityService entityService;
    protected final DimensionInfo dimensionInfo;
    protected final World world;
    protected final Set<EntityPlayer> players;

    public AllayDimension(World world, Generator generator, DimensionInfo dimensionInfo) {
        this.world = world;
        this.dimensionInfo = dimensionInfo;
        this.generator = generator;
        this.chunkService = new AllayChunkService(this, world.getWorldStorage());
        this.entityPhysicsService = new AllayEntityPhysicsService(this);
        this.entityService = new AllayEntityService(entityPhysicsService);
        this.blockUpdateService = new AllayBlockUpdateService(this);
        this.players = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    @Override
    public void tick(long currentTick) {
        chunkService.tick();
        entityService.tick();
        entityPhysicsService.tick();
        blockUpdateService.tick(currentTick);
    }

    @Override
    public void close() {
        chunkService.unloadAllChunks();
    }

    @Override
    public void addPlayer(EntityPlayer player, Runnable runnable) {
        players.add(player);
        chunkService.addChunkLoader(player);
        entityService.addEntity(player, runnable);
    }

    @Override
    public void removePlayer(EntityPlayer player) {
        entityService.removeEntity(player);
        chunkService.removeChunkLoader(player);
        players.remove(player);
    }

    @Override
    public @UnmodifiableView Collection<EntityPlayer> getPlayers() {
        return Collections.unmodifiableCollection(players);
    }

    @Override
    public String toString() {
        return "world=" + this.world.getWorldData().getName() + " dimId=" + this.dimensionInfo.dimensionId();
    }
}
