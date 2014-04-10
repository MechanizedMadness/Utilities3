package darkevilmac.utilities.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import darkevilmac.utilities.tile.TileEntityItemNetworkBridge;

public class ContainerItemNetworkBridge extends Container {

    TileEntityItemNetworkBridge tile;

    public ContainerItemNetworkBridge(InventoryPlayer invPlayer, TileEntityItemNetworkBridge bridge) {
        tile = bridge;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invPlayer, 9 + x + y * 9, 8 + x * 18, 84 + y * 18));
            }
        }

        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invPlayer, x, 8 + x * 18, 142));
        }

        int i = 1;
        int slotX = 26;
        while (i < 15) {
            if (i >= 8) {
                if (slotX == 152) {
                    slotX = 26;
                }
                addSlotToContainer(new Slot(invPlayer, i - 1, slotX, 35));
            } else {
                addSlotToContainer(new Slot(invPlayer, i - 1, slotX, 17));
            }
            slotX = slotX + 18;
            i++;
        }

        addSlotToContainer(new Slot(invPlayer, 1, 26, 17));

    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }

}
