package darkevilmac.utilities.tile.base;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityUtilities extends TileEntity {

    protected String owner;
    protected static World world;

    @Override
    public void validate() {
        super.validate();
        owner = null;
        world = worldObj;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        owner = nbt.getString("owner");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        if (owner != null)
            nbt.setString("owner", owner);
        if (owner == null)
            nbt.setString("owner", "unknownPlayer");
    }

    public TileEntityUtilities getTile() {
        return (TileEntityUtilities) TileEntityUtilities.world.getBlockTileEntity(xCoord, yCoord, zCoord);
    }

    /**
     * I wonder what this does? It returns the metadata of the tile's block.
     * 
     * @return
     */
    public int getMeta() {
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        return meta;
    }

    /**
     * Notifies the tile of a change of a nearby block.
     */
    public void onNeighborBlockChange() {

    }

    /**
     * Called when block is placed and a tile exists.
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @param entity
     * @param itemstack
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {

    }

    /**
     * Returns the owner of the tile.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the tile.
     */
    public void setOwner(String newOwner) {
        owner = newOwner;
    }

    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
    }

}
