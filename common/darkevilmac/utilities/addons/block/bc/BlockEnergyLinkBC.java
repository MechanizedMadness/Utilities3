package darkevilmac.utilities.addons.block.bc;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.addons.tile.bc.TileEntityEnergyLinkBC;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;

public class BlockEnergyLinkBC extends BlockEnergyLinkBase {

    protected BlockEnergyLinkBC() {
        super();
        setBlockName(Strings.BC_LINK_BLOCKNAME);
        setBlockTextureName(Reference.MOD_TEXTURE_ID + getUnlocalizedName().substring(5));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityEnergyLinkBC();
    }

}
