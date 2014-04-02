package darkevilmac.utilities.fluid;

import darkevilmac.utilities.Utilities;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidUtilities extends BlockFluidClassic {

    public BlockFluidUtilities(Fluid fluid, Material material) {
        super(fluid, material);
        setCreativeTab(Utilities.modTab);
    }

}
