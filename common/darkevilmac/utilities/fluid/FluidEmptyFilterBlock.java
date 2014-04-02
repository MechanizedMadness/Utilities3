package darkevilmac.utilities.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.lib.Strings;

public class FluidEmptyFilterBlock extends BlockFluidUtilities {

    public FluidEmptyFilterBlock() {
        super(ModFluids.fluidEmptyFilter, Material.water);
        setBlockName(Strings.FLUID_EMPTY_FILTER_BLOCKNAME);
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
        stillIcon = register.registerIcon("utilities3:" + Strings.FLUID_EMPTY_FILTER_BLOCKNAME + "_still");
        flowingIcon = register.registerIcon("utilities3:" + Strings.FLUID_EMPTY_FILTER_BLOCKNAME + "_flow");
    }
}
