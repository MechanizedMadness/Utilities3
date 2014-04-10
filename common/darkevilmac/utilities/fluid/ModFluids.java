package darkevilmac.utilities.fluid;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
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

    public static ItemBucket bucketFluidEnergy;

    public static ItemBucket bucketSteam;

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

        bucketFluidEnergy = new ItemBucketFluidEnergy();

        GameRegistry.registerItem(bucketFluidEnergy, "item." + Strings.BUCKET_FLUID_ENERGY_UNLOCALIZEDNAME);

        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(fluidEnergy.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketFluidEnergy),
                new ItemStack(Items.bucket));

        if (!Loader.isModLoaded("Railcraft")) {
            ModFluids.fluidSteam = new FluidSteam();

            ModFluids.blockFluidSteam = new FluidSteamBlock();

            GameRegistry.registerBlock(ModFluids.blockFluidSteam, Strings.FLUID_STEAM_BLOCKNAME);

            bucketSteam = new ItemBucketSteam();

            GameRegistry.registerItem(bucketSteam, "item." + Strings.BUCKET_STEAM_UNLOCALIZEDNAME);

            FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(fluidSteam.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketSteam),
                    new ItemStack(Items.bucket));
        }
    }
}
