package darkevilmac.utilities.configuration;

import net.minecraftforge.common.Configuration;
import darkevilmac.utilities.Utilities;
import darkevilmac.utilities.lib.BlockIds;
import darkevilmac.utilities.lib.ItemIds;
import darkevilmac.utilities.lib.Strings;

public class UtilitiesConfiguration {

    public static Configuration cfg = Utilities.config;

    public static void init() {
        cfg.load();

        // Items
        ItemIds.TELEPEBBLE_ID = cfg.getItem(Strings.TELEPEBBLE_INGAMENAME, ItemIds.TELEPEBBLE_ID_DEFAULT).getInt(ItemIds.TELEPEBBLE_ID_DEFAULT);

        // Fluids
        BlockIds.FLUID_ENERGY_ID = cfg.get("fluid", Strings.FLUID_ENERGY_INGAMENAME, BlockIds.FLUID_ENERGY_ID_DEFAULT).getInt();
        BlockIds.FLUID_STEAM_ID =  cfg.get("fluid", Strings.FLUID_STEAM_INGAMENAME, BlockIds.FLUID_STEAM_ID_DEFAULT).getInt();

        // Blocks
        BlockIds.IC2_LINK_ID = cfg.get(Configuration.CATEGORY_BLOCK, Strings.IC2_LINK_INGAMENAME, BlockIds.IC2_LINK_ID_DEFAULT).getInt();
        BlockIds.STEAM_LINK_ID = cfg.get(Configuration.CATEGORY_BLOCK, Strings.STEAM_LINK_INGAMENAME, BlockIds.STEAM_LINK_ID_DEFAULT).getInt();
        BlockIds.BC_LINK_ID = cfg.get(Configuration.CATEGORY_BLOCK, Strings.BC_LINK_INGAMENAME, BlockIds.BC_LINK_ID_DEFAULT).getInt();
        cfg.save();
    }

}
