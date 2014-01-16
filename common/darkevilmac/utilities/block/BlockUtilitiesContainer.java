package darkevilmac.utilities.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.tile.prefab.TileEntityUtilities;

public class BlockUtilitiesContainer extends BlockContainer {

    protected BlockUtilitiesContainer(int id, Material material) {
        super(id, material);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }

    public TileEntityUtilities getTile(World world, int x, int y, int z) {
        if (world.getBlockTileEntity(x, y, z) instanceof TileEntityUtilities) {
            return (TileEntityUtilities) world.getBlockTileEntity(x, y, z);
        } else {
            return null;
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int par5) {
        super.onNeighborBlockChange(world, x, y, z, par5);
        if (getTile(world, x, y, z) != null)
            getTile(world, x, y, z).onNeighborBlockChange();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
        if (!world.isRemote) {
            if (world.getBlockTileEntity(x, y, z) instanceof TileEntityUtilities) {
                TileEntityUtilities tile = getTile(world, x, y, z);
                tile.onBlockPlacedBy(world, x, y, z, entity, itemstack);
                if (entity instanceof EntityPlayer) {
                    EntityPlayer entityPlayer = (EntityPlayer) entity;
                    getTile(world, x, y, z).setOwner(entityPlayer.username);
                }
            }
        }
    }

}
