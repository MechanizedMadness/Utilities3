package darkevilmac.utilities.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class BlockUtilitiesContainer extends BlockContainer implements ITileEntityProvider {

    protected BlockUtilitiesContainer(Material material) {
        super(material);
        setBlockTextureName("utilities:stub");
    }

    public TileEntityUtilities getTile(World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntityUtilities) {
            return (TileEntityUtilities) world.getTileEntity(x, y, z);
        } else {
            return null;
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
        if (getTile(world, x, y, z) != null)
            getTile(world, x, y, z).onNeighborBlockChange(block);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
        if (!world.isRemote) {
            if (world.getTileEntity(x, y, z) instanceof TileEntityUtilities) {
                TileEntityUtilities tile = getTile(world, x, y, z);
                tile.onBlockPlacedBy(world, x, y, z, entity, itemstack);
                if (entity instanceof EntityPlayer) {
                    EntityPlayer entityPlayer = (EntityPlayer) entity;
                    getTile(world, x, y, z).setOwner(entityPlayer.getDisplayName());
                }
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return null;
    }

}
