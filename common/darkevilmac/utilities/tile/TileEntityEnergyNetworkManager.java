package darkevilmac.utilities.tile;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class TileEntityEnergyNetworkManager extends TileEntityUtilities {

    public TileEntityEnergyNetworkManager() {
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
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
    }

    @Override
    public void onNeighborBlockChange(Block blockType) {
        super.onNeighborBlockChange(blockType);
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
    }
}
