package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.lib.Strings;

public class BlockDiscBurner extends BlockUtilitiesContainer {

    protected BlockDiscBurner() {
        super(Material.rock);
        setBlockName(Strings.DISC_BURNER_BLOCKNAME);
    }

}
