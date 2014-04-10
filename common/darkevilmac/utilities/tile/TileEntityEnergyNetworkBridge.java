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
    public boolean readNBT;
    public boolean hasManager;

    public int loops = 0;

    public TileEntityEnergyNetworkBridge() {
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        if (manager != null) {
            managerXCoord = manager.xCoord;
            managerYCoord = manager.yCoord;
            managerZCoord = manager.zCoord;

            nbt.setInteger("managerXCoord", managerXCoord);
            nbt.setInteger("managerYCoord", managerYCoord);
            nbt.setInteger("managerZCoord", managerZCoord);
            nbt.setBoolean("readNBT", true);
        } else {
            nbt.setBoolean("readNBT", false);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.getBoolean("readNBT")) {
            managerXCoord = nbt.getInteger("managerXCoord");
            managerYCoord = nbt.getInteger("managerYCoord");
            managerZCoord = nbt.getInteger("managerZCoord");
        } else {
            managerXCoord = 0;
            managerYCoord = 0;
            managerYCoord = 0;
        }
        readNBT = nbt.getBoolean("readNBT");
    }

    @Override
    public void validate() {
        super.validate();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!world.isRemote) {

            if (readNBT) {
                manager = (TileEntityEnergyNetworkManager) world.getTileEntity(managerXCoord, managerYCoord, managerZCoord);
                readNBT = false;
            }

            if (bufferTank == null)
                bufferTank = new FluidTank(ModFluids.fluidEnergy, 0, FluidContainerRegistry.BUCKET_VOLUME / 3);

            if (tileBuffer == null)
                tileBuffer = TileBuffer.makeBuffer(world, xCoord, yCoord, zCoord, false);

            if (manager != null) {
                checkManager();
                if (manager != null) {
                    if (getMeta() == 0) {
                        if (bufferTank.getFluidAmount() < bufferTank.getCapacity()) {
                            if (manager.internalEnergy.amount > 0) {
                                int accepted = bufferTank.fill(new FluidStack(ModFluids.fluidEnergy, manager.internalEnergy.amount), true);

                                manager.useEnergy(accepted);
                            }
                        }
                    } else {
                        manager.addEnergy(bufferTank.getFluidAmount());

                        bufferTank.setFluid(new FluidStack(ModFluids.fluidEnergy, 0));
                    }
                }
                if (getMeta() == 0) {
                    FluidUtils.pushFluidToConsumers(bufferTank, 50, tileBuffer);
                } else {
                    FluidUtils.pullFluidFromProducers(bufferTank, new FluidStack(ModFluids.fluidEnergy, 125), tileBuffer);
                }
            }
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
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { new FluidTankInfo(new FluidStack(ModFluids.fluidEnergy, 0), 0) };
    }
    /*
     * End IFluidHandler implementation.
     * 
     * @see net.minecraftforge.fluids.IFluidHandler
     */

}
