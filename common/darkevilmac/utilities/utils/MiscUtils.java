package darkevilmac.utilities.utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import darkevilmac.utilities.shadows.TileBuffer;
import darkevilmac.utilities.tile.TileEntityItemNetworkBridge;
import darkevilmac.utilities.tile.TileEntityItemNetworkManager;

public class MiscUtils {

    public static void pushItemToConsumers(TileEntityItemNetworkManager manager, TileBuffer[] tileBuffer, ItemStack[] bridgeInv, boolean useFilters) {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity tile = tileBuffer[side.ordinal()].getTile();
            if (manager != null) {
                if (tile != null && tile instanceof IInventory) {
                    if (tile instanceof TileEntityItemNetworkBridge) {
                        if (!useFilters) {
                            TileEntityItemNetworkBridge bridgeDestination = (TileEntityItemNetworkBridge) tile;
                            if (bridgeDestination.manager != null) {
                                int i = 0;
                                while (i <= manager.itemList.size() - 1) {
                                    if (manager.itemAmounts.get(i) > 0) {
                                        bridgeDestination.manager.addItem(new ItemStack(manager.itemList.get(i), 1));
                                        manager.useItem(new ItemStack(manager.itemList.get(i), 1));
                                        break;
                                    }
                                    i++;
                                }
                            }
                        } else {
                            TileEntityItemNetworkBridge bridgeDestination = (TileEntityItemNetworkBridge) tile;
                            if (bridgeDestination.manager != null) {
                                int i = 0;
                                while (i <= bridgeInv.length - 1) {
                                    if (bridgeInv[i] != null) {
                                        if (manager.hasItem(bridgeInv[i].getItem()) == -1) {
                                            manager.addItem(new ItemStack(bridgeInv[i].getItem(), 0));
                                        }
                                        int itemIndex = manager.hasItem(bridgeInv[i].getItem());

                                        if (manager.itemAmounts.get(itemIndex) > 0) {
                                            bridgeDestination.manager.addItem(new ItemStack(manager.itemList.get(itemIndex), 1));
                                            manager.useItem(new ItemStack(manager.itemList.get(itemIndex), 1));
                                            break;
                                        }
                                    }
                                    i++;
                                }
                            }
                        }
                    } else {
                        if (!useFilters) {
                            int i = 0;
                            while (i <= manager.itemList.size() - 1) {
                                if (manager.itemAmounts.get(i).intValue() > 0) {
                                    ItemStack mergeStack = InvUtils.mergeItemStack((IInventory) tile, new ItemStack(manager.itemList.get(i), 1), side.getOpposite().ordinal());
                                    if (mergeStack == null) {
                                        manager.useItem(new ItemStack(manager.itemList.get(i), 1));
                                    }
                                }
                                i++;
                            }
                        } else {
                            int i = 0;
                            while (i <= bridgeInv.length - 1) {
                                if (bridgeInv[i] != null) {
                                    if (manager.hasItem(bridgeInv[i].getItem()) == -1) {
                                        manager.addItem(new ItemStack(bridgeInv[i].getItem(), 0));
                                    }
                                    int itemIndex = manager.hasItem(bridgeInv[i].getItem());

                                    if (manager.itemAmounts.get(itemIndex).intValue() > 0) {
                                        ItemStack mergeStack = InvUtils.mergeItemStack((IInventory) tile, new ItemStack(manager.itemList.get(itemIndex), 1), side.getOpposite()
                                                .ordinal());
                                        if (mergeStack == null) {
                                            manager.useItem(new ItemStack(manager.itemList.get(itemIndex), 1));
                                        }
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

    public static void pullItemFromProducers(TileEntityItemNetworkManager manager, TileBuffer[] tileBuffer, ItemStack[] bridgeInv, boolean useFilters) {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity tile = tileBuffer[side.ordinal()].getTile();
            if (manager != null) {
                if (tile != null && tile instanceof IInventory) {
                    if (tile instanceof TileEntityItemNetworkBridge) {
                        if (!useFilters) {

                        } else {

                        }
                    } else {
                        if (!useFilters) {
                            ItemStack removeStack = InvUtils.removeItems((IInventory) tile, side.getOpposite().ordinal(), null, 1);
                            if (removeStack != null) {
                                manager.addItem(removeStack);
                            }
                        } else {
                            int i = 0;
                            while (i <= bridgeInv.length - 1) {
                                if (bridgeInv[i] != null) {
                                    if (manager.hasItem(bridgeInv[i].getItem()) == -1) {
                                        manager.addItem(new ItemStack(bridgeInv[i].getItem(), 0));
                                    }

                                    int itemIndex = manager.hasItem(bridgeInv[i].getItem());

                                    ItemStack removeStack = InvUtils.removeItems((IInventory) tile, side.getOpposite().ordinal(),
                                            new ItemStack(manager.itemList.get(itemIndex), 1), 1);

                                    if (removeStack != null) {
                                        manager.addItem(removeStack);
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
