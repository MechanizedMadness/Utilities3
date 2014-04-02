package darkevilmac.utilities.fluid;

import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.lib.Strings;

public class ModFluids {

    public static Fluid fluidEnergy;
    public static Fluid fluidSteam;
    public static Fluid fluidEmptyFilter;
    public static Fluid fluidAnyFilter;
    public static BlockFluidClassic fluidEnergyBlock;
    public static BlockFluidClassic fluidSteamBlock;
    public static BlockFluidClassic fluidEmptyFilterBlock;
    public static BlockFluidClassic fluidAnyFilterBlock;
    
    public static void init() {
        ModFluids.fluidEnergy = new FluidEnergy();
        ModFluids.fluidEmptyFilter = new FluidEmptyFilter();
        ModFluids.fluidAnyFilter = new FluidAnyFilter();

        ModFluids.fluidEnergyBlock = new FluidEnergyBlock();
        ModFluids.fluidEmptyFilterBlock = new FluidEmptyFilterBlock();
        ModFluids.fluidAnyFilterBlock = new FluidAnyFilterBlock();

        GameRegistry.registerBlock(ModFluids.fluidEnergyBlock, Strings.FLUID_ENERGY_BLOCKNAME);
        GameRegistry.registerBlock(ModFluids.fluidEmptyFilterBlock, Strings.FLUID_EMPTY_FILTER_BLOCKNAME);
        GameRegistry.registerBlock(ModFluids.fluidAnyFilterBlock, Strings.FLUID_ANY_FILTER_BLOCKNAME);
        
        if (!Loader.isModLoaded("Railcraft")) {
            ModFluids.fluidSteam = new FluidSteam();

            ModFluids.fluidSteamBlock = new FluidSteamBlock();

            GameRegistry.registerBlock(ModFluids.fluidSteamBlock, Strings.FLUID_STEAM_BLOCKNAME);
        }
    }
}
