package darkevilmac.utilities.utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import darkevilmac.utilities.shadows.TileBuffer;
import darkevilmac.utilities.tile.TileEntityItemNetworkManager;

public class StackUtils {

    public static void pushItemsToInventories(TileEntityItemNetworkManager manager, TileBuffer[] tileBuffer, ItemStack[] inventory, boolean useFilters) {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            if (manager != null) {
                TileEntity tile = tileBuffer[side.ordinal()].getTile();
                if (tile != null && tile instanceof IInventory) {
                    if (tile instanceof ISidedInventory) {
                        ISidedInventory sidedInventoryTile = (ISidedInventory) tile;
                        int[] insertionSlots = sidedInventoryTile.getAccessibleSlotsFromSide(side.getOpposite().ordinal());

                        if (!useFilters) {
                            int i = 0;
                            while (i <= manager.itemList.size() - 1) {
                                int j = 0;
                                while (j <= insertionSlots.length - 1) {
                                    if (manager.itemAmounts.get(i).intValue() < 1 == false) {
                                        if (sidedInventoryTile.isItemValidForSlot(insertionSlots[j], new ItemStack(manager.itemList.get(i)))) {
                                            if (sidedInventoryTile.getStackInSlot(insertionSlots[j]) != null) {
                                                if (sidedInventoryTile.getStackInSlot(insertionSlots[j]).stackSize + manager.itemAmounts.get(i) > 64) {
                                                    int amountToUse = 0;
                                                    while (amountToUse + sidedInventoryTile.getStackInSlot(insertionSlots[j]).stackSize < 64) {
                                                        amountToUse++;
                                                    }
                                                    sidedInventoryTile.setInventorySlotContents(insertionSlots[j],
                                                            new ItemStack(manager.itemList.get(i), sidedInventoryTile.getStackInSlot(insertionSlots[j]).stackSize + amountToUse));
                                                    manager.useItem(new ItemStack(manager.itemList.get(i), amountToUse));
                                                } else {
                                                    int stackSize = sidedInventoryTile.getStackInSlot(insertionSlots[j]).stackSize;
                                                    sidedInventoryTile.setInventorySlotContents(insertionSlots[j], new ItemStack(manager.itemList.get(i), stackSize
                                                            + manager.itemAmounts.get(i)));
                                                    manager.useItem(new ItemStack(manager.itemList.get(i), stackSize + manager.itemAmounts.get(i)));
                                                }
                                            } else {
                                                sidedInventoryTile.setInventorySlotContents(insertionSlots[j], new ItemStack(manager.itemList.get(i), manager.itemAmounts.get(i)
                                                        .intValue()));
                                                manager.useItem(new ItemStack(manager.itemList.get(i), manager.itemAmounts.get(i).intValue()));
                                            }
                                        }
                                    }
                                    j++;
                                }
                                i++;
                            }
                        } else {
                            int i = 0;
                            while (i <= inventory.length - 1) {
                                if (inventory[i] != null) {
                                    int j = 0;
                                    while (j <= insertionSlots.length - 1) {
                                        if (manager.hasItem(inventory[i].getItem()) == -1) {
                                            manager.addItem(new ItemStack(inventory[i].getItem(), 0));
                                        }
                                        int itemIndex = manager.hasItem(inventory[i].getItem());

                                        if (manager.itemAmounts.get(itemIndex).intValue() < 1 == false) {
                                            if (sidedInventoryTile.isItemValidForSlot(insertionSlots[j],
                                                    new ItemStack(manager.itemList.get(itemIndex), manager.itemAmounts.get(itemIndex)))) {
                                                if (sidedInventoryTile.getStackInSlot(insertionSlots[j]) != null) {
                                                    if (sidedInventoryTile.getStackInSlot(insertionSlots[j]).stackSize + manager.itemAmounts.get(itemIndex) > 64) {
                                                        int amountToUse = 0;
                                                        while (amountToUse + sidedInventoryTile.getStackInSlot(insertionSlots[j]).stackSize < 64) {
                                                            amountToUse++;
                                                        }
                                                        sidedInventoryTile.setInventorySlotContents(insertionSlots[j], new ItemStack(manager.itemList.get(itemIndex),
                                                                sidedInventoryTile.getStackInSlot(insertionSlots[j]).stackSize + amountToUse));
                                                        manager.useItem(new ItemStack(manager.itemList.get(itemIndex), amountToUse));
                                                    } else {
                                                        int stackSize = sidedInventoryTile.getStackInSlot(insertionSlots[j]).stackSize;
                                                        sidedInventoryTile.setInventorySlotContents(insertionSlots[j], new ItemStack(manager.itemList.get(itemIndex), stackSize
                                                                + manager.itemAmounts.get(itemIndex)));
                                                        manager.useItem(new ItemStack(manager.itemList.get(itemIndex), stackSize + manager.itemAmounts.get(itemIndex)));
                                                    }
                                                } else {
                                                    sidedInventoryTile.setInventorySlotContents(insertionSlots[j], new ItemStack(manager.itemList.get(itemIndex),
                                                            manager.itemAmounts.get(itemIndex).intValue()));
                                                    manager.useItem(new ItemStack(manager.itemList.get(itemIndex), manager.itemAmounts.get(itemIndex).intValue()));
                                                }
                                            }
                                        }
                                        j++;
                                    }
                                }
                                i++;
                            }
                        }
                    } else {
                        IInventory inventoryTile = (IInventory) tile;
                        if (!useFilters) {
                            int i = 0;
                            while (i <= manager.itemList.size() - 1) {
                                int j = 0;
                                while (j <= inventoryTile.getSizeInventory() - 1) {
                                    if (manager.itemAmounts.get(i).intValue() < 1 == false) {
                                        if (inventoryTile.isItemValidForSlot(j, new ItemStack(manager.itemList.get(i)))) {
                                            if (inventoryTile.getStackInSlot(j) != null) {
                                                if (inventoryTile.getStackInSlot(j).stackSize + manager.itemAmounts.get(i) > 64) {
                                                    int amountToUse = 0;
                                                    while (amountToUse + inventoryTile.getStackInSlot(j).stackSize < 64) {
                                                        amountToUse++;
                                                    }
                                                    inventoryTile.setInventorySlotContents(j, new ItemStack(manager.itemList.get(i), inventoryTile.getStackInSlot(j).stackSize
                                                            + amountToUse));
                                                    manager.useItem(new ItemStack(manager.itemList.get(i), amountToUse));
                                                } else {
                                                    int stackSize = inventoryTile.getStackInSlot(j).stackSize;
                                                    inventoryTile.setInventorySlotContents(j, new ItemStack(manager.itemList.get(i), stackSize + manager.itemAmounts.get(i)));
                                                    manager.useItem(new ItemStack(manager.itemList.get(i), stackSize + manager.itemAmounts.get(i)));
                                                }
                                            } else {
                                                inventoryTile.setInventorySlotContents(j, new ItemStack(manager.itemList.get(i), manager.itemAmounts.get(i).intValue()));
                                                manager.useItem(new ItemStack(manager.itemList.get(i), manager.itemAmounts.get(i).intValue()));
                                            }
                                        }
                                    }
                                    j++;
                                }
                                i++;
                            }
                        } else {
                            int i = 0;
                            while (i <= inventory.length - 1) {
                                if (inventory[i] != null) {
                                    int j = 0;
                                    while (j <= inventoryTile.getSizeInventory() - 1) {
                                        if (manager.hasItem(inventory[i].getItem()) == -1) {
                                            manager.addItem(new ItemStack(inventory[i].getItem(), 0));
                                        }
                                        int itemIndex = manager.hasItem(inventory[i].getItem());
                                        if (manager.itemAmounts.get(itemIndex).intValue() < 1 == false) {
                                            if (inventoryTile.isItemValidForSlot(j, new ItemStack(manager.itemList.get(itemIndex), manager.itemAmounts.get(itemIndex)))) {
                                                if (inventoryTile.getStackInSlot(j) != null) {
                                                    if (inventoryTile.getStackInSlot(j).stackSize + manager.itemAmounts.get(itemIndex) > 64) {
                                                        int amountToUse = 0;
                                                        while (amountToUse + inventoryTile.getStackInSlot(j).stackSize < 64) {
                                                            amountToUse++;
                                                        }
                                                        inventoryTile.setInventorySlotContents(j, new ItemStack(manager.itemList.get(itemIndex),
                                                                inventoryTile.getStackInSlot(j).stackSize + amountToUse));
                                                        manager.useItem(new ItemStack(manager.itemList.get(itemIndex), amountToUse));
                                                    } else {
                                                        int stackSize = inventoryTile.getStackInSlot(j).stackSize;
                                                        inventoryTile.setInventorySlotContents(j,
                                                                new ItemStack(manager.itemList.get(itemIndex), stackSize + manager.itemAmounts.get(itemIndex)));
                                                        manager.useItem(new ItemStack(manager.itemList.get(itemIndex), stackSize + manager.itemAmounts.get(itemIndex)));
                                                    }
                                                } else {
                                                    inventoryTile.setInventorySlotContents(j, new ItemStack(manager.itemList.get(itemIndex), manager.itemAmounts.get(itemIndex)
                                                            .intValue()));
                                                    manager.useItem(new ItemStack(manager.itemList.get(itemIndex), manager.itemAmounts.get(itemIndex).intValue()));
                                                }
                                            }
                                        }
                                        j++;
                                    }
                                }
                                i++;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void pullItemsFromInventories(TileEntityItemNetworkManager manager, TileBuffer[] tileBuffer, ItemStack[] inventory, boolean useFilters) {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            if (manager != null) {
                TileEntity tile = tileBuffer[side.ordinal()].getTile();
                if (tile != null && tile instanceof IInventory) {
                    if (tile instanceof ISidedInventory) {
                        ISidedInventory sidedInventoryTile = (ISidedInventory) tile;
                        int[] extractionSlots = sidedInventoryTile.getAccessibleSlotsFromSide(side.getOpposite().ordinal());

                        if (!useFilters) {
                            int i = 0;
                            while (i <= manager.itemList.size() - 1) {
                                int j = 0;
                                while (j <= extractionSlots.length - 1) {
                                    if (sidedInventoryTile.getStackInSlot(extractionSlots[j]) != null) {
                                        if (manager.hasItem(sidedInventoryTile.getStackInSlot(extractionSlots[j]).getItem()) == -1) {
                                            manager.addItem(new ItemStack(sidedInventoryTile.getStackInSlot(extractionSlots[j]).getItem(), 0));
                                        }
                                        int itemIndex = manager.hasItem(sidedInventoryTile.getStackInSlot(extractionSlots[j]).getItem());

                                        if (sidedInventoryTile.canExtractItem(extractionSlots[j], new ItemStack(manager.itemList.get(itemIndex), 1), side.getOpposite().ordinal())) {
                                            ItemStack itemToAdd = sidedInventoryTile.getStackInSlot(extractionSlots[j]);
                                            manager.addItem(itemToAdd);
                                            sidedInventoryTile.setInventorySlotContents(extractionSlots[j], null);
                                        }
                                    }
                                    j++;
                                }
                                i++;
                            }
                        } else {
                            int i = 0;
                            while (i <= 13) {
                                if (inventory[i] != null) {
                                    int j = 0;
                                    while (j <= extractionSlots.length - 1) {
                                        if (sidedInventoryTile.getStackInSlot(extractionSlots[j]) != null) {
                                            if (manager.hasItem(inventory[i].getItem()) == -1) {
                                                manager.addItem(new ItemStack(inventory[i].getItem(), 0));
                                            }
                                            int itemIndex = manager.hasItem(inventory[i].getItem());
                                            if (sidedInventoryTile.canExtractItem(extractionSlots[j], new ItemStack(manager.itemList.get(itemIndex), 1), side.getOpposite()
                                                    .ordinal())) {
                                                ItemStack itemToAdd = sidedInventoryTile.getStackInSlot(extractionSlots[j]);
                                                manager.addItem(itemToAdd);
                                                sidedInventoryTile.setInventorySlotContents(extractionSlots[j], null);
                                            }
                                        }
                                        j++;
                                    }
                                    i++;
                                }
                            }
                        }
                    } else {
                        IInventory inventoryTile = (IInventory) tile;
                        if (!useFilters) {
                            int i = 0;
                            while (i <= manager.itemList.size() - 1) {
                                int j = 0;
                                while (j <= inventory.length - 1) {
                                    if (inventoryTile.getStackInSlot(j) != null) {
                                        if (manager.hasItem(inventoryTile.getStackInSlot(j).getItem()) == -1) {
                                            manager.addItem(new ItemStack(inventoryTile.getStackInSlot(j).getItem(), 0));
                                        }

                                        int itemIndex = manager.hasItem(inventoryTile.getStackInSlot(j).getItem());

                                        ItemStack itemToAdd = inventoryTile.getStackInSlot(j);
                                        manager.addItem(itemToAdd);
                                        inventoryTile.setInventorySlotContents(j, null);
                                    }
                                    j++;
                                }
                                i++;
                            }
                        } else {
                            int i = 0;
                            while (i <= 13) {
                                if (inventory[i] != null) {
                                    int j = 0;
                                    while (j <= inventory.length - 1) {
                                        if (manager.hasItem(inventory[i].getItem()) == -1) {
                                            manager.addItem(new ItemStack(inventory[i].getItem(), 0));
                                        }

                                        int itemIndex = manager.hasItem(inventory[i].getItem());

                                        ItemStack itemToAdd = inventoryTile.getStackInSlot(j);
                                        manager.addItem(itemToAdd);
                                        inventoryTile.setInventorySlotContents(j, null);

                                        j++;
                                    }
                                }
                                i++;
                            }
                        }
                    }
                }
            }
        }
    }
}
