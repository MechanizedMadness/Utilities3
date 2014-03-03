package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.item.ItemPipeLinker;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityMultiNetworkManager;

public class BlockMultiNetworkManager extends BlockUtilitiesContainer {

    public BlockMultiNetworkManager() {
        super(Material.iron);
        setBlockName(Strings.MULTINETWORK_MANAGER_BLOCKNAME);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            if (player.getCurrentEquippedItem() != null) {
                ItemStack playerItem = player.getCurrentEquippedItem();
                if (playerItem.getItem() instanceof ItemPipeLinker) {
                    if (playerItem.hasTagCompound())
                        playerItem.setTagCompound(new NBTTagCompound());

                    playerItem.getTagCompound().setInteger("managerXCoord", x);
                    playerItem.getTagCompound().setInteger("managerYCoord", y);
                    playerItem.getTagCompound().setInteger("managerZCoord", z);
                    playerItem.getTagCompound().setString("managerType", "multi");
                }
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityMultiNetworkManager();
    }
}
