package darkevilmac.utilities.events;

import net.minecraftforge.common.MinecraftForge;

public class ModEvents {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new EatSaplingEvent());
    }
}
