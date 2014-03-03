package darkevilmac.utilities.tile;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityEnergyNetworkBridge extends TileEntityUtilities implements IFluidHandler {

    public TileEntityEnergyNetworkManager manager;
    public FluidTank bufferTank;
    public int managerXCoord;
    public int managerYCoord;
    public int managerZCoord;
    public boolean hasManager;
    public boolean needToSetManager;

    public int loops = 0;

    public TileEntityEnergyNetworkBridge() {
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        if (hasManager) {
            System.out.println("Setting hasManager to true");
            nbt.setBoolean("hasManager", true);
            nbt.setInteger("managerXCoord", managerXCoord);
            nbt.setInteger("managerYCoord", managerYCoord);
            nbt.setInteger("managerZCoord", managerZCoord);
        } else {
            nbt.setBoolean("hasManager", false);
        }

        bufferTank.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        if (nbt.getBoolean("hasManager")) {
            hasManager = nbt.getBoolean("hasManager");
            managerXCoord = nbt.getInteger("managerXCoord");
            managerYCoord = nbt.getInteger("managerYCoord");
            managerZCoord = nbt.getInteger("managerZCoord");
        }

        bufferTank.readFromNBT(nbt);
    }

    @Override
    public void validate() {
        super.validate();

        if (bufferTank == null)
            bufferTank = new FluidTank(ModFluids.fluidEnergy, 0, 60);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (manager == null) {
            if (world.getTileEntity(managerXCoord, managerYCoord, managerZCoord) != null
                    && world.getTileEntity(managerXCoord, managerYCoord, managerZCoord) instanceof TileEntityEnergyNetworkManager) {
                manager = (TileEntityEnergyNetworkManager) world.getTileEntity(managerXCoord, managerYCoord, managerZCoord);
            }
        }

        if (bufferTank == null)
            bufferTank = new FluidTank(ModFluids.fluidEnergy, 0, 60);

        if (manager != null) {

            checkManager();

            if (manager != null) {
                if (world.getTileEntity(manager.xCoord, manager.yCoord, manager.zCoord) == null) {
                    manager = null;
                    hasManager = false;
                }

                // Send Fluid to the manager for safekeeping.
                if (getMeta() == 1) {
                    manager.addEnergy(bufferTank.getFluidAmount());
                    bufferTank.drain(bufferTank.getFluidAmount(), true);
                }

                // Keep the bridge with a little fluid for pushing to others as
                // needed.
                if (getMeta() == 0) {
                    if (manager.internalEnergy.amount >= 40) {
                        if (bufferTank.getFluidAmount() < 40) {
                            manager.useEnergy(40 - bufferTank.getFluidAmount());
                            bufferTank.fill(new FluidStack(ModFluids.fluidEnergy, 40 - bufferTank.getFluidAmount()), true);
                        }
                    }
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

    public void setManager(int managerXCoordToSet, int managerYCoordToSet, int managerZCoordToSet) {
        managerXCoord = managerXCoordToSet;
        managerYCoord = managerYCoordToSet;
        managerZCoord = managerZCoordToSet;

        manager = (TileEntityEnergyNetworkManager) world.getTileEntity(managerXCoord, managerYCoord, managerZCoord);
        hasManager = true;
    }

    public void clearManager() {
        hasManager = false;
        manager = null;
    }

    public void checkManager() {
        if (world.getTileEntity(managerXCoord, managerYCoord, managerZCoord) == null) {
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
        if (getMeta() == 1) {
            return bufferTank.fill(resource, doFill);
        }
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (manager != null) {
            if (getMeta() == 0) {
                if (manager.internalEnergy.amount >= resource.amount) {
                    if (bufferTank.getFluidAmount() >= resource.amount) {
                        return bufferTank.drain(resource.amount, doDrain);
                    }
                    if (bufferTank.getFluidAmount() > 0) {
                        manager.useEnergy(resource.amount - bufferTank.getFluidAmount());
                        bufferTank.drain(bufferTank.getFluidAmount(), true);
                        return new FluidStack(ModFluids.fluidEnergy, resource.amount);
                    }
                }
                if (manager.internalEnergy.amount > 0) {
                    if (bufferTank.getFluidAmount() >= resource.amount) {
                        return bufferTank.drain(resource.amount, doDrain);
                    }
                    if (bufferTank.getFluidAmount() > 0) {
                        int amount = bufferTank.getFluidAmount();
                        bufferTank.drain(amount, true);
                        return new FluidStack(ModFluids.fluidEnergy, amount);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if (manager != null) {
            if (getMeta() == 0) {
                if (manager.internalEnergy.amount >= maxDrain) {
                    if (bufferTank.getFluidAmount() >= maxDrain) {
                        return bufferTank.drain(maxDrain, doDrain);
                    }
                    if (bufferTank.getFluidAmount() > 0) {
                        manager.useEnergy(maxDrain - bufferTank.getFluidAmount());
                        bufferTank.drain(bufferTank.getFluidAmount(), true);
                        return new FluidStack(ModFluids.fluidEnergy, maxDrain);
                    }
                }
                if (manager.internalEnergy.amount > 0) {
                    if (bufferTank.getFluidAmount() >= maxDrain) {
                        return bufferTank.drain(maxDrain, doDrain);
                    }
                    if (bufferTank.getFluidAmount() > 0) {
                        int amount = bufferTank.getFluidAmount();
                        bufferTank.drain(amount, true);
                        return new FluidStack(ModFluids.fluidEnergy, amount);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return getMeta() == 1 && fluid == ModFluids.fluidEnergy && manager != null;
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
