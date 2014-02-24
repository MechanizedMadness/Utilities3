package darkevilmac.utilities.addons.block.bc;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.addons.tile.bc.TileEntityEnergyLinkBC;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;

public class BlockEnergyLinkBC extends BlockEnergyLinkBase {

    protected BlockEnergyLinkBC() {
        super();
        setBlockName(Strings.BC_LINK_BLOCKNAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityEnergyLinkBC();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockTexture = iconRegister.registerIcon(Reference.MOD_TEXTURE_ID + Strings.BC_LINK_BLOCKNAME);
    }

}
