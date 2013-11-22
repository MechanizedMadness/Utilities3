package darkevilmac.utilities.fluid;

import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import darkevilmac.utilities.lib.BlockIds;
import darkevilmac.utilities.lib.Strings;

public class ModFluids {

    public static Fluid fluidEnergy;
    public static BlockFluidClassic fluidEnergyBlock;

    public static void init() {
        fluidEnergy = new FluidEnergy();

        fluidEnergyBlock = new FluidEnergyBlock(BlockIds.FLUID_ENERGY_ID);

        GameRegistry.registerBlock(fluidEnergyBlock, Strings.FLUID_ENERGY_UNLOCALIZEDNAME);

        LanguageRegistry.addName(fluidEnergyBlock, Strings.FLUID_ENERGY_INGAMENAME);
    }

}
