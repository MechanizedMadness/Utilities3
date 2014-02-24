package darkevilmac.utilities.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import darkevilmac.utilities.fluid.ModFluids;
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
        if (tileBuffer == null)
            tileBuffer = TileBuffer.makeBuffer(TileEntityUtilities.world, xCoord, yCoord, zCoord, false);

        if (hasBrain()) {
            for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
                if (getMeta() == 0) {
                    TileEntity tile = tileBuffer[side.ordinal()].getTile();
                    if (tile != null && tile instanceof IFluidHandler) {
                        IFluidHandler fluidTile = ((IFluidHandler) tile);
                        if (fluidTile.canFill(side.getOpposite(), ModFluids.fluidEnergy)) {
                            fluidTile.fill(side.getOpposite(), getBrain().internalFluidBuffer, true);
                        }
                    }
                }
                if (getMeta() == 1) {
                    TileEntity tile = tileBuffer[side.ordinal()].getTile();
                    if (tile != null && tile instanceof IFluidHandler) {
                        IFluidHandler fluidTile = ((IFluidHandler) tile);

                    }
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
        if (pipeBrain != null)
            TileEntityEnergyPipeBrain.reformPipeNetwork();
    }

    @Override
    public void onNeighborBlockChange() {
        super.onNeighborBlockChange();
        if (pipeBrain != null)
            TileEntityEnergyPipeBrain.reformPipeNetwork();
    }

    public static void setBrain(TileEntityEnergyPipeBrain brain) {
        pipeBrain = brain;
    }

    public static TileEntityEnergyPipeBrain getBrain() {
        return pipeBrain;
    }

    public static void removeBrain() {
        pipeBrain = null;
    }

    public static void setBrainDir(ForgeDirection dir) {
        brainDir = dir;
    }

    public static ForgeDirection getBrainDir() {
        return brainDir;
    }

    public static void removeBrainDir() {
        brainDir = null;
    }

    public static void checkRequests() {

    }

    public static ForgeDirection getDir(ForgeDirection dir) {
        ForgeDirection replacementDir = ForgeDirection.UNKNOWN;
        if (brainDir == ForgeDirection.UP || brainDir == ForgeDirection.DOWN) {
            replacementDir = ForgeDirection.NORTH;
        }
        if (brainDir == ForgeDirection.NORTH || brainDir == ForgeDirection.SOUTH) {
            replacementDir = ForgeDirection.UP;
        }
        if (brainDir == ForgeDirection.WEST || brainDir == ForgeDirection.EAST) {
            replacementDir = ForgeDirection.UP;
        }
        if (dir != null) {
            if (dir == brainDir || dir.getOpposite() == brainDir) {
                return replacementDir;
            } else {
                return dir;
            }
        } else {
            return ForgeDirection.UNKNOWN;
        }
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (hasBrain()) {
            if (doFill) {
                getBrain();
                int oldAmount = TileEntityEnergyPipeBrain.internalFluidBuffer.amount;
                getBrain();
                TileEntityEnergyPipeBrain.internalFluidBuffer = new FluidStack(ModFluids.fluidEnergy, oldAmount + resource.amount);
            }
            return resource.amount;
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
        return getMeta() == 1;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return getMeta() == 0;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return null;
    }

}
