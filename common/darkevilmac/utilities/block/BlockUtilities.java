package darkevilmac.utilities.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import darkevilmac.utilities.tile.prefab.TileEntityUtilities;

public class BlockUtilities extends Block {

    public BlockUtilities(int id, Material material) {
        super(id, material);
    }

    public TileEntityUtilities getTile(World world, int x, int y, int z) {
        if (world.getBlockTileEntity(x, y, z) != null) {
            if (world.getBlockTileEntity(x, y, z) instanceof TileEntityUtilities) {
                return (TileEntityUtilities) world.getBlockTileEntity(x, y, z);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int par5) {
        if (getTile(world, x, y, z) != null)
            getTile(world, x, y, z).onNeighborBlockChange();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack par6ItemStack) {
        if (!world.isRemote) {
            if (world.getBlockTileEntity(x, y, z) instanceof TileEntityUtilities) {
                if (entity instanceof EntityPlayer) {
                    EntityPlayer entityPlayer = (EntityPlayer) entity;
                    getTile(world, x, y, z).setOwner(entityPlayer.username);
                }
            }
        }
    }
}
