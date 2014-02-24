package darkevilmac.utilities.fluid;

import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.lib.BlockIds;
import darkevilmac.utilities.lib.Strings;

public class ModFluids {

    public static Fluid fluidEnergy;
    public static Fluid fluidSteam;
    public static BlockFluidClassic fluidEnergyBlock;
    public static BlockFluidClassic fluidSteamBlock;

    public static void init() {
        ModFluids.fluidEnergy = new FluidEnergy();

        ModFluids.fluidEnergyBlock = new FluidEnergyBlock();

        GameRegistry.registerBlock(ModFluids.fluidEnergyBlock, Strings.FLUID_ENERGY_BLOCKNAME);

        if (!Loader.isModLoaded("Railcraft")) {
            ModFluids.fluidSteam = new FluidSteam();

            ModFluids.fluidSteamBlock = new FluidSteamBlock();

            GameRegistry.registerBlock(ModFluids.fluidSteamBlock, Strings.FLUID_STEAM_BLOCKNAME);
        }
    }
}
