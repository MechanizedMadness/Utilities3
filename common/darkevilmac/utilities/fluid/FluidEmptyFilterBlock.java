package darkevilmac.utilities.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;

public class FluidEmptyFilterBlock extends BlockFluidClassic {

    @SideOnly(Side.CLIENT)
    public static IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon flowingIcon;

    public FluidEmptyFilterBlock() {
        super(ModFluids.fluidEmptyFilter, Material.water);
        setBlockName(Strings.FLUID_EMPTY_FILTER_BLOCKNAME);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return (side == 0 || side == 1) ? stillIcon : flowingIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        stillIcon = register.registerIcon(Reference.MOD_ID + ":" + Strings.FLUID_EMPTY_FILTER_BLOCKNAME);
        flowingIcon = register.registerIcon(Reference.MOD_ID + ":" + Strings.FLUID_EMPTY_FILTER_BLOCKNAME);
    }

    @Override
    public int colorMultiplier(IBlockAccess iblockaccess, int x, int y, int z) {
        return 0xFF0000;
    }
}
