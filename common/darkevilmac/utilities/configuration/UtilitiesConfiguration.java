package darkevilmac.utilities.configuration;

import net.minecraftforge.common.config.Configuration;
import darkevilmac.utilities.Utilities;

public class UtilitiesConfiguration {

    public static Configuration cfg = Utilities.config;

    public static void init() {
        UtilitiesConfiguration.cfg.load();

        UtilitiesConfiguration.cfg.save();
    }

}
