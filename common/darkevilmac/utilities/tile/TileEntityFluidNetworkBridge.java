package darkevilmac.utilities.tile;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityFluidNetworkBridge extends TileEntityUtilities implements IFluidHandler {

    public TileEntityFluidNetworkManager manager;
    public int managerXCoord;
    public int managerYCoord;
    public int managerZCoord;
    public boolean hasManager;

    public int loops = 0;

    public TileEntityFluidNetworkBridge() {
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        if (manager != null) {
            nbt.setInteger("managerXCoord", manager.xCoord);
            nbt.setInteger("managerYCoord", manager.yCoord);
            nbt.setInteger("managerZCoord", manager.zCoord);
            nbt.setBoolean("hasManager", true);
        }
        if (manager == null && nbt.getBoolean("hasManager")) {
            nbt.removeTag("managerXCoord");
            nbt.removeTag("managerYCoord");
            nbt.removeTag("managerZCoord");
            nbt.removeTag("hasManager");
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        managerXCoord = nbt.getInteger("managerXCoord");
        managerYCoord = nbt.getInteger("managerYCoord");
        managerZCoord = nbt.getInteger("managerZCoord");
        hasManager = nbt.getBoolean("hasManager");

        if (hasManager) {
            manager = (TileEntityFluidNetworkManager) world.getTileEntity(nbt.getInteger("managerXCoord"), nbt.getInteger("managerYCoord"), nbt.getInteger("managerZCoord"));
        }
    }

    @Override
    public void validate() {
        super.validate();

    }

    @Override
    public void updateEntity() {
        super.updateEntity();
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
        super.onBlockDestroyedByPlayer(world, x, y, z, par5);

    }

    @Override
    public void onNeighborBlockChange(Block blockType) {
        super.onNeighborBlockChange(blockType);

    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return getMeta() == 0;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return getMeta() == 1;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return null;
    }

}