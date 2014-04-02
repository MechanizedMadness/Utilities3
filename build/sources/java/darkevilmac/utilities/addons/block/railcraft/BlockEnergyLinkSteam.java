package darkevilmac.utilities.addons.block.railcraft;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.addons.tile.railcraft.TileEntityEnergyLinkSteam;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Strings;

public class BlockEnergyLinkSteam extends BlockEnergyLinkBase {

    protected BlockEnergyLinkSteam() {
        super();
        setBlockName(Strings.STEAM_LINK_BLOCKNAME);
        setBlockTextureName("utilities:" + Strings.STEAM_LINK_BLOCKNAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityEnergyLinkSteam();
    }

}
