package darkevilmac.utilities.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import darkevilmac.utilities.Utilities;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.item.ModItems;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.network.packet.PacketEnergySolidifierUpdateClient;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityEnergySolidifier extends TileEntityUtilities implements ISidedInventory, IFluidHandler {

    private ItemStack[] inventory;
    public FluidTank tank;
    private int conversionTick;

    private int clientDisplayEnergy;

    public TileEntityEnergySolidifier() {
        inventory = new ItemStack[1];
        tank = new FluidTank(ModFluids.fluidEnergy, 0, 1003 * 10);
        conversionTick = 0;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("inventory", nbttaglist);
        nbt.setInteger("conversionTick", conversionTick);
        tank.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        NBTTagList nbttaglist = nbt.getTagList("inventory", 10);
        this.inventory = new ItemStack[1];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.inventory.length) {
                this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        conversionTick = nbt.getInteger("conversionTick");
        tank.readFromNBT(nbt);
    }

    @Override
    public void validate() {
        super.validate();

        if (inventory.length < 1) {
            inventory = new ItemStack[1];
        }

        if (tank == null) {
            tank = new FluidTank(ModFluids.fluidEnergy, 0, 1003 * 10);
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!world.isRemote) {
            if (conversionTick >= 20 * 3) {
                if (tank.getFluidAmount() >= FluidContainerRegistry.BUCKET_VOLUME) {
                    tank.setFluid(new FluidStack(ModFluids.fluidEnergy, tank.getFluidAmount() - FluidContainerRegistry.BUCKET_VOLUME));
                    if (inventory[0] == null) {
                        inventory[0] = new ItemStack(ModItems.solidEnergy, 1);
                    } else if (inventory[0].stackSize < 64) {
                        inventory[0] = new ItemStack(inventory[0].getItem(), inventory[0].stackSize + 1);
                    }
                }
                conversionTick = 0;
            }
            conversionTick++;
            Utilities.packetPipeline.sendToAll(new PacketEnergySolidifierUpdateClient(world.provider.dimensionId, xCoord, yCoord, zCoord, tank.getFluidAmount()));
        }
    }

    public void setClientDisplayEnergy(int set) {
        clientDisplayEnergy = set;
    }

    public int getClientDisplayEnergy() {
        return clientDisplayEnergy;
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
        return 64;
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
        return false;
    }

    /**
     * End IInventory implementation
     * 
     * @see IInventory
     */

    /**
     * Begin ISidedInventory implementation
     * 
     * @see ISidedInventory
     */

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        return null;
    }

    @Override
    public boolean canInsertItem(int var1, ItemStack var2, int var3) {
        return false;
    }

    @Override
    public boolean canExtractItem(int var1, ItemStack var2, int var3) {
        return true;
    }

    /**
     * End ISidedInventory implementation
     * 
     * @see ISidedInventory
     */

    /**
     * Begin IFluidHandler implementation
     * 
     * @see IFluidHandler
     */

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        if (fluid.equals(ModFluids.fluidEnergy)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        if (fluid.equals(ModFluids.fluidEnergy)) {
            return true;
        }

        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { tank.getInfo() };
    }

    /**
     * End IFluidHandler implementation
     * 
     * @see IFluidHandler
     */

}