package darkevilmac.utilities.item;

import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.lib.ItemIds;
import darkevilmac.utilities.lib.Strings;

public class ModItems {

    public static ItemUtilities telePebble;

    public static void init() {

        telePebble = new ItemTelePebble(ItemIds.TELEPEBBLE_ID);

        GameRegistry.registerItem(telePebble, "item." + Strings.TELEPEBBLE_UNLOCALIZEDNAME);

    }

}
