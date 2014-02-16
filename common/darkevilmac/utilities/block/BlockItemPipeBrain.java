package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import darkevilmac.utilities.block.base.BlockUtilities;
import darkevilmac.utilities.lib.Strings;

public class BlockItemPipeBrain extends BlockUtilities {

    public BlockItemPipeBrain(int id) {
        super(id, Material.iron);
        setUnlocalizedName(Strings.ENERGYPIPE_BRAIN_UNLOCALIZEDNAME);
    }

}
