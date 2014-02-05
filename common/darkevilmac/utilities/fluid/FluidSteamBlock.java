package darkevilmac.utilities.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.lib.BlockIds;
import darkevilmac.utilities.lib.Strings;

public class FluidSteamBlock extends BlockFluidClassic {

    public FluidSteamBlock(int Id) {
        super(BlockIds.FLUID_STEAM_ID, ModFluids.fluidSteam, Material.water);
        setUnlocalizedName(Strings.FLUID_STEAM_UNLOCALIZEDNAME);
        ModFluids.fluidSteam.setBlockID(BlockIds.FLUID_STEAM_ID);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        return Block.waterMoving.getIcon(side, meta);
    }

    @Override
    public int colorMultiplier(IBlockAccess iblockaccess, int x, int y, int z) {
        return 0xF0F0F0;
    }

}
