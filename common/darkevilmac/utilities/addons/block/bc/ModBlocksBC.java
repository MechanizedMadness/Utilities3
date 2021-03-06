package darkevilmac.utilities.addons.block.bc;

import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.addons.tile.bc.TileEntityEnergyLinkBC;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Strings;

public class ModBlocksBC {

    public static BlockEnergyLinkBase bcLink;

    public static void init() {

        GameRegistry.registerTileEntity(TileEntityEnergyLinkBC.class, "tile" + Strings.BC_LINK_BLOCKNAME);

        bcLink = new BlockEnergyLinkBC();

        GameRegistry.registerBlock(bcLink, Strings.BC_LINK_BLOCKNAME);

    }

}
