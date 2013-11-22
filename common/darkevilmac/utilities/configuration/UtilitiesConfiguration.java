package darkevilmac.utilities.configuration;

import net.minecraftforge.common.Configuration;
import darkevilmac.utilities.Utilities;
import darkevilmac.utilities.lib.BlockIds;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.utils.Utils;

public class UtilitiesConfiguration {

    public static Configuration cfg = Utilities.config;

    public static void init() {
        cfg.load();

        // Items

        // Fluids
        BlockIds.FLUID_ENERGY_ID = cfg.get("fluid", Strings.FLUID_ENERGY_INGAMENAME, Utils.getBlockID()).getInt();
        Utils.addToBlockID(1);

        // Blocks
        BlockIds.ENERGY_LINK_IC2_ID = cfg.get(Configuration.CATEGORY_BLOCK, Strings.ENERGY_LINK_IC2_INGAMENAME,
                Utils.getBlockID()).getInt();
        BlockIds.ENERGY_LINK_STEAM_ID = cfg.get(Configuration.CATEGORY_BLOCK, Strings.ENERGY_LINK_STEAM_INGAMENAME,
                Utils.getBlockID()).getInt();
        cfg.save();
    }

}
