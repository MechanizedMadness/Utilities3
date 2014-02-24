package darkevilmac.utilities.addons.block.railcraft;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.addons.tile.railcraft.TileEntityEnergyLinkSteam;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Reference;
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
        blockTexture = iconRegister.registerIcon(Reference.MOD_TEXTURE_ID + Strings.STEAM_LINK_BLOCKNAME);
    }

}
