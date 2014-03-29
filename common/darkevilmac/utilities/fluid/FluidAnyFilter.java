package darkevilmac.utilities.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import darkevilmac.utilities.lib.Strings;

public class FluidAnyFilter extends Fluid {

    public FluidAnyFilter() {
        super(Strings.FLUID_ANY_FILTER_INGAMENAME);
        setIcons(FluidAnyFilterBlock.stillIcon);
        setLuminosity(1);
        setDensity(1);
        setViscosity(25);
        setUnlocalizedName(Strings.FLUID_ANY_FILTER_BLOCKNAME);
        FluidRegistry.registerFluid(this);
    }
}