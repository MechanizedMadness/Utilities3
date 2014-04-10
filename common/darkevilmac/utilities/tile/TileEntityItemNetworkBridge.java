package darkevilmac.utilities.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import darkevilmac.utilities.shadows.TileBuffer;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityItemNetworkBridge extends TileEntityUtilities implements IInventory{

    public TileEntityItemNetworkManager manager;

    protected TileBuffer[] tileBuffer = null;
    public int managerXCoord;
    public int managerYCoord;
    public int managerZCoord;
    public boolean readNBT;
    public boolean hasManager;

    public TileEntityItemNetworkBridge() {

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
    }

    @Override
    public void validate() {
        super.validate();

    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!world.isRemote) {

            if (manager == null) {
                if (readNBT) {
                    manager = (TileEntityItemNetworkManager) world.getTileEntity(managerXCoord, managerYCoord, managerZCoord);
                    readNBT = false;
                }
            }

            if (manager != null) {
                checkManager();
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
    
    /**
     * Begin IInventory implementation
     * 
     * @see IInventory
     */

    @Override
    public int getSizeInventory() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getInventoryName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void openInventory() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void closeInventory() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * End IInventory implementation
     * 
     * @see IInventory
     */

}
