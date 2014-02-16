package darkevilmac.utilities.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import darkevilmac.utilities.shadows.TileBuffer;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityEnergyPipe extends TileEntityUtilities implements IFluidHandler {

    private static TileEntityEnergyPipeBrain pipeBrain;
    private static ForgeDirection brainDir;
    private TileBuffer[] tileBuffer = null;

    public TileEntityEnergyPipe() {

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
    }

    @Override
    public void validate() {
        super.validate();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (hasBrain()) {
            for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
                TileEntity tile = tileBuffer[side.ordinal()].getTile();
                if (tile != null) {

                }
            }
        }
    }

    public boolean hasBrain() {
        return getTile() != null;
    }

    protected TileEntity getTile(ForgeDirection side) {
        if (tileBuffer == null)
            tileBuffer = TileBuffer.makeBuffer(TileEntityUtilities.world, xCoord, yCoord, zCoord, false);
        return tileBuffer[side.ordinal()].getTile();
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
        if (TileEntityEnergyPipe.pipeBrain != null)
            TileEntityEnergyPipeBrain.reformPipeNetwork(TileEntityEnergyPipe.pipeBrain.worldObj, TileEntityEnergyPipe.pipeBrain.xCoord,
                    TileEntityEnergyPipe.pipeBrain.yCoord, TileEntityEnergyPipe.pipeBrain.zCoord);
    }

    @Override
    public void onNeighborBlockChange() {
        super.onNeighborBlockChange();
        if (TileEntityEnergyPipe.pipeBrain != null)
            TileEntityEnergyPipeBrain.reformPipeNetwork(TileEntityEnergyPipe.pipeBrain.worldObj, TileEntityEnergyPipe.pipeBrain.xCoord,
                    TileEntityEnergyPipe.pipeBrain.yCoord, TileEntityEnergyPipe.pipeBrain.zCoord);
    }

    public static void setBrain(TileEntityEnergyPipeBrain brain) {
        TileEntityEnergyPipe.pipeBrain = brain;
    }

    public static TileEntityEnergyPipeBrain getBrain() {
        return TileEntityEnergyPipe.pipeBrain;
    }

    public static void removeBrain() {
        TileEntityEnergyPipe.pipeBrain = null;
    }

    public static void setBrainDir(ForgeDirection dir) {
        TileEntityEnergyPipe.brainDir = dir;
    }

    public static ForgeDirection getBrainDir() {
        return TileEntityEnergyPipe.brainDir;
    }

    public static void removeBrainDir() {
        TileEntityEnergyPipe.brainDir = null;
    }

    public static void checkRequests() {

    }

    public static ForgeDirection getDir(ForgeDirection dir) {
        ForgeDirection replacementDir = ForgeDirection.UNKNOWN;
        if (TileEntityEnergyPipe.brainDir == ForgeDirection.UP || TileEntityEnergyPipe.brainDir == ForgeDirection.DOWN) {
            replacementDir = ForgeDirection.NORTH;
        }
        if (TileEntityEnergyPipe.brainDir == ForgeDirection.NORTH || TileEntityEnergyPipe.brainDir == ForgeDirection.SOUTH) {
            replacementDir = ForgeDirection.UP;
        }
        if (TileEntityEnergyPipe.brainDir == ForgeDirection.WEST || TileEntityEnergyPipe.brainDir == ForgeDirection.EAST) {
            replacementDir = ForgeDirection.UP;
        }

        if (dir == TileEntityEnergyPipe.brainDir || dir == TileEntityEnergyPipe.brainDir.getOpposite()) {
            return replacementDir;
        } else {
            return dir;
        }
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (getBrain() != null) {
            if (doFill) {
                int i = 0;
                getBrain();
                while (TileEntityEnergyPipeBrain.fluidList.size() <= i) {
                    getBrain();
                    if (((FluidStack) TileEntityEnergyPipeBrain.fluidList.get(i)).getFluid().equals(resource.getFluid())) {

                        return resource.amount;
                    }
                    i++;
                }
                getBrain();
                TileEntityEnergyPipeBrain.fluidList.add(new FluidStack(resource.getFluid(), resource.amount));
                return resource.amount;
            }
            if (!doFill) {
                return resource.amount;
            }
        }
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
        if (getMeta() == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        if (getMeta() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return null;
    }

}
