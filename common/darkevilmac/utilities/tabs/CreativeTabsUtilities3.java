package darkevilmac.utilities.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.item.ModItems;


public class CreativeTabsUtilities3 extends CreativeTabs {

    public CreativeTabsUtilities3(String label) {
        super(label);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Item getTabIconItem() {
        return ModItems.pipeLinker;
    }

}
