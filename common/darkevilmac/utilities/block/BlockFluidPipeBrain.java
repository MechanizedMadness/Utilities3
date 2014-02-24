package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import darkevilmac.utilities.block.base.BlockUtilities;
import darkevilmac.utilities.lib.Strings;

public class BlockFluidPipeBrain extends BlockUtilities {

    public BlockFluidPipeBrain() {
        super(Material.iron);
        setBlockName(Strings.ENERGYPIPE_BRAIN_BLOCKNAME);
    }

}
