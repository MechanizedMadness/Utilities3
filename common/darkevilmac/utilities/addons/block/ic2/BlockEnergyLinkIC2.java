package darkevilmac.utilities.addons.block.ic2;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.addons.tile.ic2.TileEntityEnergyLinkIC2;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;

public class BlockEnergyLinkIC2 extends BlockEnergyLinkBase {

    protected BlockEnergyLinkIC2() {
        super();
        setBlockName(Strings.IC2_LINK_BLOCKNAME);
        setBlockTextureName(Reference.MOD_TEXTURE_ID + getUnlocalizedName().substring(5));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityEnergyLinkIC2();
    }

}
