package darkevilmac.utilities.proxy;

import cpw.mods.fml.common.Loader;
import darkevilmac.utilities.fluid.ModFluids;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerFluidIcons() {

        ModFluids.fluidEnergy.setIcons(ModFluids.blockFluidEnergy.getBlockTextureFromSide(1), ModFluids.blockFluidEnergy.getBlockTextureFromSide(2));
        ModFluids.fluidEmptyFilter.setIcons(ModFluids.blockFluidEmptyFilter.getBlockTextureFromSide(1), ModFluids.blockFluidEmptyFilter.getBlockTextureFromSide(2));
        ModFluids.fluidAnyFilter.setIcons(ModFluids.blockFluidAnyFilter.getBlockTextureFromSide(1), ModFluids.blockFluidAnyFilter.getBlockTextureFromSide(2));

        if (!Loader.isModLoaded("Railcraft")) {
            ModFluids.fluidSteam.setIcons(ModFluids.blockFluidSteam.getBlockTextureFromSide(1), ModFluids.blockFluidSteam.getBlockTextureFromSide(2));
        }

    }

    @Override
    public void registerRenderers() {
    }

}