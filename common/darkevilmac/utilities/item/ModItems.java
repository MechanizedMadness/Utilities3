package darkevilmac.utilities.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import darkevilmac.utilities.lib.ItemIds;
import darkevilmac.utilities.lib.Strings;

public class ModItems {

    public static Item notALinkingBook;

    public static void init() {

        notALinkingBook = new ItemNotALinkingBook(ItemIds.NOT_A_LINKINGBOOK_ID);

        GameRegistry.registerItem(notALinkingBook, "item." + Strings.NOT_A_LINKINGBOOK_UNLOCALIZEDNAME);

    }

}
