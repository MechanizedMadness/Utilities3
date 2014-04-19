package darkevilmac.utilities.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.item.ItemPipeLinker;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityItemNetworkManager;

public class BlockItemNetworkManager extends BlockUtilitiesContainer {

    public BlockItemNetworkManager() {
        super(Material.iron);
        setBlockName(Strings.ITEMNETWORK_MANAGER_BLOCKNAME);
        setBlockTextureName(Reference.MOD_ID + ":" + Strings.ITEMNETWORK_MANAGER_BLOCKNAME);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            if (player.getCurrentEquippedItem() != null) {
                ItemStack playerItem = player.getCurrentEquippedItem();
                if (playerItem.getItem() instanceof ItemPipeLinker) {
                    if (!playerItem.hasTagCompound())
                        playerItem.setTagCompound(new NBTTagCompound());

                    playerItem.setItemDamage(10);

                    playerItem.getTagCompound().setInteger("dimID", world.provider.dimensionId);
                    playerItem.getTagCompound().setInteger("managerXCoord", x);
                    playerItem.getTagCompound().setInteger("managerYCoord", y);
                    playerItem.getTagCompound().setInteger("managerZCoord", z);
                    playerItem.getTagCompound().setBoolean("hasManager", true);
                    playerItem.getTagCompound().setString("managerType", "item");
                }
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityItemNetworkManager();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityItemNetworkManager) {
            if (!((TileEntityItemNetworkManager) world.getTileEntity(x, y, z)).itemBridges.isEmpty()) {
                int i = 0;
                while (i <= ((TileEntityItemNetworkManager) world.getTileEntity(x, y, z)).itemBridges.size() - 1) {
                    ((TileEntityItemNetworkManager) world.getTileEntity(x, y, z)).itemBridges.get(i).clearManager();
                    i++;
                }
                ((TileEntityItemNetworkManager) world.getTileEntity(x, y, z)).itemBridges.clear();
                world.setTileEntity(x, y, z, null);
            }
        }
        super.breakBlock(world, x, y, z, block, par6);
    }

}
