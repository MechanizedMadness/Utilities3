package darkevilmac.utilities.addons.block.ic2;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.addons.tile.ic2.TileEntityEnergyLinkIC2;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;

public class BlockEnergyLinkIC2 extends BlockEnergyLinkBase {

    protected BlockEnergyLinkIC2() {
        super();
        setBlockName(Strings.IC2_LINK_BLOCKNAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityEnergyLinkIC2();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockTexture = iconRegister.registerIcon(Reference.MOD_TEXTURE_ID + Strings.IC2_LINK_BLOCKNAME);
    }

}
