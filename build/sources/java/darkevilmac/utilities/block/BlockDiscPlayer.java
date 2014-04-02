package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.lib.Strings;

public class BlockDiscPlayer extends BlockUtilitiesContainer {

    protected BlockDiscPlayer() {
        super(Material.rock);
        setBlockName(Strings.DISC_PLAYER_BLOCKNAME);
    }

}
