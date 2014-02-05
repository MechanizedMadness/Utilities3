package darkevilmac.utilities.shadows;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import darkevilmac.utilities.tile.prefab.TileEntityEnergyLinkBase;

/**
 * 
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public class FluidUtils {

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
