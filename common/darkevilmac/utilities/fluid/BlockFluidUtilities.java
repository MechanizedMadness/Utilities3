package darkevilmac.utilities.fluid;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import darkevilmac.utilities.Utilities;

public class BlockFluidUtilities extends BlockFluidClassic {

    public BlockFluidUtilities(Fluid fluid, Material material) {
        super(fluid, material);
        setCreativeTab(Utilities.modTab);
    }

}
