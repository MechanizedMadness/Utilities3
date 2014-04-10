package darkevilmac.utilities.utils;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.shadows.TileBuffer;
import darkevilmac.utilities.tile.TileEntityFluidNetworkManager;
import darkevilmac.utilities.tile.base.TileEntityEnergyLinkBase;

/**
 * Originally from buildcraft with some modifications.
 * 
 * Original @author CovertJaguar
 */
public class FluidUtils {

    public static void pushFluidToHandlers(TileEntityFluidNetworkManager manager, TileBuffer[] tileBuffer, int[] fluidFilters, boolean useFilters) {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity tile = tileBuffer[side.ordinal()].getTile();
            if (tile != null && tile instanceof IFluidHandler) {
                IFluidHandler fluidTile = (IFluidHandler) tile;
                if (!useFilters) {
                    int i = 0;
                    while (i <= manager.internalFluids.size() - 1) {
                        int amountToPush = 50;
                        if (manager.internalFluids.get(i).amount < 50) {
                            amountToPush = manager.internalFluids.get(i).amount;
                        }
                        if (manager.internalFluids.get(i).getFluid().getID() != ModFluids.fluidEmptyFilter.getID()) {
                            int amountPushed = fluidTile.fill(side.getOpposite(), new FluidStack(manager.internalFluids.get(i).getFluid(), amountToPush), true);
                            manager.useFluid(amountPushed, manager.internalFluids.get(i).getFluid());
                        }
                        i++;
                    }
                } else {
                    int i = 0;
                    while (i <= 13) {
                        if (fluidTile.canFill(side.getOpposite(), FluidRegistry.getFluid(fluidFilters[i]))) {
                            int index = manager.hasFluid(FluidRegistry.getFluid(fluidFilters[i]));
                            int amountToPush = 50;
                            if (index != -1) {
                                if (manager.internalFluids.get(index).amount <= 50) {
                                    amountToPush = manager.internalFluids.get(index).amount;
                                }

                                int amountPushed = fluidTile.fill(side.getOpposite(), new FluidStack(manager.internalFluids.get(index), amountToPush), true);
                                manager.useFluid(amountPushed, manager.internalFluids.get(index).getFluid());
                            }
                        }
                        i++;
                    }
                }
            }
        }
    }

    public static void pullFluidFromHandlers(TileEntityFluidNetworkManager manager, TileBuffer[] tileBuffer, int[] fluidFilters, boolean useFilters) {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity tile = tileBuffer[side.ordinal()].getTile();
            if (tile != null && tile instanceof IFluidHandler) {
                IFluidHandler fluidTile = (IFluidHandler) tile;
                if (!useFilters) {
                    FluidStack amountPulled = fluidTile.drain(side.getOpposite(), 50, true);
                    if (amountPulled != null) {
                        manager.addFluid(amountPulled.amount, amountPulled.getFluid());
                    }
                } else {
                    int i = 0;
                    while (i <= 13) {
                        FluidStack amountPulled = fluidTile.drain(side.getOpposite(), new FluidStack(FluidRegistry.getFluid(fluidFilters[i]), 50), true);
                        if (amountPulled != null) {
                            manager.addFluid(amountPulled.amount, amountPulled.getFluid());
                        }
                        i++;
                    }
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

    public static void pullFluidFromProducers(IFluidTank tank, FluidStack fluidToPull, TileBuffer[] tileBuffer) {
        int amountToPull = fluidToPull.amount;
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity tile = tileBuffer[side.ordinal()].getTile();
            if (tile != null && tile instanceof IFluidHandler) {
                tank.fill(((IFluidHandler) tile).drain(side.getOpposite(), fluidToPull, true), true);
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
