package darkevilmac.utilities.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.tile.TileEntityFluidNetworkBridge;

public class FluidFilterReadNBTPacket extends AbstractPacket {

    int dimension, x, y, z, fluidID, fluidIDIndex;

    public FluidFilterReadNBTPacket() {

    }

    public FluidFilterReadNBTPacket(int dimension, int x, int y, int z, int fluidID, int fluidIDIndex) {
        this.dimension = dimension;
        this.x = x;
        this.y = y;
        this.z = z;
        this.fluidID = fluidID;
        this.fluidIDIndex = fluidIDIndex;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(dimension);
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        buffer.writeInt(fluidID);
        buffer.writeInt(fluidIDIndex);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        dimension = buffer.readInt();
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        fluidID = buffer.readInt();
        fluidIDIndex = buffer.readInt();
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        World world = DimensionManager.getWorld(dimension);

        TileEntityFluidNetworkBridge tile = (TileEntityFluidNetworkBridge) world.getTileEntity(x, y, z);

        if (!tile.fluidFilters.isEmpty()) {
            int i = 0;
            while (i <= 13) {
                tile.fluidFilters.add(ModFluids.fluidEmptyFilter);
                i++;
            }
        }

        if (tile.fluidFilters.size() - 1 < fluidIDIndex) {
            while (tile.fluidFilters.size() - 1 < fluidIDIndex) {
                tile.fluidFilters.add(ModFluids.fluidEmptyFilter);
            }
        }

        tile.changeFilter(fluidIDIndex, fluidID);
    }

    @Override
    public void handleServerSide(EntityPlayer player) {

    }

}
