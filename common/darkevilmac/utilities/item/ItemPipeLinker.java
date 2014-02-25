package darkevilmac.utilities.item;

import darkevilmac.utilities.lib.Strings;

public class ItemPipeLinker extends ItemUtilities {

    public ItemPipeLinker() {
        super();
        setUnlocalizedName(Strings.PIPELINKER_UNLOCALIZEDNAME);
        setTextureName("utilities:" + getUnlocalizedName().substring(5));
    }

}
