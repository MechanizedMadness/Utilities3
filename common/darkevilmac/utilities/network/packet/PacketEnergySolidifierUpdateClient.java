package darkevilmac.utilities.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import darkevilmac.utilities.tile.TileEntityEnergySolidifier;

public class PacketEnergySolidifierUpdateClient extends AbstractPacket {

    int dimID, x, y, z, fluidAmount;

    public PacketEnergySolidifierUpdateClient() {

    }

    public PacketEnergySolidifierUpdateClient(int dimID, int x, int y, int z, int fluidAmount) {
        this.dimID = dimID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.fluidAmount = fluidAmount;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(dimID);
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        buffer.writeInt(fluidAmount);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        dimID = buffer.readInt();
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        fluidAmount = buffer.readInt();
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        World world = DimensionManager.getWorld(dimID);

        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityEnergySolidifier) {
            TileEntityEnergySolidifier tile = (TileEntityEnergySolidifier) world.getTileEntity(x, y, z);

            tile.setClientDisplayEnergy(fluidAmount);
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
    }

}
