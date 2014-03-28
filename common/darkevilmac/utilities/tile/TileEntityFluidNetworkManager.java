package darkevilmac.utilities.tile;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityFluidNetworkManager extends TileEntityUtilities {

    public ArrayList<FluidStack> internalFluids = new ArrayList<FluidStack>();
    public ArrayList<TileEntityFluidNetworkBridge> fluidBridges = new ArrayList<TileEntityFluidNetworkBridge>();

    public int loops = 0;
    public String[] internalFluidsNames;
    public int[] internalFluidsAmounts;
    public int[] fluidBridgesXCoords;
    public int[] fluidBridgesYCoords;
    public int[] fluidBridgesZCoords;

    public boolean shouldReadFluidsFromNBT;
    public boolean justReadNBT;

    public TileEntityFluidNetworkManager() {
    }

    @Override
    public void validate() {
        super.validate();

        if (fluidBridges == null)
            fluidBridges = new ArrayList<TileEntityFluidNetworkBridge>();

        if (internalFluids == null)
            internalFluids = new ArrayList<FluidStack>();

        if (!justReadNBT) {
            fluidBridgesXCoords = new int[1];
            fluidBridgesYCoords = new int[1];
            fluidBridgesZCoords = new int[1];
            // 260 is an impossible coordinate just used so it can be compared
            // with me being sure the block wasn't set by normal means
            fluidBridgesYCoords[0] = 260;
        }

        loops = 0;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!world.isRemote) {
            if (shouldReadFluidsFromNBT) {
                int i = 0;
                while (i <= internalFluidsAmounts.length - 1) {
                    internalFluids.add(new FluidStack(FluidRegistry.getFluid(internalFluidsNames[i]), internalFluidsAmounts[i]));
                }
                shouldReadFluidsFromNBT = false;
            }

            int zCoordsLength = fluidBridgesZCoords.length;
            if (fluidBridges.isEmpty() && zCoordsLength > 0) {
                if (fluidBridgesYCoords[0] == 260) {
                } else {
                    int i = 0;
                    while (i <= zCoordsLength - 1) {
                        fluidBridges.add((TileEntityFluidNetworkBridge) worldObj.getTileEntity(fluidBridgesXCoords[i], fluidBridgesYCoords[i], fluidBridgesZCoords[i]));
                        i++;
                    }
                }
            }

            int i = 0;
            // Removes bridges that don't exist anymore.
            if (!fluidBridges.isEmpty()) {
                while (i <= fluidBridges.size() - 1) {
                    if (world.getTileEntity(fluidBridges.get(i).xCoord, fluidBridges.get(i).yCoord, fluidBridges.get(i).zCoord) == null) {
                        fluidBridges.remove(i);
                    }
                    i++;
                }
            }
            i = 0;

            if (!fluidBridges.isEmpty()) {
                i = 0;
                while (i <= fluidBridges.size() - 1) {
                    if (fluidBridges.get(i).manager != null && fluidBridges.get(i).manager != getTile()) {
                        fluidBridges.get(i).setManager((TileEntityFluidNetworkManager) getTile());
                    }
                    i++;
                }
            }

            if (!fluidBridges.isEmpty()) {
                i = 0;
                while (i <= fluidBridges.size() - 1) {
                    TileEntityFluidNetworkBridge bridge = fluidBridges.get(i);

                    if (bridge.getMeta() == 0) {
                        if (0 >= bridge.bufferTank.getCapacity()) {
                            if (bridge.bufferTank.getFluidAmount() < bridge.bufferTank.getCapacity()) {
                                bridge.bufferTank.fill(new FluidStack(ModFluids.fluidEnergy, bridge.bufferTank.getCapacity() - bridge.bufferTank.getFluidAmount()), true);
                            }
                        }
                    }

                    if (bridge.getMeta() == 1) {
                        bridge.bufferTank.drain(bridge.bufferTank.getFluidAmount(), true);
                    }

                    i++;
                }
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        if (!fluidBridges.isEmpty()) {
            int[] fluidBridgesXCoords = new int[fluidBridges.size()];
            int[] fluidBridgesYCoords = new int[fluidBridges.size()];
            int[] fluidBridgesZCoords = new int[fluidBridges.size()];

            int i = 0;
            while (i <= fluidBridges.size() - 1) {
                fluidBridgesXCoords[i] = fluidBridges.get(i).xCoord;
                fluidBridgesYCoords[i] = fluidBridges.get(i).yCoord;
                fluidBridgesZCoords[i] = fluidBridges.get(i).zCoord;
                i++;
            }
            nbt.setIntArray("fluidBridgesXCoords", fluidBridgesXCoords);
            nbt.setIntArray("fluidBridgesYCoords", fluidBridgesYCoords);
            nbt.setIntArray("fluidBridgesZCoords", fluidBridgesZCoords);
            nbt.setBoolean("hasBridgesInNBT", true);
        }

        if (!internalFluids.isEmpty()) {
            String[] internalFluidsNames = new String[internalFluids.size()];
            int[] internalFluidsAmounts = new int[internalFluids.size()];

            int i = 0;
            while (i <= internalFluids.size() - 1) {
                internalFluidsNames[i] = internalFluids.get(i).getFluid().getName();
                internalFluidsAmounts[i] = internalFluids.get(i).amount;
                i++;
            }

            nbt.setIntArray("internalFluidsAmounts", internalFluidsAmounts);

            i = 0;
            while (i <= internalFluidsNames.length) {
                nbt.setString("internalFluidsNames" + i, internalFluidsNames[i]);
                i++;
            }
            nbt.setBoolean("hasFluidsInNBT", true);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.getBoolean("hasBridgesInNBT")) {
            fluidBridgesXCoords = nbt.getIntArray("fluidBridgesXCoords");
            fluidBridgesYCoords = nbt.getIntArray("fluidBridgesYCoords");
            fluidBridgesZCoords = nbt.getIntArray("fluidBridgesZCoords");
        } else {
            fluidBridgesXCoords = new int[1];
            fluidBridgesYCoords = new int[1];
            fluidBridgesZCoords = new int[1];
            fluidBridgesYCoords[0] = 260;
        }
        if (nbt.getBoolean("hasFluidsInNBT")) {
            shouldReadFluidsFromNBT = true;
            internalFluidsAmounts = nbt.getIntArray("internalFluidsAmounts");
            internalFluidsNames = new String[internalFluidsAmounts.length];

            int i = 0;
            while (i <= internalFluidsAmounts.length - 1) {
                internalFluidsNames[i] = nbt.getString("internalFluidsNames" + i);
                i++;
            }
        } else {
            shouldReadFluidsFromNBT = false;
        }

        justReadNBT = true;

    }

    @Override
    public void onNeighborBlockChange(Block blockType) {
        super.onNeighborBlockChange(blockType);

    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
        super.onBlockDestroyedByPlayer(world, x, y, z, par5);

        int i = 0;
        if (!fluidBridges.isEmpty()) {
            while (i <= fluidBridges.size() - 1) {
                fluidBridges.get(i).clearManager();
            }
        }
    }

    /**
     * Returns index of internalFluids based on the fluid passed in the
     * arguments. If the fluid is not in the internalFluids it will return -1
     * 
     * @param fluid
     * @return
     */
    public int hasFluid(Fluid fluid) {
        int i = 0;
        if (!internalFluids.isEmpty()) {
            while (i <= internalFluids.size() - 1) {
                if (fluid.getName() == internalFluids.get(i).getFluid().getName()) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    /**
     * @param add
     * @param internalFluidIndex
     * @param fluid
     * @return amount added
     */
    public int addFluid(int add, Fluid fluid) {
        if (hasFluid(fluid) != -1) {
            int newAmount = internalFluids.get(hasFluid(fluid)).amount + add;
            Fluid fluidToAdd = FluidRegistry.getFluid(fluid.getName());
            internalFluids.set(hasFluid(fluid), new FluidStack(fluidToAdd, newAmount));
            return add;
        } else {
            internalFluids.add(new FluidStack(FluidRegistry.getFluid(fluid.getName()), add));
            return add;
        }
    }

    /**
     * @param use
     * @param fluid
     * @return amount added
     */
    public int useFluid(int use, Fluid fluid) {
        if (hasFluid(fluid) != -1) {
            if (internalFluids.get(hasFluid(fluid)).amount >= use) {
                internalFluids.set(hasFluid(fluid), new FluidStack(fluid, internalFluids.get(hasFluid(fluid)).amount - use));
                return use;
            }
        } else {
            internalFluids.get(hasFluid(fluid));
        }
        return 0;
    }
}
