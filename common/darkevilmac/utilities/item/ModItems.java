package darkevilmac.utilities.item;

import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.lib.ItemIds;
import darkevilmac.utilities.lib.Strings;

public class ModItems {

    public static ItemUtilities notALinkingBook;

    public static void init() {

        notALinkingBook = new ItemNotALinkingBook(ItemIds.NOT_A_LINKINGBOOK_ID);

        GameRegistry.registerItem(notALinkingBook, "item." + Strings.NOT_A_LINKINGBOOK_UNLOCALIZEDNAME);

    }

}
