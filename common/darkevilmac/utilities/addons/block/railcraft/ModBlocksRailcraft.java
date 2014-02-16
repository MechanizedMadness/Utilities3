package darkevilmac.utilities.addons.block.railcraft;

import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.addons.tile.railcraft.TileEntityEnergyLinkSteam;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.BlockIds;
import darkevilmac.utilities.lib.Strings;

public class ModBlocksRailcraft {

    public static BlockEnergyLinkBase steamLink;

    public static void init() {

        GameRegistry.registerTileEntity(TileEntityEnergyLinkSteam.class, "tile" + Strings.STEAM_LINK_UNLOCALIZEDNAME);

        steamLink = new BlockEnergyLinkSteam(BlockIds.STEAM_LINK_ID);

        GameRegistry.registerBlock(steamLink, Strings.STEAM_LINK_UNLOCALIZEDNAME);

    }

}
