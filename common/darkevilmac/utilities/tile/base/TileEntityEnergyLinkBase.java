package darkevilmac.utilities.tile.base;

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
import darkevilmac.utilities.addons.tile.bc.TileEntityEnergyLinkBC;
import darkevilmac.utilities.addons.tile.ic2.TileEntityEnergyLinkIC2;
import darkevilmac.utilities.addons.tile.railcraft.TileEntityEnergyLinkSteam;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.shadows.TileBuffer;
import darkevilmac.utilities.utils.FluidUtils;

public class TileEntityEnergyLinkBase extends TileEntityUtilities implements IFluidHandler {

    protected TileBuffer[] tileBuffer = null;
    protected World world;
    protected IFluidHandler fluidHandler;
    public int maxEnergyPoints = 60000;
    public int energyPoints = 0;
    protected FluidStack energyEmptyFluid = new FluidStack(ModFluids.fluidEnergy, 0);
    protected int energyTankSize = FluidContainerRegistry.BUCKET_VOLUME * 4;
    protected FluidTank energyTank = new FluidTank(energyTankSize);

    @Override
    public void validate() {
        super.validate();
        world = worldObj;
    }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            checkFluid();
            if (getMeta() == 0) {
                convertFluidToUnits();
            }
            if (getMeta() == 1) {
                convertUnitsToFluid();
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
        if (tank.getFluidAmount() > 0)
            FluidUtils.pushFluidToConsumersLink(tank, 400, getTileType(), tileBuffer);
    }

    protected TileEntity getTile(ForgeDirection side) {
        if (tileBuffer == null)
            tileBuffer = TileBuffer.makeBuffer(world, xCoord, yCoord, zCoord, false);
        return tileBuffer[side.ordinal()].getTile();
    }

    public int getTileType() {
        if (world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEnergyLinkBC) {
            return 1;
        }
        if (world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEnergyLinkIC2) {
            return 2;
        }
        if (world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEnergyLinkSteam) {
            return 3;
        }
        return 0;
    }

    public boolean isTile(int x, int y, int z, int tileType) {
        boolean bool = false;
        if (tileType == 1) {
            if (world.getTileEntity(x, y, z) != null) {
                if (world.getTileEntity(x, y, z) instanceof TileEntityEnergyLinkBC) {
                    bool = true;
                } else {
                    bool = false;
                }
            }
        }
        if (tileType == 2) {
            if (world.getTileEntity(x, y, z) != null) {
                if (world.getTileEntity(x, y, z) instanceof TileEntityEnergyLinkIC2) {
                    bool = true;
                } else {
                    bool = false;
                }
            }
        }
        if (tileType == 3) {
            if (world.getTileEntity(x, y, z) != null) {
                if (world.getTileEntity(x, y, z) instanceof TileEntityEnergyLinkSteam) {
                    bool = true;
                } else {
                    bool = false;
                }
            }
        }
        return bool;
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
            if (maxEnergyPoints >= energyPoints + energyTank.getFluidAmount() * 32) {
                energyPoints = energyPoints + energyTank.getFluidAmount() * 32;
                energyTank.setFluid(new FluidStack(ModFluids.fluidEnergy, energyTank.getFluidAmount() - energyTank.getFluidAmount()));
            }
        }
    }

    public void convertUnitsToFluid() {
        if (energyPoints >= 32) {
            if (energyTankSize >= energyTank.getFluidAmount() + 1) {
                while (energyPoints >= 32 && energyTankSize >= energyTank.getFluidAmount() + 1) {
                    energyPoints = energyPoints - 32;
                    energyTank.setFluid(new FluidStack(ModFluids.fluidEnergy, energyTank.getFluidAmount() + 1));
                }
            }
        }
    }

    @Override
    public void onNeighborBlockChange(Block blockType) {
        super.onNeighborBlockChange(blockType);

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