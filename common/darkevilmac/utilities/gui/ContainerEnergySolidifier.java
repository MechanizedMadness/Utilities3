package darkevilmac.utilities.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import darkevilmac.utilities.tile.TileEntityEnergySolidifier;

public class ContainerEnergySolidifier extends Container {

    public TileEntityEnergySolidifier tile;

    public ContainerEnergySolidifier(InventoryPlayer invPlayer, TileEntityEnergySolidifier tile) {
        this.tile = tile;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invPlayer, 9 + x + y * 9, 8 + x * 18, 84 + y * 18));
            }
        }
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invPlayer, x, 8 + x * 18, 142));
        }

        addSlotToContainer(new Slot(tile, 0, 0, 0));

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

}
