package darkevilmac.utilities.tile.base;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.block.base.BlockUtilities;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;

public class TileEntityUtilities extends TileEntity {

    public String owner;
    public World world;
    public int worldid;
    public int xCoordinate;
    public int yCoordinate;
    public int zCoordinate;

    @Override
    public void validate() {
        super.validate();
        owner = null;
        world = worldObj;
        worldid = world.provider.dimensionId;
        xCoordinate = xCoord;
        yCoordinate = yCoord;
        zCoordinate = zCoord;
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

    /**
     * @see BlockUtilities
     * @return BlockUtilities associated with the instance of this tile
     */
    public BlockUtilities getBlock() {
        return (BlockUtilities) world.getBlock(xCoord, yCoord, zCoord);
    }

    /**
     * @see BlockUtilitiesContainer
     * @return BlockUtilitiesContainer associated with the instance of this tile
     */
    public BlockUtilitiesContainer getBlockContainer() {
        return (BlockUtilitiesContainer) world.getBlock(xCoord, yCoord, zCoord);
    }

    /**
     * @return TileEntityUtilities associated with the instance of this tile
     */
    public TileEntityUtilities getTile() {
        return (TileEntityUtilities) world.getTileEntity(xCoord, yCoord, zCoord);
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
    public void onNeighborBlockChange(Block changedBlock) {

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
