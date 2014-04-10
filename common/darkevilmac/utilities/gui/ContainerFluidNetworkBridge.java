package darkevilmac.utilities.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import darkevilmac.utilities.tile.TileEntityFluidNetworkBridge;

public class ContainerFluidNetworkBridge extends Container {

    public TileEntityFluidNetworkBridge tile;

    public ContainerFluidNetworkBridge(InventoryPlayer invPlayer, TileEntityFluidNetworkBridge bridge) {
        tile = bridge;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invPlayer, 9 + x + y * 9, 8 + x * 18, 84 + y * 18));
            }
        }
        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(invPlayer, x, 8 + x * 18, 142));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }

}
