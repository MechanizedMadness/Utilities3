package darkevilmac.utilities.addons.block.railcraft;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.addons.tile.railcraft.TileEntityEnergyLinkSteam;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;

public class BlockEnergyLinkSteam extends BlockEnergyLinkBase {

    protected BlockEnergyLinkSteam() {
        super();
        setBlockName(Strings.STEAM_LINK_BLOCKNAME);
        setBlockTextureName(Reference.MOD_TEXTURE_ID + getUnlocalizedName().substring(5));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityEnergyLinkSteam();
    }

}
