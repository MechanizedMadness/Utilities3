package darkevilmac.utilities.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import darkevilmac.utilities.tile.TileEntityFluidNetworkBridge;

public class FluidFilterChangePacket extends AbstractPacket {

    private int dimID, x, y, z, fluidID, fluidIDIndex;

    public FluidFilterChangePacket() {

    }

    public FluidFilterChangePacket(int dimID, int x, int y, int z, int fluidID, int fluidIDIndex) {
        this.dimID = dimID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.fluidID = fluidID;
        this.fluidIDIndex = fluidIDIndex;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(dimID);
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        buffer.writeInt(fluidID);
        buffer.writeInt(fluidIDIndex);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        dimID = buffer.readInt();
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        fluidID = buffer.readInt();
        fluidIDIndex = buffer.readInt();
    }

    @Override
    public void handleClientSide(EntityPlayer player) {

    }

    @Override
    public void handleServerSide(EntityPlayer player) {
        World world = DimensionManager.getWorld(dimID);

        TileEntityFluidNetworkBridge tile = (TileEntityFluidNetworkBridge) world.getTileEntity(x, y, z);

        tile.changeFilter(fluidID, fluidIDIndex);
    }

}
