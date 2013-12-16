package darkevilmac.utilities.tile.prefab;

import buildcraft.core.TileBuffer;
import buildcraft.core.fluids.FluidUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import darkevilmac.utilities.fluid.ModFluids;

public class TileEntityEnergyLinkBase extends TileEntity implements IFluidHandler {
    FluidStack fluidToSend;

    protected World world;
    protected IFluidHandler fluidHandler;
    protected ForgeDirection handlerDir;
    private TileBuffer[] tileBuffer = null;
    public int maxEnergyPoints = 60000;
    public int energyPoints = 0;
    protected FluidStack energyEmptyFluid = new FluidStack(ModFluids.fluidEnergy, 0);
    protected int energyTankSize = FluidContainerRegistry.BUCKET_VOLUME * 4;
    protected FluidTank energyTank = new FluidTank(energyTankSize);

    @Override
    public void validate() {
        super.validate();
        this.world = worldObj;
        tileBuffer = null;
        handlerDir = ForgeDirection.UNKNOWN;
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote == false) {
            checkFluid();
            if (getMeta() == 0) {
                convertFluidToUnits();
            }
            if (getMeta() == 1) {
                convertUnitsToFluid();
                if(energyTank.getFluidAmount()>=200)
                pushToConsumers(energyTank);
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        energyTank.writeToNBT(tag);
        tag.setInteger("energypoints", energyPoints);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        energyTank.readFromNBT(tag);
        energyPoints = tag.getInteger("energypoints");
    }

    protected void pushToConsumers(FluidTank tank) {
        if (tileBuffer == null)
            tileBuffer = TileBuffer.makeBuffer(world, xCoord, yCoord, zCoord, false);
        FluidUtils.pushFluidToConsumers(tank, 200, tileBuffer);
    }

    protected void checkFluid() {
        if (energyTank.getFluid() != null) {
            if (energyTank.getFluid().getFluid() != ModFluids.fluidEnergy) {
                energyTank.setFluid(energyEmptyFluid);
            }
        }
        if (energyTank.getFluid() == null) {
            energyTank.setFluid(energyEmptyFluid);
        }
    }

    public void convertFluidToUnits() {
        if (energyTank.getFluidAmount() >= 1) {
            if (maxEnergyPoints >= energyPoints + energyTank.getFluidAmount() * 15) {
                energyPoints = energyPoints + energyTank.getFluidAmount() * 15;
                energyTank.setFluid(new FluidStack(ModFluids.fluidEnergy, energyTank.getFluidAmount() - energyTank.getFluidAmount()));
            }
        }
    }

    public void convertUnitsToFluid() {
        if (energyPoints >= 15) {
            if (energyTankSize >= energyTank.getFluidAmount() + 1) {
                while (energyPoints >= 15 && energyTankSize >= energyTank.getFluidAmount() + 1) {
                    energyPoints = energyPoints - 15;
                    energyTank.setFluid(new FluidStack(ModFluids.fluidEnergy, energyTank.getFluidAmount() + 1));
                }
            }
        }
    }

    public int getMeta() {
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        return meta;
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