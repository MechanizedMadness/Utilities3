package darkevilmac.utilities.fluid;

import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;//fuel
import darkevilmac.utilities.lib.Strings;

public class FluidEnergy extends Fluid {
    public static Icon fluidIcon;

    public FluidEnergy() {
        super(Strings.FLUID_ENERGY_INGAMENAME);

        setLuminosity(1);
        setDensity(1); // How tick the fluid is, affects movement inside the liquid.
        setViscosity(25); // How fast the fluid flows.
        this.setUnlocalizedName(Strings.FLUID_ENERGY_UNLOCALIZEDNAME);
        FluidRegistry.registerFluid(this); // Registering inside it self, keeps things neat :)
    }
}