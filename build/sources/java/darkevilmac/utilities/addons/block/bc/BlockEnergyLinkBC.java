package darkevilmac.utilities.addons.block.bc;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.addons.tile.bc.TileEntityEnergyLinkBC;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Strings;

public class BlockEnergyLinkBC extends BlockEnergyLinkBase {

    protected BlockEnergyLinkBC() {
        super();
        setBlockName(Strings.BC_LINK_BLOCKNAME);
        setBlockTextureName("utilities:" + Strings.BC_LINK_BLOCKNAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityEnergyLinkBC();
    }

}
