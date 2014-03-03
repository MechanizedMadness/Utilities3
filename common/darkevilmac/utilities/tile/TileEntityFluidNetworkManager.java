package darkevilmac.utilities.tile;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityFluidNetworkManager extends TileEntityUtilities {

    public int loops = 0;
    public ArrayList<TileEntityFluidNetworkBridge> fluidBridges = new ArrayList<TileEntityFluidNetworkBridge>();
    public ArrayList<FluidStack> internalFluids = new ArrayList<FluidStack>();

    public TileEntityFluidNetworkManager() {
    }

    @Override
    public void validate() {
        super.validate();

        loops = 0;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!world.isRemote) {
            if (loops == 5) {
                loops = -1;

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

                // Remove empty fluids in list.
                if (!internalFluids.isEmpty()) {
                    while (i <= internalFluids.size() - 1) {
                        if (internalFluids.get(i).amount == 0) {
                            internalFluids.remove(i);
                        }
                        i++;
                    }
                }
                i = 0;

            }
            loops++;
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
        } else {
            nbt.setBoolean("hasBridgesInNBT", false);
        }

        if (!internalFluids.isEmpty()) {
            String[] internalFluidsTypes = new String[internalFluids.size()];
            int[] internalFluidsAmounts = new int[internalFluids.size()];
            int i = 0;
            while (i <= internalFluids.size() - 1) {
                internalFluidsTypes[i] = internalFluids.get(i).getFluid().getName();
                internalFluidsAmounts[i] = internalFluids.get(i).amount;
                i++;
            }
            i = 0;
            while (i <= internalFluids.size() - 1) {
                nbt.setString("internalFluidsTypes" + i, internalFluidsTypes[i]);
                i++;
            }
            nbt.setIntArray("internalFluidsAmounts", internalFluidsAmounts);
            nbt.setBoolean("hasInternalFluidsInNBT", true);
        } else {
            int i = 0;
            while (i <= nbt.getIntArray("internalFluidsAmounts").length - 1) {
                nbt.removeTag("internalFluidsTypes" + i);
                i++;
            }
            nbt.removeTag("internalFluidsAmounts");
            nbt.setBoolean("hasInternalFluidsInNBT", false);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        if (fluidBridges.isEmpty()) {
            if (nbt.getBoolean("hasBridgesInNBT")) {
                int i = 0;

                while (i <= nbt.getIntArray("fluidBridgesXCoords").length - 1) {
                    fluidBridges.add((TileEntityFluidNetworkBridge) world.getTileEntity(nbt.getIntArray("fluidBridgesXCoords")[i], nbt.getIntArray("fluidBridgesYCoords")[i],
                            nbt.getIntArray("fluidBridgesZCoords")[i]));
                    i++;
                }
            }
        }
        if (internalFluids.isEmpty()) {
            if (nbt.getBoolean("hasInternalFluidsInNBT")) {
                int i = 0;

                while (i <= nbt.getIntArray("internalFluidsAmounts").length - 1) {
                    internalFluids.add(new FluidStack(FluidRegistry.getFluid(nbt.getString("internalFluidsTypes") + i), nbt.getIntArray("internalFluidsAmounts")[i]));
                    i++;
                }
            }
        }
    }

    /**
     * Checks if a fluid is in the manager.
     * 
     * @param fluid
     *            Fluid to check if there is a stack in the manager
     * @return True if the fluid is in the manager
     */
    public boolean hasInternalFluid(Fluid fluid) {
        if (!internalFluids.isEmpty()) {
            int i = 0;

            while (i <= internalFluids.size() - 1) {
                if (internalFluids.get(i).getFluid() == fluid) {
                    return true;
                }
                i++;
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * Gets FluidStack in the manager relative to the type of fluid.
     * 
     * NOTE: Please use hasInternalFluid() before using this method.
     * 
     * @param fluid
     *            Fluid to get the FluidStack for.
     * @return The FluidStack belonging to a fluid type in the manager
     */
    public FluidStack getInternalFluid(Fluid fluid) {
        if (!internalFluids.isEmpty()) {
            int i = 0;

            while (i <= internalFluids.size() - 1) {
                if (internalFluids.get(i).getFluid() == fluid) {
                    return internalFluids.get(i);
                }
                i++;
            }
        }
        return null;
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
                fluidBridges.get(i).manager = null;
            }
        }
    }
}
