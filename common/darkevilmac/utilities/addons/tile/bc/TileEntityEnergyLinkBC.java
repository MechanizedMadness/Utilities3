package darkevilmac.utilities.addons.tile.bc;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile.PipeType;
import darkevilmac.utilities.shadows.TileBuffer;
import darkevilmac.utilities.tile.base.TileEntityEnergyLinkBase;
import darkevilmac.utilities.utils.MJUtils;

public class TileEntityEnergyLinkBC extends TileEntityEnergyLinkBase implements IPowerEmitter, IPowerReceptor, IFluidHandler, IPipeConnection {

    private PowerHandler powerHandler;
    private TileBuffer[] energyTileBuffer = null;

    public TileEntityEnergyLinkBC() {

    }

    @Override
    public void validate() {
        super.validate();
        powerHandler = new PowerHandler(this, Type.STORAGE);
        initPowerProvider();
    }

    private void initPowerProvider() {
        if (powerHandler != null) {
            powerHandler.configure(50, 300, 10000, 10000);
            powerHandler.configurePowerPerdition(1, 1);
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        int flAmount = energyTank.getFluidAmount();
        if (!worldObj.isRemote) {
            /* MJ<--Fluid Energy */
            // {{
            if (getMeta() == 0) {
                convertMJFromPoints();
                if (energyTileBuffer == null)
                    energyTileBuffer = TileBuffer.makeBuffer(world, xCoord, yCoord, zCoord, false);
                MJUtils.pushToPowerReceptors(powerHandler, getTileType(), energyTileBuffer);
            }
            // }}
            /* MJ-->Fluid Energy */
            // {{
            if (getMeta() == 1) {
                convertPointsFromMJ();
            }
            // }}
        }
    }

    // BCPOWER
    // {{

    public void convertMJFromPoints() {
        float MJ = 0F;
        int falsePoints = energyPoints;
        while (falsePoints >= 175) {
            falsePoints = falsePoints - 175;
            MJ++;
        }
        // The following should be impossible but I'm weird and plan for mess
        // ups.
        if (energyPoints - 175 * MJ < 0) {
            MJ--;
        }

        powerHandler.addEnergy(MJ);
        energyPoints = (int) (energyPoints - 175 * MJ);
    }

    public void convertPointsFromMJ() {
        float MJ = (float) powerHandler.getEnergyStored();
        int falsePoints = energyPoints;

        while (MJ >= 1F) {
            MJ = MJ - 1;
            falsePoints = falsePoints + 175;
            if (falsePoints > maxEnergyPoints) {
                falsePoints = falsePoints - 175;
                MJ = MJ + 1;
                break;
            }
        }
        powerHandler.setEnergy(MJ);
        energyPoints = falsePoints;
    }

    protected TileEntity getEnergyTile(ForgeDirection side) {
        if (energyTileBuffer == null)
            energyTileBuffer = TileBuffer.makeBuffer(world, xCoord, yCoord, zCoord, false);
        return energyTileBuffer[side.ordinal()].getTile();
    }

    // }}

    @Override
    public void onNeighborBlockChange(Block blockType) {
        super.onNeighborBlockChange(blockType);
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
        return this.world;
    }

    @Override
    public ConnectOverride overridePipeConnection(PipeType type, ForgeDirection with) {
        if (type == PipeType.POWER) {
            return ConnectOverride.CONNECT;
        } else if (type == PipeType.FLUID) {
            return ConnectOverride.CONNECT;
        } else {
            return ConnectOverride.DEFAULT;
        }
    }

    /* IFluidHandler */
    // {{
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
    // }}

}
