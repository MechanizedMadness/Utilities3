package darkevilmac.utilities.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.lib.Strings;

public class FluidAnyFilterBlock extends BlockFluidUtilities {

    public FluidAnyFilterBlock() {
        super(ModFluids.fluidAnyFilter, Material.water);
        setBlockName(Strings.FLUID_ANY_FILTER_BLOCKNAME);
    }

    @SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;

    @Override
    public IIcon getIcon(int side, int meta) {
        return (side == 0 || side == 1) ? stillIcon : flowingIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        stillIcon = register.registerIcon("utilities3:" + Strings.FLUID_ANY_FILTER_BLOCKNAME + "_still");
        flowingIcon = register.registerIcon("utilities3:" + Strings.FLUID_ANY_FILTER_BLOCKNAME + "_flow");
    }
}
