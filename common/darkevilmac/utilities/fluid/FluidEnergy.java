package darkevilmac.utilities.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;//fuel
import darkevilmac.utilities.lib.Strings;

public class FluidEnergy extends Fluid {

    public FluidEnergy() {
        super(Strings.FLUID_ENERGY_INGAMENAME);
        setIcons(FluidEnergyBlock.fluidIcon);
        setLuminosity(1);
        setDensity(1);
        setViscosity(25);
        setUnlocalizedName(Strings.FLUID_ENERGY_BLOCKNAME);
        FluidRegistry.registerFluid(this);
    }
}