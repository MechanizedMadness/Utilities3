package darkevilmac.utilities.configuration;

import net.minecraftforge.common.config.Configuration;
import darkevilmac.utilities.Utilities;
import darkevilmac.utilities.lib.Booleans;

public class UtilitiesConfiguration {

    public static Configuration cfg = Utilities.config;

    public static void init() {
        UtilitiesConfiguration.cfg.load();

        Booleans.devMode = cfg.get("Booleans", "devMode", Booleans.devMode_DEFAULT).getBoolean(Booleans.devMode_DEFAULT);

        UtilitiesConfiguration.cfg.save();
    }

}
