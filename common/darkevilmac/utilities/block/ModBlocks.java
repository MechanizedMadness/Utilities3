package darkevilmac.utilities.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import darkevilmac.utilities.lib.BlockIds;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityEnergyLinkIC2;
import darkevilmac.utilities.tile.TileEntityEnergyLinkSteam;

public class ModBlocks {

    public static BlockEnergyLinkBase energyLinkIC2;
    public static BlockEnergyLinkBase energyLinkSteam;

    public static void init() {
        
        GameRegistry.registerTileEntity(TileEntityEnergyLinkSteam.class, "tile"+Strings.ENERGY_LINK_STEAM_UNLOCALIZEDNAME);
        GameRegistry.registerTileEntity(TileEntityEnergyLinkIC2.class, "tile"+Strings.ENERGY_LINK_IC2_UNLOCALIZEDNAME);

        // energyLink = new BlockEnergyLink(BlockIds.ENERGY_LINK_ID);
        energyLinkSteam = new BlockEnergyLinkSteam(BlockIds.ENERGY_LINK_STEAM_ID);
        energyLinkIC2 = new BlockEnergyLinkIC2(BlockIds.ENERGY_LINK_IC2_ID);

        // GameRegistry.registerBlock(energyLink, Strings.ENERGY_LINK_UNLOCALIZEDNAME);
        GameRegistry.registerBlock(energyLinkSteam, Strings.ENERGY_LINK_STEAM_UNLOCALIZEDNAME);
        GameRegistry.registerBlock(energyLinkIC2, Strings.ENERGY_LINK_IC2_UNLOCALIZEDNAME);

        // LanguageRegistry.addName(energyLink, Strings.ENERGY_LINK_INGAMENAME);
        LanguageRegistry.addName(energyLinkSteam, Strings.ENERGY_LINK_STEAM_INGAMENAME);
        LanguageRegistry.addName(energyLinkIC2, Strings.ENERGY_LINK_IC2_INGAMENAME);
    }

}
