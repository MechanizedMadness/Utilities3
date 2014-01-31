package darkevilmac.utilities.item;

import darkevilmac.utilities.lib.Strings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemNotALinkingBook extends Item {

    public ItemNotALinkingBook(int id) {
        super(id);
        this.setCreativeTab(CreativeTabs.tabAllSearch);
        this.setUnlocalizedName(Strings.NOT_A_LINKINGBOOK_UNLOCALIZEDNAME);
        maxStackSize = 1;
    }

}
