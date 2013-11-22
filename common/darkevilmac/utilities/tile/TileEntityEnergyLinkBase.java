package darkevilmac.utilities.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.TileFluidHandler;
import darkevilmac.utilities.fluid.ModFluids;

public class TileEntityEnergyLinkBase extends TileFluidHandler implements IFluidHandler {

    protected FluidStack energyEmptyFluid = new FluidStack(ModFluids.fluidEnergy, 0);
    protected int energyTankSize = FluidContainerRegistry.BUCKET_VOLUME * 4;
    protected FluidTank energyTank = new FluidTank(energyTankSize);

    protected void checkFluid() {
        if (energyTank.getFluid() != null) {
            if (energyTank.getFluid().getFluid() != ModFluids.fluidEnergy) {
                energyTank.setFluid(energyEmptyFluid);
            }
        }
    }
    
    public int getMeta() {
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        return meta;
    }

    @Override
    public void updateEntity() {
        if (energyTank.getFluid() != null) {
            if (energyTank.getFluid().getFluid() != ModFluids.fluidEnergy) {
                energyTank.setFluid(energyEmptyFluid);
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        energyTank.writeToNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        energyTank.readFromNBT(tag);
    }

    /* IFluidHandler */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return energyTank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(energyTank.getFluid())) {
            return null;
        }
        return energyTank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return energyTank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { energyTank.getInfo() };
    }

}