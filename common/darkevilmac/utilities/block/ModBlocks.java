package darkevilmac.utilities.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import darkevilmac.utilities.lib.BlockIds;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityEnergyLinkBC;
import darkevilmac.utilities.tile.TileEntityEnergyLinkIC2;
import darkevilmac.utilities.tile.TileEntityEnergyLinkSteam;

public class ModBlocks {

    public static BlockEnergyLinkBase steamLink;
    public static BlockEnergyLinkBase ic2Link;
    public static BlockEnergyLinkBase bcLink;

    public static void init() {

        GameRegistry.registerTileEntity(TileEntityEnergyLinkSteam.class, "tile" + Strings.STEAM_LINK_UNLOCALIZEDNAME);
        GameRegistry.registerTileEntity(TileEntityEnergyLinkIC2.class, "tile" + Strings.IC2_LINK_UNLOCALIZEDNAME);
        GameRegistry.registerTileEntity(TileEntityEnergyLinkBC.class, "tile" + Strings.BC_LINK_UNLOCALIZEDNAME);

        // energyLink = new BlockEnergyLink(BlockIds.ENERGY_LINK_ID);
        steamLink = new BlockEnergyLinkSteam(BlockIds.STEAM_LINK_ID);
        ic2Link = new BlockEnergyLinkIC2(BlockIds.IC2_LINK_ID);
        bcLink = new BlockEnergyLinkBC(BlockIds.BC_LINK_ID);

        // GameRegistry.registerBlock(energyLink, Strings.ENERGY_LINK_UNLOCALIZEDNAME);
        GameRegistry.registerBlock(steamLink, Strings.STEAM_LINK_UNLOCALIZEDNAME);
        GameRegistry.registerBlock(ic2Link, Strings.IC2_LINK_UNLOCALIZEDNAME);
        GameRegistry.registerBlock(bcLink, Strings.BC_LINK_UNLOCALIZEDNAME);

        // LanguageRegistry.addName(energyLink, Strings.ENERGY_LINK_INGAMENAME);
        LanguageRegistry.addName(steamLink, Strings.STEAM_LINK_INGAMENAME);
        LanguageRegistry.addName(ic2Link, Strings.IC2_LINK_INGAMENAME);
        LanguageRegistry.addName(bcLink, Strings.BC_LINK_INGAMENAME);
    }

}
