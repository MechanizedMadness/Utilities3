package darkevilmac.utilities.tile;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityItemNetworkManager extends TileEntityUtilities {

    public ArrayList<TileEntityItemNetworkBridge> itemBridges = new ArrayList<TileEntityItemNetworkBridge>();
    public int[] itemBridgesXCoords;
    public int[] itemBridgesYCoords;
    public int[] itemBridgesZCoords;

    public boolean shouldReadBridgesFromNBT;
    public boolean justReadNBT;

    public TileEntityItemNetworkManager() {

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        if (!itemBridges.isEmpty()) {
            int[] itemBridgesXCoords = new int[itemBridges.size()];
            int[] itemBridgesYCoords = new int[itemBridges.size()];
            int[] itemBridgesZCoords = new int[itemBridges.size()];

            int i = 0;
            while (i <= itemBridges.size() - 1) {
                itemBridgesXCoords[i] = itemBridges.get(i).xCoord;
                itemBridgesYCoords[i] = itemBridges.get(i).yCoord;
                itemBridgesZCoords[i] = itemBridges.get(i).zCoord;
                i++;
            }
            nbt.setIntArray("itemBridgesXCoords", itemBridgesXCoords);
            nbt.setIntArray("itemBridgesYCoords", itemBridgesYCoords);
            nbt.setIntArray("itemBridgesZCoords", itemBridgesZCoords);
            nbt.setBoolean("hasBridgesInNBT", true);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.getBoolean("hasBridgesInNBT")) {
            itemBridgesXCoords = nbt.getIntArray("itemBridgesXCoords");
            itemBridgesYCoords = nbt.getIntArray("itemBridgesYCoords");
            itemBridgesZCoords = nbt.getIntArray("itemBridgesZCoords");
            shouldReadBridgesFromNBT = true;
        } else {
            shouldReadBridgesFromNBT = false;
        }

        justReadNBT = true;
    }

    @Override
    public void validate() {
        super.validate();

        if (itemBridges == null)
            itemBridges = new ArrayList<TileEntityItemNetworkBridge>();

        if (!justReadNBT) {
            itemBridgesXCoords = new int[1];
            itemBridgesYCoords = new int[1];
            itemBridgesZCoords = new int[1];
            // 260 is an impossible coordinate just used so it can be compared
            // with me being sure the block wasn't set by normal means
            itemBridgesYCoords[0] = 260;
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!world.isRemote) {

            int xCoordsLength = itemBridgesXCoords.length;
            if (itemBridges.isEmpty() && shouldReadBridgesFromNBT) {
                shouldReadBridgesFromNBT = false;
                if (itemBridgesYCoords[0] == 260) {
                } else {
                    int i = 0;
                    while (i <= xCoordsLength - 1) {
                        itemBridges.add((TileEntityItemNetworkBridge) worldObj.getTileEntity(itemBridgesXCoords[i], itemBridgesYCoords[i], itemBridgesZCoords[i]));
                        i++;
                    }
                }
            }

            int i = 0;
            // Removes bridges that don't exist anymore.
            if (!itemBridges.isEmpty()) {
                while (i <= itemBridges.size() - 1) {
                    if (world.getTileEntity(itemBridges.get(i).xCoord, itemBridges.get(i).yCoord, itemBridges.get(i).zCoord) == null) {
                        itemBridges.remove(i);
                        i--;
                    }
                    i++;
                }
            }
        }
    }

    @Override
    public void onNeighborBlockChange(Block blockType) {
        super.onNeighborBlockChange(blockType);

    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
        super.onBlockDestroyedByPlayer(world, x, y, z, par5);

        int i = 0;
        if (!itemBridges.isEmpty()) {
            while (i <= itemBridges.size() - 1) {
                itemBridges.get(i).clearManager();
            }
        }
    }

}
