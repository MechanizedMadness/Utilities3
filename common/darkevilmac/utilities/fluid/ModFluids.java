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
        fluidEnergy = new FluidEnergy();

        fluidEnergyBlock = new FluidEnergyBlock(BlockIds.FLUID_ENERGY_ID);

        GameRegistry.registerBlock(fluidEnergyBlock, Strings.FLUID_ENERGY_UNLOCALIZEDNAME);
        
        
        if(!Loader.isModLoaded("Railcraft")){
            fluidSteam = new FluidSteam();
            
            fluidSteamBlock = new FluidSteamBlock(BlockIds.FLUID_STEAM_ID);
            
            GameRegistry.registerBlock(fluidSteamBlock, Strings.FLUID_STEAM_UNLOCALIZEDNAME);
        }
    }
}
