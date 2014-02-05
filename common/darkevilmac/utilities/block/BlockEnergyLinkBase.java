package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnergyLinkBase extends BlockUtilitiesContainer {

    protected BlockEnergyLinkBase(int id, String unlocalizedName) {
        super(id, Material.iron);
        this.setHardness(1F);
        this.setUnlocalizedName(unlocalizedName);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            if (world.getBlockMetadata(x, y, z) == 0) {
                world.setBlockMetadataWithNotify(x, y, z, 1, 2);
                player.addChatMessage("Converting to Fluid");
                return true;
            } else if (world.getBlockMetadata(x, y, z) == 1) {
                world.setBlockMetadataWithNotify(x, y, z, 0, 2);
                player.addChatMessage("Converting to Energy");
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
