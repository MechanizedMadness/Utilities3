package darkevilmac.utilities.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.item.base.ItemUtilities;
import darkevilmac.utilities.lib.Strings;

public class ItemPipeLinker extends ItemUtilities {

    public ItemPipeLinker() {
        super();
        setUnlocalizedName(Strings.PIPELINKER_UNLOCALIZEDNAME);
        setHasSubtypes(true);
        setMaxDamage(0);
        setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    private IIcon linkedIcon;
    @SideOnly(Side.CLIENT)
    private IIcon unlinkedIcon;

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        linkedIcon = iconRegister.registerIcon("utilities3:" + Strings.PIPELINKER_UNLOCALIZEDNAME + "Linked");
        unlinkedIcon = iconRegister.registerIcon("utilities3:" + Strings.PIPELINKER_UNLOCALIZEDNAME + "Unlinked");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int damage) {
        if (damage == 10) {
            return linkedIcon;
        } else {
            return unlinkedIcon;
        }
    }

}
