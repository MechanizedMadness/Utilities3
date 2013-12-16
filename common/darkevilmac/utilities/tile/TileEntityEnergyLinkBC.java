package darkevilmac.utilities.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
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
import darkevilmac.utilities.tile.prefab.TileEntityEnergyLinkBase;

public class TileEntityEnergyLinkBC extends TileEntityEnergyLinkBase implements IPowerEmitter, IPowerReceptor, IFluidHandler, IPipeConnection {

    private PowerHandler powerHandler;
    private ForgeDirection receptorDir;
    private ForgeDirection emitterDir;
    private IPowerReceptor receptor;
    private IPowerEmitter emitter;

    public TileEntityEnergyLinkBC() {

    }

    @Override
    public void validate() {
        super.validate();
        powerHandler = new PowerHandler(this, Type.STORAGE);
        receptorDir = ForgeDirection.UNKNOWN;
        emitterDir = ForgeDirection.UNKNOWN;
        initPowerProvider();
    }

    private void initPowerProvider() {
        if (powerHandler != null) {
            powerHandler.configure(50, 300, 1, 5000);
            powerHandler.configurePowerPerdition(1, 1);
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        int flAmount = energyTank.getFluidAmount();
        if (worldObj.isRemote == false) {
            /* MJ<--Fluid Energy */
            // {{
            if (getMeta() == 0) {
                checkReceptor();
                if (receptorDir != ForgeDirection.UNKNOWN) {
                    System.out.println(receptorDir + " is good.");
                    if (receptor != null) {
                        System.out.println("Receptor isn't null");
                        if (receptor.getPowerReceiver(receptorDir.getOpposite()) != null) {
                            System.out.println("Receptor reciever isn't null");
                            if (receptor.getPowerReceiver(receptorDir.getOpposite()).powerRequest() >= 0F) {
                                System.out.println("Power Wanted");
                                if (energyPoints >= 175) {
                                    System.out.println("Has fluid to do it.");
                                    receptor.getPowerReceiver(receptorDir.getOpposite()).receiveEnergy(Type.PIPE, 1, receptorDir.getOpposite());
                                    energyPoints = energyPoints - 175;
                                }
                            }
                        }
                    }
                }
            }
            // }}
            /* MJ-->Fluid Energy */
            // {{
            if (getMeta() == 1) {
                if (powerHandler != null) {
                    if (powerHandler.getEnergyStored() >= 1F) {
                        if (energyPoints <= maxEnergyPoints - 175) {
                            energyPoints = energyPoints + 175;
                            powerHandler.setEnergy(powerHandler.getEnergyStored() - 1F);
                        }
                    }
                }
            }
            // }}
        }
    }

    // BCPOWER
    // {{
    private void checkReceptor() {
        TileEntity tile;
        if (world != null) {
            if (world.getBlockTileEntity(xCoord, yCoord + 1, zCoord) != null) {
                if (world.getBlockTileEntity(xCoord, yCoord + 1, zCoord) instanceof IPowerReceptor) {
                    if (((IPowerReceptor) world.getBlockTileEntity(xCoord, yCoord + 1, zCoord)).getPowerReceiver(ForgeDirection.UP.getOpposite()) != null) {
                        receptorDir = ForgeDirection.UP;
                        tile = world.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
                        receptor = (IPowerReceptor) tile;
                        return;
                    }
                }
            }
            if (world.getBlockTileEntity(xCoord, yCoord - 1, zCoord) != null) {
                if (world.getBlockTileEntity(xCoord, yCoord - 1, zCoord) instanceof IPowerReceptor) {
                    if (((IPowerReceptor) world.getBlockTileEntity(xCoord, yCoord - 1, zCoord)).getPowerReceiver(ForgeDirection.DOWN.getOpposite()) != null) {
                        receptorDir = ForgeDirection.DOWN;
                        tile = world.getBlockTileEntity(xCoord, yCoord - 1, zCoord);
                        receptor = (IPowerReceptor) tile;
                        return;
                    }
                }
            }
            if (world.getBlockTileEntity(xCoord + 1, yCoord, zCoord) != null) {
                if (world.getBlockTileEntity(xCoord + 1, yCoord, zCoord) instanceof IPowerReceptor) {
                    if (((IPowerReceptor) world.getBlockTileEntity(xCoord + 1, yCoord, zCoord)).getPowerReceiver(ForgeDirection.EAST.getOpposite()) != null) {
                        receptorDir = ForgeDirection.EAST;
                        tile = world.getBlockTileEntity(xCoord + 1, yCoord, zCoord);
                        receptor = (IPowerReceptor) tile;
                        return;
                    }
                }
            }
            if (world.getBlockTileEntity(xCoord - 1, yCoord, zCoord) != null) {
                if (world.getBlockTileEntity(xCoord - 1, yCoord, zCoord) instanceof IPowerReceptor) {
                    if (((IPowerReceptor) world.getBlockTileEntity(xCoord - 1, yCoord, zCoord)).getPowerReceiver(ForgeDirection.WEST.getOpposite()) != null) {
                        receptorDir = ForgeDirection.WEST;
                        tile = world.getBlockTileEntity(xCoord - 1, yCoord, zCoord);
                        receptor = (IPowerReceptor) tile;
                        return;
                    }
                }
            }
            if (world.getBlockTileEntity(xCoord, yCoord, zCoord + 1) != null) {
                if (world.getBlockTileEntity(xCoord, yCoord, zCoord + 1) instanceof IPowerReceptor) {
                    if (((IPowerReceptor) world.getBlockTileEntity(xCoord, yCoord, zCoord + 1)).getPowerReceiver(ForgeDirection.SOUTH.getOpposite()) != null) {
                        receptorDir = ForgeDirection.SOUTH;
                        tile = world.getBlockTileEntity(xCoord, yCoord, zCoord + 1);
                        receptor = (IPowerReceptor) tile;
                        return;
                    }
                }
            }
            if (world.getBlockTileEntity(xCoord, yCoord, zCoord - 1) != null) {
                if (world.getBlockTileEntity(xCoord, yCoord, zCoord - 1) instanceof IPowerReceptor) {
                    if (((IPowerReceptor) world.getBlockTileEntity(xCoord, yCoord, zCoord - 1)).getPowerReceiver(ForgeDirection.NORTH.getOpposite()) != null) {
                        receptorDir = ForgeDirection.NORTH;
                        tile = world.getBlockTileEntity(xCoord, yCoord, zCoord - 1);
                        receptor = (IPowerReceptor) tile;
                        return;
                    }
                }
            }
        }
        receptor = null;
        receptorDir = ForgeDirection.UNKNOWN;
        return;
    }

    private IPowerEmitter getEmitter() {
        TileEntity tile;
        if (world != null) {
            if (world.getBlockTileEntity(xCoord, yCoord + 1, zCoord) != null) {
                if (world.getBlockTileEntity(xCoord, yCoord + 1, zCoord) instanceof IPowerEmitter) {
                    emitterDir = ForgeDirection.UP;
                    tile = world.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
                    return ((IPowerEmitter) tile);
                }
            }
            if (world.getBlockTileEntity(xCoord, yCoord - 1, zCoord) != null) {
                if (world.getBlockTileEntity(xCoord, yCoord - 1, zCoord) instanceof IPowerEmitter) {
                    emitterDir = ForgeDirection.DOWN;
                    tile = world.getBlockTileEntity(xCoord, yCoord - 1, zCoord);
                    return ((IPowerEmitter) tile);
                }
            }
            if (world.getBlockTileEntity(xCoord + 1, yCoord, zCoord) != null) {
                if (world.getBlockTileEntity(xCoord + 1, yCoord, zCoord) instanceof IPowerEmitter) {
                    emitterDir = ForgeDirection.EAST;
                    tile = world.getBlockTileEntity(xCoord + 1, yCoord, zCoord);
                    return ((IPowerEmitter) tile);
                }
            }
            if (world.getBlockTileEntity(xCoord - 1, yCoord, zCoord) != null) {
                if (world.getBlockTileEntity(xCoord - 1, yCoord, zCoord) instanceof IPowerEmitter) {
                    emitterDir = ForgeDirection.WEST;
                    tile = world.getBlockTileEntity(xCoord - 1, yCoord, zCoord);
                    return ((IPowerEmitter) tile);
                }
            }
            if (world.getBlockTileEntity(xCoord, yCoord, zCoord + 1) != null) {
                if (world.getBlockTileEntity(xCoord, yCoord, zCoord + 1) instanceof IPowerEmitter) {
                    emitterDir = ForgeDirection.SOUTH;
                    tile = world.getBlockTileEntity(xCoord, yCoord, zCoord + 1);
                    return ((IPowerEmitter) tile);
                }
            }
            if (world.getBlockTileEntity(xCoord, yCoord, zCoord - 1) != null) {
                if (world.getBlockTileEntity(xCoord, yCoord, zCoord - 1) instanceof IPowerEmitter) {
                    emitterDir = ForgeDirection.NORTH;
                    tile = world.getBlockTileEntity(xCoord, yCoord, zCoord - 1);
                    return ((IPowerEmitter) tile);
                }
            }
        }
        emitterDir = ForgeDirection.UNKNOWN;
        return null;
    }

    // }}

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
