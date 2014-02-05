package darkevilmac.utilities.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.tile.prefab.TileEntityEnergyLinkBase;

public class TileEntityEnergyLinkSteam extends TileEntityEnergyLinkBase implements IFluidHandler {

    private int flAmount = 0;
    private FluidStack energyEmptyFluid = new FluidStack(ModFluids.fluidEnergy, 0);
    private FluidStack steamEmptyFluid = new FluidStack(FluidRegistry.getFluid("steam"), 0);
    private int energyTankSize = FluidContainerRegistry.BUCKET_VOLUME * 8;
    protected FluidTank energyTank = new FluidTank(energyTankSize);
    private int steamTankSize = FluidContainerRegistry.BUCKET_VOLUME * 16;
    protected FluidTank steamTank = new FluidTank(steamTankSize);

    private void checkSetFluid(FluidStack emptyFluid, Fluid comparefluid, FluidTank tankCheck) {
        if (tankCheck.getFluid() != null) {
            if (tankCheck.getFluid().getFluid() != comparefluid) {
                tankCheck.setFluid(emptyFluid);
            }
        }
    }

    @Override
    public void onNeighborBlockChange() {

    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        energyTank.readFromNBT(tag);
        steamTank.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        energyTank.writeToNBT(tag);
        steamTank.writeToNBT(tag);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
            /* Steam<--Fluid Energy */
            // {{
            if (getMeta() == 0) {
                checkSetFluid(energyEmptyFluid, ModFluids.fluidEnergy, energyTank);
                if (energyPoints >= 35) {
                    convertSteamFromPoints();
                    pushToConsumers(steamTank);
                }
            }
            // }}
            /* Steam-->Fluid Energy */
            // {{
            if (getMeta() == 1) {
                checkSetFluid(steamEmptyFluid, FluidRegistry.getFluid("steam"), steamTank);
                if (steamTank.getFluidAmount() >= 1) {
                    convertSteamToPoints();
                }
            }
            // }}
        }
    }

    private void convertSteamToPoints() {
        int steam = steamTank.getFluidAmount();
        int falsePoints = energyPoints;

        while (steam >= 1) {
            steam--;
            falsePoints = falsePoints + 35;

            if (falsePoints > maxEnergyPoints) {
                falsePoints = falsePoints - 35;
                steam++;

                break;
            }
        }
        steamTank.setFluid(new FluidStack(FluidRegistry.getFluid("steam"), steam));
        energyPoints = falsePoints;
    }

    private void convertSteamFromPoints() {
        int steam = 0;
        int falsePoints = energyPoints;
        while (falsePoints >= 35) {
            falsePoints = falsePoints - 35;
            steam++;

        }
        // The following should be impossible but I'm weird and plan for mess
        // ups.
        if (energyPoints - 35 * steam < 0) {
            steam--;
        }
        while (steam + steamTank.getFluidAmount() > steamTank.getCapacity()) {
            steam--;

        }
        steamTank.setFluid(new FluidStack(FluidRegistry.getFluid("steam"), steam + steamTank.getFluidAmount()));
        energyPoints = (int) (energyPoints - 35 * steam);
    }

    /* IFluidHandler */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (getMeta() == 0) {
            return energyTank.fill(resource, doFill);
        } else {
            if (getMeta() == 1) {
                return steamTank.fill(resource, doFill);
            } else {
                return energyTank.fill(resource, doFill);
            }
        }

    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (getMeta() == 0) {
            if (resource == null || !resource.isFluidEqual(steamTank.getFluid())) {
                return null;
            }
            return steamTank.drain(resource.amount, doDrain);
        } else {
            if (getMeta() == 1) {
                if (resource == null || !resource.isFluidEqual(steamTank.getFluid())) {
                    return null;
                }
                return energyTank.drain(resource.amount, doDrain);
            } else {
                if (resource == null || !resource.isFluidEqual(steamTank.getFluid())) {
                    return null;
                }
                return steamTank.drain(resource.amount, doDrain);
            }
        }
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if (getMeta() == 0) {
            return steamTank.drain(maxDrain, doDrain);
        } else {
            if (getMeta() == 1) {
                return energyTank.drain(maxDrain, doDrain);
            } else {
                return steamTank.drain(maxDrain, doDrain);
            }
        }
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
