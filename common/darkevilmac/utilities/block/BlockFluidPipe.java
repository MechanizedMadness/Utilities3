package darkevilmac.utilities.block;

import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.lib.Strings;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockFluidPipe extends BlockUtilitiesContainer {

    protected BlockFluidPipe(int id) {
        super(id, Material.iron);
        setUnlocalizedName(Strings.FLUIDPIPE_UNLOCALIZEDNAME);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            if (world.getBlockMetadata(x, y, z) == 0) {
                world.setBlockMetadataWithNotify(x, y, z, 1, 2);
                player.addChatMessage("Inputting to network");
                return true;
            } else if (world.getBlockMetadata(x, y, z) == 1) {
                world.setBlockMetadataWithNotify(x, y, z, 0, 2);
                player.addChatMessage("Outputting from network");
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
