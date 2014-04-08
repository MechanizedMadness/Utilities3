package darkevilmac.utilities.tile;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.shadows.TileBuffer;
import darkevilmac.utilities.tile.base.TileEntityUtilities;
import darkevilmac.utilities.utils.FluidUtils;

public class TileEntityFluidNetworkBridge extends TileEntityUtilities implements IFluidHandler {

    public int[] fluidFilters = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    public TileEntityFluidNetworkManager manager;
    public FluidTank bufferTank;
    protected TileBuffer[] tileBuffer = null;

    public int managerXCoord;
    public int managerYCoord;
    public int managerZCoord;
    public boolean hasManager;

    public int loops = 0;

    public TileEntityFluidNetworkBridge() {
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setIntArray("fluidFilters", fluidFilters);

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        fluidFilters = nbt.getIntArray("fluidFilters");

    }

    @Override
    public void validate() {
        super.validate();

        if (fluidFilters[0] == 0) {
            int emptyFilterID = ModFluids.fluidEmptyFilter.getID();
            int i = 0;
            while (i <= 13) {
                fluidFilters[i] = emptyFilterID;
                i++;
            }
        }

        if (bufferTank == null)
            bufferTank = new FluidTank(new FluidStack(FluidRegistry.LAVA, 100), FluidContainerRegistry.BUCKET_VOLUME / 3);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (bufferTank == null)
            bufferTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME / 3);

        if (tileBuffer == null)
            tileBuffer = TileBuffer.makeBuffer(world, xCoord, yCoord, zCoord, false);

        if (manager != null) {
            checkManager();

            if (getMeta() == 0) {
                FluidUtils.pushFluidToConsumers(manager, tileBuffer, fluidFilters, useFilters());
            } else {

            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public void onNeighborBlockChange(Block blockType) {
        super.onNeighborBlockChange(blockType);

    }

    public boolean useFilters() {
        int i = 0;
        while (i <= 13) {
            if (fluidFilters[i] == ModFluids.fluidAnyFilter.getID()) {
                return true;
            }
            i++;
        }
        return false;
    }

    protected TileEntity getTile(ForgeDirection side) {
        if (tileBuffer == null)
            tileBuffer = TileBuffer.makeBuffer(world, xCoord, yCoord, zCoord, false);
        return tileBuffer[side.ordinal()].getTile();
    }

    public void changeFilter(int fluidID, int fluidIDIndex) {
        fluidFilters[fluidIDIndex] = fluidID;
    }

    public void setManager(TileEntityFluidNetworkManager managerToSet) {
        manager = managerToSet;
        managerXCoord = managerToSet.xCoord;
        managerYCoord = managerToSet.yCoord;
        managerZCoord = managerToSet.zCoord;
        hasManager = true;
    }

    public void clearManager() {
        hasManager = false;
        manager = null;
    }

    public void checkManager() {
        if (world.getTileEntity(managerXCoord, managerYCoord, managerZCoord) == null
                && world.getTileEntity(managerXCoord, managerYCoord, managerZCoord) instanceof TileEntityEnergyNetworkManager) {
            clearManager();
        }
    }

    /*
     * Begin IFluidHandler implementation.
     * 
     * @see net.minecraftforge.fluids.IFluidHandler
     */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (manager != null) {
            if (doFill)
                manager.addFluid(resource.amount, resource.getFluid());

            return resource.amount;
        }
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (manager != null) {
            if (doDrain)
                manager.useFluid(resource.amount, resource.getFluid());

            return resource;
        }
        return new FluidStack(resource.getFluid(), 0);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return new FluidStack(FluidRegistry.WATER, 0);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return getMeta() == 1;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return getMeta() == 0;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { bufferTank.getInfo() };
    }

    /*
     * End IFluidHandler implementation.
     * 
     * @see net.minecraftforge.fluids.IFluidHandler
     */

}