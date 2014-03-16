package darkevilmac.utilities.shadows;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.tile.TileEntityEnergyNetworkManager;
import darkevilmac.utilities.tile.base.TileEntityEnergyLinkBase;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

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

    public static void pullFluidFromConsumers(TileEntityEnergyNetworkManager manager, TileBuffer[] tileBuffer) {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity tile = tileBuffer[side.ordinal()].getTile();
            if (tile instanceof IFluidHandler) {
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
