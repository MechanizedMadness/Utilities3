package darkevilmac.utilities.addons.block.ic2;

import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.addons.tile.ic2.TileEntityEnergyLinkIC2;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Strings;

public class ModBlocksIC2 {

    public static BlockEnergyLinkBase ic2Link;

    public static void init() {

        GameRegistry.registerTileEntity(TileEntityEnergyLinkIC2.class, "tile" + Strings.IC2_LINK_BLOCKNAME);

        ic2Link = new BlockEnergyLinkIC2();

        GameRegistry.registerBlock(ic2Link, Strings.IC2_LINK_BLOCKNAME);

    }

}
