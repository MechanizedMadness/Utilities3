package darkevilmac.utilities.addons.block.railcraft;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.addons.tile.railcraft.TileEntityEnergyLinkSteam;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Strings;

public class BlockEnergyLinkSteam extends BlockEnergyLinkBase {

    protected BlockEnergyLinkSteam() {
        super();
        setBlockName(Strings.STEAM_LINK_BLOCKNAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityEnergyLinkSteam();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockTexture = iconRegister.registerIcon("utilities:" + Strings.STEAM_LINK_BLOCKNAME);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess var1BlockAccess, int var2, int var3, int var4, int var5)
    {
        return blockTexture;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int var1, int var2) {
        return blockTexture;
    }

}
