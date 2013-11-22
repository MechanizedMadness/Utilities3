package darkevilmac.utilities.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.lib.BlockIds;

public class FluidEnergyBlock extends BlockFluidClassic {
    public FluidEnergyBlock(int Id) {
        super(BlockIds.FLUID_ENERGY_ID, ModFluids.fluidEnergy, Material.water);
        ModFluids.fluidEnergy.setBlockID(BlockIds.FLUID_ENERGY_ID);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        return Block.waterMoving.getIcon(side, meta);
    }

    @Override
    public int colorMultiplier(IBlockAccess iblockaccess, int x, int y, int z) {
        return 0xFF0000;
    }
}
