package darkevilmac.utilities.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import darkevilmac.utilities.tile.base.TileEntityUtilities;

public class BlockUtilities extends Block {

    public BlockUtilities(Material material) {
        super(Material.iron);
        setBlockTextureName("utilities:stub");
    }

    public TileEntityUtilities getTile(World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) != null) {
            if (world.getTileEntity(x, y, z) instanceof TileEntityUtilities) {
                return (TileEntityUtilities) world.getTileEntity(x, y, z);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (getTile(world, x, y, z) != null)
            getTile(world, x, y, z).onNeighborBlockChange(block);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack par6ItemStack) {
        if (!world.isRemote) {
            if (getTile(world, x, y, z) != null && getTile(world, x, y, z) instanceof TileEntityUtilities) {
                if (entity instanceof EntityPlayer) {
                    EntityPlayer entityPlayer = (EntityPlayer) entity;
                    getTile(world, x, y, z).setOwner(entityPlayer.getDisplayName());
                }
            }
        }
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
        if (!world.isRemote) {
            if (getTile(world, x, y, z) != null && getTile(world, x, y, z) instanceof TileEntityUtilities) {
                getTile(world, x, y, z).onBlockDestroyedByPlayer(world, x, y, z, par5);
            }
        }
    }
}