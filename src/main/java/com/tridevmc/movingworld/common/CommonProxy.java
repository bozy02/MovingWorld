package com.tridevmc.movingworld.common;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class CommonProxy {

    public World getWorld(int id) {
        MinecraftServer currentServer = ServerLifecycleHooks.getCurrentServer();
        return DimensionManager.getWorld(currentServer, DimensionType.getById(id), false, false);
    }

}
