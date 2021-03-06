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
import darkevilmac.utilities.tile.TileEntityEnergyNetworkManager;

public class BlockEnergyNetworkManager extends BlockUtilitiesContainer {

    public BlockEnergyNetworkManager() {
        super(Material.iron);
        setBlockName(Strings.ENERGYNETWORK_MANAGER_BLOCKNAME);
        setBlockTextureName(Reference.MOD_ID + ":" + Strings.ENERGYNETWORK_MANAGER_BLOCKNAME);
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
                    playerItem.getTagCompound().setString("managerType", "energy");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityEnergyNetworkManager();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        super.breakBlock(world, x, y, z, block, par6);

    }

}
