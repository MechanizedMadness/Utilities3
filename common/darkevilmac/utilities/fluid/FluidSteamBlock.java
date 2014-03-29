package darkevilmac.utilities.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;

public class FluidSteamBlock extends BlockFluidClassic {

    public static IIcon flowingIcon;
    public static IIcon stillIcon;

    public FluidSteamBlock() {
        super(ModFluids.fluidSteam, Material.water);
        setBlockName(Strings.FLUID_STEAM_BLOCKNAME);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iiconRegister) {
        super.registerBlockIcons(iiconRegister);

        flowingIcon = iiconRegister.registerIcon(Reference.MOD_ID + ":" + "steam_flow");
        stillIcon = iiconRegister.registerIcon(Reference.MOD_ID + ":" + "steam_still");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return ((Block) Block.blockRegistry.getObject("flowing_water")).getIcon(side, meta);
    }

    @Override
    public int colorMultiplier(IBlockAccess iblockaccess, int x, int y, int z) {
        return 0xF0F0F0;
    }

}
