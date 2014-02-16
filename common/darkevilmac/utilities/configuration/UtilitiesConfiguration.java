package darkevilmac.utilities.configuration;

import net.minecraftforge.common.Configuration;
import darkevilmac.utilities.Utilities;
import darkevilmac.utilities.lib.BlockIds;
import darkevilmac.utilities.lib.ItemIds;
import darkevilmac.utilities.lib.Strings;

public class UtilitiesConfiguration {

    public static Configuration cfg = Utilities.config;

    public static void init() {
        UtilitiesConfiguration.cfg.load();

        // Items
        ItemIds.TELEPEBBLE_ID = UtilitiesConfiguration.cfg.getItem(Strings.TELEPEBBLE_INGAMENAME, ItemIds.TELEPEBBLE_ID_DEFAULT).getInt(
                ItemIds.TELEPEBBLE_ID_DEFAULT);
        ItemIds.PIPELINKER_ID = UtilitiesConfiguration.cfg.getItem(Strings.PIPELINKER_INGAMENAME, ItemIds.PIPELINKER_ID_DEFAULT).getInt(
                ItemIds.PIPELINKER_ID_DEFAULT);

        // Fluids
        BlockIds.FLUID_ENERGY_ID = UtilitiesConfiguration.cfg.get("fluid", Strings.FLUID_ENERGY_INGAMENAME, BlockIds.FLUID_ENERGY_ID_DEFAULT).getInt();
        BlockIds.FLUID_STEAM_ID = UtilitiesConfiguration.cfg.get("fluid", Strings.FLUID_STEAM_INGAMENAME, BlockIds.FLUID_STEAM_ID_DEFAULT).getInt();

        // Blocks
        BlockIds.IC2_LINK_ID = UtilitiesConfiguration.cfg.get(Configuration.CATEGORY_BLOCK, Strings.IC2_LINK_INGAMENAME, BlockIds.IC2_LINK_ID_DEFAULT).getInt();
        BlockIds.STEAM_LINK_ID = UtilitiesConfiguration.cfg.get(Configuration.CATEGORY_BLOCK, Strings.STEAM_LINK_INGAMENAME, BlockIds.STEAM_LINK_ID_DEFAULT)
                .getInt();
        BlockIds.BC_LINK_ID = UtilitiesConfiguration.cfg.get(Configuration.CATEGORY_BLOCK, Strings.BC_LINK_INGAMENAME, BlockIds.BC_LINK_ID_DEFAULT).getInt();
        BlockIds.ENERGYPIPE_BRAIN_ID = UtilitiesConfiguration.cfg.get(Configuration.CATEGORY_BLOCK, Strings.ENERGYPIPE_BRAIN_INGAMENAME,
                BlockIds.ENERGYPIPE_BRAIN_ID_DEFAULT).getInt();
        BlockIds.ITEMPIPE_BRAIN_ID = UtilitiesConfiguration.cfg.get(Configuration.CATEGORY_BLOCK, Strings.ITEMPIPE_BRAIN_INGAMENAME,
                BlockIds.ITEMPIPE_BRAIN_ID_DEFAULT).getInt();
        BlockIds.FLUIDPIPE_BRAIN_ID = UtilitiesConfiguration.cfg.get(Configuration.CATEGORY_BLOCK, Strings.FLUIDPIPE_BRAIN_INGAMENAME,
                BlockIds.FLUIDPIPE_BRAIN_ID_DEFAULT).getInt();
        BlockIds.MULTIPIPE_BRAIN_ID = UtilitiesConfiguration.cfg.get(Configuration.CATEGORY_BLOCK, Strings.MULTIPIPE_BRAIN_INGAMENAME,
                BlockIds.MULTIPIPE_BRAIN_ID_DEFAULT).getInt();
        BlockIds.ENERGYPIPE_ID = UtilitiesConfiguration.cfg.get(Configuration.CATEGORY_BLOCK, Strings.ENERGYPIPE_INGAMENAME, BlockIds.ENERGYPIPE_ID_DEFAULT)
                .getInt();
        BlockIds.ITEMPIPE_ID = UtilitiesConfiguration.cfg.get(Configuration.CATEGORY_BLOCK, Strings.ITEMPIPE_INGAMENAME, BlockIds.ITEMPIPE_ID_DEFAULT).getInt();
        BlockIds.FLUIDPIPE_ID = UtilitiesConfiguration.cfg.get(Configuration.CATEGORY_BLOCK, Strings.FLUIDPIPE_INGAMENAME, BlockIds.FLUIDPIPE_ID_DEFAULT)
                .getInt();
        BlockIds.MULTIPIPE_ID = UtilitiesConfiguration.cfg.get(Configuration.CATEGORY_BLOCK, Strings.MULTIPIPE_INGAMENAME, BlockIds.MULTIPIPE_ID_DEFAULT)
                .getInt();

        UtilitiesConfiguration.cfg.save();
    }

}
