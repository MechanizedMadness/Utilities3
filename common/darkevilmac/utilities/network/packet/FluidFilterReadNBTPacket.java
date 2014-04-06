package darkevilmac.utilities.network.packet;

import darkevilmac.utilities.tile.TileEntityFluidNetworkBridge;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidFilterReadNBTPacket extends AbstractPacket {

    int dimension, x, y, z, fluidID0, fluidID1, fluidID2, fluidID3, fluidID4, fluidID5, fluidID6, fluidID7, fluidID8, fluidID9, fluidID10, fluidID11, fluidID12, fluidID13;

    public FluidFilterReadNBTPacket() {

    }

    public FluidFilterReadNBTPacket(int dimension, int x, int y, int z, int fluidID0, int fluidID1, int fluidID2, int fluidID3, int fluidID4, int fluidID5, int fluidID6,
            int fluidID7, int fluidID8, int fluidID9, int fluidID10, int fluidID11, int fluidID12, int fluidID13) {
        this.dimension = dimension;
        this.x = x;
        this.y = y;
        this.z = z;
        this.fluidID0 = fluidID0;
        this.fluidID1 = fluidID1;
        this.fluidID2 = fluidID2;
        this.fluidID3 = fluidID3;
        this.fluidID4 = fluidID4;
        this.fluidID5 = fluidID5;
        this.fluidID6 = fluidID6;
        this.fluidID7 = fluidID7;
        this.fluidID8 = fluidID8;
        this.fluidID9 = fluidID9;
        this.fluidID10 = fluidID10;
        this.fluidID11 = fluidID11;
        this.fluidID12 = fluidID12;
        this.fluidID13 = fluidID13;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(dimension);
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        buffer.writeInt(fluidID0);
        buffer.writeInt(fluidID1);
        buffer.writeInt(fluidID2);
        buffer.writeInt(fluidID3);
        buffer.writeInt(fluidID4);
        buffer.writeInt(fluidID5);
        buffer.writeInt(fluidID6);
        buffer.writeInt(fluidID7);
        buffer.writeInt(fluidID8);
        buffer.writeInt(fluidID9);
        buffer.writeInt(fluidID10);
        buffer.writeInt(fluidID11);
        buffer.writeInt(fluidID12);
        buffer.writeInt(fluidID13);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        dimension = buffer.readInt();
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        fluidID0 = buffer.readInt();
        fluidID1 = buffer.readInt();
        fluidID2 = buffer.readInt();
        fluidID3 = buffer.readInt();
        fluidID4 = buffer.readInt();
        fluidID5 = buffer.readInt();
        fluidID6 = buffer.readInt();
        fluidID7 = buffer.readInt();
        fluidID8 = buffer.readInt();
        fluidID9 = buffer.readInt();
        fluidID10 = buffer.readInt();
        fluidID11 = buffer.readInt();
        fluidID12 = buffer.readInt();
        fluidID13 = buffer.readInt();
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        World world = DimensionManager.getWorld(dimension);
        System.out.println("Client recieved packet.");

        TileEntityFluidNetworkBridge tile = (TileEntityFluidNetworkBridge) world.getTileEntity(x, y, z);

        if (tile.fluidFilters.isEmpty()) {
            int i = 0;
            while (i <= 13) {
                tile.fluidFilters.add(null);
                i++;
            }
        }

        tile.fluidFilters.set(0, FluidRegistry.getFluid(fluidID0));
        tile.fluidFilters.set(1, FluidRegistry.getFluid(fluidID1));
        tile.fluidFilters.set(2, FluidRegistry.getFluid(fluidID2));
        tile.fluidFilters.set(3, FluidRegistry.getFluid(fluidID3));
        tile.fluidFilters.set(4, FluidRegistry.getFluid(fluidID4));
        tile.fluidFilters.set(5, FluidRegistry.getFluid(fluidID5));
        tile.fluidFilters.set(6, FluidRegistry.getFluid(fluidID6));
        tile.fluidFilters.set(7, FluidRegistry.getFluid(fluidID7));
        tile.fluidFilters.set(8, FluidRegistry.getFluid(fluidID8));
        tile.fluidFilters.set(9, FluidRegistry.getFluid(fluidID9));
        tile.fluidFilters.set(10, FluidRegistry.getFluid(fluidID10));
        tile.fluidFilters.set(11, FluidRegistry.getFluid(fluidID11));
        tile.fluidFilters.set(12, FluidRegistry.getFluid(fluidID12));
        tile.fluidFilters.set(13, FluidRegistry.getFluid(fluidID13));
    }

    @Override
    public void handleServerSide(EntityPlayer player) {

    }

}
