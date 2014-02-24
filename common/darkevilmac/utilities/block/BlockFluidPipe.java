package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.lib.Strings;

public class BlockFluidPipe extends BlockUtilitiesContainer {

    protected BlockFluidPipe(Material material) {
        super(Material.iron);
        setBlockName(Strings.FLUIDPIPE_BLOCKNAME);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            if (world.getBlockMetadata(x, y, z) == 0) {
                world.setBlockMetadataWithNotify(x, y, z, 1, 2);
                player.addChatComponentMessage(new ChatComponentText("Inputting to network"));
                return true;
            } else if (world.getBlockMetadata(x, y, z) == 1) {
                world.setBlockMetadataWithNotify(x, y, z, 0, 2);
                player.addChatComponentMessage(new ChatComponentText("Outputting from network"));
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
