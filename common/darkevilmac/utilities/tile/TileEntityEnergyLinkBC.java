package darkevilmac.utilities.tile;

import darkevilmac.utilities.tile.prefab.TileEntityEnergyLinkBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;

public class TileEntityEnergyLinkBC extends TileEntityEnergyLinkBase implements IPowerEmitter, IPowerReceptor {

    private PowerHandler powerHandler;

    public TileEntityEnergyLinkBC() {
        powerHandler = new PowerHandler(this, Type.STORAGE);
        initPowerProvider();
    }

    private void initPowerProvider() {
        powerHandler.configure(50, 300, 1, 5000);
        powerHandler.configurePowerPerdition(1, 1);
    }

    @Override
    public void updateEntity() {
        checkFluid();
        /* MJ<--Fluid Energy */
        // {{
        if (getMeta() == 0) {
            if (powerHandler.getEnergyStored() <= powerHandler.getMaxEnergyStored() - 1) {
                if (energyTank.getFluidAmount() >= 175) {
                    
                    energyTank.setFluid(new FluidStack(energyTank.getFluid().getFluid(), flAmount - 175));
                }
            }
        }
        // }}
        /* MJ-->Fluid Energy */
        // {{
        if (getMeta() == 1) {
            if (powerHandler.getEnergyStored() >= 1) {
                if (energyTank.getFluidAmount() <= energyTankSize - 175) {
                    
                    energyTank.setFluid(new FluidStack(energyTank.getFluid().getFluid(), flAmount + 175));
                }
            }
        }
        // }}
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        energyTank.readFromNBT(tag);
        powerHandler.readFromNBT(tag);
        initPowerProvider();
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        energyTank.writeToNBT(tag);
        powerHandler.writeToNBT(tag);
    }

    @Override
    public PowerReceiver getPowerReceiver(ForgeDirection side) {
        if (getMeta() == 1) {
            return powerHandler.getPowerReceiver();
        } else {
            return null;
        }
    }

    @Override
    public void doWork(PowerHandler workProvider) {
        // See onUpdate
    }

    @Override
    public boolean canEmitPowerFrom(ForgeDirection side) {
        return getMeta() == 0;
    }

    @Override
    public World getWorld() {
        return this.worldObj;
    }

}
