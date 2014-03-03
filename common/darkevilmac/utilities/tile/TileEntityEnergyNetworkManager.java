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

    public TileEntityEnergyNetworkManager() {
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
                if (!energyBridges.isEmpty()) {
                    while (i <= energyBridges.size() - 1) {
                        if (world.getTileEntity(energyBridges.get(i).xCoord, energyBridges.get(i).yCoord, energyBridges.get(i).zCoord) == null) {
                            energyBridges.remove(i);
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

        if (!energyBridges.isEmpty()) {
            int i = 0;
            while (i <= energyBridges.size() - 1) {
                i++;
            }
        }

        nbt.setInteger("internalEnergyAmount", internalEnergy.amount);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        if (energyBridges.isEmpty()) {
            int i = 0;
            while (i <= energyBridges.size() - 1) {
                i++;
            }
        }

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
                energyBridges.get(i).manager = null;
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
