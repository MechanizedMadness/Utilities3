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
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityFluidNetworkBridge extends TileEntityUtilities implements IFluidHandler {

    public ArrayList<Fluid> fluidFilters = new ArrayList<Fluid>();

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
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
    }

    @Override
    public void validate() {
        super.validate();

        if (bufferTank == null)
            bufferTank = new FluidTank(new FluidStack(FluidRegistry.LAVA, 100), FluidContainerRegistry.BUCKET_VOLUME / 3);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (fluidFilters.isEmpty()) {
            int i = 0;
            while (i <= 13) {
                fluidFilters.add(ModFluids.fluidEmptyFilter);
                i++;
            }
        }

        if (bufferTank == null)
            bufferTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME / 3);

        if (manager != null) {
            checkManager();
        }
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
        super.onBlockDestroyedByPlayer(world, x, y, z, par5);

    }

    @Override
    public void onNeighborBlockChange(Block blockType) {
        super.onNeighborBlockChange(blockType);

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