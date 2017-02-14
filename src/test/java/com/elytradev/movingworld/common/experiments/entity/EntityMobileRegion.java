package com.elytradev.movingworld.common.experiments.entity;

import com.elytradev.movingworld.client.experiments.MobileRegionWorldClient;
import com.elytradev.movingworld.common.experiments.MobileRegion;
import com.elytradev.movingworld.common.experiments.MobileRegionWorldServer;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Experimental MobileRegion entity.
 */
public class EntityMobileRegion extends Entity {

    public MobileRegion region;
    public World mobileRegionWorld;

    public EntityMobileRegion(World worldIn, MobileRegion region) {
        super(worldIn);
        this.region = region;
    }

    @Override
    protected void entityInit() {
        if (world.isRemote) {
            entityInitClient();
        } else {
            entityInitCommon();
        }
    }

    protected void entityInitCommon() {
        this.mobileRegionWorld = new MobileRegionWorldServer(getParentWorld().getMinecraftServer(),
                getParentWorld().getSaveHandler(),
                getParentWorld().getWorldInfo(),
                region.dimension, getParentWorld().profiler);
    }

    @SideOnly(Side.CLIENT)
    protected void entityInitClient() {
        this.mobileRegionWorld = new MobileRegionWorldClient(null, genWorldSettings(),
                region.dimension, getParentWorld().getDifficulty(), getParentWorld().profiler);

        // TODO: Request data from server or make the server figure it out itself. Not sure yet.
    }


    private WorldSettings genWorldSettings() {
        WorldSettings settings = new WorldSettings(0L,
                getParentWorld().getWorldInfo().getGameType(),
                false,
                getParentWorld().getWorldInfo().isHardcoreModeEnabled(),
                getParentWorld().getWorldType());

        return settings;
    }

    public World getParentWorld() {
        return DimensionManager.getWorld(region.dimension);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }
}
