package darkevilmac.utilities.tile;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import darkevilmac.utilities.Utilities;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.network.packet.FluidFilterReadNBTPacket;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityFluidNetworkBridge extends TileEntityUtilities implements IFluidHandler {

    public ArrayList<Fluid> fluidFilters;
    public int[] filterIds;

    public boolean readNBT;
    public boolean sendNBTPacket;
    public TileEntityFluidNetworkManager manager;
    public FluidTank bufferTank;
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

        int i = 0;
        while (i <= 13) {
            filterIds[i] = fluidFilters.get(i).getID();
            i++;
        }

        nbt.setIntArray("filterIds", filterIds);
        nbt.setBoolean("readNBTFilters", true);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        if (nbt.getBoolean("readNBTFilters")) {

            filterIds = nbt.getIntArray("filterIds");

            nbt.setBoolean("readNBTFilters", false);
        }
        readNBT = true;
    }

    @Override
    public void validate() {
        super.validate();

        filterIds = new int[14];
        fluidFilters = new ArrayList<Fluid>();
        if (!world.isRemote) {
            if (readNBT == true) {
                int i = 0;
                while (i <= 13) {
                    fluidFilters.add(FluidRegistry.getFluid(filterIds[i]));
                    i++;
                }
                readNBT = false;
            } else {
                int i = 0;
                while (i <= 13) {
                    filterIds[i] = FluidRegistry.getFluidID(ModFluids.fluidEmptyFilter.getName());
                    fluidFilters.add(FluidRegistry.getFluid(filterIds[i]));
                    i++;
                }
            }
            sendNBTPacket = true;
        }

        if (bufferTank == null)
            bufferTank = new FluidTank(new FluidStack(FluidRegistry.LAVA, 100), FluidContainerRegistry.BUCKET_VOLUME / 3);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!world.isRemote && sendNBTPacket) {
            System.out.println("Sending NBT Packet");
            Utilities.packetPipeline.sendToAll(new FluidFilterReadNBTPacket(world.provider.dimensionId, xCoord, yCoord, zCoord, fluidFilters.get(0).getID(), fluidFilters.get(1)
                    .getID(), fluidFilters.get(2).getID(), fluidFilters.get(3).getID(), fluidFilters.get(4).getID(), fluidFilters.get(5).getID(), fluidFilters.get(6).getID(),
                    fluidFilters.get(7).getID(), fluidFilters.get(8).getID(), fluidFilters.get(9).getID(), fluidFilters.get(10).getID(), fluidFilters.get(11).getID(), fluidFilters
                            .get(12).getID(), fluidFilters.get(13).getID()));
            sendNBTPacket = false;
        }

        if (bufferTank == null)
            bufferTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME / 3);

        if (manager != null) {
            checkManager();
        }
    }

    @Override
    public void onNeighborBlockChange(Block blockType) {
        super.onNeighborBlockChange(blockType);

    }

    public void fixEmptyFilters() {
        int i = 0;
        while (i <= 13) {
            fluidFilters.add(ModFluids.fluidEmptyFilter);
            i++;
        }
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