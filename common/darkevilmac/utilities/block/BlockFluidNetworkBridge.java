package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityFluidNetworkBridge;

public class BlockFluidNetworkBridge extends BlockUtilitiesContainer {

    protected BlockFluidNetworkBridge() {
        super(Material.iron);
        setBlockName(Strings.FLUIDNETWORK_BRIDGE_BLOCKNAME);
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
    
    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityFluidNetworkBridge();
    }

}
