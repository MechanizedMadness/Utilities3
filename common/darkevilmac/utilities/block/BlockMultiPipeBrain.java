package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import darkevilmac.utilities.block.base.BlockUtilities;
import darkevilmac.utilities.lib.Strings;

public class BlockMultiPipeBrain extends BlockUtilities {

    public BlockMultiPipeBrain() {
        super(Material.iron);
        setBlockName(Strings.ENERGYPIPE_BRAIN_BLOCKNAME);
    }
}
