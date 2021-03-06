package darkevilmac.utilities.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import darkevilmac.utilities.lib.Strings;

public class FluidSteam extends Fluid {

    public FluidSteam() {
        super(Strings.FLUID_STEAM_INGAMENAME);
        setDensity(1);
        setViscosity(4);
        setUnlocalizedName(Strings.FLUID_STEAM_BLOCKNAME);
        FluidRegistry.registerFluid(this);
    }

}
