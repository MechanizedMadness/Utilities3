package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityMultiNetworkManager;

public class BlockMultiNetworkManager extends BlockUtilitiesContainer {

    public BlockMultiNetworkManager() {
        super(Material.iron);
        setBlockName(Strings.MULTINETWORK_MANAGER_BLOCKNAME);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityMultiNetworkManager();
    }
}
