package darkevilmac.utilities.tile;

import ic2.api.energy.EnergyNet;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.info.Info;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import darkevilmac.utilities.fluid.ModFluids;

public class TileEntityEnergyLinkIC2 extends TileEntityEnergyLinkBase implements IFluidHandler, IEnergySink,
        IEnergySource {

    protected boolean addedToEnet;
    protected int currentEnergy = 0;
    protected int maxEnergy = 72 * 14;

    // {{ Fluid Handler
    /* Begin IFluidHandler */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return energyTank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
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

    /* End IFluidHandler */
    // }}

    // {{ NBT
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        currentEnergy = tag.getInteger("energyLevel");
        energyTank.writeToNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("energyLevel", (int) currentEnergy);
        energyTank.readFromNBT(tag);
    }

    // }}

    // {{ Energy Source
    @Override
    public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
        return true;
    }

    @Override
    public double getOfferedEnergy() {
        if (getMeta() == 0) {
            int power = EnergyNet.instance.getPowerFromTier(1);

            if (currentEnergy >= power) {
                return power;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public void drawEnergy(double amount) {
        if (getMeta() == 0) {
            currentEnergy -= amount;
        } else {
            // Do Nothing really important stuff here
        }
    }

    // }}

    // {{ Energy Sink

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        return true;
    }

    @Override
    public double demandedEnergyUnits() {
        if (getMeta() == 1) {
            return Math.max(0, maxEnergy - currentEnergy);
        } else {
            return 0;
        }
    }

    @Override
    public double injectEnergyUnits(ForgeDirection directionFrom, double amount) {
        if (getMeta() == 1) {
            currentEnergy += amount;
            return 0;
        } else {
            return 0;
        }
    }

    @Override
    public int getMaxSafeInput() {
        return EnergyNet.instance.getPowerFromTier(3);
    }

    // }}

    @Override
    public void updateEntity() {
        if (!addedToEnet) {
            onLoaded();
        }
        checkFluid();
        /* EU<--Fluid Energy */
        // {{
        if (getMeta() == 0) {
            if (energyTank.getFluidAmount() >= 72) {
                if (currentEnergy != maxEnergy) {
                    int fluidAmount = energyTank.getFluidAmount();
                    energyTank.setFluid(new FluidStack(ModFluids.fluidEnergy, fluidAmount - 72));
                    currentEnergy++;
                }
            }
        }
        // }}
        /* EU-->Fluid Energy */
        // {{
        if (getMeta() == 1) {
            System.out.println(currentEnergy);
        }
        // }}
    }

    public void onLoaded() {
        if (!addedToEnet && !FMLCommonHandler.instance().getEffectiveSide().isClient() && Info.isIc2Available()) {

            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));

            addedToEnet = true;
        }
    }

    @Override
    public void onChunkUnload() {
        if (addedToEnet && Info.isIc2Available()) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));

            addedToEnet = false;
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();

        onChunkUnload();
    }

}
