package darkevilmac.movingworld.chunk;

import darkevilmac.movingworld.entity.EntityMovingWorld;
import darkevilmac.movingworld.render.old.MobileChunkRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class MobileChunkClient extends MobileChunk {
    private MobileChunkRenderer renderer;

    public MobileChunkClient(World world, EntityMovingWorld movingWorld) {
        super(world, movingWorld);
        renderer = new MobileChunkRenderer(this);
    }

    public MobileChunkRenderer getRenderer() {
        return renderer;
    }

    @Override
    public void onChunkUnload() {
        List<TileEntity> iterator = new ArrayList<TileEntity>(chunkTileEntityMap.values());
        for (TileEntity te : iterator) {
            removeChunkBlockTileEntity(te.getPos());
        }
        super.onChunkUnload();
        renderer.markRemoved();
    }

    @Override
    public void setChunkModified() {
        super.setChunkModified();
        renderer.markDirty();
    }
}