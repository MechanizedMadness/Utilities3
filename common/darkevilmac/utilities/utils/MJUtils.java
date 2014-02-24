package darkevilmac.utilities.utils;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import darkevilmac.utilities.shadows.TileBuffer;
import darkevilmac.utilities.tile.base.TileEntityEnergyLinkBase;

public class MJUtils {

    /**
     * Returns the amount of energy pushed.
     * 
     * @param mjToPush
     * @param linkType
     * @param tileBuffer
     * @return
     */
    public static void pushToPowerReceptors(PowerHandler powerHandler, int linkType, TileBuffer[] tileBuffer) {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity tile = tileBuffer[side.ordinal()].getTile();
            if (tile != null) {
                if (tile instanceof IPowerReceptor && ((IPowerReceptor) tile).getPowerReceiver(side.getOpposite()) != null) {
                    if (tile instanceof TileEntityEnergyLinkBase == false) {
                        IPowerReceptor tileReceptor = ((IPowerReceptor) tile);
                        PowerReceiver tileReciever = tileReceptor.getPowerReceiver(side.getOpposite());

                        if (powerHandler.getEnergyStored() >= tileReciever.powerRequest()) {
                            tileReciever.receiveEnergy(Type.PIPE,
                                    powerHandler.useEnergy(tileReciever.powerRequest(), tileReciever.powerRequest(), true),
                                    side.getOpposite());
                        } else if (powerHandler.getEnergyStored() > 0) {
                            tileReciever.receiveEnergy(Type.PIPE,
                                    powerHandler.useEnergy(powerHandler.getEnergyStored(), powerHandler.getEnergyStored(), true),
                                    side.getOpposite());
                        }
                    } else if (((TileEntityEnergyLinkBase) tile).getTileType() != linkType) {
                        IPowerReceptor tileReceptor = ((IPowerReceptor) tile);
                        PowerReceiver tileReciever = tileReceptor.getPowerReceiver(side.getOpposite());

                        if (powerHandler.getEnergyStored() >= tileReciever.powerRequest()) {
                            tileReciever.receiveEnergy(Type.PIPE,
                                    powerHandler.useEnergy(tileReciever.powerRequest(), tileReciever.powerRequest(), true),
                                    side.getOpposite());
                        } else if (powerHandler.getEnergyStored() > 0) {
                            tileReciever.receiveEnergy(Type.PIPE,
                                    powerHandler.useEnergy(powerHandler.getEnergyStored(), powerHandler.getEnergyStored(), true),
                                    side.getOpposite());
                        }
                    }
                }
            }
        }
    }
}
