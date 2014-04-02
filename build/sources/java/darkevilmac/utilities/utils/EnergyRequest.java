package darkevilmac.utilities.utils;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class EnergyRequest {

    public static IFluidHandler fluidHandler;
    public static FluidStack request;

    public static void setHandler(IFluidHandler handler) {
        fluidHandler = handler;
    }

    public static IFluidHandler getHandler() {
        return fluidHandler;
    }

}
