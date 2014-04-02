package darkevilmac.utilities.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import darkevilmac.utilities.lib.Strings;

public class FluidEmptyFilter extends Fluid {

    public FluidEmptyFilter() {
        super(Strings.FLUID_EMPTY_FILTER_INGAMENAME);
        setIcons(FluidEmptyFilterBlock.stillIcon);
        setLuminosity(1);
        setDensity(1);
        setViscosity(25);
        setUnlocalizedName(Strings.FLUID_EMPTY_FILTER_BLOCKNAME);
        FluidRegistry.registerFluid(this);
    }
}