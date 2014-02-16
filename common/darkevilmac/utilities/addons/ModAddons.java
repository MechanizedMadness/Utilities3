package darkevilmac.utilities.addons;

import cpw.mods.fml.common.Loader;
import darkevilmac.utilities.addons.block.bc.ModBlocksBC;
import darkevilmac.utilities.addons.block.ic2.ModBlocksIC2;
import darkevilmac.utilities.addons.block.railcraft.ModBlocksRailcraft;

public class ModAddons {

    public static void init() {
        if (Loader.isModLoaded("Railcraft")) {
            ModBlocksRailcraft.init();
        }
        if (Loader.isModLoaded("BuildCraft|Energy")) {
            ModBlocksBC.init();
        }
        if (Loader.isModLoaded("IC2")) {
            ModBlocksIC2.init();
        }
    }

}
