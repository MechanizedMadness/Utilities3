package darkevilmac.utilities.utils;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.shadows.TileBuffer;
import darkevilmac.utilities.tile.TileEntityEnergyNetworkManager;
import darkevilmac.utilities.tile.TileEntityFluidNetworkManager;
import darkevilmac.utilities.tile.base.TileEntityEnergyLinkBase;

/**
 * Originally from buildcraft with some modifications.
 * 
 * Original @author CovertJaguar
 */
public class FluidUtils {

    // Begin Network Bridge FluidUtils

    public static void pushFluidToConsumers(TileEntityEnergyNetworkManager manager, TileBuffer[] tileBuffer) {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            if (manager.internalEnergy != null && manager.internalEnergy.amount >= 50) {
                TileEntity tile = tileBuffer[side.ordinal()].getTile();
                if (tile instanceof IFluidHandler) {
                    if (((IFluidHandler) tile).canFill(side.getOpposite(), ModFluids.fluidEnergy)) {
                        FluidStack fill = new FluidStack(ModFluids.fluidEnergy, 50);
                        int used = ((IFluidHandler) tile).fill(side.getOpposite(), fill, true);
                        if (used > 0) {
                            int amountToUse = 50 - used;
                            manager.useEnergy(amountToUse);
                            if (amountToUse <= 0)
                                return;
                        }
                    }
                }
            }
        }
    }

    public static void pullFluidFromConsumers(TileEntityEnergyNetworkManager manager, TileBuffer[] tileBuffer) {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity tile = tileBuffer[side.ordinal()].getTile();
            if (tile instanceof IFluidHandler) {
                if (((IFluidHandler) tile).canDrain(side.getOpposite(), ModFluids.fluidEnergy)) {
                    FluidStack drain = new FluidStack(ModFluids.fluidEnergy, 100);
                    int pulled = ((IFluidHandler) tile).drain(side.getOpposite(), drain, true).amount;
                    if (pulled > 0) {
                        manager.addEnergy(pulled);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public static void pushFluidToConsumers(TileEntityFluidNetworkManager manager, TileBuffer[] tileBuffer, int[] fluidFilters, boolean useFilters) {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity tile = tileBuffer[side.ordinal()].getTile();
            if (tile instanceof IFluidHandler) {
                if (!useFilters) {
                    int i = 0;
                    while (i <= ((IFluidHandler) tile).getTankInfo(side.getOpposite()).length - 1) {
                        int drained;
                        Fluid fluidToDrain = null;
                        if (((IFluidHandler) tile).canDrain(side.getOpposite(), ((IFluidHandler) tile).getTankInfo(side.getOpposite())[i].fluid.getFluid())) {
                            fluidToDrain = ((IFluidHandler) tile).getTankInfo(side.getOpposite())[i].fluid.getFluid();
                            drained = ((IFluidHandler) tile).drain(side.getOpposite(), new FluidStack(((IFluidHandler) tile).getTankInfo(side.getOpposite())[i].fluid.getFluid(),
                                    50), true).amount;
                            if (drained > 0)
                                manager.addFluid(drained, fluidToDrain);
                        }
                        i++;
                    }
                } else {
                    int i = 0;
                    while (i <= ((IFluidHandler) tile).getTankInfo(side.getOpposite()).length - 1) {
                        int j = 0;
                        while (j <= 13) {
                            if (((IFluidHandler) tile).getTankInfo(side.getOpposite())[i].fluid.getFluid().getID() == fluidFilters[j]) {
                                if (((IFluidHandler) tile).canDrain(side.getOpposite(), FluidRegistry.getFluid(fluidFilters[j]))) {
                                    Fluid fluidToDrain = ((IFluidHandler) tile).getTankInfo(side.getOpposite())[i].fluid.getFluid();
                                    int drained = ((IFluidHandler) tile).drain(side.getOpposite(), new FluidStack(FluidRegistry.getFluid(fluidFilters[j]), 50), true).amount;
                                    if (drained > 0)
                                        manager.addFluid(drained, fluidToDrain);
                                }
                            }
                            j++;
                        }
                        i++;
                    }
                }
            }
        }
    }

    public static void pullFluidFromConsumers(TileEntityFluidNetworkManager manager, TileBuffer[] tileBuffer, ArrayList<Fluid> fluidFilters, boolean useFilters) {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity tile = tileBuffer[side.ordinal()].getTile();
            if (tile instanceof IFluidHandler) {
                int i = 0;
                while (i <= ((IFluidHandler) tile).getTankInfo(side.getOpposite()).length - 1) {
                    int drained;
                    Fluid fluidToDrain = null;
                    if (((IFluidHandler) tile).canDrain(side.getOpposite(), ((IFluidHandler) tile).getTankInfo(side.getOpposite())[i].fluid.getFluid())) {
                        fluidToDrain = ((IFluidHandler) tile).getTankInfo(side.getOpposite())[i].fluid.getFluid();
                        drained = ((IFluidHandler) tile).drain(side.getOpposite(), new FluidStack(((IFluidHandler) tile).getTankInfo(side.getOpposite())[i].fluid.getFluid(), 50),
                                true).amount;
                        if (drained > 0)
                            manager.addFluid(drained, fluidToDrain);
                    }
                    i++;
                }

            }
        }
    }

    // End Network Bridge FluidUtils (Could be useful in the future?)

    public static void pushFluidToConsumers(IFluidTank tank, int flowCap, TileBuffer[] tileBuffer) {
        int amountToPush = flowCap;
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            FluidStack fluidStack = tank.drain(amountToPush, false);
            if (fluidStack != null && fluidStack.amount > 0) {
                TileEntity tile = tileBuffer[side.ordinal()].getTile();
                if (tile instanceof IFluidHandler) {
                    int used = ((IFluidHandler) tile).fill(side.getOpposite(), fluidStack, true);
                    if (used > 0) {
                        amountToPush -= used;
                        tank.drain(used, true);
                        if (amountToPush <= 0)
                            return;
                    }
                }
            }
        }
    }

    public static void pushFluidToConsumersLink(IFluidTank tank, int flowCap, int linkType, TileBuffer[] tileBuffer) {
        int amountToPush = flowCap;
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            FluidStack fluidStack = tank.drain(amountToPush, false);
            if (fluidStack != null && fluidStack.amount > 0) {
                TileEntity tile = tileBuffer[side.ordinal()].getTile();
                if (tile instanceof IFluidHandler) {
                    if (tile instanceof TileEntityEnergyLinkBase == false) {
                        int used = ((IFluidHandler) tile).fill(side.getOpposite(), fluidStack, true);
                        if (used > 0) {
                            amountToPush -= used;
                            tank.drain(used, true);
                            if (amountToPush <= 0)
                                return;
                        }
                    } else if (((TileEntityEnergyLinkBase) tile).getTileType() != linkType) {
                        int used = ((IFluidHandler) tile).fill(side.getOpposite(), fluidStack, true);
                        if (used > 0) {
                            amountToPush -= used;
                            tank.drain(used, true);
                            if (amountToPush <= 0)
                                return;
                        }
                    }
                }
            }
        }
    }
}
