package darkevilmac.utilities.tile.prefab;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.tile.TileEntityEnergyLinkBC;
import darkevilmac.utilities.tile.TileEntityEnergyLinkIC2;
import darkevilmac.utilities.tile.TileEntityEnergyLinkSteam;
import darkevilmac.utilities.tile.TileEntityFluidLink;

public class TileEntityEnergyLinkBase extends TileEntityUtilities implements IFluidHandler {

    FluidStack fluidToSend;

    protected TileEntityFluidLink fluidLink;
    protected World world;
    protected IFluidHandler fluidHandler;
    protected ForgeDirection handlerDir;
    public int maxEnergyPoints = 60000;
    public int energyPoints = 0;
    protected FluidStack energyEmptyFluid = new FluidStack(ModFluids.fluidEnergy, 0);
    protected int energyTankSize = FluidContainerRegistry.BUCKET_VOLUME * 4;
    protected FluidTank energyTank = new FluidTank(energyTankSize);
    protected ForgeDirection fluidAcceptorDir;
    protected IFluidHandler fluidAcceptor;

    @Override
    public void validate() {
        super.validate();

        handlerDir = ForgeDirection.UNKNOWN;
        fluidAcceptorDir = ForgeDirection.UNKNOWN;
        world = worldObj;
        checkFluidAcceptor();
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote == false) {
            checkFluidAcceptor();
            checkFluid();
            if (getMeta() == 0) {
                convertFluidToUnits();
            }
            if (getMeta() == 1) {
                convertUnitsToFluid();
                if (energyTank.getFluidAmount() >= 20)
                    pushToConsumers(energyTank);

            }
        }
    }

    public void doNothing(IFluidHandler fl) {

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

    public void pushToConsumers(FluidTank tank) {
        if (fluidAcceptor != null) {
            fluidAcceptor.fill(fluidAcceptorDir.getOpposite(), tank.drain(20, true), true);
        }
    }

    public void checkFluidAcceptor() {
        System.out.println("Checking.");

        if (world != null) {
            System.out.println("World isnt null");
            int tileType = 0;
            if (world.getBlockTileEntity(xCoord, yCoord, zCoord) != null) {
                if (world.getBlockTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEnergyLinkBC) {
                    tileType = 1;
                }
                if (world.getBlockTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEnergyLinkIC2) {
                    tileType = 2;
                }
                if (world.getBlockTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityEnergyLinkSteam) {
                    tileType = 3;
                }
            }
            if (tileType != 0) {
                if (world.getBlockTileEntity(xCoord + 1, yCoord, zCoord) != null) {
                    System.out.println("Tile not null");
                    if (!isTile(xCoord + 1, yCoord, zCoord, tileType)) {
                        if (world.getBlockTileEntity(xCoord + 1, yCoord, zCoord) instanceof IFluidHandler) {
                            System.out.println("FluidHandler Exists.");
                            if (((IFluidHandler) world.getBlockTileEntity(xCoord + 1, yCoord, zCoord)).canFill(ForgeDirection.EAST.getOpposite(), ModFluids.fluidEnergy)) {
                                System.out.println("It accepts fluidEnergy");
                                fluidAcceptorDir = ForgeDirection.EAST;
                                fluidAcceptor = ((IFluidHandler) world.getBlockTileEntity(xCoord + 1, yCoord, zCoord));
                                return;
                            }
                        }
                    }
                }
                if (world.getBlockTileEntity(xCoord - 1, yCoord, zCoord) != null) {
                    System.out.println("Tile not null");
                    if (!isTile(xCoord - 1, yCoord, zCoord, tileType)) {
                        if (world.getBlockTileEntity(xCoord - 1, yCoord, zCoord) instanceof IFluidHandler) {
                            System.out.println("FluidHandler Exists.");
                            if (((IFluidHandler) world.getBlockTileEntity(xCoord - 1, yCoord, zCoord)).canFill(ForgeDirection.EAST.getOpposite(), ModFluids.fluidEnergy)) {
                                System.out.println("It accepts fluidEnergy");
                                fluidAcceptorDir = ForgeDirection.WEST;
                                fluidAcceptor = ((IFluidHandler) world.getBlockTileEntity(xCoord - 1, yCoord, zCoord));
                                return;
                            }
                        }
                    }
                }
                if (world.getBlockTileEntity(xCoord, yCoord + 1, zCoord) != null) {
                    System.out.println("Tile not null");
                    if (!isTile(xCoord, yCoord + 1, zCoord, tileType)) {
                        if (world.getBlockTileEntity(xCoord, yCoord + 1, zCoord) instanceof IFluidHandler) {
                            System.out.println("FluidHandler Exists.");
                            if (((IFluidHandler) world.getBlockTileEntity(xCoord, yCoord + 1, zCoord)).canFill(ForgeDirection.EAST.getOpposite(), ModFluids.fluidEnergy)) {
                                System.out.println("It accepts fluidEnergy");
                                fluidAcceptorDir = ForgeDirection.UP;
                                fluidAcceptor = ((IFluidHandler) world.getBlockTileEntity(xCoord, yCoord + 1, zCoord));
                                return;
                            }
                        }
                    }
                }
                if (world.getBlockTileEntity(xCoord, yCoord - 1, zCoord) != null) {
                    System.out.println("Tile not null");
                    if (!isTile(xCoord, yCoord - 1, zCoord, tileType)) {
                        if (world.getBlockTileEntity(xCoord, yCoord - 1, zCoord) instanceof IFluidHandler) {
                            System.out.println("FluidHandler Exists.");
                            if (((IFluidHandler) world.getBlockTileEntity(xCoord, yCoord - 1, zCoord)).canFill(ForgeDirection.EAST.getOpposite(), ModFluids.fluidEnergy)) {
                                System.out.println("It accepts fluidEnergy");
                                fluidAcceptorDir = ForgeDirection.DOWN;
                                fluidAcceptor = ((IFluidHandler) world.getBlockTileEntity(xCoord, yCoord - 1, zCoord));
                                return;
                            }
                        }
                    }
                }
                if (world.getBlockTileEntity(xCoord, yCoord, zCoord + 1) != null) {
                    System.out.println("Tile not null");
                    if (!isTile(xCoord, yCoord, zCoord + 1, tileType)) {
                        if (world.getBlockTileEntity(xCoord, yCoord, zCoord + 1) instanceof IFluidHandler) {
                            System.out.println("FluidHandler Exists.");
                            if (((IFluidHandler) world.getBlockTileEntity(xCoord, yCoord, zCoord + 1)).canFill(ForgeDirection.EAST.getOpposite(), ModFluids.fluidEnergy)) {
                                System.out.println("It accepts fluidEnergy");
                                fluidAcceptorDir = ForgeDirection.SOUTH;
                                fluidAcceptor = ((IFluidHandler) world.getBlockTileEntity(xCoord, yCoord, zCoord + 1));
                                return;
                            }
                        }
                    }
                }
                if (world.getBlockTileEntity(xCoord, yCoord, zCoord - 1) != null) {
                    System.out.println("Tile not null");
                    if (!isTile(xCoord, yCoord, zCoord - 1, tileType)) {
                        if (world.getBlockTileEntity(xCoord, yCoord, zCoord - 1) instanceof IFluidHandler) {
                            System.out.println("FluidHandler Exists.");
                            if (((IFluidHandler) world.getBlockTileEntity(xCoord, yCoord, zCoord - 1)).canFill(ForgeDirection.EAST.getOpposite(), ModFluids.fluidEnergy)) {
                                System.out.println("It accepts fluidEnergy");
                                fluidAcceptorDir = ForgeDirection.NORTH;
                                fluidAcceptor = ((IFluidHandler) world.getBlockTileEntity(xCoord, yCoord, zCoord - 1));
                                return;
                            }
                        }
                    }
                }
            }
            fluidAcceptorDir = ForgeDirection.UNKNOWN;
            fluidAcceptor = null;
            return;
        }
        fluidAcceptorDir = ForgeDirection.UNKNOWN;
        fluidAcceptor = null;
        return;
    }

    public boolean isTile(int x, int y, int z, int tileType) {
        boolean bool = false;
        if (tileType == 1) {
            if (world.getBlockTileEntity(x, y, z) != null) {
                if (world.getBlockTileEntity(x, y, z) instanceof TileEntityEnergyLinkBC) {
                    bool = true;
                } else {
                    bool = false;
                }
            }
        }
        if (tileType == 2) {
            if (world.getBlockTileEntity(x, y, z) != null) {
                if (world.getBlockTileEntity(x, y, z) instanceof TileEntityEnergyLinkIC2) {
                    bool = true;
                } else {
                    bool = false;
                }
            }
        }
        if (tileType == 3) {
            if (world.getBlockTileEntity(x, y, z) != null) {
                if (world.getBlockTileEntity(x, y, z) instanceof TileEntityEnergyLinkSteam) {
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

    public void onNeighborBlockChange() {
        super.onNeighborBlockChange();

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