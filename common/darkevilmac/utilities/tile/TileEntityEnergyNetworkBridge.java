package darkevilmac.utilities.tile;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.shadows.TileBuffer;
import darkevilmac.utilities.tile.base.TileEntityUtilities;
import darkevilmac.utilities.utils.FluidUtils;

public class TileEntityEnergyNetworkBridge extends TileEntityUtilities implements IFluidHandler {

    public TileEntityEnergyNetworkManager manager;
    public FluidTank bufferTank;
    protected TileBuffer[] tileBuffer = null;
    public int managerXCoord;
    public int managerYCoord;
    public int managerZCoord;
    public boolean hasManager;

    public int loops = 0;

    public TileEntityEnergyNetworkBridge() {
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
            bufferTank = new FluidTank(ModFluids.fluidEnergy, 0, FluidContainerRegistry.BUCKET_VOLUME / 3);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (bufferTank == null)
            bufferTank = new FluidTank(ModFluids.fluidEnergy, 0, FluidContainerRegistry.BUCKET_VOLUME / 3);

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

    protected void pushToConsumers(FluidTank tank) {
        if (tileBuffer == null)
            tileBuffer = TileBuffer.makeBuffer(world, xCoord, yCoord, zCoord, false);
        if (tank.getFluidAmount() > 0)
            FluidUtils.pushFluidToConsumers(tank, 400, tileBuffer);
    }

    protected TileEntity getTile(ForgeDirection side) {
        if (tileBuffer == null)
            tileBuffer = TileBuffer.makeBuffer(world, xCoord, yCoord, zCoord, false);
        return tileBuffer[side.ordinal()].getTile();
    }

    public void setManager(TileEntityEnergyNetworkManager managerToSet) {
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
        if (getMeta() == 1 && resource.getFluid() == ModFluids.fluidEnergy) {
            return bufferTank.fill(resource, doFill);
        }
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (getMeta() == 0 && resource.getFluid() == ModFluids.fluidEnergy) {
            bufferTank.drain(resource.amount, doDrain);
        }
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if (getMeta() == 0) {
            return bufferTank.drain(maxDrain, doDrain);
        }
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return getMeta() == 1 && fluid == ModFluids.fluidEnergy;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return getMeta() == 0 && fluid == ModFluids.fluidEnergy;
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
