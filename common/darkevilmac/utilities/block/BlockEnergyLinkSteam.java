package darkevilmac.utilities.block;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityEnergyLinkSteam;

public class BlockEnergyLinkSteam extends BlockEnergyLinkBase {

    protected BlockEnergyLinkSteam(int id) {
        super(id, Strings.STEAM_LINK_UNLOCALIZEDNAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityEnergyLinkSteam();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
        blockIcon = register.registerIcon("utilities:" + Strings.STEAM_LINK_UNLOCALIZEDNAME);
    }

}
