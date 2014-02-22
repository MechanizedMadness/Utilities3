package darkevilmac.utilities.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityEnergyPipeBrain extends TileEntityUtilities {

    public static List pipeList = new ArrayList<TileEntityEnergyPipe>();
    public static FluidStack internalFluidBuffer = new FluidStack(ModFluids.fluidEnergy, 0);
    public static int checked = 0;

    public static int[] requestListAmount;
    public static String[] requestListFluidName;

    public TileEntityEnergyPipeBrain() {
    }

    @Override
    public void validate() {
        super.validate();

        reformPipeNetwork();
        if (internalFluidBuffer == null)
            internalFluidBuffer = new FluidStack(ModFluids.fluidEnergy, 0);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!world.isRemote) {
            if (!pipeList.isEmpty() && checked >= 5) {
                int i = 0;
                while (i <= pipeList.size()) {
                    System.out.println("Brain has " + ((TileEntityEnergyPipe) pipeList.get(i)).xCoord + " " + ((TileEntityEnergyPipe) pipeList.get(i)).yCoord
                            + " " + ((TileEntityEnergyPipe) pipeList.get(i)).zCoord + " in it's network");
                    i++;
                }
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setInteger("BufferAmount", internalFluidBuffer.amount);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        internalFluidBuffer = new FluidStack(ModFluids.fluidEnergy, nbt.getInteger("BufferAmount"));
    }

    @Override
    public void onNeighborBlockChange() {
        super.onNeighborBlockChange();
        reformPipeNetwork();
    }

    public static void fillRequests() {

    }

    public static void reformPipeNetwork() {
        int i = 0;
        World world = DimensionManager.getWorld(worldid);
        int x = xCoordinate;
        int y = yCoordinate;
        int z = zCoordinate;
        while (i <= pipeList.size()) {
            TileEntityEnergyPipe.removeBrain();
            TileEntityEnergyPipe.removeBrainDir();
            i++;
        }
        i = 0;

        pipeList.clear();
        int checkPipeX = x;
        int checkPipeY = y;
        int checkPipeZ = z;
        int iX = 1;
        int iY = 1;
        int iZ = 1;

        // Form PipeNetwork for WEST and EAST
        while (world.getBlockTileEntity(checkPipeX + iX, checkPipeY, checkPipeZ) != null
                && world.getBlockTileEntity(checkPipeX + iX, checkPipeY, checkPipeZ) instanceof TileEntityEnergyPipe) {
            pipeList.add(world.getBlockTileEntity(checkPipeX + iX, checkPipeY, checkPipeZ));
            TileEntityEnergyPipe pipeToSet = ((TileEntityEnergyPipe) world.getBlockTileEntity(checkPipeX + iX, checkPipeY, checkPipeZ));
            pipeToSet.setBrain(((TileEntityEnergyPipeBrain) world.getBlockTileEntity(x, y, z)));
            iX++;
        }
        iX = 1;
        while (world.getBlockTileEntity(checkPipeX - iX, checkPipeY, checkPipeZ) != null
                && world.getBlockTileEntity(checkPipeX - iX, checkPipeY, checkPipeZ) instanceof TileEntityEnergyPipe) {
            pipeList.add(world.getBlockTileEntity(checkPipeX - iX, checkPipeY, checkPipeZ));
            TileEntityEnergyPipe pipeToSet = ((TileEntityEnergyPipe) world.getBlockTileEntity(checkPipeX - iX, checkPipeY, checkPipeZ));
            pipeToSet.setBrain(((TileEntityEnergyPipeBrain) world.getBlockTileEntity(x, y, z)));
            iX++;
        }
        iX = 1;
        // Formed WEST and EAST

        // Form PipeNetwork for UP and DOWN
        while (world.getBlockTileEntity(checkPipeX, checkPipeY + iY, checkPipeZ) != null
                && world.getBlockTileEntity(checkPipeX, checkPipeY + iY, checkPipeZ) instanceof TileEntityEnergyPipe) {
            pipeList.add(world.getBlockTileEntity(checkPipeX, checkPipeY + iY, checkPipeZ));
            TileEntityEnergyPipe pipeToSet = ((TileEntityEnergyPipe) world.getBlockTileEntity(checkPipeX, checkPipeY + iY, checkPipeZ));
            pipeToSet.setBrain(((TileEntityEnergyPipeBrain) world.getBlockTileEntity(x, y, z)));
            iY++;
        }
        iY = 1;
        while (world.getBlockTileEntity(checkPipeX, checkPipeY - iY, checkPipeZ) != null
                && world.getBlockTileEntity(checkPipeX, checkPipeY - iY, checkPipeZ) instanceof TileEntityEnergyPipe) {
            pipeList.add(world.getBlockTileEntity(checkPipeX, checkPipeY - iY, checkPipeZ));
            TileEntityEnergyPipe pipeToSet = ((TileEntityEnergyPipe) world.getBlockTileEntity(checkPipeX, checkPipeY - iY, checkPipeZ));
            pipeToSet.setBrain(((TileEntityEnergyPipeBrain) world.getBlockTileEntity(x, y, z)));
            iY++;
        }
        iY = 1;
        // Formed UP and DOWN

        // Form PipeNetwork for NORTH and SOUTH
        while (world.getBlockTileEntity(checkPipeX, checkPipeY, checkPipeZ + iZ) != null
                && world.getBlockTileEntity(checkPipeX, checkPipeY, checkPipeZ + iZ) instanceof TileEntityEnergyPipe) {
            pipeList.add(world.getBlockTileEntity(checkPipeX, checkPipeY, checkPipeZ + iZ));
            TileEntityEnergyPipe pipeToSet = ((TileEntityEnergyPipe) world.getBlockTileEntity(checkPipeX, checkPipeY, checkPipeZ + iZ));
            pipeToSet.setBrain(((TileEntityEnergyPipeBrain) world.getBlockTileEntity(x, y, z)));
            iZ++;
        }
        iZ = 1;
        while (world.getBlockTileEntity(checkPipeX, checkPipeY, checkPipeZ - iZ) != null
                && world.getBlockTileEntity(checkPipeX, checkPipeY, checkPipeZ - iZ) instanceof TileEntityEnergyPipe) {
            pipeList.add(world.getBlockTileEntity(checkPipeX, checkPipeY, checkPipeZ - iZ));
            TileEntityEnergyPipe pipeToSet = ((TileEntityEnergyPipe) world.getBlockTileEntity(checkPipeX, checkPipeY, checkPipeZ - iZ));
            pipeToSet.setBrain(((TileEntityEnergyPipeBrain) world.getBlockTileEntity(x, y, z)));
            iZ++;
        }
        iZ = 1;
        // Formed NORTH and SOUTH

        // Begin SubNetwork Forming
        i = 0;
        if (!pipeList.isEmpty()) {
            while (!pipeList.isEmpty() && i <= pipeList.size()) {
                TileEntityEnergyPipe tile = ((TileEntityEnergyPipe) pipeList.get(i));
                ForgeDirection dirUp = TileEntityEnergyPipe.getDir(ForgeDirection.UP);
                ForgeDirection dirDown = TileEntityEnergyPipe.getDir(ForgeDirection.DOWN);
                ForgeDirection dirNorth = TileEntityEnergyPipe.getDir(ForgeDirection.NORTH);
                ForgeDirection dirSouth = TileEntityEnergyPipe.getDir(ForgeDirection.SOUTH);
                ForgeDirection dirWest = TileEntityEnergyPipe.getDir(ForgeDirection.WEST);
                ForgeDirection dirEast = TileEntityEnergyPipe.getDir(ForgeDirection.EAST);

                iX = 1;
                iY = 1;
                iZ = 1;

                if (dirUp == ForgeDirection.UP) {
                    if (tile.getTile(dirUp) != null && tile.getTile(dirUp) instanceof TileEntityEnergyPipe) {
                        while (world.getBlockTileEntity(tile.getTile(dirUp).xCoord, tile.getTile(dirUp).yCoord + iY, tile.getTile(dirUp).zCoord) != null
                                && world.getBlockTileEntity(tile.getTile(dirUp).xCoord, tile.getTile(dirUp).yCoord + iY, tile.getTile(dirUp).zCoord) instanceof TileEntityEnergyPipe) {
                            pipeList.add(world.getBlockTileEntity(tile.getTile(dirUp).xCoord, tile.getTile(dirUp).yCoord + iY, tile.getTile(dirUp).zCoord));
                            TileEntityEnergyPipe pipeToSet = ((TileEntityEnergyPipe) world.getBlockTileEntity(tile.getTile(dirUp).xCoord,
                                    tile.getTile(dirUp).yCoord + iY, tile.getTile(dirUp).zCoord));
                            pipeToSet.setBrain((TileEntityEnergyPipeBrain) world.getBlockTileEntity(x, y, z));
                            iY++;
                        }
                    }
                }
                iY = 1;

                if (dirDown == ForgeDirection.DOWN) {
                    if (tile.getTile(dirDown) != null && tile.getTile(dirDown) instanceof TileEntityEnergyPipe) {
                        while (world.getBlockTileEntity(tile.getTile(dirUp).xCoord, tile.getTile(dirUp).yCoord - iY, tile.getTile(dirUp).zCoord) != null
                                && world.getBlockTileEntity(tile.getTile(dirUp).xCoord, tile.getTile(dirUp).yCoord - iY, tile.getTile(dirUp).zCoord) instanceof TileEntityEnergyPipe) {
                            pipeList.add(world.getBlockTileEntity(tile.getTile(dirUp).xCoord, tile.getTile(dirUp).yCoord - iY, tile.getTile(dirUp).zCoord));
                            TileEntityEnergyPipe pipeToSet = ((TileEntityEnergyPipe) world.getBlockTileEntity(tile.getTile(dirUp).xCoord,
                                    tile.getTile(dirUp).yCoord - iY, tile.getTile(dirUp).zCoord));
                            pipeToSet.setBrain((TileEntityEnergyPipeBrain) world.getBlockTileEntity(x, y, z));
                            iY++;
                        }
                    }
                }
                iY = 1;

                if (dirNorth == ForgeDirection.NORTH) {
                    if (tile.getTile(dirNorth) != null && tile.getTile(dirNorth) instanceof TileEntityEnergyPipe) {
                        while (world.getBlockTileEntity(tile.getTile(dirNorth).xCoord, tile.getTile(dirNorth).yCoord, tile.getTile(dirNorth).zCoord - iZ) != null
                                && world.getBlockTileEntity(tile.getTile(dirNorth).xCoord, tile.getTile(dirNorth).yCoord, tile.getTile(dirNorth).zCoord - iZ) instanceof TileEntityEnergyPipe) {
                            pipeList.add(world.getBlockTileEntity(tile.getTile(dirNorth).xCoord, tile.getTile(dirNorth).yCoord, tile.getTile(dirNorth).zCoord
                                    - iZ));
                            TileEntityEnergyPipe pipeToSet = ((TileEntityEnergyPipe) world.getBlockTileEntity(tile.getTile(dirNorth).xCoord,
                                    tile.getTile(dirNorth).yCoord, tile.getTile(dirNorth).zCoord - iZ));
                            pipeToSet.setBrain((TileEntityEnergyPipeBrain) world.getBlockTileEntity(x, y, z));
                            iZ++;
                        }
                    }
                }
                iZ = 1;

                if (dirSouth == ForgeDirection.SOUTH) {
                    if (tile.getTile(dirSouth) != null && tile.getTile(dirSouth) instanceof TileEntityEnergyPipe) {
                        while (world.getBlockTileEntity(tile.getTile(dirSouth).xCoord, tile.getTile(dirSouth).yCoord, tile.getTile(dirSouth).zCoord + iZ) != null
                                && world.getBlockTileEntity(tile.getTile(dirSouth).xCoord, tile.getTile(dirSouth).yCoord, tile.getTile(dirSouth).zCoord + iZ) instanceof TileEntityEnergyPipe) {
                            pipeList.add(world.getBlockTileEntity(tile.getTile(dirSouth).xCoord, tile.getTile(dirSouth).yCoord, tile.getTile(dirSouth).zCoord
                                    + iZ));
                            TileEntityEnergyPipe pipeToSet = ((TileEntityEnergyPipe) world.getBlockTileEntity(tile.getTile(dirSouth).xCoord,
                                    tile.getTile(dirSouth).yCoord, tile.getTile(dirSouth).zCoord + iZ));
                            pipeToSet.setBrain((TileEntityEnergyPipeBrain) world.getBlockTileEntity(x, y, z));
                            iZ++;
                        }
                    }
                }
                iZ = 1;

                if (dirWest == ForgeDirection.WEST) {
                    if (tile.getTile(dirWest) != null && tile.getTile(dirWest) instanceof TileEntityEnergyPipe) {
                        while (world.getBlockTileEntity(tile.getTile(dirWest).xCoord - iX, tile.getTile(dirWest).yCoord, tile.getTile(dirWest).zCoord) != null
                                && world.getBlockTileEntity(tile.getTile(dirWest).xCoord - iX, tile.getTile(dirWest).yCoord, tile.getTile(dirWest).zCoord) instanceof TileEntityEnergyPipe) {
                            pipeList.add(world.getBlockTileEntity(tile.getTile(dirWest).xCoord - iX, tile.getTile(dirWest).yCoord, tile.getTile(dirWest).zCoord));
                            TileEntityEnergyPipe pipeToSet = ((TileEntityEnergyPipe) world.getBlockTileEntity(tile.getTile(dirWest).xCoord - iX,
                                    tile.getTile(dirWest).yCoord, tile.getTile(dirWest).zCoord));
                            pipeToSet.setBrain((TileEntityEnergyPipeBrain) world.getBlockTileEntity(x, y, z));
                            iX++;
                        }
                    }
                }
                iX = 1;

                if (dirEast == ForgeDirection.EAST) {
                    if (tile.getTile(dirEast) != null && tile.getTile(dirEast) instanceof TileEntityEnergyPipe) {
                        while (world.getBlockTileEntity(tile.getTile(dirEast).xCoord + iX, tile.getTile(dirEast).yCoord, tile.getTile(dirEast).zCoord) != null
                                && world.getBlockTileEntity(tile.getTile(dirEast).xCoord + iX, tile.getTile(dirEast).yCoord, tile.getTile(dirEast).zCoord) instanceof TileEntityEnergyPipe) {
                            pipeList.add(world.getBlockTileEntity(tile.getTile(dirEast).xCoord + iX, tile.getTile(dirEast).yCoord, tile.getTile(dirEast).zCoord));
                            TileEntityEnergyPipe pipeToSet = ((TileEntityEnergyPipe) world.getBlockTileEntity(tile.getTile(dirEast).xCoord + iX,
                                    tile.getTile(dirEast).yCoord, tile.getTile(dirEast).zCoord));
                            pipeToSet.setBrain((TileEntityEnergyPipeBrain) world.getBlockTileEntity(x, y, z));
                            iX++;
                        }
                    }
                }
                iX = 1;
            }
        }
    }

}
