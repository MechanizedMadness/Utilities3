package darkevilmac.utilities.tile;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityEnergyNetworkManager extends TileEntityUtilities {

    public FluidStack internalEnergy = new FluidStack(ModFluids.fluidEnergy, 0);
    public int loops = 0;
    public ArrayList<TileEntityEnergyNetworkBridge> energyBridges = new ArrayList<TileEntityEnergyNetworkBridge>();
    public int[] energyBridgesXCoords;
    public int[] energyBridgesYCoords;
    public int[] energyBridgesZCoords;
    public boolean justReadNBT;

    public TileEntityEnergyNetworkManager() {
    }

    @Override
    public void validate() {
        super.validate();

        if (energyBridges == null)
            energyBridges = new ArrayList<TileEntityEnergyNetworkBridge>();

        if (!justReadNBT) {
            energyBridgesXCoords = new int[1];
            energyBridgesYCoords = new int[1];
            energyBridgesZCoords = new int[1];
            // 260 is an impossible coordinate just used so it can be compared
            // with me being sure the block wasn't set by normal means
            energyBridgesYCoords[0] = 260;
        }

        loops = 0;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!world.isRemote) {
            int zCoordsLength = energyBridgesZCoords.length;
            if (energyBridges.isEmpty() && zCoordsLength > 0) {
                if (energyBridgesYCoords[0] == 260) {
                } else {
                    int i = 0;
                    while (i <= zCoordsLength - 1) {
                        energyBridges.add((TileEntityEnergyNetworkBridge) worldObj.getTileEntity(energyBridgesXCoords[i], energyBridgesYCoords[i], energyBridgesZCoords[i]));
                        i++;
                    }
                }
            }

            int i = 0;
            // Removes bridges that don't exist anymore.
            if (!energyBridges.isEmpty()) {
                while (i <= energyBridges.size() - 1) {
                    if (world.getTileEntity(energyBridges.get(i).xCoord, energyBridges.get(i).yCoord, energyBridges.get(i).zCoord) == null) {
                        energyBridges.remove(i);
                    }
                    i++;
                }
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        if (!energyBridges.isEmpty()) {
            int[] energyBridgesXCoords = new int[energyBridges.size()];
            int[] energyBridgesYCoords = new int[energyBridges.size()];
            int[] energyBridgesZCoords = new int[energyBridges.size()];

            int i = 0;
            while (i <= energyBridges.size() - 1) {
                energyBridgesXCoords[i] = energyBridges.get(i).xCoord;
                energyBridgesYCoords[i] = energyBridges.get(i).yCoord;
                energyBridgesZCoords[i] = energyBridges.get(i).zCoord;
                i++;
            }
            nbt.setIntArray("energyBridgesXCoords", energyBridgesXCoords);
            nbt.setIntArray("energyBridgesYCoords", energyBridgesYCoords);
            nbt.setIntArray("energyBridgesZCoords", energyBridgesZCoords);
            nbt.setBoolean("hasBridgesInNBT", true);
        }

        nbt.setInteger("internalEnergyAmount", internalEnergy.amount);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.getBoolean("hasBridgesInNBT")) {
            energyBridgesXCoords = nbt.getIntArray("energyBridgesXCoords");
            energyBridgesYCoords = nbt.getIntArray("energyBridgesYCoords");
            energyBridgesZCoords = nbt.getIntArray("energyBridgesZCoords");
        } else {
            energyBridgesXCoords = new int[1];
            energyBridgesYCoords = new int[1];
            energyBridgesZCoords = new int[1];
            energyBridgesYCoords[0] = 260;
        }

        justReadNBT = true;

        internalEnergy = new FluidStack(ModFluids.fluidEnergy, nbt.getInteger("internalEnergyAmount"));
    }

    @Override
    public void onNeighborBlockChange(Block blockType) {
        super.onNeighborBlockChange(blockType);

    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
        super.onBlockDestroyedByPlayer(world, x, y, z, par5);

        int i = 0;
        if (!energyBridges.isEmpty()) {
            while (i <= energyBridges.size() - 1) {
                energyBridges.get(i).clearManager();
            }
        }
    }

    public void addEnergy(int add) {
        int old = internalEnergy.amount;
        internalEnergy = new FluidStack(ModFluids.fluidEnergy, old + add);
    }

    public void useEnergy(int use) {
        int old = internalEnergy.amount;
        internalEnergy = new FluidStack(ModFluids.fluidEnergy, old - use);
    }
}
