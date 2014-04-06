package darkevilmac.utilities.fluid;

import net.minecraft.block.Block;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.lib.Strings;

public class ModFluids {

    public static Fluid fluidEnergy;
    public static Fluid fluidEmptyFilter;
    public static Fluid fluidAnyFilter;

    public static Fluid fluidSteam;

    public static Block blockFluidEnergy;
    public static Block blockFluidEmptyFilter;
    public static Block blockFluidAnyFilter;

    public static Block blockFluidSteam;

    public static void init() {
        ModFluids.fluidEnergy = new FluidEnergy();
        ModFluids.fluidEmptyFilter = new FluidEmptyFilter();
        ModFluids.fluidAnyFilter = new FluidAnyFilter();

        ModFluids.blockFluidEnergy = new FluidEnergyBlock();
        ModFluids.blockFluidEmptyFilter = new FluidEmptyFilterBlock();
        ModFluids.blockFluidAnyFilter = new FluidAnyFilterBlock();

        GameRegistry.registerBlock(ModFluids.blockFluidEnergy, Strings.FLUID_ENERGY_BLOCKNAME);
        GameRegistry.registerBlock(ModFluids.blockFluidEmptyFilter, Strings.FLUID_EMPTY_FILTER_BLOCKNAME);
        GameRegistry.registerBlock(ModFluids.blockFluidAnyFilter, Strings.FLUID_ANY_FILTER_BLOCKNAME);

        if (!Loader.isModLoaded("Railcraft")) {
            ModFluids.fluidSteam = new FluidSteam();

            ModFluids.blockFluidSteam = new FluidSteamBlock();

            GameRegistry.registerBlock(ModFluids.blockFluidSteam, Strings.FLUID_STEAM_BLOCKNAME);
        }
    }
}
