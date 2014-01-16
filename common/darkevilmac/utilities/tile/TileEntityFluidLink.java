package darkevilmac.utilities.tile;

import net.minecraft.nbt.NBTTagCompound;
import darkevilmac.utilities.tile.prefab.TileEntityUtilities;

public class TileEntityFluidLink extends TileEntityUtilities {
    // No max energy for this it will just keep filling up!
    private int energyPoints;

    public void validate() {
        super.validate();

        energyPoints = 0;
    }

    public void injectEnergyPoints(int points) {
        energyPoints = energyPoints + points;
    }

    public void takeEnergyPoints(int points) {
        energyPoints = energyPoints - points;
    }

    public boolean canTakePoints(int points) {
        return energyPoints - points >= 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        tag.setInteger("energypoints", energyPoints);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        energyPoints = tag.getInteger("energypoints");
    }

}
