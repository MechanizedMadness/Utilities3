package darkevilmac.utilities.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.shadows.TileBuffer;
import darkevilmac.utilities.tile.base.TileEntityUtilities;
import darkevilmac.utilities.utils.StackUtils;

public class TileEntityItemNetworkBridge extends TileEntityUtilities implements IInventory {

    public TileEntityItemNetworkManager manager;

    private ItemStack[] inventory;

    protected TileBuffer[] tileBuffer = null;
    public int managerXCoord;
    public int managerYCoord;
    public int managerZCoord;
    public boolean readNBT;
    public boolean hasManager;

    public TileEntityItemNetworkBridge() {
        inventory = new ItemStack[14];
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        if (manager != null) {
            managerXCoord = manager.xCoord;
            managerYCoord = manager.yCoord;
            managerZCoord = manager.zCoord;

            nbt.setInteger("managerXCoord", managerXCoord);
            nbt.setInteger("managerYCoord", managerYCoord);
            nbt.setInteger("managerZCoord", managerZCoord);
            nbt.setBoolean("readNBT", true);
        } else {
            nbt.setBoolean("readNBT", false);
        }

        NBTTagList list = new NBTTagList();

        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack itemstack = getStackInSlot(i);

            if (itemstack != null) {
                NBTTagCompound item = new NBTTagCompound();

                item.setByte("slotItemNetworkBridge", (byte) i);
                itemstack.writeToNBT(item);
                list.appendTag(item);
            }
        }

        nbt.setTag("itemsItemNetworkBridge", list);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        if (nbt.getBoolean("readNBT")) {
            managerXCoord = nbt.getInteger("managerXCoord");
            managerYCoord = nbt.getInteger("managerYCoord");
            managerZCoord = nbt.getInteger("managerZCoord");
        } else {
            managerXCoord = 0;
            managerYCoord = 0;
            managerYCoord = 0;
        }
        readNBT = nbt.getBoolean("readNBT");

        NBTTagList list = nbt.getTagList("itemsItemNetworkBridge", 11);

        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound item = (NBTTagCompound) list.getCompoundTagAt(i);
            int slot = item.getByte("slotItemNetworkBridge");

            if (slot >= 0 && slot < getSizeInventory()) {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }

    }

    @Override
    public void validate() {
        super.validate();

        if (inventory.length < 14) {
            inventory = new ItemStack[14];
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!world.isRemote) {

            if (tileBuffer == null)
                tileBuffer = TileBuffer.makeBuffer(world, xCoord, yCoord, zCoord, false);

            if (manager == null) {
                if (readNBT) {
                    manager = (TileEntityItemNetworkManager) world.getTileEntity(managerXCoord, managerYCoord, managerZCoord);
                    readNBT = false;
                }
            }

            if (manager != null) {
                checkManager();

                if (getMeta() == 0) {
                    StackUtils.pushItemsToInventories(manager, tileBuffer, inventory, useFilters());
                } else {
                    StackUtils.pullItemsFromInventories(manager, tileBuffer, inventory, useFilters());
                }
            }

        }
    }

    protected TileEntity getTile(ForgeDirection side) {
        if (tileBuffer == null)
            tileBuffer = TileBuffer.makeBuffer(world, xCoord, yCoord, zCoord, false);
        return tileBuffer[side.ordinal()].getTile();
    }

    public void setManager(TileEntityItemNetworkManager managerToSet) {
        manager = managerToSet;
        managerXCoord = managerToSet.xCoord;
        managerYCoord = managerToSet.yCoord;
        managerZCoord = managerToSet.zCoord;
        hasManager = true;
    }

    public void clearManager() {
        hasManager = false;
        manager = null;
    }

    public void checkManager() {
        if (world.getTileEntity(manager.xCoord, manager.yCoord, manager.zCoord) == null
                || (world.getTileEntity(managerXCoord, managerYCoord, managerZCoord) != null && world.getTileEntity(manager.xCoord, manager.yCoord, manager.zCoord) instanceof TileEntityItemNetworkManager == false)) {
            clearManager();
        }
    }

    public boolean useFilters() {
        int i = 0;
        while (i <= getSizeInventory() - 1) {
            if (getStackInSlot(i) != null) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Begin IInventory implementation
     * 
     * @see IInventory
     */

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);

        if (stack != null) {
            if (stack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amount);
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        setInventorySlotContents(slot, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;

        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return Strings.ITEMNETWORK_BRIDGE_INGAMENAME;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return true;
    }

    /**
     * End IInventory implementation
     * 
     * @see IInventory
     */

}
