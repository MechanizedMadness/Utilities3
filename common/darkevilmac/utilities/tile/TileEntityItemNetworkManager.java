package darkevilmac.utilities.tile;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityItemNetworkManager extends TileEntityUtilities {

    public ArrayList<Item> itemList;
    public ArrayList<Integer> itemAmounts;
    public String[] itemRegistryNames;
    public int[] itemAmountsInt;
    public String[] itemRegistryNamesNBT;
    public int[] itemAmountsIntNBT;

    public ArrayList<TileEntityItemNetworkBridge> itemBridges = new ArrayList<TileEntityItemNetworkBridge>();
    public int[] itemBridgesXCoords;
    public int[] itemBridgesYCoords;
    public int[] itemBridgesZCoords;

    public boolean shouldReadBridgesFromNBT;
    public boolean shouldReadItemsFromNBT;
    public boolean doneReadingItemsFromNBT;
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
        } else {
            nbt.setBoolean("hasBridgesInNBT", false);
        }

        if (!itemList.isEmpty()) {
            itemAmountsIntNBT = new int[itemAmounts.size()];
            itemRegistryNamesNBT = new String[itemAmounts.size()];

            int i = 0;
            while (i <= itemAmounts.size() - 1) {
                itemAmountsIntNBT[i] = itemAmounts.get(i).intValue();
                itemRegistryNamesNBT[i] = Item.itemRegistry.getNameForObject(itemList.get(i));
                i++;
            }

            nbt.setIntArray("itemAmountsIntNBT", itemAmountsIntNBT);
            i = 0;
            while (i <= itemAmounts.size() - 1) {
                nbt.setString("itemRegistryNamesNBT" + i, itemRegistryNamesNBT[i]);
                i++;
            }

            nbt.setBoolean("hasItemsInNBT", true);
        } else {
            nbt.setBoolean("hasItemsInNBT", false);
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
            nbt.setBoolean("hasBridgesInNBT", false);
        } else {
            shouldReadBridgesFromNBT = false;
        }

        if (nbt.getBoolean("hasItemsInNBT")) {
            itemAmountsInt = nbt.getIntArray("itemAmountsIntNBT");
            itemRegistryNames = new String[itemAmountsInt.length];

            int i = 0;
            while (i <= itemAmountsInt.length - 1) {
                itemRegistryNames[i] = nbt.getString("itemRegistryNamesNBT" + 1);
                i++;
            }

            shouldReadItemsFromNBT = true;
            nbt.setBoolean("hasItemsInNBT", false);
        } else {
            shouldReadItemsFromNBT = false;
        }

        justReadNBT = true;
    }

    @Override
    public void validate() {
        super.validate();

        if (itemList == null) {
            itemList = new ArrayList<Item>();
        }

        if (itemAmounts == null) {
            itemAmounts = new ArrayList<Integer>();
        }

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

            if (itemList.isEmpty() && !shouldReadItemsFromNBT && !doneReadingItemsFromNBT) {
                int i = 0;
                doneReadingItemsFromNBT = true;
                while (i <= Item.itemRegistry.getKeys().size() - 1) {
                    Item itemToAdd = (Item) Item.itemRegistry.getObjectById(i);
                    itemList.add(itemToAdd);
                    itemAmounts.add(new Integer(0));
                    i++;
                }
            }

            if (itemList.isEmpty() && shouldReadItemsFromNBT && !doneReadingItemsFromNBT) {
                shouldReadItemsFromNBT = false;
                doneReadingItemsFromNBT = true;
                int i = 0;
                while (i <= itemAmountsInt.length) {
                    itemList.add((Item) Item.itemRegistry.getObject(itemRegistryNames[i]));
                    itemAmounts.add(new Integer(itemAmountsInt[i]));
                    i++;
                }
            }

            if (itemBridges.isEmpty() && shouldReadBridgesFromNBT) {
                int xCoordsLength = itemBridgesXCoords.length;
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

    public int hasItem(Item item) {
        if (!itemList.isEmpty()) {
            int i = 0;
            while (i <= itemList.size() - 1) {
                if (itemList.get(i) == item) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public void addItem(ItemStack stack) {
        if (stack != null) {
            int amountToAdd = stack.stackSize;
            Item itemToAdd = stack.getItem();

            int index = hasItem(itemToAdd);
            if (index != -1) {
                itemAmounts.set(index, new Integer(itemAmounts.get(index).intValue() + amountToAdd));
            } else {
                itemList.add(itemToAdd);
                itemAmounts.add(new Integer(amountToAdd));
            }
        }
    }

    public int useItem(ItemStack stack) {
        if (stack != null) {
            int amountToUse = stack.stackSize;
            Item itemToUse = stack.getItem();

            int index = hasItem(itemToUse);

            int returnVal = 0;
            if (index != -1) {
                if (amountToUse <= itemAmounts.get(index).intValue()) {
                    returnVal = amountToUse;
                    itemAmounts.set(index, new Integer(itemAmounts.get(index).intValue() - amountToUse));
                } else {
                    returnVal = itemAmounts.get(index).intValue();
                    itemAmounts.set(index, new Integer(itemAmounts.get(index).intValue() - itemAmounts.get(index).intValue()));
                }
            } else {
                if (amountToUse <= itemAmounts.get(index).intValue()) {
                    returnVal = amountToUse;
                    itemList.add(itemToUse);
                    itemAmounts.add(new Integer(itemAmounts.get(itemAmounts.size() - 1).intValue() - amountToUse));
                } else {
                    returnVal = itemAmounts.get(index).intValue();
                    itemList.add(itemToUse);
                    itemAmounts.add(new Integer(itemAmounts.get(itemAmounts.size() - 1).intValue() - itemAmounts.get(itemAmounts.size() - 1).intValue()));
                }
            }
            return returnVal;
        }
        return 0;
    }

}
